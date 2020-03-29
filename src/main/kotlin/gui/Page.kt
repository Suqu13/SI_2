package gui

import Runner
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.scene.text.TextFlow
import logic.models.Result
import logic.utils.FileIO
import tornadofx.*

class Page : View() {
    val pageController: Controller by inject()

    private val fileIO = FileIO()
    private val problems = fileIO.load("src/main/resources/Sudoku.csv")
    private val runner = Runner()

    private val ids = problems.map { it.id }.asObservable()

    private val matrix = (0..8).map { IntArray(9) { 0 } }.toTypedArray()
    private var pointer = 0
    private lateinit var result: Result

    private var currentId = -1
    private var currentAlgorithm = ""
    private var currentVariableHeuristic = ""
    private var currentValueHeuristic = ""

    private var valid = false



    private fun onValueHeuristicChange(valueHeuristic: String) {
        currentValueHeuristic = valueHeuristic
    }

    private fun onVariableHeuristicChange(variableHeuristic: String) {
        currentVariableHeuristic = variableHeuristic
    }

    private fun onAlgorithmChange(algorithm: String) {
        currentAlgorithm = algorithm
    }

    private fun onIdChange(id: String) {
        currentId = id.toInt()
    }


    private fun onClickEvaluate() {
        if (currentId != -1 && currentAlgorithm != "" && currentValueHeuristic != "" && currentVariableHeuristic != "") {
            result = runner.execute(
                problems.first { it.id == currentId },
                currentValueHeuristic,
                currentVariableHeuristic,
                currentAlgorithm
            )
            valid = true
        } else {
            valid = false
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
            sudokuTableBuilder(if (::result.isInitialized) result.solutions[pointer] else matrix)
            controlButtonsBuilder()
        }
        center = contextBuilder()
    }

    private fun EventTarget.sudokuTableBuilder(matrix: Array<IntArray>): GridPane = gridpane {
        alignment = Pos.CENTER
        matrix.mapIndexed { i, m ->
            row {
                m.mapIndexed { j, v ->
                    button(if (v != -1) v.toString() else "") {
                        addClass(Styles.element)
                        style {
                            textFill = if (v == matrix[i][j]) c("#ffd31d") else Color.WHITE
                        }
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

    private fun EventTarget.formBuilder(): Form {
        val onClick = {
            onClickEvaluate()
        }


        return form {
            vbox {
                customComboBoxBuilder("Sudoku ID", ids) { value -> onIdChange(value) }
                customComboBoxBuilder("Algorithm", TemplateProvider.algorithms) { value -> onAlgorithmChange(value) }
                customComboBoxBuilder("Variable heuristics", TemplateProvider.variableHeuristics) { value ->
                    onVariableHeuristicChange(
                        value
                    )
                }
                customComboBoxBuilder("Value heuristics", TemplateProvider.valueHeuristics) { value -> onValueHeuristicChange(value) }
                customButtonBuilder("Evaluate!", onClick)
                vboxConstraints {
                    margin = Insets(100.0, 100.0, 0.0, 100.0)
                }
            }
        }
    }

    private fun EventTarget.messageBuilder(label: String, text: String): TextFlow = textflow {
        text("$label: ") {
            fill = Styles.white
            style {
                fontWeight = FontWeight.findByWeight(700)
            }
        }
        text(text) {
            fill = Styles.white
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
            messageBuilder("Sudoku ID", "1")
            messageBuilder("Time to first solution [ms]", "1")
            messageBuilder("Nodes number to first solution", "1")
            messageBuilder("Returns number to first solution", "1")
            messageBuilder("Total time [ms]", "1")
            messageBuilder("Total nodes number", "1")
            messageBuilder("Total return number", "1")
            messageBuilder("Solutions number", "1")
            vboxConstraints {
                margin = Insets(20.0, 120.0, 0.0, 120.0)
            }
        }
    }

    private fun EventTarget.controlButtonsBuilder(): HBox {
        return hbox {
            alignment = Pos.CENTER
            button("Next") {
                addClass(Styles.customControllButton)
                hboxConstraints {
                    margin = Insets(10.0)
                }
            }
            button("Previous") {
                addClass(Styles.customControllButton)
                hboxConstraints {
                    margin = Insets(10.0)
                }
            }
        }
    }
}



