package utils

import com.github.doyaaaaaken.kotlincsv.dsl.context.WriteQuoteMode
import models.Problem
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import models.Result

class FileIO {

    val writer = csvWriter {
        delimiter = '\n'
        lineTerminator = "\n\n"

    }

    fun load(fileName: String): List<Problem> {
        val problems = ArrayList<Problem>()
        csvReader().open(fileName) {
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


    fun write(fileName: String, results: List<Result>) {
        writer.open(fileName) {
            results.forEach {
                writeRow(
                    it.prepareResultToWriting()
                )
            }
        }
    }
}