package advent.issue_6

import java.io.File

fun main() {
    val fileName = "src/advent/issue_6/input.txt"
    val lines = File(fileName).readLines()

    var total = 0
    val answers = mutableSetOf<Char>()

    for (lineIndex in lines.indices) {
        if (lines[lineIndex] == "") {
            total += answers.size
            answers.clear()
        } else {
            answers.addAll(lines[lineIndex].toCharArray().asSequence())
            if (lineIndex == lines.lastIndex) {
                total += answers.size
            }
        }
    }
    println(total)

}