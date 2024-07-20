package backend

class Archive (
    title : String
) : ItemCollection(title) {
    override fun toString () : String {
        return "Архив $title {$items}"
    }
}
