fun main() {
    fun part1(input: List<String>): Int {
        var depthMeasureIncreases = 0

        for ((index, value) in input.withIndex()) {
            if (index == 0) {
                // this one is the baseline -> skip
                continue
            }

            if (value.toIntOrNull() == null) {
                continue
            }

            val previousValue = input[index - 1]
            if (value.toInt() > previousValue.toInt()) {
                depthMeasureIncreases += 1
            }
        }

        return depthMeasureIncreases
    }

    fun part2(input: List<String>): Int {
        val slidingWindow = 3

        val inputAggregated = input.mapIndexed { index, _ ->
            if (index > input.size - slidingWindow) {
                null
            } else {
                (input[index].toInt() + input[index + 1].toInt() + input[index + 2].toInt()).toString()
            }
        }.filterNotNull()

        return part1(inputAggregated)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
