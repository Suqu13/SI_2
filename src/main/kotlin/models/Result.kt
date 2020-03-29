package models

import kotlin.properties.Delegates

data class Result(private val problemId: Int, private val difficulty: Float) {
    private var startTime by Delegates.notNull<Long>()
    private var timeToFirstSolution = 0L
    private var nodesNumberToFirstSolution = 0L
    private var totalNodesNumber = 0L
    private var totalTime = 0L
    private val solutions: ArrayList<Array<IntArray>> = ArrayList()


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
        }
    }

    fun incrementTotalNodesNumber() {
        totalNodesNumber += 1
    }

    fun stopWatching() {
        totalTime = System.currentTimeMillis() - startTime
    }

    fun showStatistics() {
        println("\nProblem ID: $problemId")
        println("Difficulty: $difficulty")
        println("Time to first solution: $timeToFirstSolution")
        println("Nodes number to first solution: $nodesNumberToFirstSolution")
        println("Total time: $totalTime")
        println("Total nodes number: $totalNodesNumber")
        println("Solutions number: ${solutions.size}\n")
    }

    fun prepareResultToWriting(): ArrayList<String> {
        val resultList = arrayListOf(
            "Problem ID: $problemId",
            "Difficulty: $difficulty",
            "Time to first solution: $timeToFirstSolution",
            "Nodes number to first solution: $nodesNumberToFirstSolution",
            "Total time: $totalTime",
            "Total nodes number: $totalNodesNumber",
            "Solutions number: ${solutions.size}",
            "Solutions: "
        )
        resultList.addAll(solutions.map { solution -> solution.map { row -> row.map { "$it" }}.joinToString(prefix = "[", postfix = "]") })
        return resultList
    }
}