//import logic.algorithms.BacktrackingAlgorithm
//import logic.heuristics.value.ValueInSequenceHeuristic
//import logic.heuristics.variable.VariableInSequenceHeuristic
//import logic.utils.ConstraintsChecker
//import logic.utils.FileIO
import gui.MyApp
import tornadofx.launch
//
fun main() {
//
//    val fileIO = FileIO()
//    val problems = fileIO.load("src/main/resources/Sudoku.csv")
//    val constraintsChecker = ConstraintsChecker()
//    val backtrackingAlgorithm = BacktrackingAlgorithm(
//        constraintsChecker = constraintsChecker,
//        variableHeuristic = VariableInSequenceHeuristic(),
//        valueHeuristic = ValueInSequenceHeuristic()
//    )
//    val results = problems.map { problem ->
//        backtrackingAlgorithm.run(problem)
//    }
//
    launch<MyApp>()
//
//    fileIO.write("src/main/resources/results.csv", results)
}