import java.io.File
import java.util.Comparator.comparingInt

fun main() {
    val lines = File("input/7.txt").readLines()

    fun Char.cardStrength(withJoker: Boolean): Int = when (this) {
        'A'  -> 15
        'K'  -> 14
        'Q'  -> 13
        'J'  -> 12 * if (withJoker) 0 else 1
        'T'  -> 11
        else -> this.toString().toInt()
    }

    fun String.type() = groupingBy { it }.eachCount().let { counts ->
        val isTwoPair = counts.count { it.value == 2 } == 2
        when {
            5 in counts.values -> 7
            4 in counts.values -> 6
            3 in counts.values -> if (2 in counts.values) 5 else 4
            isTwoPair          -> 3
            2 in counts.values -> 2
            else               -> 1
        }
    }

    data class ImprovedHand(val original: String, val improved: String)

    fun Comparator<ImprovedHand>.compareCards(withJoker: Boolean) = this
        .thenComparingInt { it.original[0].cardStrength(withJoker) }
        .thenComparingInt { it.original[1].cardStrength(withJoker) }
        .thenComparingInt { it.original[2].cardStrength(withJoker) }
        .thenComparingInt { it.original[3].cardStrength(withJoker) }
        .thenComparingInt { it.original[4].cardStrength(withJoker) }

    val originalComparator = comparingInt<ImprovedHand> { it.original.type() }.compareCards(withJoker = false)
    val jokerComparator = comparingInt<ImprovedHand> { it.improved.type() }.compareCards(withJoker = true)

    fun String.improve() = toList().distinct()
        .map { ImprovedHand(this, this.replace('J', it)) }
        .maxWith(jokerComparator)
        .improved

    val improvedToBids = lines.associate {
        val (hand, bid) = it.split(" ").let { it.first() to it.last().toLong() }
        val improved = ImprovedHand(hand, hand.improve())
        improved to bid
    }

    fun sumWinnings(comp: Comparator<ImprovedHand>) = improvedToBids
        .toSortedMap(comp)
        .keys
        .mapIndexed { i, improved -> improvedToBids[improved]!! * (i + 1) }
        .sum()

    val ans1 = sumWinnings(originalComparator)
    val ans2 = sumWinnings(jokerComparator)
    println(ans1)
    println(ans2)
}
