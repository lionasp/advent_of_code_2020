package advent.issue_9

import java.io.File


fun main() {
    val fileName = "src/advent/issue_9/input.txt"
    val numbers = File(fileName).readLines().map { it.toLong() }

    val needed = 127L
    var cursorFrom = 0
    while (true) {
        var counter = 0L
        for (i in cursorFrom..numbers.lastIndex) {
            counter += numbers[i]
            if (counter == needed && numbers[i] != needed) {
                val slice = numbers.slice(cursorFrom..i)
                println(slice.minOrNull())
                println(slice.maxOrNull())
                return
            }
            if (counter > needed) {
                break
            }
        }
        cursorFrom++
    }
}