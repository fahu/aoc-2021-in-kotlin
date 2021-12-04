fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontalPosition = 0


        input.map {
            val separatedValues = it.split(" ")
            Pair(separatedValues[0], separatedValues[1])
        }.forEach {
            when (it.first) {
                "forward" -> horizontalPosition += it.second.toInt()
                "up" -> depth -= it.second.toInt()
                "down" -> depth += it.second.toInt()
            }
        }

        return depth * horizontalPosition
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var depth = 0
        var horizontalPosition = 0


        input.map {
            val separatedValues = it.split(" ")
            Pair(separatedValues[0], separatedValues[1])
        }.forEach {
            when (it.first) {
                "forward" -> {
                    horizontalPosition += it.second.toInt()
                    depth += aim * it.second.toInt()
                }
                "up" -> aim -= it.second.toInt()
                "down" -> aim += it.second.toInt()
            }
        }

        return depth * horizontalPosition
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")

    println(part1(input))
    println(part2(input))
}
