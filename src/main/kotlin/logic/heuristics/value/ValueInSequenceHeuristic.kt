package logic.heuristics.value

class ValueInSequenceHeuristic : ValueHeuristic {
    override fun findValue(values: MutableList<Int>): Pair<Int, MutableList<Int>> {
        val first = values.first()
        values.removeAt(0)
        return Pair(first, values)
    }
}