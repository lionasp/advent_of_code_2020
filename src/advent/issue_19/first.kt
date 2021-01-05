package advent.issue_19

import java.io.File
import java.lang.StringBuilder


// without memoization
fun getPart(rawRules: Map<Int, String>, index: Int): String {
    val value = rawRules.getValue(index)
    if (value.contains("\"")) {
        return value.replace("\"", "")
    }
    val parts = mutableListOf<String>()
    for (part in value.split(" | ")) {
        val subIndexBuilder = StringBuilder()
        for (subIndex in part.split(" ")) {
            subIndexBuilder.append(getPart(rawRules, subIndex.toInt()))
        }
        parts.add("($subIndexBuilder)")
    }
    return "(${parts.joinToString("|")})"
}

fun buildRegex(rawRules: Map<Int, String>): String {
    val builder = StringBuilder()
    for (item in rawRules.getValue(0).split(" ")) {
        builder.append(getPart(rawRules, item.toInt()))
    }
    return builder.toString()
}

// 2: (aa) | (bb)
// 3: (ab) | (ba)
// 1: ( ((aa)|(bb)) ((ab)|(ba)) ) | ( ((ab)|(ba)) ((aa)|(bb)) )
fun main() {
    val fileName = "src/advent/issue_19/input.txt"
    val lines = File(fileName).readLines()

    val rawRules = mutableMapOf<Int, String>()
    var index = 0
    for (line in lines) {
        if (line == "") {
            break
        }
        val split = line.split(": ")
        rawRules[split[0].toInt()] = split[1]
        index++
    }

    val messages = mutableListOf<String>()
    messages.addAll(lines.slice(index+1..lines.lastIndex))

    val reg = Regex(buildRegex(rawRules))
    println(reg)
    var counter = 0
    for (message in messages) {
        if (reg.matchEntire(message) != null) {
            counter++
        }
    }

    println(counter)
}