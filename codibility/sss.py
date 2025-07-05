#!/usr/bin/env python3

from Crypto.Cipher import AES
from Crypto.Protocol.KDF import PBKDF2
from Crypto.Hash import SHA256
from Crypto.Random import get_random_bytes

from shamir_mnemonic import generate_mnemonics, combine_mnemonics


# === AES-256-GCM ENCRYPTION ===

def aes_encrypt(plaintext: str, passphrase: str) -> str:
    """Encrypt plaintext using AES-256-GCM with PBKDF2 key derivation."""
    try:
        salt = get_random_bytes(16)
        key = PBKDF2(passphrase, salt, dkLen=32, count=200_000, hmac_hash_module=SHA256)

        nonce = get_random_bytes(12)
        cipher = AES.new(key, AES.MODE_GCM, nonce=nonce)
        ciphertext, tag = cipher.encrypt_and_digest(plaintext.encode('utf-8'))

        blob = salt + nonce + tag + ciphertext
        return blob.hex()
    except Exception as e:
        raise ValueError(f"Encryption failed: {str(e)}") from e


def aes_decrypt(encrypted_hex: str, passphrase: str) -> str:
    """Decrypt ciphertext using AES-256-GCM with PBKDF2 key derivation."""
    try:
        blob = bytes.fromhex(encrypted_hex)
        
        if len(blob) < 44:  # salt(16) + nonce(12) + tag(16) = 44 minimum
            raise ValueError("Invalid encrypted data format")
            
        salt = blob[:16]
        nonce = blob[16:28]
        tag = blob[28:44]
        ciphertext = blob[44:]

        key = PBKDF2(passphrase, salt, dkLen=32, count=200_000, hmac_hash_module=SHA256)
        cipher = AES.new(key, AES.MODE_GCM, nonce=nonce)
        plaintext = cipher.decrypt_and_verify(ciphertext, tag)
        return plaintext.decode('utf-8')
    except ValueError as e:
        if "MAC check failed" in str(e):
            raise ValueError("Decryption failed: Wrong passphrase or corrupted data") from e
        raise ValueError(f"Decryption failed: {str(e)}") from e
    except Exception as e:
        raise ValueError(f"Decryption failed: {str(e)}") from e


# === SLIP-39 SHAMIR SECRET SHARING ===

def pad_secret_even_length(secret: bytes) -> bytes:
    """Pads the master secret to an even length for SLIP-39."""
    if len(secret) % 2 != 0:
        return secret + b'\x00'
    return secret


def split_passphrase_slip39(passphrase: str, threshold: int, total_shares: int) -> list:
    """Split passphrase into SLIP-39 mnemonic shares."""
    try:
        if threshold > total_shares:
            raise ValueError("Threshold cannot be greater than total shares")
        if threshold < 1 or total_shares < 1:
            raise ValueError("Threshold and total shares must be positive")
        if total_shares > 16:
            raise ValueError("Maximum 16 shares supported")
            
        # Encode and pad to even bytes
        master_secret = pad_secret_even_length(passphrase.encode('utf-8'))

        mnemonics = generate_mnemonics(
            group_threshold=1,
            groups=[[threshold, total_shares]],
            master_secret=master_secret
        )
        return mnemonics[0]
    except Exception as e:
        raise ValueError(f"Failed to split passphrase: {str(e)}") from e


def recover_passphrase_slip39(shares: list) -> str:
    """Recover passphrase from SLIP-39 mnemonic shares."""
    try:
        # The shares are already strings, so we can use them directly
        recovered = combine_mnemonics(shares)
        # Remove any trailing null bytes
        return recovered.rstrip(b'\x00').decode('utf-8')
    except Exception as e:
        raise ValueError(f"Failed to recover passphrase: {str(e)}") from e


def fix_spaced_words(text: str) -> str:
    """Fix spaced-out words like 'o a s i s' -> 'oasis'."""
    # Split by whitespace to get all tokens
    tokens = text.split()
    
    result = []
    i = 0
    
    while i < len(tokens):
        # If this is a single character, look ahead to see if we have more single chars
        if len(tokens[i]) == 1 and tokens[i].isalpha():
            # Collect all consecutive single characters
            chars = [tokens[i]]
            i += 1
            
            # Keep collecting single characters
            while i < len(tokens) and len(tokens[i]) == 1 and tokens[i].isalpha():
                chars.append(tokens[i])
                i += 1
            
            # Join them into a word
            result.append(''.join(chars))
        else:
            # Regular word
            result.append(tokens[i])
            i += 1
    
    return ' '.join(result)


def validate_passphrase_strength(passphrase: str) -> bool:
    """Basic passphrase strength validation."""
    if len(passphrase) < 8:
        print("⚠️  Warning: Passphrase is shorter than 8 characters")
        return False
    return True


def get_operation_choice() -> str:
    """Get user's choice of operation."""
    while True:
        print("Choose operation:")
        print("1. Encrypt data and generate shares")
        print("2. Decrypt data using shares")
        choice = input("Enter choice (1 or 2): ").strip()
        
        if choice in ['1', '2']:
            return choice
        print("❌ Invalid choice. Please enter 1 or 2.\n")


