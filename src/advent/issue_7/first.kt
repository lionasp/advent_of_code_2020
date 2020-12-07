package advent.issue_7

import java.io.File

fun parseLine(line: String): Array<String> {
    val split = line.split(" bags contain ")
    val value = mutableListOf(split[0])

    val regex = Regex("\\d+\\s([\\w\\s]+)\\sbag")
    for (innerBag in split[1].split(", ")) {
        val result = regex.find(innerBag)
        if (result != null) {
            value.add(result.groupValues.last())
        }
    }
    return value.toTypedArray()
}

var visitedVertex = mutableSetOf<String>()

fun searchInGraph(graph: Map<String, ArrayList<String>>, need: String): Int {
    var deep = 0
    if (!visitedVertex.contains(need)) {
        deep++
        visitedVertex.add(need)
    }

    if (!graph.containsKey(need)) {
        return deep
    }

    for (value in graph.getValue(need)) {
        deep += searchInGraph(graph, value)
    }
    return deep
}

fun main() {
    val fileName = "src/advent/issue_7/input.txt"
    val lines = File(fileName).readLines()

    val graphDirect = HashMap<String, Array<String>>()
    for (line in lines) {
        val parsedLine = parseLine(line)
        graphDirect[parsedLine[0]] = parsedLine.sliceArray(1..parsedLine.lastIndex)
    }

    val graphReversed = HashMap<String, ArrayList<String>>()
    for ((key, values) in graphDirect) {
        for (value in values) {
            val newMoves = graphReversed[value]
            if (newMoves != null) {
                newMoves.add(key)
            } else {
                graphReversed[value] = arrayListOf(key)
            }

        }
    }

    println(searchInGraph(graphReversed, "shiny gold") - 1)
}