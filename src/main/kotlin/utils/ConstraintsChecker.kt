package utils

class ConstraintsChecker {

    fun check(value: Int, row: Int, column: Int, matrix: Array<IntArray>): Boolean {
        val rowCheck = !matrix[row].contains(value)
        val columnCheck = !intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8).map { matrix[it][column] }.contains(value)
        val squareCheck = !squareArrayCreator(row, column, matrix).contains(value)
        return rowCheck && columnCheck && squareCheck
    }

    private fun squareArrayCreator(row: Int, column: Int, matrix: Array<IntArray>): IntArray {
        val x = row / 3
        val y = column / 3
        val rows = ((3 * x)..(3 * x + 2)).map { matrix[it] }
        return rows.map { r -> ((3 * y)..(3 * y + 2)).map { r[it] } }.flatten().toIntArray()
    }
}