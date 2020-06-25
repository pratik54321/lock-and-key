lock = [
    [2, 9, 1],
    [2, 4, 5],
    [4, 6, 3],
    [5, 7, 8],
    [5, 6, 9],
] # ans - 394


def correct_but_in_wrong_pos_count(l1, l2):
    count = 0
    for p1, v1 in enumerate(l1):
        for p2, v2 in enumerate(l2):
            if p1 != p2 and v1 == v2:
                count += 1

    return count


def crack(key):
    # step 1
    if not (len([x for x in key if x in lock[0]]) == 1 and len([x for x, y in zip(key, lock[0]) if x == y]) == 1):
        return False

    # step 2
    if not (len([x for x in key if x in lock[1]]) == 1 and correct_but_in_wrong_pos_count(key, lock[1]) == 1):
        return False

    # step 3
    if not (len([x for x in key if x in lock[2]]) == 2 and correct_but_in_wrong_pos_count(key, lock[2]) == 2):
        return False

    # step 4
    if [x for x in key if x in lock[3]]:
        return False

    # step 5
    if not (len([x for x in key if x in lock[4]]) == 1 and correct_but_in_wrong_pos_count(key, lock[4]) == 1):
        return False

    return True


if __name__ == '__main__':
    ans = None
    for key in [list(map(int, x)) for x in ["{0:03}".format(i) for i in range(1000)]]:
        if crack(key):
            ans = key
            print(key)
            # break

    print(f"ANS : {ans}" if ans else "NO KEY FOUND")
