package advent.issue_2

import java.io.File

fun main() {
    val fileName = "src/advent/issue_2/input.txt"
    val lines = File(fileName).readLines()
    var validLinesCount = 0
    for (line in lines) {
        val parts = line.split(" ")

        val fromTo = parts[0].split("-")
        val fromCount = fromTo.first().toInt()
        val toCount = fromTo.last().toInt()

        val neededChar = parts[1].first()

        val password = parts[2]

        val charsCount = password.count{ c -> c == neededChar }
        if (charsCount in fromCount..toCount) {
            validLinesCount++
        }

    }
    println(validLinesCount)
}