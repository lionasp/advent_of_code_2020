package advent.issue_12

import java.io.File
import kotlin.math.abs

fun main() {
    val fileName = "src/advent/issue_12/input.txt"
    val commands = File(fileName).readLines()

    val availableDirections = listOf('E', 'S', 'W', 'N')

    var eastPosition = 0
    var northPosition = 0
    var direction = 'E'
    for (command in commands) {
        val distance = command.substring(1..command.lastIndex).toInt()
        when {
            command[0] == 'F' -> {
                when (direction) {
                    'E' -> {
                        eastPosition += distance
                    }
                    'W' -> {
                        eastPosition -= distance
                    }
                    'N' -> {
                        northPosition += distance
                    }
                    else -> {
                        northPosition -= distance
                    }
                }
            }
            command[0] == 'N' -> {
                northPosition += distance
            }
            command[0] == 'E' -> {
                eastPosition += distance
            }
            command[0] == 'W' -> {
                eastPosition -= distance
            }
            command[0] == 'S' -> {
                northPosition -= distance
            }
            command[0] == 'L' -> {
                val currentDirectionIndex = availableDirections.indexOf(direction)
                var newDirection = currentDirectionIndex - distance / 90
                if (newDirection < 0) {
                    newDirection += 4
                }
                direction = availableDirections[newDirection]
            }
            else -> {
                val currentDirectionIndex = availableDirections.indexOf(direction)
                direction = availableDirections[(currentDirectionIndex + distance / 90) % 4]
            }
        }
    }
    println(eastPosition)
    println(northPosition)
    println(abs(eastPosition) + abs(northPosition))
}