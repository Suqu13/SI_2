package heuristics.variable

interface VariableHeuristic {
    fun findVariable(matrix: Array<IntArray>, row: Int, column: Int): Pair<Int, Int>

}