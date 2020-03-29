package logic.models

import kotlin.properties.Delegates

data class Result(private val problemId: Int, private val difficulty: Float) {
    private var startTime by Delegates.notNull<Long>()
    private var timeToFirstSolution = 0L
    private var nodesNumberToFirstSolution = 0L
    private var returnsNumberToFirstSolution = 0L
    private var totalNodesNumber = 0L
    private var totalTime = 0L
    private var totalReturnsNumber = 0L

    val solutions: ArrayList<Array<IntArray>> = ArrayList()


    fun startWatching() {
        startTime = System.currentTimeMillis()
    }

    fun watchForSolution(solution: Array<IntArray>) {
        solutions.add(solution)
        watchForFirstSolution()
    }

    private fun watchForFirstSolution() {
        if (solutions.size == 1) {
            timeToFirstSolution = System.currentTimeMillis() - startTime
            nodesNumberToFirstSolution = totalNodesNumber
            returnsNumberToFirstSolution = totalReturnsNumber
        }
    }

    fun incrementTotalNodesNumber() {
        totalNodesNumber += 1
    }

    fun incrementReturnsNumber() {
        totalReturnsNumber += 1
    }


    fun stopWatching() {
        totalTime = System.currentTimeMillis() - startTime
    }

    fun showStatistics() {
        println("\n${Stats.PROBLEM_ID.kind} $problemId")
        println("${Stats.DIFFICULTY.kind}: $difficulty")
        println("${Stats.TIME_TO_FIRST_SOLUTION.kind}: $timeToFirstSolution")
        println("${Stats.NODES_NUMBER_TO_FIRST_SOLUTION.kind}: $nodesNumberToFirstSolution")
        println("${Stats.RETURNS_NUMBER_TO_FIRST_SOLUTION.kind}: $returnsNumberToFirstSolution")
        println("${Stats.TOTAL_TIME.kind}: $totalTime")
        println("${Stats.TOTAL_NODES_NUMBER.kind}: $totalNodesNumber")
        println("${Stats.TOTAL_RETURNS_NUMBER.kind}: $totalReturnsNumber")
        println("${Stats.SOLUTIONS_NUMBER.kind}: ${solutions.size}\n")
    }

    fun prepareResultToWriting(): ArrayList<String> {
        val resultList = arrayListOf(
            "${Stats.PROBLEM_ID.kind} : $problemId",
            "${Stats.DIFFICULTY.kind}: $difficulty",
            "${Stats.TIME_TO_FIRST_SOLUTION.kind}: $timeToFirstSolution",
            "${Stats.NODES_NUMBER_TO_FIRST_SOLUTION.kind}: $nodesNumberToFirstSolution",
            "${Stats.RETURNS_NUMBER_TO_FIRST_SOLUTION.kind}: $returnsNumberToFirstSolution",
            "${Stats.TOTAL_TIME.kind}: $totalTime",
            "${Stats.TOTAL_NODES_NUMBER.kind}: $totalNodesNumber",
            "${Stats.TOTAL_RETURNS_NUMBER.kind}: $totalReturnsNumber",
            "${Stats.SOLUTIONS_NUMBER.kind}: ${solutions.size}",
            "${Stats.SOLUTIONS.kind}: "
        )
        resultList.addAll(solutions.map { solution ->
            solution.map { row -> row.map { "$it" } }.joinToString(prefix = "[", postfix = "]")
        })
        return resultList
    }
}