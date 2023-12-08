package utils

fun lcm(a: Long, b: Long) = a*b/gcd(a, b)

fun gcd(a: Long, b: Long): Long {
    var a = a
    var b = b
    if (a < 0 || b < 0 || a + b <= 0) {
        throw IllegalArgumentException("GCD Error: a=$a, b=$b")
    }

    while (a > 0 && b > 0) {
        if (a >= b) {
            a %= b
        } else {
            b %= a
        }
    }

    return maxOf(a, b)
}
