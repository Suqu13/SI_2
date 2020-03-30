package gui

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.scene.text.TextFlow
import tornadofx.*

class Page : View() {
    private val pageController: PageController by inject()
    private val pageContext = PageContext()

    private var currentId = -1
    private var currentAlgorithm = ""
    private var currentVariableHeuristic = ""
    private var currentValueHeuristic = ""

    private fun onClickEvaluate() {
        if (currentId != -1 && currentAlgorithm != "" && currentValueHeuristic != "" && currentVariableHeuristic != "") {
            pageController.resolveProblem(
                pageContext,
                currentId,
                currentValueHeuristic,
                currentVariableHeuristic,
                currentAlgorithm
            )
        }
    }

    override val root = borderpane {
        addClass(Styles.page)
        setPrefSize(1440.0, 900.0)
        left = vbox {
            style {
                paddingBottom = 100
                paddingTop = 100
                paddingLeft = 100
            }

            sudokuTableBuilder(pageController)
            controlButtonsBuilder(
                pageContext = pageContext,
                next = { pageController.next(pageContext) },
                previous = { pageController.previous(pageContext) })
        }
        center = contextBuilder()
    }

    private fun EventTarget.sudokuTableBuilder(pageController: PageController): GridPane = gridpane {
        alignment = Pos.CENTER
        pageController.initialMatrix.map { m ->
            row {
                m.map { _ ->
                    button("") {
                        addClass(Styles.element)
                        style {
                            textFill = Color.WHITE
                        }
                    }
                }
            }
        }
        pageController.matrix.onChange {
            pageController.matrix.forEachIndexed { i, m ->
                row {
                    m.forEachIndexed { j, v ->
                        val cell = button(if (v != -1) v.toString() else "") {
                            addClass(Styles.element)
                            style {
                                textFill = if (v == pageController.initialMatrix[i][j]) c("#ffd31d") else Color.WHITE
                            }
                        }
                        add(cell, i, j)
                    }
                }
            }
        }
    }


    private fun <T> EventTarget.customComboBoxBuilder(
        label: String,
        options: ObservableList<T>,
        onChange: (value: String) -> Unit
    ): VBox =
        vbox {
            label(label) {
                style {
                    textFill = Styles.white
                    fontWeight = FontWeight.findByWeight(700)
                }
            }
            combobox(values = options) {
                addClass(Styles.customComboBox)
                setOnAction {
                    onChange(value.toString())
                }
            }
            vboxConstraints {
                margin = Insets(0.0, 10.0, 20.0, 10.0)
            }
        }

    private fun EventTarget.customButtonBuilder(label: String, onClick: () -> Unit): VBox =
        vbox {
            button(label) {
                style {
                    alignment = Pos.CENTER
                }
                addClass(Styles.customButton)
                action { onClick() }
            }
            vboxConstraints {
                margin = Insets(10.0, 10.0, 10.0, 10.0)
            }
        }

    private fun EventTarget.contextBuilder(): VBox {
        return vbox {
            formBuilder()
            resultMessagesBuilder()
        }
    }

    private fun EventTarget.formBuilder(): Form = form {
        vbox {
            customComboBoxBuilder("Sudoku ID", pageContext.problemsIdsProperty) { value ->
                {
                    currentId = value.toInt()
                }()
            }
            customComboBoxBuilder("Algorithm", pageContext.algorithmsProperty) { value ->
                {
                    currentAlgorithm = value
                }()
            }
            customComboBoxBuilder(
                "Variable heuristics",
                pageContext.variableHeuristicsProperty
            ) { value -> { currentVariableHeuristic = value }() }
            customComboBoxBuilder(
                "Value heuristics",
                pageContext.valueHeuristicsProperty
            ) { value -> { currentValueHeuristic = value }() }
            customButtonBuilder("Evaluate!") { onClickEvaluate() }
            vboxConstraints {
                margin = Insets(100.0, 100.0, 0.0, 100.0)
            }
        }
    }


    private fun EventTarget.statsBuilder(label: SimpleStringProperty): TextFlow = textflow {
        text(label) {
            fill = Styles.white
            style {
                fontWeight = FontWeight.findByWeight(700)
            }
        }
    }


    private fun EventTarget.resultMessagesBuilder(): VBox {
        return vbox {
            style {
                fontSize = 18.px
            }
            text("Result") {
                fill = Styles.orange
                style {
                    fontWeight = FontWeight.findByWeight(700)
                    fontSize = 25.px
                }
            }
            statsBuilder(pageContext.statsProperty)
            vboxConstraints {
                margin = Insets(20.0, 120.0, 0.0, 120.0)
            }
        }
    }

    private fun EventTarget.controlButtonsBuilder(
        pageContext: PageContext,
        next: () -> Unit,
        previous: () -> Unit
    ): VBox {
        return vbox {
            alignment = Pos.CENTER
            hbox {
                alignment = Pos.CENTER
                button("Previous") {
                    addClass(Styles.customControllButton)
                    hboxConstraints {
                        margin = Insets(10.0)
                    }
                    action(previous)
                }
                button("Next") {
                    addClass(Styles.customControllButton)
                    hboxConstraints {
                        margin = Insets(10.0)
                    }
                    action(next)

                }
            }
            hbox {
                alignment = Pos.CENTER
                text("Current solution number: ") {
                    fill = Styles.white
                }
                label(pageContext.pointerProperty + 1) {
                    style {
                        textFill = Styles.white
                        fontWeight = FontWeight.findByWeight(700)
                    }
                }
            }

        }
    }
}



