package utils

class ConstraintsChecker {

    fun check(value: Int, row: Int, column: Int, matrix: Array<IntArray>): Boolean =
        checkRow(value, row, matrix) && checkColumn(value, column, matrix) && checkSquare(value, row, column, matrix)

    private fun checkRow(value: Int, row: Int, matrix: Array<IntArray>): Boolean = !matrix[row].contains(value)

    private fun checkColumn(value: Int, column: Int, matrix: Array<IntArray>): Boolean =
        !(0..8).any { matrix[it][column] == value }

    private fun checkSquare(value: Int, row: Int, column: Int, matrix: Array<IntArray>): Boolean {
        val x = (row / 3) * 3
        val y = (column / 3) * 3
        val rows = IntArray(3) { x + it }.map { matrix[it] }
        return !rows.any { r -> IntArray(3) { y + it }.any { r[it] == value } }
    }
}