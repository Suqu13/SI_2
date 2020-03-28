package heuristics.value

import models.Variable

interface ValueHeuristic {
    fun findValue(variable: Variable)

}