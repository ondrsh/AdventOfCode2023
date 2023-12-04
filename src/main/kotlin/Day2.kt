import java.io.File

fun main() {
    val input = File("input/2.txt").readLines()
    val globalMax = mapOf("red" to 12, "green" to 13, "blue" to 14)

    var ans1 = 0
    val ans2 = input.mapIndexed { i, line ->
        val tempMax = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        line.substringAfter(": ").split("; ").map { cubes ->
            cubes.split(", ").map {
                val (countStr, color) = it.split(" ")
                tempMax[color] = maxOf(tempMax[color]!!, countStr.toInt())
            }
        }
        ans1 += if (tempMax.all { it.value <= globalMax[it.key]!! }) i + 1 else 0
        tempMax.values.reduce(Int::times)
    }.sum()

    println(ans1)
    println(ans2)
}
