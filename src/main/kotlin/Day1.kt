import java.io.File

fun main() {
    val input = File("input/1.txt").readLines()
    val strs = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun List<String>.solve(part: Int) = sumOf {
        val digits = mutableListOf<String>()
        var s = it
        while (s.isNotEmpty()) {
            if (s.first().isDigit()) digits.add(s.first().toString())
            if (part == 2) {
                strs.find { s.startsWith(it) }?.let { found ->
                    digits.add((strs.indexOf(found) + 1).toString())
                }
            }
            s = s.drop(1)
        }
        (digits.first() + digits.last()).toInt()
    }

    val ans1 = input.solve(1)
    val ans2 = input.solve(2)
    println(ans1)
    println(ans2)
}
