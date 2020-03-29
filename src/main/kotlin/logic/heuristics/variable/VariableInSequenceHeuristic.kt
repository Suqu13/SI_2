package logic.heuristics.variable

class VariableInSequenceHeuristic : VariableHeuristic {
    override fun findVariable(matrix: Array<IntArray>, row: Int, column: Int): Pair<Int, Int> {
        var r = row
        var c = column
        while (matrix[r][c] != -1) {
            c++
            if (c > 8) {
                c = 0
                r += 1
            }
            if (r > 8) {
                return Pair(r, c)
            }
        }
        return Pair(r, c)
    }

}