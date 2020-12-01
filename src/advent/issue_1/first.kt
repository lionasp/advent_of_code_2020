package advent.issue_1

import java.io.File

fun main() {
    val fileName = "src/advent/issue_1/input.txt"
    val numbers = File(fileName).readLines().map { it.toInt() }
    val needed = 2020
    for (i in numbers.indices) {
        for (j in i until numbers.size) {
            if (numbers[i] + numbers[j] == needed) {
                println(numbers[i] * numbers[j])
                return
            }
        }
    }
}