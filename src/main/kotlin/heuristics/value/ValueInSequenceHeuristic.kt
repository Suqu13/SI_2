package heuristics.value

import models.Variable

class ValueInSequenceHeuristic : ValueHeuristic {
    override fun findValue(variable: Variable) {
        variable.value = variable.domain.first()
        variable.domain.remove(variable.value)
        variable.domain.add(variable.value)
    }
}