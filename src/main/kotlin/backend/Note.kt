package backend

class Note (
    title : String,
    var content : String
) : Item(title) {
    override fun toString () : String {
        return "Заметка $title {$content}"
    }
}
