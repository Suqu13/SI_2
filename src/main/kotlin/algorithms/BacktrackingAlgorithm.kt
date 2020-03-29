package algorithms

import heuristics.value.ValueHeuristic
import heuristics.variable.VariableHeuristic
import models.Problem
import models.Result
import utils.ConstraintsChecker
import kotlin.collections.ArrayList

class BacktrackingAlgorithm(
    private val valueHeuristic: ValueHeuristic,
    private val variableHeuristic: VariableHeuristic,
    private val constraintsChecker: ConstraintsChecker
) {

    private lateinit var result: Result

    fun run(problem: Problem): Result {
        result = Result(problem.id, problem.difficulty)
        result.startWatching()
        solve(ArrayList(), problem.matrix, 0, 0)
        result.stopWatching()
        result.showStatistics()
        return result
    }

    private fun solve(
        solutions: ArrayList<Array<IntArray>>,
        matrix: Array<IntArray>,
        initialRow: Int,
        initialColumn: Int
    ): ArrayList<Array<IntArray>> {
        val (row, column) = variableHeuristic.findVariable(matrix, initialRow, initialColumn)

        if (row <= 8) {
            var domain = (1..9).toMutableList()
            while (domain.isNotEmpty()) {
                val (value, updatedDomain) = valueHeuristic.findValue(domain)
                domain = updatedDomain
                if (constraintsChecker.check(value, row, column, matrix)) {
                    matrix[row][column] = value
                    solve(solutions, matrix.map { it.clone() }.toTypedArray(), row, column)
                    matrix[row][column] = -1
                    result.incrementTotalNodesNumber()
                }
            }
            return solutions
        }
        solutions.add(matrix)
        result.watchForSolution(matrix)
        return solutions
    }
}