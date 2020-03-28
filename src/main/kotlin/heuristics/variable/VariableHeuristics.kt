package heuristics.variable

import models.Variable

interface VariableHeuristic {
    fun findVariable(variables: Set<Variable>): Variable?

}