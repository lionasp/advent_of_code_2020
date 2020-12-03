package advent.issue_3

import java.io.File

fun process(field: List<String>, speedX: Int, speedY: Int, maxX: Int, maxY: Int): Int {
    var currentX = 0
    var currentY = 0
    var treeCounter = 0
    while (currentY < maxY) {
        if (field[currentY][currentX] == '#') {
            treeCounter++
        }
        currentX = (currentX + speedX) % maxX
        currentY += speedY
    }
    return treeCounter
}

fun main() {
    val fileName = "src/advent/issue_3/input.txt"
    val lines = File(fileName).readLines()
    val maxX = lines.first().length
    val maxY = lines.size

    var mult = 1L
    val speeds = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(3, 1),
        intArrayOf(5, 1),
        intArrayOf(7, 1),
        intArrayOf(1, 2),
    )

    for (speed in speeds) {
        mult *= process(lines, speed[0], speed[1], maxX, maxY)
    }

    println(mult)
}