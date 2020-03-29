import algorithms.BacktrackingAlgorithm
import heuristics.value.ValueInSequenceHeuristic
import heuristics.variable.VariableInSequenceHeuristic
import utils.ConstraintsChecker
import utils.FileIO

fun main() {
    val fileIO = FileIO()
    val problems = fileIO.load("src/main/resources/Sudoku.csv")
    val constraintsChecker = ConstraintsChecker()
    val backtrackingAlgorithm = BacktrackingAlgorithm(
        constraintsChecker = constraintsChecker,
        variableHeuristic = VariableInSequenceHeuristic(),
        valueHeuristic = ValueInSequenceHeuristic()
    )
    val results = problems.map { problem ->
        backtrackingAlgorithm.run(problem)
    }

    fileIO.write("src/main/resources/results.csv", results)
}