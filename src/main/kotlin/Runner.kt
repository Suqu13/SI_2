import logic.algorithms.BacktrackingAlgorithm
import logic.heuristics.value.ValueInSequenceHeuristic
import logic.heuristics.variable.VariableInSequenceHeuristic
import logic.models.Problem
import logic.models.Result
import logic.utils.ConstraintsChecker

class Runner {
    fun execute(problem: Problem, valueHeuristicsName: String, variableHeuristicsName: String, algorithmName: String): Result {
        val constraintsChecker = ConstraintsChecker()

        val valueHeuristics = when (valueHeuristicsName) {
            "In Sequence" -> ValueInSequenceHeuristic()
            else -> ValueInSequenceHeuristic()
        }

        val variableHeuristics = when (variableHeuristicsName) {
            "In Sequence" -> VariableInSequenceHeuristic()
            else -> VariableInSequenceHeuristic()
        }

        val algorithm = BacktrackingAlgorithm(
            constraintsChecker = constraintsChecker,
            variableHeuristic = variableHeuristics,
            valueHeuristic = valueHeuristics
        )
        return algorithm.run(problem)
    }
}