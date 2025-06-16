
def solution(A, K):
    n = len(A)
    if n == 0:
        return A
    
    K = K % n
    print (f"A[-K:]: {A[-K:]}")
    print (f"A[:-K]: {A[:-K]}")
    return A[-K:] + A[:-K]

A = [1,2,3,4]
K = 1
print(f"A = {A}, K = {K} -> {solution(A, K)}")