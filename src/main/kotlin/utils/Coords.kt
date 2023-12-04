package utils

data class Coords(var m: Int = 0, var n: Int = 0) {

    fun manhattan(): Int = m.abs + n.abs

    fun asList() = listOf(m, n)

    operator fun plus(other: Coords) = Coords(m = this.m + other.m,
                                              n = this.n + other.n)

    operator fun minus(other: Coords) = Coords(m = this.m - other.m,
                                               n = this.n - other.n)

    operator fun div(k: Int) = Coords(m = this.m / k,
                                      n = this.n / k)

    operator fun times(k: Int) = Coords(m = this.m * k,
                                        n = this.n * k)

    infix fun vectorTo(other: Coords) = Coords(m = other.m - this.m,
                                               n = other.n - this.n)

    fun normalized() = this / asList().maxOf { it.abs }

    fun moveLeft() = n--
    fun moveRight() = n++
    fun moveUp() = m--
    fun moveDown() = m++

    fun moveLeft(i: Int): Int {
        n -= i
        return n
    }

    fun moveRight(i: Int): Int {
        n += i
        return n
    }

    fun moveUp(i: Int): Int {
        m -= i
        return m
    }

    fun moveDown(i: Int): Int {
        m += i
        return m
    }

    fun moveDirection(c: Char) = when (c) {
        'R'  -> right()
        'U'  -> up()
        'L'  -> left()
        'D'  -> down()
        else -> {
            throw RuntimeException("Invalid direction char: $c")
        }
    }

    fun moveDirection(s: String) = if (s.length != 1) throw RuntimeException("Direction string invalid: %s") else {
        moveDirection(s.first())
    }

    fun left(): Coords = copy(n = n - 1)
    fun right(): Coords = copy(n = n + 1)
    fun up(): Coords = copy(m = m - 1)
    fun down(): Coords = copy(m = m + 1)

    fun left(i: Int): Coords = copy(n = n - i)
    fun right(i: Int): Coords = copy(n = n + i)
    fun up(i: Int): Coords = copy(m = m - i)
    fun down(i: Int): Coords = copy(m = m + i)

    infix fun isAdjacentTo(other: Coords) = (other.m - m).abs <= 1 && (other.n - n).abs <= 1

    infix fun isNotAdjacentTo(other: Coords) = !isAdjacentTo(other)

    infix fun isCardinalAdjacentTo(other: Coords): Boolean {
        return if (other.m == m) {
            (other.n - n).abs <= 1
        } else if (other.n == n) {
            (other.m - m).abs <= 1
        } else false
    }

    fun cardinalAdjacents(): List<Coords> = listOf(left(), right(), up(), down())

    fun adjacents(): List<Coords> = listOf(
        left(),
        right(),
        up(),
        down(),
        Coords(m + 1, n + 1),
        Coords(m - 1, n - 1),
        Coords(m - 1, n + 1),
        Coords(m + 1, n - 1),
                                          )

    override fun equals(other: Any?): Boolean {
        return if (other !is Coords) false
        else m == other.m && n == other.n
    }

    override fun hashCode(): Int {
        var result = m
        result = 31 * result + n
        return result
    }

    fun mod(rows: Int, cols: Int) = copy(m = m.mod(rows),
                                         n = n.mod(cols))
}

fun String.toVec() = Coords(0, 0).moveDirection(this)

fun Char.toVec() = Coords(0, 0).moveDirection(this)

operator fun Array<BooleanArray>.contains(p: Coords): Boolean {
    return p.m in indices && p.n in 0 until first().size
}

operator fun Array<BooleanArray>.get(p: Coords) = get(p.m)[p.n]
