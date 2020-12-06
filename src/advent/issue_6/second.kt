package advent.issue_6

import java.io.File

fun main() {
    val fileName = "src/advent/issue_6/input.txt"
    val lines = File(fileName).readLines()

    var total = 0
    var answers = setOf<Char>()
    var previousAnswers = setOf<Char>()

    for (lineIndex in lines.indices) {
        if (lines[lineIndex] == "") {
            total += answers.size
            answers = setOf<Char>()
            previousAnswers = setOf<Char>()
        } else {
            val currentUserAnswers = lines[lineIndex].toCharArray().asSequence().toSet()
            if (previousAnswers.isNotEmpty()) {
                answers = currentUserAnswers.intersect(answers)
            } else {
                answers = currentUserAnswers
            }
            previousAnswers = currentUserAnswers
            if (lineIndex == lines.lastIndex) {
                total += answers.size
            }
        }
    }
    println(total)
}