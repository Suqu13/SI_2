package utils

import models.Problem
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class FileLoader {

    fun load(): Set<Problem> {
        val problems = mutableSetOf<Problem>()
        csvReader().open("src/main/resources/Sudoku.csv") {
            readAllAsSequence().forEachIndexed { index, row ->
                if (index != 0) {
                    val problemAsString = row.joinToString().split(";")
                    val matrix = createMatrix(problemAsString[2].map { v -> Character.getNumericValue(v) }.toIntArray())
                    problems.add(
                        Problem(
                            id = problemAsString[0].toInt(),
                            difficulty = problemAsString[1].toFloat(),
                            matrix = matrix
                        )
                    )
                }
            }
        }
        return problems
    }

    private fun createMatrix(data: IntArray): Array<IntArray> {
        val matrix = Array(9) { IntArray(9) }
        data.forEachIndexed { i, v -> matrix[i / 9][i % 9] = v }
        return matrix
    }

}