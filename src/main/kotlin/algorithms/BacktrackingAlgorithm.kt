package algorithms

import ConstraintResolver
import Variable

class BacktrackingAlgorithm(private val constraintResolver: ConstraintResolver) {
    fun run(variables: Set<Variable>): Set<Variable> = backtracking(variables)

    private fun backtracking(variables: Set<Variable>): Set<Variable> {
        val variable = findVariableWithSmallestDomain(variables)
        var resultVariables = variables;

        if (variable != null) {
            if (variable.domain.isNotEmpty()) {
                variable.chooseValueInSequence()
                if (constraintResolver.resolve(variable, variables)) {
                    resultVariables = backtracking(variables.map { v ->
                        variable.copy(
                            column = v.column,
                            row = v.row,
                            value = v.value,
                            domain = v.domain
                        )
                    }.toSet())
                }
            }
            return resultVariables;
        }
        return resultVariables;
    }

    private fun findVariableWithSmallestDomain(variables: Set<Variable>): Variable? {
        val hasAnyVariableWithoutValue = variables.any { variable -> variable.value == -1 }
        if (hasAnyVariableWithoutValue) {
            var smallestDomainLength = variables.first().domain.size
            var variableWithSmallestDomain = variables.first()
            variables.forEach { variable ->
                if (variable.domain.size < smallestDomainLength) {
                    smallestDomainLength = variable.domain.size
                    variableWithSmallestDomain = variable
                }
            }
            return variableWithSmallestDomain
        }
        return null
    }


}