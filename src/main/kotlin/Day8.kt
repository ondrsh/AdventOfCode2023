import utils.lcm
import java.io.File

fun main() {
    val lines = File("input/8.txt").readLines()
    val instructions = lines.first()
    val network = lines.drop(2).associate {
        it.split(" = ").let {
            val (node, targets) = it.first() to it.last().drop(1).dropLast(1).split(", ")
            node to mapOf('L' to targets.first(), 'R' to targets.last())
        }
    }

    fun String.solveForNode(): Long {
        var cur = this
        var multi = 0L
        var i = 0
        while (true) {
            val inst = instructions[i++]
            if (i == instructions.length) {
                i = 0
                multi += 1L
            }
            val next = network[cur]!![inst]!!
            if (next.endsWith('Z')) {
                return multi * instructions.length + i
            }
            cur = next
        }
    }

    val ans1 = network.keys.find { it == "AAA" }!!.solveForNode()
    val ans2 = network.keys.filter { it.last() == 'A' }.map { it.solveForNode() }.reduce(::lcm)
    println(ans1)
    println(ans2)
}
