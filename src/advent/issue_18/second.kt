package advent.issue_18

import java.io.File
import java.math.BigInteger


fun createBackNotationNew(items: List<String>, operators: Set<String>): List<String> {
    val result = mutableListOf<String>()
    val stack = mutableListOf<String>()
    for (item in items) {
        if (item == "+") {
            if (stack.isNotEmpty() && stack.last() != "*" && stack.last() != "(") {
                result.add(stack.removeLast())
            }
            stack.add(item)
        } else if (item == "*") {
            if (stack.isNotEmpty() && operators.contains(stack.last())) {
                result.add(stack.removeLast())
            }
            stack.add(item)
        } else if (item == "(") {
            stack.add(item)
        } else if (item == ")") {
            var stackElement: String
            while (true) {
                stackElement = stack.removeLast()
                if (stackElement == "(") {
                    break
                }
                result.add(stackElement)
            }
        } else {
            result.add(item)
        }
    }

    while (stack.isNotEmpty()) {
        result.add(stack.removeLast())
    }
    return result
}

fun main() {
    val fileName = "src/advent/issue_18/input.txt"
    val lines = File(fileName).readLines()

    val operators = mapOf(
        "*" to { a: Long, b: Long -> a * b },
        "+" to { a: Long, b: Long -> a + b }
    )

    var sum = BigInteger.ZERO

    for (line in lines) {
        val properLine = addSpaces(line)
        val split = properLine.split(" ")
        val backNotation = createBackNotationNew(split, operators.keys)
        val lineResult = calculateBackNotation(backNotation, operators)
        sum = sum.plus(BigInteger.valueOf(lineResult))
    }
    println(sum)
}