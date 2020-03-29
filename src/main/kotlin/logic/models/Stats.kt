package logic.models

enum class Stats(val kind: String) {
    PROBLEM_ID( "Problem ID"),
    DIFFICULTY( "Difficulty"),
    TIME_TO_FIRST_SOLUTION( "Time to first solution"),
    NODES_NUMBER_TO_FIRST_SOLUTION( "Nodes number to first solution"),
    RETURNS_NUMBER_TO_FIRST_SOLUTION("Returns number to first solution"),
    TOTAL_TIME("Total time"),
    TOTAL_NODES_NUMBER("Total nodes number"),
    TOTAL_RETURNS_NUMBER("Total returns number"),
    SOLUTIONS_NUMBER("Solutions number"),
    SOLUTIONS("Solutions")
}