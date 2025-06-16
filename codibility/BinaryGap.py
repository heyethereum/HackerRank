def solution_brute_force(N):
    max_gap = 0
    current_gap = 0
    binary_str = bin(N)[2:]  # Remove '0b' prefix
    
    for digit in binary_str:
        if digit == '0':
            current_gap += 1
        else:  # digit == '1'
            max_gap = max(max_gap, current_gap)
            current_gap = 0
    
    return max_gap

# Alternative bit manipulation approach (more efficient for very large numbers)
def solution_bitwise(N):
    """
    Alternative solution using bit manipulation instead of string conversion.
    Slightly more efficient as it avoids string operations.
    """
    max_gap = 0
    current_gap = 0
    found_first_one = False
    
    while N > 0:
        if N & 1:  # Current bit is 1
            if found_first_one:
                max_gap = max(max_gap, current_gap)
            found_first_one = True
            current_gap = 0
        else:  # Current bit is 0
            if found_first_one:
                current_gap += 1
        N >>= 1  # Right shift to check next bit

def solution(N):
    # Convert to binary and strip trailing zeros
    binary = bin(N)[2:].strip('0')
    print(f"binary: {binary}")

    # Split on '1' and find the longest gap
    gaps = binary.split('1')
    print(f"gaps: {gaps}")
    return max((len(gap) for gap in gaps), default=0)


print(solution(1041))