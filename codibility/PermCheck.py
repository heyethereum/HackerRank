def solution(A):
    count_dict = {}
    for i in range (len(A)):
        if count_dict.get(A[i]) is None:
            count_dict[A[i]] = 1
        else:
            count_dict[A[i]] += 1
    # print (count_dict)

    for i in range (1, len(A) + 1):
        if count_dict.get(i) is None or count_dict.get(i) > 1:
            return 0

    return 1

def solution_clean_up(A):
    count_dict = {}
    n = len(A)

    for num in A:
        if num > n:
            return 0  # can't be a permutation if a number > N appears
        if num in count_dict:
            return 0  # duplicate found
        count_dict[num] = 1

    return 1 if len(count_dict) == n else 0

def solution_1_liner(A):
    return 1 if set(A) == set(range(1, len(A)+1)) else 0 