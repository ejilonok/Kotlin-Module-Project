package text_interface

import java.util.Scanner

abstract class TIMenu (
    protected var menuMaxNum : Int = 1,
    protected val scanner: Scanner
) {
    abstract fun printMenu()
    abstract fun startAction(index : Int)

    protected fun getMenuIdx() : Int {
        val result: String = TIUtils.getCorrectText(scanner, "\nВведите пункт меню > ",
            { str ->
                val num: Int? = str.toIntOrNull()
                if (num != null) ((num <= menuMaxNum) && (num >= 0)) else false
            },
            { str ->
                val num: Int? = str.toIntOrNull()
                if (num == null) "Введите число." else "Число должно быть от 0 до $menuMaxNum."
            })

        return result.toIntOrNull() as Int
//        Ниже - Альтернативный код без использования лямбда под более специфическую задачу ввода меню, эту реализацию оставляю в комметариях,
//        так как преобразование введенной строки в число производится 1 раз вместо 3, конечно, в данной задаче это не принципиально.
//          Так как задание сформулировано, что аналогичная логика не должна использоваться повторно, оставляю используемым вариант выше.

//        print("Введите пункт меню > ")
//        var num : Int? = scanner.nextLine().toIntOrNull()
//        var reason : String
//        while ( if (num != null) ((num > menuMaxNum) || (num < 0)) else true ) {
//            reason = if (num == null) "Введите число." else "Число должно быть от 0 до $menuMaxNum."
//            print("Вы ввели некорректный пункт меню. $reason Повторите ввод > ")
//            num = scanner.nextLine().toIntOrNull()
//        }
//
//        return num as Int
    }

    open fun start() {
        var index : Int

        while (true) {
            printMenu()
            index = getMenuIdx()

            if (index == menuMaxNum) return
            startAction(index)
        }
    }
}
