package text_interface

import backend.*
import java.util.Scanner

class TICollectionView (
    private val collection: ItemCollection = Library(),
    scanner: Scanner = Scanner(System.`in`)
) : TIMenu(1, scanner) {
    override fun printMenu() {
        println("\n > ${collection.title} <")
        var createText = "0. Создать"
        var prevItemText = ""
        when (collection) {
            is Library -> {
                createText = "$createText новый архив"
                prevItemText = "Архив"
            }
            is Archive -> {
                createText = "$createText новую заметку"
                prevItemText = "Заметка"
            }
        }

        println(createText)
        var index = 1
        for (item in collection.items) {
            println("$index. $prevItemText \"${item.title}\"")
            ++index
        }
        println("$index. Выход")
        calcMenuMaxNum()
    }

    private fun calcMenuMaxNum() {
        menuMaxNum = 1 + collection.items.size
    }
    override fun startAction(index : Int) {
        when (index) {
            0 -> {
                addItem()
                calcMenuMaxNum()
            }
            else -> {
                when (collection) {
                    is Library -> TICollectionView(collection.items.elementAt(index-1) as ItemCollection, scanner).start()
                    is Archive -> TINoteView(collection.items.elementAt(index-1) as Note, scanner).start()
                }
            }
        }
    }

    private fun addItem() {
        when (collection) {
            is Archive -> {
                // в данном контекте использование универсального метода крайне удобно
                val title = TIUtils.getCorrectText(scanner,
                    "Введите название новой заметки. Обратите внимание, что ввод учитывает регистр\n> ",
                    {str -> collection.availableTitle(str)},
                    {str -> if (str.isBlank()) "Нельзя создавать заметки с пустым названием." else "Заметка с названием \"$str\" уже существует."})

                val content = TIUtils.getCorrectText(scanner,
                    "Введите текст заметки\n> ",
                    {str -> str.isNotBlank()},
                    {str -> "Заметку с пустым содержанием создать нельзя."})

                collection.addItem(Note(title, content))
            }
            is Library -> {
                val title = TIUtils.getCorrectText(scanner,
                    "Введите название нового архива. Обратите внимание, что ввод учитывает регистр\n> ",
                    {str -> collection.availableTitle(str)},
                    {str -> if (str.isBlank()) "Нельзя создавать архивы с пустым названием." else "Архив с названием \"$str\" уже существует."})

                collection.addItem(Archive(title))
            }
        }
    }
}
