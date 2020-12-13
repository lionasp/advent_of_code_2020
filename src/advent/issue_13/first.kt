package advent.issue_13

import java.io.File

fun main() {
    val fileName = "src/advent/issue_13/input.txt"
    val lines = File(fileName).readLines()
    val earliestTimestamp = lines[0].toInt()
    val busIDs = lines[1].split(",").filter { it != "x" }.map { it.toInt() }
    val busTimestamps = IntArray(busIDs.size)

    for (i in busIDs.indices) {
        val busID = busIDs[i]
        while (busTimestamps[i] < earliestTimestamp) {
            busTimestamps[i] += busID
        }
    }

    val fastest = busTimestamps.minOrNull()
    val fastestIndex = busTimestamps.indexOf(fastest!!)
    println(busIDs[fastestIndex] * (fastest - earliestTimestamp))

}