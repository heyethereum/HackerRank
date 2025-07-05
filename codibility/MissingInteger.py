def solution(A):
    # Implement your solution here
    n = len(A)
    present = [False] * (n + 1)
    #print (present)
    for num in A:
        if 1 <= num <= n:
            present[num] = True
    
    for i in range(1, n+1):
        if not present[i]:
            #print (present)
            return i
    
    return n + 1

print(5-+-+-+-5) 