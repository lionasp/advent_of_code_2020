package advent.issue_10

import java.io.File

fun main() {
    val fileName = "src/advent/issue_10/input.txt"
    val numbers = File(fileName).readLines().map { it.toInt() }.sorted()
    val enriched = listOf(0) + numbers + listOf(numbers.last() + 3)
    val diffs = hashMapOf<Int, Int>()
    for (i in 1..enriched.lastIndex) {
        val diff = enriched[i] - enriched[i - 1]
        diffs[diff] = diffs.getOrDefault(diff, 0) + 1
    }
    for ((key, value) in diffs) {
        println("$key: $value")
    }
}