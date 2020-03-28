import algorithms.BacktrackingAlgorithm
import heuristics.value.ValueInSequenceHeuristic
import heuristics.variable.VariableWithSmallestDomainHeuristic
import utils.FileLoader

fun main(args: Array<String>) {
    val fileLoader = FileLoader()
    val problems = fileLoader.load().take(5)
    val constraintResolver = ConstraintResolver()
    val backtrackingAlgorithm = BacktrackingAlgorithm(
        constraintResolver = constraintResolver,
        variableHeuristic = VariableWithSmallestDomainHeuristic(),
        valueHeuristic = ValueInSequenceHeuristic()
    )
    problems.forEach { problem ->
        val result = backtrackingAlgorithm.run(problem.variables)
        result.forEach { variable -> print(variable.value) }
        println("\n")
    }
    print(1)

}