def collect_shares_for_decryption() -> list:
    """Collect mnemonic shares from user for decryption."""
    shares = []
    print("\nEnter your mnemonic shares (one per line).")
    print("Type 'DONE' when you've entered all shares:")
    print("Note: If words are spaced out (like 't a c t i c s'), they will be automatically fixed.")
    
    share_count = 1
    while True:
        share_input = input(f"Share {share_count}: ").strip()
        
        if share_input.upper() == 'DONE':
            if len(shares) == 0:
                print("❌ No shares entered. Please enter at least one share.")
                continue
            break
        
        if not share_input:
            print("❌ Empty share. Please enter a valid mnemonic or 'DONE'.")
            continue
        
        # Fix spaced-out words (like 't a c t i c s' -> 'tactics')
        processed_share = fix_spaced_words(share_input)
        
        # Basic validation - check if it looks like a mnemonic
        words = processed_share.split()
        if len(words) < 20:  # SLIP-39 shares are typically 20+ words
            print(f"⚠️  Warning: Share has {len(words)} words (expected 20+)")
            print(f"Processed share: {processed_share}")
            confirm = input("Continue anyway? (y/n): ").strip().lower()
            if confirm != 'y':
                continue
        
        shares.append(processed_share)
        share_count += 1
        print(f"✅ Share {share_count-1} added successfully")
        
    return shares


def encryption_workflow():
    """Handle encryption workflow."""
    print("\n=== ENCRYPTION MODE ===")
    
    # Get passphrase
    passphrase = input("Enter your master passphrase: ").strip()
    if not passphrase:
        print("❌ Passphrase cannot be empty")
        return
        
    validate_passphrase_strength(passphrase)
    
    # Get secret text
    plaintext = input("Enter your secret text to encrypt: ").strip()
    if not plaintext:
        print("❌ Secret text cannot be empty")
        return

    # Configure Shamir parameters
    while True:
        try:
            threshold = int(input("Enter threshold (minimum shares needed to recover): ").strip())
            total_shares = int(input("Enter total number of shares to generate: ").strip())
            if threshold <= total_shares and threshold > 0 and total_shares > 0:
                break
            else:
                print("❌ Invalid parameters. Threshold must be > 0 and <= total shares")
        except ValueError:
            print("❌ Please enter valid numbers")

    print(f"\n📊 Configuration: {threshold}-of-{total_shares} threshold scheme")
    
    # Encrypt the secret
    print("\n🔐 Encrypting secret...")
    encrypted_hex = aes_encrypt(plaintext, passphrase)
    print("\n✅ Encrypted secret (save this!):")
    print(encrypted_hex)

    # Generate shares
    print(f"\n🔑 Generating SLIP-39 shares...")
    mnemonics = split_passphrase_slip39(passphrase, threshold, total_shares)

    print(f"\n✅ Your SLIP-39 Shares (any {threshold} of {total_shares} needed):")
    print("=" * 60)
    for i, mnemonic_string in enumerate(mnemonics, 1):
        print(f"\nShare {i}:")
        print(mnemonic_string)
    
    print("\n" + "=" * 60)
    print("💡 Security reminders:")
    print("• Store shares in different secure locations")
    print("• Keep encrypted data separate from shares") 
    print("• You need the encrypted hex AND the shares to recover your data")


def decryption_workflow():
    """Handle decryption workflow."""
    print("\n=== DECRYPTION MODE ===")
    
    # Get encrypted data
    encrypted_hex = input("Enter the encrypted data (hex): ").strip()
    if not encrypted_hex:
        print("❌ Encrypted data cannot be empty")
        return
    
    # Validate hex format
    try:
        bytes.fromhex(encrypted_hex)
    except ValueError:
        print("❌ Invalid hex format")
        return
    
    # Collect shares
    shares = collect_shares_for_decryption()
    print(f"\n🔑 Received {len(shares)} share(s)")
    
    # Recover passphrase
    print("🔄 Recovering passphrase from shares...")
    try:
        recovered_passphrase = recover_passphrase_slip39(shares)
        print("✅ Passphrase recovered successfully!")
    except Exception as e:
        print(f"❌ Failed to recover passphrase: {str(e)}")
        print("💡 Make sure you have enough valid shares and they're entered correctly")
        return
    
    # Decrypt the data
    print("🔓 Decrypting data...")
    try:
        decrypted_text = aes_decrypt(encrypted_hex, recovered_passphrase)
        print("✅ Decryption successful!")
        print(f"\n📝 Your secret: {decrypted_text}")
    except Exception as e:
        print(f"❌ Decryption failed: {str(e)}")
        print("💡 This could mean:")
        print("  - Wrong or insufficient shares")
        print("  - Corrupted encrypted data")
        print("  - Shares don't match this encrypted data")


def main():
    print("=== AES-GCM + SLIP-39 Shamir Secret Sharing Tool ===")
    print("Secure your data with military-grade encryption and distributed key recovery\n")

    try:
        choice = get_operation_choice()
        
        if choice == '1':
            encryption_workflow()
        else:
            decryption_workflow()
            
        print(f"\n{'='*50}")
        print("🎉 Operation completed!")
        
    except KeyboardInterrupt:
        print("\n\n👋 Operation cancelled by user")
    except Exception as e:
        print(f"\n❌ Unexpected error: {str(e)}")


if __name__ == "__main__":
    main()