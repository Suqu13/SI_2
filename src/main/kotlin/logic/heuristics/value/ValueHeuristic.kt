package logic.heuristics.value


interface ValueHeuristic {
    fun findValue(values: MutableList<Int>): Pair<Int, MutableList<Int>>
}