package gui

import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList

object TemplateProvider {
    val algorithms: ObservableList<String> = observableArrayList(
        "Backtracking"
    )
    val variableHeuristics: ObservableList<String> = observableArrayList(
        "In Sequence"
    )

    val valueHeuristics: ObservableList<String> = observableArrayList(
        "In Sequence"
    )
}
