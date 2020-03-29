package heuristics.value


interface ValueHeuristic {
    fun findValue(values: IntArray): Pair<Int, IntArray>
}