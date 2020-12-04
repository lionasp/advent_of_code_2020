package advent.issue_4

import java.io.File



fun isPassportValidNew(passportData: String): Boolean {
    val requiredFields = setOf<String>(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid",
        //    "cid",
    )

    val availableFieldsWithValues = HashMap<String, String>()
    for (rawPassportField in passportData.split(" ")) {
        if (rawPassportField == "") continue
        val parsedField = rawPassportField.split(":")
        availableFieldsWithValues[parsedField[0]] = parsedField[1]
    }

    for (required in requiredFields) {
        if (!passportData.contains(required)) {
            return false
        }
    }

    if (availableFieldsWithValues["byr"]?.toInt() !in 1920..2002) return false
    if (availableFieldsWithValues["iyr"]?.toInt() !in 2010..2020) return false
    if (availableFieldsWithValues["eyr"]?.toInt() !in 2020..2030) return false

    val hgt = availableFieldsWithValues["hgt"]!!
    var realHgt = ""
    var mHgt = ""
    for (c in hgt) {
        if (c.isDigit()) {
            realHgt = "$realHgt$c"
        } else {
            mHgt = "$mHgt$c"
        }
    }
    if (mHgt == "in") {
        if (realHgt.toInt() !in 59..76) return false
    } else {
        if (realHgt.toInt() !in 150..193) return false
        if (mHgt != "cm") return false
    }

    val hcl = availableFieldsWithValues["hcl"]!!
    for (i in 1..hcl.lastIndex) {
        if (hcl[i] !in '0'..'9') {
            if (hcl[i] !in 'a'..'f') return false
        }
    }
    if (hcl[0] != '#') return false
    if (hcl.length != 7) return false

    val availableEcl = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    if (!availableEcl.contains(availableFieldsWithValues["ecl"]!!)) return false

    val pid = availableFieldsWithValues["pid"]!!
    for (i in pid) {
        if (!i.isDigit()) return false
    }
    if (pid.length != 9) return false

    return true
}

fun main() {
    val fileName = "src/advent/issue_4/input.txt"
    val lines = File(fileName).readLines()

    var counter = 0
    var passportData = ""
    for (line in lines) {
        if (line == "") {
            if (isPassportValidNew(passportData)) {
                counter++
            }
            passportData = ""
        } else {
            passportData = "$passportData $line"
        }
    }
    if (isPassportValidNew(passportData)) {
        counter++
    }
    println(counter)

}