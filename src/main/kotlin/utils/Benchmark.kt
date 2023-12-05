package utils

import kotlin.system.measureNanoTime

fun <T> bench(runs: Int = 10_000,
              warmup: Boolean = true,
              block: () -> T): Pair<T, Double> {
    var res = block()
    if (warmup) {
        repeat(runs) {
            res = block()
        }
    }
    val nanos = measureNanoTime {
        repeat(runs) {
            res = block()
        }
    }
    val millis = 1.0 * nanos / 1e6 / runs
    return res to millis
}
