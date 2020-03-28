package utils

import models.Problem
import models.Variable
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class FileLoader {

    fun load(): Set<Problem> {
        val problems = mutableSetOf<Problem>()
        csvReader().open("src/main/resources/Sudoku.csv") {
            readAllAsSequence().forEachIndexed { index, row ->
                if (index != 0) {
                    val problemAsString = row.joinToString().split(";")
                    val variables = removeExcessDomain(createVariablesSet(problemAsString[2]))
                    problems.add(
                        Problem(
                            id = problemAsString[0].toInt(),
                            difficulty = problemAsString[1].toFloat(),
                            variables = variables
                        )
                    )
                }
            }
        }
        return problems
    }

    private fun findAssociatedVariables(variable: Variable, variables: Set<Variable>): Set<Variable> =
        variables.filter { v -> (v.row != variable.row || v.column != variable.column)
                && (v.row == variable.row || v.column == variable.column || v.square == variable.square) }.toSet()

    private fun removeExcessDomain(variables: Set<Variable>): Set<Variable> {
        val variablesWithValues = variables.filter { variable -> variable.value != -1 }
        variablesWithValues.forEach { variable ->
            val associatedVariables = findAssociatedVariables(variable, variables)
            associatedVariables.forEach{ associatedVariable -> associatedVariable.domain.remove(variable.value)}
        }
        return variables
    }

    private fun createVariablesSet(values: String): Set<Variable> =
        values.mapIndexed { i, v -> createVariable(i, v) }.toSet()

    private fun createVariable(i: Int, v: Char): Variable {
        val row = i / 9 + 1
        val column = i % 9 + 1
        val value = Character.getNumericValue(v)
        val domain = (1..9).toMutableSet()
        domain.remove(value)
        return Variable(row = row, column = column, value = value, domain = domain)
    }
}