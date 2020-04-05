package logic.heuristics.variable

class VariableRestrictedDomainHeuristic : VariableHeuristic {
    override fun findVariable(matrix: Array<IntArray>, row: Int, column: Int): Pair<Int, Int> {
        val filledValuesPerSquare = countFilledValuesPerSquare(matrix)
        val flattenedFilledValuesPerSquare = mutableListOf<Int>()
        filledValuesPerSquare.flatMapTo(flattenedFilledValuesPerSquare, { it.asList() })

        if (flattenedFilledValuesPerSquare.min() == 9) return Pair(9, 0)
        val (x, y) = findSquareWithTheGreatestValueCoverage(filledValuesPerSquare, flattenedFilledValuesPerSquare)


        IntArray(3) { x + it }.forEach { rowIndex ->
            IntArray(3) { y + it }.forEach{ colIndex->
                if (matrix[rowIndex][colIndex] == -1)
                    return Pair(rowIndex, colIndex)
            }
        }
        return Pair(9, 0)
    }

    private fun findSquareWithTheGreatestValueCoverage(
        filledValuesPerSquare: List<IntArray>,
        flattenedFilledValuesPerSquare: MutableList<Int>
    ): Pair<Int, Int> {
        val maxNumberOfFilledFields = flattenedFilledValuesPerSquare.filter { it < 9 }.max() as Int
        var square: Pair<Int, Int> = Pair(0, 0)
        filledValuesPerSquare.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, it ->
                if (it >= maxNumberOfFilledFields) square = Pair(rowIndex, colIndex)
            }
        }
        return square
    }

    private fun countFilledValuesPerSquare(matrix: Array<IntArray>): List<IntArray> {
        val filledValuesPerSquare: Array<IntArray> = Array(3) { IntArray(3) }
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, value ->
                if (value != 0) filledValuesPerSquare[rowIndex / 3][colIndex / 3]++
            }
        }
        return filledValuesPerSquare.toList()
    }
}