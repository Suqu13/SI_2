package heuristics.value

class ValueInSequenceHeuristic : ValueHeuristic {
    override fun findValue(values: IntArray): Pair<Int, IntArray> {
        val first = values.first()
        return Pair(first, values.dropWhile { it == first }.toIntArray())
    }
}