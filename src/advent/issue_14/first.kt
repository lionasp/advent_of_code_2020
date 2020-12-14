package advent.issue_14

import java.io.File
import java.lang.StringBuilder
import java.math.BigInteger

fun createBinaryValue(value: Int, neededSize: Int): String {
    val shortBinaryValue = Integer.toBinaryString(value)
    val missedZeros = neededSize - shortBinaryValue.length
    val finalBinaryValue = StringBuilder()
    repeat(missedZeros) {
        finalBinaryValue.append("0")
    }
    finalBinaryValue.append(shortBinaryValue)
    return finalBinaryValue.toString()
}

fun main() {
    val fileName = "src/advent/issue_14/input.txt"
    val lines = File(fileName).readLines()
    var currentMask = ""
    val memoryNumberRegex = "\\[(\\d+)".toRegex()
    val memory = HashMap<Int, Long>()

    for (line in lines) {
        val split = line.split(" ")
        when {
            split[0] == "mask" -> {
                currentMask = split[2]
            }
            else -> {
                val memoryNumber = memoryNumberRegex.find(split[0])?.groupValues?.last()?.toInt()!!
                val binaryValue = createBinaryValue(split[2].toInt(), currentMask.length)

                val currentMemory = StringBuilder()
                for (i in binaryValue.lastIndex downTo 0) {
                    currentMemory.append(if (currentMask[i] == 'X') binaryValue[i] else currentMask[i])
                }
                memory[memoryNumber] =  java.lang.Long.parseLong(currentMemory.reverse().toString(), 2)
            }
        }
    }

    var sum = BigInteger.ZERO
    for ((key, value) in memory) {
        // println("$key $value")
        sum = sum.plus(value.toBigInteger())
    }
    println(sum)
}