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
        var i = 0
        while (cur.endsWith('Z') == false) {
            val inst = instructions[i++ % instructions.length]
            cur = network[cur]!![inst]!!
        }
        return i.toLong()
    }

    val ans1 = network.keys.find { it == "AAA" }!!.solveForNode()
    val ans2 = network.keys.filter { it.last() == 'A' }.map { it.solveForNode() }.reduce(::lcm)
    println(ans1)
    println(ans2)
}
