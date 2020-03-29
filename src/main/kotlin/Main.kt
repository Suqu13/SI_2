import algorithms.BacktrackingAlgorithm
import heuristics.value.ValueInSequenceHeuristic
import heuristics.variable.VariableInSequenceHeuristic
import utils.ConstraintsChecker
import utils.FileLoader

fun main() {
    val fileLoader = FileLoader()
    val problems = fileLoader.load()
    val constraintsChecker = ConstraintsChecker()
    val backtrackingAlgorithm = BacktrackingAlgorithm(
        constraintsChecker = constraintsChecker,
        variableHeuristic = VariableInSequenceHeuristic(),
        valueHeuristic = ValueInSequenceHeuristic()
    )
    problems.forEach { problem ->
        val solutions = backtrackingAlgorithm.run(problem.matrix)
        solutions.map{ solution -> solution.forEach{ value -> value.forEach { print(it) }}; println("\n") }
    }
}