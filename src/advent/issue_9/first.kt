package advent.issue_9

import java.io.File


fun isCombinationExists(preambula: List<Long>, number: Long): Boolean {
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
    val numbers = File(fileName).readLines().map { it.toLong() }

    val preambula = mutableListOf<Long>()
    val preambulaMaxSize = 5

    for (i in 0 until preambulaMaxSize) {
        preambula.add(numbers[i])
    }

    for (i in preambulaMaxSize..numbers.lastIndex) {
        if (!isCombinationExists(preambula, numbers[i])) {
            println(numbers[i])
            return
        }
        preambula.add(numbers[i])
        preambula.removeAt(0)
    }
    println("not found")

}