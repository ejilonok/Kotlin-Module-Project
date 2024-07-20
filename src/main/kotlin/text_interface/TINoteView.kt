package text_interface

import backend.Note
import java.util.Scanner

class TINoteView (
    private val item : Note,
    scanner: Scanner
) : TIMenu(1, scanner) {
    override fun printMenu() {
        println("\nЗаметка \"${item.title}\"\nСодержание: \"${item.content}\"\n\n0. Ввести другой текст\n1. Выход")
    }

    override fun startAction(index: Int) {
        item.content = TIUtils.getCorrectText(scanner,
            "Введите новый текст заметки\n> ",
            {str -> str.isNotBlank()},
            {str -> "Заметку с пустым содержанием создать нельзя."})
    }

}
