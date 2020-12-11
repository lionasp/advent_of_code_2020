package advent.issue_11

import java.io.File


fun minusOneI(i: Int): Int {
    return if (i - 1 > 0) i - 1 else 0
}

fun minusOneJ(j: Int): Int {
    return if (j - 1 > 0) j - 1 else 0
}

fun plusOneI(i: Int, maxI: Int): Int {
    return if (i + 1 > maxI) maxI else i + 1
}

fun plusOneJ(j: Int, maxJ: Int): Int {
    return if (j + 1 > maxJ) maxJ else j + 1
}

fun calculateOccupiedCountNew(field: List<String>, i: Int, j: Int): Int {
    var occupiedAround = 0
    val maxI = field.lastIndex
    val maxJ = field[i].lastIndex

    // left-top
    var ii = minusOneI(i)
    var jj = minusOneJ(j)
    while (true) {
        if (ii == i || jj == j) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (ii == minusOneI(ii) || jj == minusOneJ(jj)) {
            break
        }
        ii = minusOneI(ii)
        jj = minusOneJ(jj)
    }

    // left
    ii = i
    jj = minusOneJ(j)
    while (true) {
        if (jj == j) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (jj == minusOneJ(jj)) {
            break
        }
        jj = minusOneJ(jj)
    }

    // left-bottom
    ii = plusOneI(i, maxI)
    jj = minusOneJ(j)
    while (true) {
        if (ii == i || jj == j) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (ii == plusOneI(ii, maxI) || jj == minusOneJ(jj)) {
            break
        }
        ii = plusOneI(ii, maxI)
        jj = minusOneJ(jj)
    }

    // bottom
    ii = plusOneI(i, maxI)
    jj = j
    while (true) {
        if (ii == i) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (ii == plusOneI(ii, maxI)) {
            break
        }
        ii = plusOneI(ii, maxI)
    }

    // right-bottom
    ii = plusOneI(i, maxI)
    jj = plusOneJ(j, maxJ)
    while (true) {
        if (ii == i || jj == j) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (ii == plusOneI(ii, maxI) || jj == plusOneJ(jj, maxJ)) {
            break
        }
        ii = plusOneI(ii, maxI)
        jj = plusOneJ(jj, maxJ)
    }

    // right
    ii = i
    jj = plusOneJ(j, maxJ)
    while (true) {
        if (jj == j) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (jj == plusOneJ(jj, maxJ)) {
            break
        }
        jj = plusOneJ(jj, maxJ)
    }

    // right-top
    ii = minusOneI(i)
    jj = plusOneJ(j, maxJ)
    while (true) {
        if (ii == i || jj == j) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (jj == plusOneJ(jj, maxJ) || ii == minusOneI(ii)) {
            break
        }
        ii = minusOneI(ii)
        jj = plusOneJ(jj, maxJ)
    }

    // top
    ii = minusOneI(i)
    jj = j
    while (true) {
        if (ii == i) {
            break
        }
        if (field[ii][jj] == '#') {
            occupiedAround++
            break
        }
        if (field[ii][jj] == 'L') {
            break
        }

        if (ii == minusOneI(ii)) {
            break
        }
        ii = minusOneI(ii)
    }

    return occupiedAround
}

fun main() {
    val fileName = "src/advent/issue_11/input.txt"
    var state = File(fileName).readLines().toMutableList()
    var lastOccupied = 0

    while (true) {

        state = iteration(state, 5, ::calculateOccupiedCountNew)
     //   printField(state)
        val total = getTotalOccupied(state)

        if (total == lastOccupied) {
            println(total)
            break
        }

        lastOccupied = total
    }
}