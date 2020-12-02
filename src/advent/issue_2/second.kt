package advent.issue_2

import java.io.File

fun main() {
    val fileName = "src/advent/issue_2/input.txt"
    val lines = File(fileName).readLines()
    var validLinesCount = 0
    for (line in lines) {
        val parts = line.split(" ")

        val fromTo = parts[0].split("-")
        val firstIndex = fromTo.first().toInt() - 1
        val secondIndex = fromTo.last().toInt() - 1

        val neededChar = parts[1].first()

        val password = parts[2]
        val first = password[firstIndex]
        val second = password[secondIndex]
        if (first != second && (first == neededChar || second == neededChar)) {
            validLinesCount++
        }

    }
    println(validLinesCount)
}