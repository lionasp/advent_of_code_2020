package advent.issue_5

import java.io.File

fun getBorderDelta(rightBorder: Int, leftBorder: Int): Int {
    return (rightBorder - leftBorder + 1) / 2
}

fun calculatePlace(line: String, maxRightBorder: Int, minLeftBorder: Int = 0, moveLeftChar: Char): Int {
    var leftBorder = minLeftBorder
    var rightBorder = maxRightBorder
    for (c in line) {
        if (c == moveLeftChar) {
            rightBorder -= getBorderDelta(rightBorder, leftBorder)
        } else {
            leftBorder += getBorderDelta(rightBorder, leftBorder)
        }
    }
    return rightBorder
}

fun main() {
    val fileName = "src/advent/issue_5/input.txt"
    val lines = File(fileName).readLines()

    var maxSeatId = 0
    for (line in lines) {
        val row = calculatePlace(line.substring(0..6), 127, moveLeftChar = 'F')
        val column = calculatePlace(line.substring(7..9), 7, moveLeftChar = 'L')
        val currentSeatId = row * 8 + column
        if (currentSeatId > maxSeatId) {
            maxSeatId = currentSeatId
        }
    }
    println(maxSeatId)

}