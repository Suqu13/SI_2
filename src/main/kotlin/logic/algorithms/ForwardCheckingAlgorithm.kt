package logic.algorithms

import logic.heuristics.value.ValueHeuristic
import logic.heuristics.variable.VariableHeuristic
import logic.utils.ConstraintsChecker
import kotlin.collections.ArrayList

class ForwardCheckingAlgorithm(
    valueHeuristic: ValueHeuristic,
    variableHeuristic: VariableHeuristic,
    constraintsChecker: ConstraintsChecker
) : Algorithm(valueHeuristic, variableHeuristic, constraintsChecker) {

    override fun evaluateAlgorithmCharacteristic(
        solutions: ArrayList<Array<IntArray>>,
        matrix: Array<IntArray>,
        row: Int,
        column: Int
    ) {
        if (checkForward(row, column, matrix)) {
            solve(solutions, matrix.map { it.clone() }.toTypedArray(), row, column)
            result.incrementReturnsNumber()
        }
    }

    private fun checkForward(row: Int, column: Int, matrix: Array<IntArray>): Boolean {
        val variablesToCheck =
            findVariablesWithoutValuesInColumn(column, matrix) + findVariablesWithoutValueInRow(
                row,
                matrix
            ) + findVariablesWithoutValuesInSquare(row, column, matrix)
        variablesToCheck.forEach {
            if (!this.checkIfVariableDomainExists(it.first, it.second, matrix))
                return false
        }
        return true
    }

    private fun findVariablesWithoutValueInRow(row: Int, matrix: Array<IntArray>): Array<Pair<Int, Int>> {
        val variablesInRowWithoutValue = arrayListOf<Pair<Int, Int>>()
        (0..8).forEach { if (matrix[row][it] == -1) variablesInRowWithoutValue.add(Pair(row, it)) }
        return variablesInRowWithoutValue.toTypedArray()
    }


    private fun findVariablesWithoutValuesInColumn(column: Int, matrix: Array<IntArray>): Array<Pair<Int, Int>> {
        val variablesInColumnWithoutValue = arrayListOf<Pair<Int, Int>>()
        (0..8).forEach { if (matrix[it][column] == -1) variablesInColumnWithoutValue.add(Pair(it, column)) }
        return variablesInColumnWithoutValue.toTypedArray()
    }


    private fun findVariablesWithoutValuesInSquare(row: Int, column: Int, matrix: Array<IntArray>): Array<Pair<Int, Int>> {
        val variablesInSquareWithoutValue = arrayListOf<Pair<Int, Int>>()
        val x = (row / 3) * 3
        val y = (column / 3) * 3
        (x..(x + 2)).mapIndexed { rowIndex, _ ->
            IntArray(3) { y + it }.forEach {
                if (matrix[rowIndex][it] == -1) variablesInSquareWithoutValue.add(
                    Pair(rowIndex, it)
                )
            }
        }
        return variablesInSquareWithoutValue.toTypedArray()
    }

    private fun checkIfVariableDomainExists(row: Int, column: Int, matrix: Array<IntArray>): Boolean {
        val distinctValues = findDistinctValuesInColumn(column, matrix) + findDistinctValuesInRow(
            row,
            matrix
        ) + findDistinctValuesInSquare(row, column, matrix)
        return distinctValues.isEmpty() || distinctValues.size < 10
    }

    private fun findDistinctValuesInRow(row: Int, matrix: Array<IntArray>): Set<Int> =
        matrix[row].map { it }.toSet()

    private fun findDistinctValuesInColumn(column: Int, matrix: Array<IntArray>): Set<Int> =
        (0..8).map { matrix[it][column] }.toSet()

    private fun findDistinctValuesInSquare(row: Int, column: Int, matrix: Array<IntArray>): Set<Int> {
        val x = (row / 3) * 3
        val y = (column / 3) * 3
        val rows = IntArray(3) { x + it }.map { matrix[it] }
        return rows.map { r -> IntArray(3) { y + it }.map { r[it] } }.flatten().toSet()
    }


}