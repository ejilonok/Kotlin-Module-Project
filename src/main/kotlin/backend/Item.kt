package backend
open class Item (
    val title : String
) {
    override fun toString(): String {
        return title
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Item

        return title == other.title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
