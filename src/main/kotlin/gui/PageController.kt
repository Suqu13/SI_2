package gui

import Runner
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import logic.models.Stats
import logic.utils.FileIO
import tornadofx.Controller
import tornadofx.ItemViewModel


class PageController: Controller() {
    private val fileIO = FileIO()
    private val runner = Runner()
    private val problems = fileIO.load("src/main/resources/Sudoku.csv")

    fun getProblemsIds() = problems.map { it.id }

}


class PageContext() {

//    val id = SimpleIntegerProperty(this,Stats.PROBLEM_ID.kind, -1)


    val problemIdProperty = SimpleIntegerProperty(this,Stats.PROBLEM_ID.kind, -1)
    val timeToFirstSolutionProperty = SimpleLongProperty(this, Stats.TIME_TO_FIRST_SOLUTION.kind, -1)
    val nodesNumberToFirstSolutionProperty = SimpleLongProperty(this, Stats.NODES_NUMBER_TO_FIRST_SOLUTION.kind, -1)
    val returnsNumberToFirstSolutionProperty = SimpleLongProperty(this, Stats.RETURNS_NUMBER_TO_FIRST_SOLUTION.kind, -1)
    val totalNodesNumberProperty = SimpleLongProperty(this, Stats.TOTAL_RETURNS_NUMBER.kind, -1)
    val totalReturnsNumberProperty = SimpleLongProperty(this, Stats.TOTAL_RETURNS_NUMBER.kind, -1)
    val solutionsNumberProperty = SimpleLongProperty(this, Stats.SOLUTIONS_NUMBER.kind, -1)
}

class PageContextModel(pageContext: PageContext): ItemViewModel<PageContext>(pageContext) {
    val id = bind(PageContext::problemIdProperty)
    val problemId = bind(PageContext::problemIdProperty)
    val timeToFirstSolution = bind(PageContext::timeToFirstSolutionProperty)
    val nodesNumberToFirstSolution = bind(PageContext::nodesNumberToFirstSolutionProperty)
    val returnsNumberToFirstSolution= bind(PageContext::returnsNumberToFirstSolutionProperty)
    val totalNodesNumber = bind(PageContext::totalNodesNumberProperty)
    val totalReturnsNumber = bind(PageContext::totalReturnsNumberProperty)
    val solutionsNumber = bind(PageContext::solutionsNumberProperty)
}



