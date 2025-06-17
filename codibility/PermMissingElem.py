def solution_brute(A):
    counts = {}
    for number in A:
        counts[number] = True

    for i in range(1, len(A)+2):
        if i not in counts:
            return i
        
def solution_expected_sum(A):
    n = len(A)
    # n(n + 1) / 2 calculates natural number 1 .. n
    expected_sum = (n + 1) * (n + 2) // 2 # calculates sum of 1 .. (n+1) inclusive
    actual_sum = sum(A)
    return expected_sum - actual_sum