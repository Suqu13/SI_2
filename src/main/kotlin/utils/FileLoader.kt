package utils

import Problem
import Variable
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class FileLoader {

    fun load(): Set<Problem> {
        val problems = mutableSetOf<Problem>()
        csvReader().open("src/main/resources/Sudoku.csv") {
            readAllAsSequence().forEachIndexed { index, row ->
                if (index != 0) {
                    val problemAsString = row.joinToString().split(";")
                    problems.add(
                        Problem(
                            id = problemAsString[0].toInt(),
                            difficulty = problemAsString[1].toFloat(),
                            variables = createVariablesSet(problemAsString[2])
                        )
                    )
                }
            }
        }
        return problems
    }

    private fun createVariablesSet(values: String): Set<Variable> =
        values.mapIndexed { i, v -> createVariable(i, v) }.toSet()


    private fun createVariable(i: Int, v: Char): Variable {
        val row = i / 9 + 1
        val column = i % 9 + 1
        val value = Character.getNumericValue(v)
        val domain = (1..9).toMutableSet()
        return Variable(row = row, column = column, value = value, domain = domain)
    }
}