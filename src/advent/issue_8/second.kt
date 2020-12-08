package advent.issue_8

import java.io.File


class ExecutionResult(val error: Boolean, val acc: Int = 0)

fun oneTry(instructions: List<Instruction>): ExecutionResult {
    var acc = 0
    var cursor = 0
    val visitedIds = mutableSetOf<Int>()
    while (true) {
        if (visitedIds.contains(instructions[cursor].id)) {
            return ExecutionResult(error = true)
        }
        visitedIds.add(instructions[cursor].id)

        var cursorDelta = 1
        if (instructions[cursor].name == "acc") {
            acc += instructions[cursor].value
        } else if (instructions[cursor].name == "jmp") {
            cursorDelta = instructions[cursor].value
        }
        cursor += cursorDelta
        if (cursor == instructions.size) {
            break
        }
    }
    return ExecutionResult(error = false, acc = acc)
}

fun swapInstruction(instructions: MutableList<Instruction>, index: Int) {
    var swapTo = "nop"
    if (instructions[index].name == "nop") {
        swapTo = "jmp"
    }
    instructions[index] = Instruction(
        name = swapTo,
        id = instructions[index].id,
        value = instructions[index].value
    )
}

fun main() {
    val fileName = "src/advent/issue_8/input.txt"
    val lines = File(fileName).readLines()

    val instructions: MutableList<Instruction> = loadInstructions(lines)
    var acc = 0
    for (i in instructions.indices) {
        if (instructions[i].name == "acc") {
            continue
        }

        swapInstruction(instructions, i)
        val result = oneTry(instructions)
        if (!result.error) {
            acc = result.acc
            break
        }
        swapInstruction(instructions, i)
    }

    println(acc)
}