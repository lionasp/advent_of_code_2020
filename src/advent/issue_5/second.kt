package advent.issue_5

import java.io.File


fun main() {
    val fileName = "src/advent/issue_5/input.txt"
    val lines = File(fileName).readLines()

    var maxSeatId = Int.MIN_VALUE
    var minSeatId = Int.MAX_VALUE
    val allSeatsIds = mutableSetOf<Int>()
    for (line in lines) {
        val row = calculatePlace(line.substring(0..6), 127, moveLeftChar = 'F')
        val column = calculatePlace(line.substring(7..9), 7, moveLeftChar = 'L')
        val currentSeatId = row * 8 + column
        allSeatsIds.add(currentSeatId)
        if (currentSeatId > maxSeatId) {
            maxSeatId = currentSeatId
        }
        if (currentSeatId < minSeatId) {
            minSeatId = currentSeatId
        }
    }

    for (i in minSeatId..maxSeatId) {
        if (!allSeatsIds.contains(i)) {
            println(i)
        }
    }

}