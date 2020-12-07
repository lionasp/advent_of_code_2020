package advent.issue_7

import java.io.File

class Bag constructor(val name: String, val count: Int)
class ParsedLine constructor(val name: String, val bags: Array<Bag>)

fun parseLineSecond(line: String): ParsedLine {
    val split = line.split(" bags contain ")

    val bags = mutableListOf<Bag>()

    val regex = Regex("(\\d+)\\s([\\w\\s]+)\\sbag")
    for (innerBag in split[1].split(", ")) {
        val result = regex.find(innerBag)
        if (result != null) {
            bags.add(
                Bag(
                    result.groupValues.last(),
                    result.groupValues[1].toInt()
                )
            )
        }
    }
    return ParsedLine(split[0], bags.toTypedArray())
}


fun searchInGraphSecond(graph: Map<String, Array<Bag>>, need: String): Long {
    var result = 0L

    for (value in graph.getValue(need)) {
        val count = searchInGraphSecond(graph, value.name)
        result += if (count == 0L) value.count.toLong() else (value.count * count) + value.count
    }
    return result
}

fun main() {
    val fileName = "src/advent/issue_7/input.txt"
    val lines = File(fileName).readLines()

    val graphDirect = HashMap<String, Array<Bag>>()
    for (line in lines) {
        val parsedLine = parseLineSecond(line)
        graphDirect[parsedLine.name] = parsedLine.bags
    }

    println(searchInGraphSecond(graphDirect, "shiny gold"))
}