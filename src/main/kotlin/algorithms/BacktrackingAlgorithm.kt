package algorithms

import ConstraintResolver
import models.Variable
import heuristics.value.ValueHeuristic
import heuristics.variable.VariableHeuristic

class BacktrackingAlgorithm(
    private val constraintResolver: ConstraintResolver,
    private val variableHeuristic: VariableHeuristic,
    private val valueHeuristic: ValueHeuristic
) {
    fun run(variables: Set<Variable>): Set<Variable> = backtracking(variables)

    private fun backtracking(variables: Set<Variable>): Set<Variable> {
        val variable = variableHeuristic.findVariable(variables)
        var resultVariables = variables.map { v ->
            Variable(
                column = v.column,
                row = v.row,
                value = v.value,
                domain = v.domain
            )
        }.toSet()

        if (variable == null) {
            return resultVariables
        }

        if (variable.domain.isNotEmpty()) {
            val domain = variable.domain
//            valueHeuristic.findValue(variable)
            domain.forEach { d ->
                variable.value = d
                if (constraintResolver.resolve(variable, variables)) {
                    resultVariables = backtracking(variables.map { v ->
                        Variable(
                            column = v.column,
                            row = v.row,
                            value = v.value,
                            domain = v.domain
                        )
                    }.toSet())
                    return resultVariables
                }
                variable.value = -1
            }
        }
        return resultVariables

    }

}