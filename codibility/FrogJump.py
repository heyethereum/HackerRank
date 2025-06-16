def solution(X, Y, D):
    hops = 0
    dist = Y - X
    hops = (dist + D - 1) // D  # round up

    #print(f"hops: {hops}")
    return hops
