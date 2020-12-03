package advent.issue_3

import java.io.File

fun main() {
    val fileName = "src/advent/issue_3/input.txt"
    val lines = File(fileName).readLines()
    val maxX = lines.first().length
    val maxY = lines.size

    var currentX = 0
    var currentY = 0
    var treeCounter = 0
    while (currentY < maxY) {
        if (lines[currentY][currentX] == '#') {
            treeCounter++
        }
        currentX = (currentX + 3) % maxX
        currentY++
    }
    println(treeCounter)
}