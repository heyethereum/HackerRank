#!/usr/bin/env python3

from Crypto.Cipher import AES
from Crypto.Protocol.KDF import PBKDF2
from Crypto.Hash import SHA256
from Crypto.Random import get_random_bytes

def encrypt(plaintext: str, passphrase: str) -> str:
    # Generate salt and derive key
    salt = get_random_bytes(16)
    key = PBKDF2(passphrase, salt, dkLen=32, count=200_000, hmac_hash_module=SHA256)

    # Random nonce
    nonce = get_random_bytes(12)

    cipher = AES.new(key, AES.MODE_GCM, nonce=nonce)
    ciphertext, tag = cipher.encrypt_and_digest(plaintext.encode('utf-8'))

    # Concatenate: salt | nonce | tag | ciphertext
    encrypted_blob = salt + nonce + tag + ciphertext

    # Return as hex
    return encrypted_blob.hex()

def decrypt(encrypted_hex: str, passphrase: str) -> str:
    # Convert hex blob to bytes
    blob = bytes.fromhex(encrypted_hex)

    # Extract parts
    salt = blob[:16]
    nonce = blob[16:28]
    tag = blob[28:44]
    ciphertext = blob[44:]

    # Re-derive key
    key = PBKDF2(passphrase, salt, dkLen=32, count=200_000, hmac_hash_module=SHA256)

    cipher = AES.new(key, AES.MODE_GCM, nonce=nonce)
    plaintext = cipher.decrypt_and_verify(ciphertext, tag)

    return plaintext.decode('utf-8')

if __name__ == "__main__":
    passphrase = input("Enter your secret passphrase: ")

    plaintext = input("Enter plain text to be encrypted: ")

    # Encrypt
    encrypted_hex = encrypt(plaintext, passphrase)

    print("\n=== ENCRYPTED HEX BLOB ===")
    print(encrypted_hex)
    print(f"Length: {len(encrypted_hex)} hex chars")

    # Decryption
    # passphrase = "" # enter pass phase
    # encrypted_hex = "" # enter encrypted hex
    print("\n=== DECRYPTION ===")
    decrypted = decrypt(encrypted_hex, passphrase)
    print("Decrypted:", decrypted)
