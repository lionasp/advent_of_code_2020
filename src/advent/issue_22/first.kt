package advent.issue_22

import java.io.File

fun main() {
    val fileName = "src/advent/issue_22/input.txt"
    val lines = File(fileName).readLines()
    val playerOneDeck = mutableListOf<Int>()
    val playerTwoDeck = mutableListOf<Int>()

    var index = 0
    for (line in lines) {
        if (line == "") {
            break
        }
        if (line == "Player 1:") {
            continue
        }
        playerOneDeck.add(line.toInt())
        index++
    }

    playerTwoDeck.addAll(lines.slice(index+3..lines.lastIndex).map { it.toInt() })

    var iteration = 0
    do {
        println(iteration++)
        val p1 = playerOneDeck.first()
        val p2 = playerTwoDeck.first()
        if (p1 > p2) {
            playerOneDeck.add(p1)
            playerOneDeck.add(p2)
        } else {
            playerTwoDeck.add(p2)
            playerTwoDeck.add(p1)
        }
        playerOneDeck.removeAt(0)
        playerTwoDeck.removeAt(0)
    } while (!(playerOneDeck.size == 0 || playerTwoDeck.size == 0))

    val winDeck = if (playerOneDeck.size == 0) playerTwoDeck else playerOneDeck
    println(winDeck.joinToString())
    var result = 0
    winDeck.reverse()
    for (i in winDeck.indices) {
        result += winDeck[i] * (i + 1)
    }
    println(result)
}