package advent.issue_8

import java.io.File

class Instruction(val id: Int, val name: String, val value: Int)


fun loadInstructions(lines: List<String>): MutableList<Instruction> {
    val instructions = mutableListOf<Instruction>()
    for (i in lines.indices) {
        val split = lines[i].split(" ")
        instructions.add(
            Instruction(i, split[0], split[1].toInt())
        )
    }
    return instructions
}

fun main() {
    val fileName = "src/advent/issue_8/input.txt"
    val lines = File(fileName).readLines()

    val instructions = loadInstructions(lines)
    var acc = 0
    var cursor = 0
    val visitedIds = mutableSetOf<Int>()
    while (true) {
        if (visitedIds.contains(instructions[cursor].id)) {
            break
        }
        visitedIds.add(instructions[cursor].id)

        var cursorDelta = 1
        if (instructions[cursor].name == "acc") {
            acc += instructions[cursor].value
        } else if (instructions[cursor].name == "jmp") {
            cursorDelta = instructions[cursor].value
        }
        cursor += cursorDelta
    }

    println(acc)
}