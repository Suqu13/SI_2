package gui

import javafx.collections.ObservableList
import logic.models.Result
import logic.utils.FileIO
import tornadofx.*


class PageController: Controller() {
    private val fileIO = FileIO()
    private val runner = ExecutionRunner()
    var initialMatrix = (0..8).map { IntArray(9) { 0 }}.toTypedArray()
    var matrix: ObservableList<IntArray> = (0..8).map { IntArray(9) { 0 }}.asObservable()
    private lateinit var result: Result

    fun resolveProblem(pageContext: PageContext, problemId: Int, valueHeuristicsName: String, variableHeuristicsName: String, algorithmName: String) {
        val problem = pageContext.problems.first { it.id == problemId }
        result = runner.execute(problem, valueHeuristicsName, variableHeuristicsName, algorithmName)
        initialMatrix = problem.matrix
        try {
            matrix.setAll(result.solutions.first().toList().toList())

        } catch (e: Exception){
            matrix.setAll(initialMatrix.toList())
        }
        pageContext.pointerProperty.set(0)
        pageContext.statsProperty.set(result.prepareResultToWriting().take(9).joinToString(separator = "\n"))
        fileIO.write("src/main/resources/${problemId}_id_problem_solutions.csv", listOf(result))
    }

    fun next(pageContext: PageContext) {
        val pointer = pageContext.pointerProperty.get()
        if (::result.isInitialized && result.solutions.isNotEmpty() && pointer < result.solutions.size - 1 ){
            pageContext.pointerProperty.set(pointer + 1)
            matrix.setAll(result.solutions[pageContext.pointerProperty.get()].toList())
        }
    }

    fun previous(pageContext: PageContext) {
        val pointer = pageContext.pointerProperty.get()
        if (::result.isInitialized && result.solutions.isNotEmpty() && pointer > 0 ) {
            pageContext.pointerProperty.set(pointer - 1)
            matrix.setAll(result.solutions[pageContext.pointerProperty.get()].toList())
        }
    }
}




