package gui

import logic.algorithms.BacktrackingAlgorithm
import logic.algorithms.ForwardCheckingAlgorithm
import logic.heuristics.value.ValueInSequenceHeuristic
import logic.heuristics.value.ValueRandomHeuristic
import logic.heuristics.variable.VariableInSequenceHeuristic
import logic.heuristics.variable.VariableRestrictedDomainHeuristic
import logic.models.Problem
import logic.models.Result
import logic.utils.ConstraintsChecker

class ExecutionRunner {
    fun execute(
        problem: Problem,
        valueHeuristicsName: String,
        variableHeuristicsName: String,
        algorithmName: String
    ): Result {
        val constraintsChecker = ConstraintsChecker()

        val valueHeuristics = when (valueHeuristicsName) {
            IN_SEQUENCE_VALUE_HEURISTIC -> ValueInSequenceHeuristic()
            RANDOM_VALUE_HEURISTIC -> ValueRandomHeuristic()
            else -> ValueInSequenceHeuristic()
        }

        val variableHeuristics = when (variableHeuristicsName) {
            IN_SEQUENCE_VARIABLE_HEURISTIC -> VariableInSequenceHeuristic()
            THE_MOST_RESTRICTED_DOMAIN_VARIABLE_HEURISTIC -> VariableRestrictedDomainHeuristic()
            else -> VariableInSequenceHeuristic()
        }

        val algorithm = when (algorithmName) {
            BACKTRACKING_ALGORITHM -> BacktrackingAlgorithm(
                constraintsChecker = constraintsChecker,
                variableHeuristic = variableHeuristics,
                valueHeuristic = valueHeuristics
            )
            FORWARD_CHECKING_ALGORITHM -> ForwardCheckingAlgorithm(
                constraintsChecker = constraintsChecker,
                variableHeuristic = variableHeuristics,
                valueHeuristic = valueHeuristics
            )
            else -> BacktrackingAlgorithm(
                constraintsChecker = constraintsChecker,
                variableHeuristic = variableHeuristics,
                valueHeuristic = valueHeuristics
            )
        }
        return algorithm.run(problem)
    }
}