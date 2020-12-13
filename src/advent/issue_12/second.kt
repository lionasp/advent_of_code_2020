package advent.issue_12

import java.io.File
import kotlin.math.abs

fun main() {
    val fileName = "src/advent/issue_12/input.txt"
    val commands = File(fileName).readLines()

    var eastShipPosition = 0
    var northShipPosition = 0
    var eastWPPosition = 10
    var northWPPosition = 1

    var counter = 0

    for (command in commands) {
        counter++
        val distance = command.substring(1..command.lastIndex).toInt()
        when {
            command[0] == 'F' -> {
                eastShipPosition += eastWPPosition * distance
                northShipPosition += northWPPosition * distance
            }
            command[0] == 'N' -> {
                northWPPosition += distance
            }
            command[0] == 'E' -> {
                eastWPPosition += distance
            }
            command[0] == 'S' -> {
                northWPPosition -= distance
            }
            command[0] == 'W' -> {
                eastWPPosition -= distance
            }
            command[0] == 'L' -> {
                repeat(distance / 90) {
                    val temp = eastWPPosition
                    if (eastWPPosition >= 0 && northWPPosition >= 0) {
                        eastWPPosition = -northWPPosition
                        northWPPosition = temp
                    } else if (eastWPPosition >= 0 && northWPPosition < 0) {
                        eastWPPosition = abs(northWPPosition)
                        northWPPosition = temp
                    } else if (eastWPPosition < 0 && northWPPosition < 0) {
                        eastWPPosition = abs(northWPPosition)
                        northWPPosition = temp
                    } else {
                        eastWPPosition = -northWPPosition
                        northWPPosition = temp
                    }
                }
            }
            else -> {
                repeat(distance / 90) {
                    val temp = eastWPPosition
                    if (eastWPPosition >= 0 && northWPPosition >= 0) {
                        eastWPPosition = northWPPosition
                        northWPPosition = -temp
                    } else if (eastWPPosition >= 0 && northWPPosition < 0) {
                        eastWPPosition = northWPPosition
                        northWPPosition = -temp
                    } else if (eastWPPosition < 0 && northWPPosition < 0) {
                        eastWPPosition = northWPPosition
                        northWPPosition = abs(temp)
                    } else {
                        eastWPPosition = northWPPosition
                        northWPPosition = abs(temp)
                    }
                }
            }
        }
        println("number $counter instruction $command ship at ($eastShipPosition, $northShipPosition) and WP ($eastWPPosition, $northWPPosition)")
    }
    println(abs(northShipPosition) + abs(eastShipPosition))
}