package gui

import Runner
import javafx.beans.property.SimpleSetProperty
import javafx.beans.property.SimpleStringProperty
import logic.models.Result
import logic.models.Stats
import logic.utils.FileIO
import tornadofx.*


class PageController(): Controller() {
    private val fileIO = FileIO()
    private val runner = Runner()
    private val problems = fileIO.load("src/main/resources/Sudoku.csv")
    val problemsIds = problems.map { it.id }.asObservable()
    var matrix = (0..8).map { IntArray(9) { 0 } } .asObservable()
    val statsProperty = SimpleStringProperty(this, "Stats property","NOT COMPUTED YET")

    val algorithms = listOf("Backtracking").asObservable()
    val variableHeuristics = listOf("In Sequence").asObservable()
    val valueHeuristics = listOf("In Sequence").asObservable()
    var pointer = 0
    private lateinit var result: Result

    fun resolveProblem(problemId: Int, valueHeuristicsName: String, variableHeuristicsName: String, algorithmName: String) {
        result = runner.execute(problems.first { it.id == problemId }, valueHeuristicsName, variableHeuristicsName, algorithmName)
        matrix.setAll(result.solutions.first().toList())
        pointer = 0
        statsProperty.set(result.prepareResultToWriting().take(9).joinToString(separator = "\n"))
    }

    fun next() {
        print("next")
        if (matrix.isNotEmpty() && pointer < matrix.size){
            pointer += pointer
            matrix.setAll(result.solutions[pointer].toList())
        }
    }

    fun previous() {
        print("previous")
        if (matrix.isNotEmpty() && pointer > 0) {
            pointer -= pointer
            matrix.setAll(result.solutions[pointer].toList())
        }
    }

}


class PageContext() {
    val statsProperty = SimpleSetProperty(this, Stats.SOLUTIONS_NUMBER.kind, observableSetOf("NOT COMPUTED YET"))
}




