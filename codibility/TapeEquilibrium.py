# fails in efficiency because of it uses sum() inside a loop, which makes 
# it O(n²) time complexity. That's too slow for large arrays
def solution(A):
    n = len(A)
    result = []
    for i in range(n-1):
        result.append(abs(sum(A[:i+1]) - sum(A[i+1:])))
    #print (f"result list: {result}")
    return min(result) 

def solution_optimized(A):
    total = sum(A)
    min_diff = float('inf')
    left_sum = 0

    for i in range(1, len(A)):
        left_sum += A[i-1]
        right_sum = total - left_sum
        diff = abs(left_sum - right_sum)
        min_diff = min(min_diff, diff)

    return min_diff