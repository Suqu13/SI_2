package logic.algorithms

import logic.heuristics.value.ValueHeuristic
import logic.heuristics.variable.VariableHeuristic
import logic.models.Problem
import logic.models.Result
import logic.utils.ConstraintsChecker
import kotlin.collections.ArrayList

class BacktrackingAlgorithm(
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
        solve(solutions, matrix.map { it.clone() }.toTypedArray(), row, column)
        result.incrementReturnsNumber()
    }


}