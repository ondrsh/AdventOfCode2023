import utils.Coords
import utils.forEach
import java.io.File

fun main() {
    val arr = File("input/3.txt").readLines().map { it.toCharArray() }
    val digitsToCoords = mutableListOf<Pair<String, List<Coords>>>()
    val symbols = mutableListOf<Coords>()
    val gears = mutableListOf<Coords>()

    var digits = ""
    val tempCoords = mutableListOf<Coords>()
    arr.forEach { coord, c ->
        if (c == '*') gears.add(coord)
        if (!c.isDigit() && c != '.') symbols.add(coord)
        if (c.isDigit()) {
            digits += c
            tempCoords.add(coord)
        } else if (digits.isNotEmpty()) {
            digitsToCoords.add(digits to tempCoords.toList())
            digits = ""
            tempCoords.clear()
        }
    }

    val ans1 = digitsToCoords.sumOf { digit ->
        if (digit.second.any { p -> symbols.any { it isAdjacentTo p } }) {
            digit.first.toInt()
        } else 0
    }
    println(ans1)

    val ans2 = gears.sumOf { gear ->
        digitsToCoords.filter { it.second.any { coord -> coord isAdjacentTo gear } }.let {
            if (it.size == 2) {
                it[0].first.toInt() * it[1].first.toInt()
            } else 0
        }
    }
    println(ans2)
}
