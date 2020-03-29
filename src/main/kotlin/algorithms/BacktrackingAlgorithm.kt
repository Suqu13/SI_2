package algorithms

import heuristics.value.ValueHeuristic
import heuristics.variable.VariableHeuristic
import utils.ConstraintsChecker

class BacktrackingAlgorithm(
    private val valueHeuristic: ValueHeuristic,
    private val variableHeuristic: VariableHeuristic,
    private val constraintsChecker: ConstraintsChecker
) {

    fun run(matrix: Array<IntArray>): ArrayList<Array<IntArray>> {
        return solve(ArrayList(), matrix, 0, 0)
    }

    private fun solve(
        solutions: ArrayList<Array<IntArray>>,
        matrix: Array<IntArray>,
        initialRow: Int,
        initialColumn: Int
    ): ArrayList<Array<IntArray>> {
        val (row, column) = variableHeuristic.findVariable(matrix, initialRow, initialColumn)


        if (row <= 8) {
            var domain = (1..9).map { it }.toIntArray()
            while (domain.isNotEmpty()) {
                val (value, updatedDomain) = valueHeuristic.findValue(domain)
                domain = updatedDomain
                if (constraintsChecker.check(value, row, column, matrix)) {
                    matrix[row][column] = value
                    solve(solutions, matrix.map { it.clone() }.toTypedArray(), row, column)
                    matrix[row][column] = -1
                }
            }
            return solutions
        }
        solutions.add(matrix)
        return solutions

    }
}