def solution(X, A):
    path = {}
    for i in range(len(A)):
        if path.get(A[i]) is None:
            path[A[i]] = i
    #print (path)
    latest_time = -1
    for i in range(1, X+1):
        if i not in path:
            return -1
        latest_time = max(latest_time, path[i])
    #print("found none!")
    return latest_time


def solution_optimized(X, A):
    positions = set()
    for i in range(len(A)):
        if 1 <= A[i] <= X:
            positions.add(A[i])
            if len(positions) == X:
                return i
    return -1
