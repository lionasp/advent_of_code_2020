package advent.issue_11

import java.io.File


fun calculateOccupiedCount(field: List<String>, i: Int, j: Int): Int {
    var occupiedAround = 0
    val kFrom = if (i - 1 > 0) i - 1 else 0
    val kTo = if (i + 1 > field.lastIndex) field[i].lastIndex else i + 1
    val zFrom = if (j - 1 > 0) j - 1 else 0
    val zTo = if (j + 1 > field[i].lastIndex) field.lastIndex else j + 1
    for (k in kFrom..kTo) {
        for (z in zFrom..zTo) {
            if (k == i && z == j) {
                continue
            }

            if (field[k][z] == '#') {
                occupiedAround++
            }
        }
    }
    return occupiedAround
}

fun getTotalOccupied(field: List<String>): Int {
    var counter = 0
    for (i in field) {
        for (j in i) {
            if (j == '#') {
                counter++
            }
        }
    }
    return counter
}

fun replaceStr(s: String, position: Int, newChar: Char): String {
    return s.substring(0, position) + newChar + s.substring(position + 1)
}

fun iteration(currentState: MutableList<String>,
              allowOccNear: Int,
              calc: (List<String>, i: Int, j: Int) -> Int): MutableList<String> {
    val newState = currentState.toMutableList()
    for (i in currentState.indices) {
        for (j in currentState[i].indices) {
            if (currentState[i][j] != '.') {
                val occupiedAround = calc(currentState, i, j)
                if (currentState[i][j] == 'L' && occupiedAround == 0) {
                    newState[i] = replaceStr(newState[i], j, '#')
                } else if (currentState[i][j] == '#' && occupiedAround >= allowOccNear) {
                    newState[i] = replaceStr(newState[i], j, 'L')
                }
            }
        }
    }
    return newState
}


fun printField(field: List<String>) {
    for (s in field) {
        println(s)
    }
    println("------------")
}

fun main() {
    val fileName = "src/advent/issue_11/input.txt"
    var state = File(fileName).readLines().toMutableList()
    var lastOccupied = 0

    while (true) {

        state = iteration(state, 4, ::calculateOccupiedCount)
        //  printField(state)
        val total = getTotalOccupied(state)

        if (total == lastOccupied) {
            println(total)
            break
        }

        lastOccupied = total
    }
}