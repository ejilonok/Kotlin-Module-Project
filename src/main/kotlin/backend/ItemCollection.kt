package backend

open class ItemCollection (
    title : String,
    val items : MutableSet<Item> = mutableSetOf()
) : Item(title) {
    fun addItem(item : Item) : Boolean {
        return items.add(item)
    }

    fun availableTitle(title : String) : Boolean {
        if (title.isBlank()) return false
        return !items.contains(Item(title))
    }

    override fun toString(): String {
        return "Коллекция \"$title\" {$items}"
    }
}
