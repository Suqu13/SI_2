package gui

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import logic.utils.FileIO
import tornadofx.asObservable

class PageContext {
    private val fileIO = FileIO()
    val problems = fileIO.load("src/main/resources/Sudoku.csv")
    val problemsIdsProperty =  SimpleListProperty(problems.map { it.id }.asObservable())
    val pointerProperty = SimpleIntegerProperty(0)
    val statsProperty = SimpleStringProperty("NOT COMPUTED YET")
    val algorithmsProperty = SimpleListProperty(listOf(BACKTRACKING_ALGORITHM, FORWARD_CHECKING_ALGORITHM).asObservable())
    val variableHeuristicsProperty = SimpleListProperty(listOf(IN_SEQUENCE_VALUE_HEURISTIC, RANDOM_VALUE_HEURISTIC).asObservable())
    val valueHeuristicsProperty = SimpleListProperty(listOf(IN_SEQUENCE_VARIABLE_HEURISTIC, THE_MOST_RESTRICTED_DOMAIN_VARIABLE_HEURISTIC).asObservable())
}