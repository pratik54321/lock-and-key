val lock: List<List<Int>> = listOf(
        listOf(2, 9, 1),
        listOf(2, 4, 5),
        listOf(4, 6, 3),
        listOf(5, 7, 8),
        listOf(5, 6, 9)
)

val correct_but_in_wrong_pos_count = {
    l1: List<Int>, l2: List<Int> -> l1.filterIndexed {
        p1, v1 ->  l2.filterIndexed {
            p2, v2 ->  ((p1 != p2) and (v1 == v2))
        }.count() > 0
    }.count()
}

fun crack(key: List<Int>): Boolean {
    when {
        // Step 1
        key.count { it in lock[0] } != 1 -> return false
        key.zip(lock[0]).count { (a, b) -> a == b } != 1 -> return false

        // Step 2
        key.count { it in lock[1] } != 1 -> return false
        correct_but_in_wrong_pos_count(key, lock[1]) != 1 -> return false

        // Step 3
        key.count { it in lock[2] } != 2 -> return false
        correct_but_in_wrong_pos_count(key, lock[2]) != 2 -> return false

        // Step 4
        key.count { it in lock[3] } != 0 -> return false

        // Step 5
        key.count { it in lock[4] } != 1 -> return false
        correct_but_in_wrong_pos_count(key, lock[4]) != 1 -> return false

        else -> return true
    }
}

fun main(args: Array<String>) {
    (0..999).map { it.toString().padStart(3, '0') }.forEach {
        s -> if (crack( s.map(Character::getNumericValue) )) println(s)
    }
}