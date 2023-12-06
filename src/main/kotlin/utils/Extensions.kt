package utils

import java.util.*
import kotlin.math.absoluteValue

val Long.abs
    get() = absoluteValue

val Int.abs
    get() = absoluteValue

fun List<CharArray>.forEach(block: (coords: Coords, c: Char) -> Unit) = forEachIndexed { row, line ->
    line.forEachIndexed { col, c ->
        block(Coords(row, col), c)
    }
}

fun <T> List<CharArray>.map(block: (coords: Coords, c: Char) -> T) = flatMapIndexed { row, line ->
    line.mapIndexed { col, c ->
        block(Coords(row, col), c)
    }
}

fun String.tokenizeLongs(): MutableList<Long> {
    val arr = mutableListOf<Long>()
    val tokenizer = StringTokenizer(this)
    while (tokenizer.hasMoreTokens()) {
        arr.add(tokenizer.nextToken()!!.toLong())
    }
    return arr
}
