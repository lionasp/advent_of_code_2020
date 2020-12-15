package advent.issue_15

import java.io.File
import java.lang.Exception


fun getNumPrevPosition(numbers: MutableList<Int>, currentIndex: Int, needed: Int): Int {
    for (i in currentIndex-2 downTo 0) {
        if (numbers[i] == needed) {
            return i
        }
    }
    throw Exception("unknown")
}

fun main() {
    val fileName = "src/advent/issue_15/input.txt"
    val nums = File(fileName).readLines()[0].split(",").map { it.toInt() }.toMutableList()
    val alreadyMeetNumbers = HashMap<Int, Int>()
    var index = 0
    for (n in nums) {
        alreadyMeetNumbers[n] = 1
        index++
    }

    val times = 2020 - index
    repeat(times) {
        val lastNumber = nums.last()
        if (alreadyMeetNumbers.getOrDefault(lastNumber, 0) > 1) {
            val lastIndex = getNumPrevPosition(nums, index, lastNumber)
            nums.add(index - (lastIndex + 1))
        } else {
            nums.add(0)
        }
        alreadyMeetNumbers[nums.last()] = alreadyMeetNumbers.getOrDefault(nums.last(), 0) + 1
        index++
    }

    println(nums.last())
}