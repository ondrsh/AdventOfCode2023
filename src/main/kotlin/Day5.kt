import java.io.File

// 0.1ms
fun main() {
    val blocks = File("input/5.txt").readText().split("\n\n")
    val seeds = blocks.first().substringAfter(": ").split(" ").map { it.toLong() }
    val seedRanges = seeds.chunked(2).map { (start, length) -> start..start + length }
    val rangeMaps = blocks.drop(1).filter { it.contains("map") }.map { block ->
        block.split("\n").drop(1).map { line ->
            val (dst, src, length) = line.split(" ").map { it.toLong() }
            (src until src + length) to (dst until dst + length)
        }.toMap()
    }

    fun mapRange(input: LongRange,
                 mappings: Map<LongRange, LongRange>): List<LongRange> = buildList {
        val outputs = mappings.mapNotNull { (src, dst) ->
            val start = maxOf(src.first, input.first)
            val end = minOf(src.last, input.last)
            val shift = dst.first - src.first
            if (end < start) null else (start + shift)..(end + shift)
        }.ifEmpty { listOf(input.first..input.last) }
        addAll(outputs)
        if (size > 1) {
            val theirMin = mappings.minOf { it.key.first }
            val theirMax = mappings.maxOf { it.key.last }
            if (input.first < theirMin) add(input.first until theirMin)
            if (input.last > theirMax) add(theirMax + 1..input.last)
        }
    }

    fun List<LongRange>.mapRangesIteratively(): Long = flatMap {
        rangeMaps.fold(listOf(it)) { ranges, rangeMappings ->
            ranges.flatMap { range -> mapRange(range, rangeMappings) }
        }
    }.minOf { it.first }

    val ans1 = seeds.map { it..it }.mapRangesIteratively()
    val ans2 = seedRanges.mapRangesIteratively()
    println(ans1)
    println(ans2)
}
