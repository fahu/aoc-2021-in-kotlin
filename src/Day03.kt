enum class BitCriteria {
    MostSignificant,
    LeastSignificant,
}

fun main() {

    fun findMostCommonBit(input: List<String>, index: Int): Int {
        val bitsAggregated = input.groupBy { it.substring(index, index + 1) }

        return if (bitsAggregated["0"]!!.size > bitsAggregated["1"]!!.size) {
            0
        } else if (bitsAggregated["0"]!!.size < bitsAggregated["1"]!!.size) {
            1
        } else {
            throw Exception("Same amount of 0 and 1 bits which means this scenario also needs to be handled.")
        }
    }

    fun String.flipBits(): String {
        return this.replace('0', 'x')
            .replace('1', '0')
            .replace('x', '1')
    }

    fun part1(input: List<String>): Int {
        val wordLength = input.first().length
        var mostCommonBits = ""

        for (i in 0 until wordLength) {
            mostCommonBits += findMostCommonBit(input, i)
        }

        val gammaRate = Integer.parseUnsignedInt(mostCommonBits, 2)
        val epsilonRate = Integer.parseUnsignedInt(mostCommonBits.flipBits(), 2)

        return gammaRate * epsilonRate
    }

    fun List<String>.getCommonValues(index: Int, bitCriteria: BitCriteria): List<String> {
        val valuesAggregated = this.groupBy { it.substring(index, index + 1) }

        if (valuesAggregated.size < 2) {    // in case only 0 or 1 bit values are left
            return valuesAggregated["0"]!!
        }

        val leastSignificant: List<String>
        val mostSignificant: List<String>

        if (valuesAggregated["0"]!!.size > valuesAggregated["1"]!!.size) {
            mostSignificant = valuesAggregated["0"]!!
            leastSignificant = valuesAggregated["1"]!!
        } else if (valuesAggregated["0"]!!.size < valuesAggregated["1"]!!.size) {
            mostSignificant = valuesAggregated["1"]!!
            leastSignificant = valuesAggregated["0"]!!
        } else {
            mostSignificant = valuesAggregated["1"]!!
            leastSignificant = valuesAggregated["0"]!!
        }

        return when (bitCriteria) {
            BitCriteria.MostSignificant -> mostSignificant
            BitCriteria.LeastSignificant -> leastSignificant
        }
    }

    fun findRating(input: List<String>, bitCriteria: BitCriteria): String {
        val wordLength = input.first().length
        var mostCommonValues = input
        for (i in 0 until wordLength) {
            mostCommonValues = mostCommonValues.getCommonValues(i, bitCriteria)

            if (mostCommonValues.size == 1) {
                return mostCommonValues.first()
            }
        }
        throw Exception("Rating could not be not found")
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = Integer.parseUnsignedInt(findRating(input, BitCriteria.MostSignificant), 2)
        val co2ScrubberRating = Integer.parseUnsignedInt(findRating(input, BitCriteria.LeastSignificant), 2)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")

    println(part1(input))
    check(part1(input) == 1458194)
    println(part2(input))
    check(part2(input) == 2829354)
}
