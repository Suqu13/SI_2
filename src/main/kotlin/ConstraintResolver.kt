import models.Variable

class ConstraintResolver {

    fun resolve(variable: Variable, variables: Set<Variable>): Boolean {
        val associatedVariables = findAssociatedVariables(variable, variables)
        val doesValueNotExist = associatedVariables.any { v -> v.value == -1 }
        if (doesValueNotExist) {
            variable.domain.remove(variable.value)
            associatedVariables.forEach { v -> v.domain.remove(variable.value) }
            return true
        }
        return false
    }

    private fun findAssociatedVariables(variable: Variable, variables: Set<Variable>): Set<Variable> =
        variables.filter { v -> (v.row != variable.row || v.column != variable.column)
                && (v.row == variable.row || v.column == variable.column || v.square == variable.square) }.toSet()

}