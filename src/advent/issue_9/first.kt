package advent.issue_9

import java.io.File


fun isCombinationExists(preambula: List<Int>, number: Int): Boolean {
    for (i in preambula) {
        val sub = number - i
        if (sub != i && preambula.contains(sub)) {
            return true
        }
    }
    return false
}

fun main() {
    val fileName = "src/advent/issue_9/input.txt"
    val lines = File(fileName).readLines()

    val preambula = mutableListOf<Int>()
    val preambulaMaxSize = 5

    for (i in 0 until preambulaMaxSize) {
        preambula.add(lines[i].toInt())
    }

    for (i in preambulaMaxSize..lines.lastIndex) {
        val currentNumber = lines[i].toInt()
        if (!isCombinationExists(preambula, currentNumber)) {
            println(currentNumber)
            return
        }
        preambula.add(currentNumber)
        preambula.removeAt(0)
    }
    println("not found")

}