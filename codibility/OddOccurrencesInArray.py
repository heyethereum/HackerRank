# (O(n) time, O(n) space)
def solution_hashmap(A):
    counts = {}

    for num in A:
        if num in counts:
            counts[num] += 1
        else:
            counts[num] = 1

    for num, count in counts.items():
        if count % 2 == 1:
            return num

# beautiful solutions using xor (O(n) time, O(1) space) 
def solution_xor(A):
    result = 0
    for number in A:
        result ^= number
    return result