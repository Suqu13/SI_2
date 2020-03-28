data class Variable(val column: Int, val row: Int, var value: Int, val domain: MutableSet<Int>) {
    val square = findSquare()

    private fun findSquare(): String {
        val x = if (column <= 3) 1 else if (column <= 6) 2 else 3
        val y = if (row <= 3) 1 else if (row <= 6) 2 else 3
        return "$x;$y"
    }

    fun chooseValueInSequence() {
        value = domain.first()
        domain.remove(value!!);
        domain.add(value!!)
    }
}