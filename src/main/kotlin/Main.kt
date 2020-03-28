import algorithms.BacktrackingAlgorithm
import utils.FileLoader

fun main(args: Array<String>){
    val fileLoader = FileLoader()
    val problem = fileLoader.load().first()
    val constraintResolver = ConstraintResolver()
    val backtrackingAlgorithm = BacktrackingAlgorithm(constraintResolver = constraintResolver)
    val result = backtrackingAlgorithm.run(problem.variables)
    print(1);

}