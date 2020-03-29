package logic.models

data class Problem(val id: Int, val difficulty: Float, val matrix: Array<IntArray>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Problem

        if (id != other.id) return false
        if (difficulty != other.difficulty) return false
        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + difficulty.hashCode()
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }
}