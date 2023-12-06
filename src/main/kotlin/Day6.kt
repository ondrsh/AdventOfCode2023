import utils.tokenizeLongs
import java.io.File
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

fun main() {
    val lines = File("input/6.txt").readLines()
    val (times, records) = lines.map { it.substringAfter(":").tokenizeLongs() }

    fun countWinning(race: List<Long>): Long {
        val (time, record) = race
        val halfTime = time / 2.0
        val root = sqrt(halfTime * halfTime - record - 1e-12)
        return (1 + floor(halfTime + root) - ceil(halfTime - root)).toLong()
    }

    val ans1 = (times zip records).map { countWinning(it.toList()) }.reduce(Long::times)
    println(ans1)

    val race = listOf(times, records).map { it.joinToString("") { it.toString() }.toLong() }
    val ans2 = countWinning(race)
    println(ans2)
}
