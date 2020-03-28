package heuristics.variable

import models.Variable

class VariableWithSmallestDomainHeuristic : VariableHeuristic {
    override fun findVariable(variables: Set<Variable>): Variable? {
        val variablesWithoutValue = variables.filter { variable -> variable.value == -1 }
        if (variablesWithoutValue.isNotEmpty()) {
            var smallestDomainLength = variablesWithoutValue.first().domain.size
            var variableWithSmallestDomain = variablesWithoutValue.first()
            variablesWithoutValue.forEach { variable ->
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