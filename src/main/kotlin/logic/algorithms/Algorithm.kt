package logic.algorithms

import logic.heuristics.value.ValueHeuristic
import logic.heuristics.variable.VariableHeuristic
import logic.models.Problem
import logic.models.Result
import logic.utils.ConstraintsChecker

abstract class Algorithm(
    private val valueHeuristic: ValueHeuristic,
    private val variableHeuristic: VariableHeuristic,
    private val constraintsChecker: ConstraintsChecker
) {
    lateinit var result: Result

    abstract fun evaluateAlgorithmCharacteristic(
        solutions: ArrayList<Array<IntArray>>,
        matrix: Array<IntArray>,
        row: Int,
        column: Int
    )

    fun run(problem: Problem): Result {
        result = Result(problem.id, problem.difficulty)
        result.startWatching()
        solve(ArrayList(), problem.matrix, 0, 0)
        result.stopWatching()
        result.showStatistics()
        return result
    }

    fun solve(
        solutions: ArrayList<Array<IntArray>>,
        matrix: Array<IntArray>,
        initialRow: Int,
        initialColumn: Int
    ): ArrayList<Array<IntArray>> {
        result.incrementTotalNodesNumber()
        val (row, column) = variableHeuristic.findVariable(matrix, initialRow, initialColumn)

        if (row <= 8) {
            var domain = (1..9).toMutableList()
            while (domain.isNotEmpty()) {
                val (value, updatedDomain) = valueHeuristic.findValue(domain)
                domain = updatedDomain
                if (constraintsChecker.check(value, row, column, matrix)) {
                    matrix[row][column] = value
                    evaluateAlgorithmCharacteristic(solutions, matrix, initialRow, initialColumn)
                    matrix[row][column] = -1
                }
            }
            result.incrementReturnsNumber()
            return solutions
        }
        solutions.add(matrix)
        result.watchForSolution(matrix)
        return solutions
    }
}