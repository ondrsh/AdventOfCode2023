import java.io.File

fun main() {
    val lines = File("input/9.txt").readLines().map { it.split(" ").map { it.toInt() } }

    fun List<Int>.extrapolate(): Int = if (all { it == 0 }) 0 else {
        last() + windowed(2) { it.last() - it.first() }.extrapolate()
    }

    val ans1 = lines.sumOf { it.extrapolate() }
    val ans2 = lines.sumOf { it.reversed().extrapolate() }
    println(ans1)
    println(ans2)
}
