package logic.heuristics.value

class ValueRandomHeuristic : ValueHeuristic  {
    override fun findValue(values: MutableList<Int>): Pair<Int, MutableList<Int>> {
        val value = values.random()
        return Pair(value, values.filter { it != value }.toMutableList())
    }

}