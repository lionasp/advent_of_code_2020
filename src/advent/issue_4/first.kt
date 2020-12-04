package advent.issue_4

import java.io.File

val requiredFields = setOf<String>(
    "byr:",
    "iyr:",
    "eyr:",
    "hgt:",
    "hcl:",
    "ecl:",
    "pid:",
    //    "cid",
)

fun isPassportValid(passportData: String): Boolean {
    for (required in requiredFields) {
        if (!passportData.contains(required)) {
            return false
        }
    }
    return true
}

fun main() {
    val fileName = "src/advent/issue_4/input.txt"
    val lines = File(fileName).readLines()


    var counter = 0
    var passportData = ""
    for (line in lines) {
        if (line == "") {
            if (isPassportValid(passportData)) {
                counter++
            }
            passportData = ""
        } else {
            passportData = "$passportData $line"
        }
    }
    if (isPassportValid(passportData)) {
        counter++
    }
    println(counter)

}