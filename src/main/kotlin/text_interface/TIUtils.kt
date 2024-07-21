package text_interface

import java.util.Scanner

class TIUtils {
    companion object {
        fun getCorrectText(
            scanner: Scanner,
            greetings: String,
            validation: (String) -> Boolean,
            exceptionText: (String) -> String = { str -> "Ошибка ввода."}
        ): String {
            print(greetings)
            var text = scanner.nextLine()
            while (!validation(text)) {
                print("${exceptionText(text)} Повторите ввод > ")
                text = scanner.nextLine()
            }

            return text
        }
    }
}