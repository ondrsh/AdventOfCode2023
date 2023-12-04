import java.io.File
import kotlin.math.pow

fun main() {
    val lines = File("input/4.txt").readLines()
    val matches = lines.map {
        val (winning, mine) = it.substringAfter(": ").split(" | ").map {
            it.split(" ").mapNotNull(String::toIntOrNull)
        }
        mine.count { it in winning }
    }
    val ans1 = matches.map { 2.0.pow(it - 1).toInt() }.sum()
    println(ans1)

    val cardsCount = IntArray(lines.size) { 1 }
    matches.forEachIndexed { i, nMatches ->
        for (j in i + 1..i + nMatches) {
            cardsCount[j] += cardsCount[i]
        }
    }
    val ans2 = cardsCount.sum()
    println(ans2)
}
