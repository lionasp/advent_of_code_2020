package advent.issue_16

import java.io.File

fun main() {
    val fileName = "src/advent/issue_16/input.txt"
    val lines = File(fileName).readLines()

    val ranges = mutableListOf<Array<IntRange>>()
    var index = 0
    for (line in lines) {
        if (line == "") {
            break
        }
        index++
        val split = line.split(": ")[1].split(" ")
        val firstRangeRaw = split[0].split("-")
        val secondRangeRaw = split[2].split("-")
        val range = arrayOf<IntRange>(
            firstRangeRaw[0].toInt()..firstRangeRaw[1].toInt(),
            secondRangeRaw[0].toInt()..secondRangeRaw[1].toInt()
        )
        ranges.add(range)
    }

    val outranged = mutableListOf<Int>()
    for (i in index+5..lines.lastIndex) {
        val numbers = lines[i].split(',').map { it.toInt() }
        for (number in numbers) {
            var present = false
            for (range in ranges) {
                present = number in range[0] || number in range[1]
                if (present) {
                    break
                }
            }
            if (!present) {
                outranged.add(number)
            }
        }
    }

    for (o in outranged) {
        println(o)
    }
    println("----")
    println(outranged.sum())

}