package HomeWork5;
/**
 * Сыпченко Алексей
 * ДЗ №5
 *
 * 1. Реализовать перевод из 10 в 2 систему счисления с использованием стека.
 *
 * 2. Добавить в программу “реализация стека на основе односвязного списка” проверку на выделение памяти.
 * Если память не выделяется, то выводится соответствующее сообщение.
 * Постарайтесь создать ситуацию, когда память не будет выделяться (добавлением большого количества данных).
 *
 * 3. Написать программу, которая определяет, является ли введенная скобочная последовательность правильной.
 * Примеры правильных скобочных выражений: (), ([])(), {}(), ([{}]),
 * не
 * правильных — )(, ())({), (, ])}), ([(]) для скобок [,(,{.
 * Например: (2+(2*2)) или [2/{5*(4+7)}]
 */

import java.util.HashMap;
import java.util.Scanner;

public class HomeWork5 {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HomeWork5 homeWork5 = new HomeWork5();
        int sel = 0;
        do {
            homeWork5.menu();
            sel = homeWork5.scanner.nextInt();
            switch (sel) {
                case 1: {
                    homeWork5.solution1();
                    break;
                }
                case 2: {
                    homeWork5.solution2();
                    break;
                }
                case 3: {
                    homeWork5.solution3();
                    break;
                }
                case 0: {
                    System.out.println("Bye-bye");
                    break;
                }
                default:
                    System.out.println("Wrong selected");
            }
        } while (sel != 0);
    }

    public void menu() {
        System.out.println();
        for (int i = 1; i < 4; i++) {
            System.out.println(i + " task");
        }
        System.out.println("0 - exit");
    }

    // 1. Реализовать перевод из 10 в 2 систему счисления с использованием стека.
    public void solution1() {
        int number; // исходное число, которое вводит пользователь
        int z = 2; // система измерения, 2 - двоичная
        System.out.println("Введите число для перевода его из 10 в " + z + " систему счисления");
        number = scanner.nextInt();
        Stack st = new Stack(); // создаю объект класса Stack (стек)
        while (number > 0) {
            st.setNumber(number % z); //пишу в стек остаток от целочисленного деления
            number /= z;
        }
        // вывод из стека в консоль преобразованного числа
        st.printStack();
    }


    /**
     * 2. Добавить в программу “реализация стека на основе односвязного списка” проверку на выделение памяти.
     * Если память не выделяется, то выводится соответствующее сообщение.
     * Постарайтесь создать ситуацию, когда память не будет выделяться (добавлением большого количества данных).
     */
    public void solution2() {
        List ml = new List();
        try {
            for (int i = 0; i < 50; i++) {
                ml.addBack(i);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Не хватает памяти");
        }

        ml.printList();
        System.out.println();
    }

    /**
     * 3. Написать программу, которая определяет, является ли введенная скобочная последовательность правильной.
     * Примеры правильных скобочных выражений: (), ([])(), {}(), ([{}]),
     * не
     * правильных — )(, ())({), (, ])}), ([(]) для скобок [,(,{.
     * Например: (2+(2*2)) или [2/{5*(4+7)}]
     */
    public void solution3() {
        String str, s = "";
        int stackFull = 0; // 0 - стек пуст, >0 - непустой
        int flagError = 0; // флаг ошибки скобочной последовательности
        System.out.println("Введите скобочную последовательность, например []({})(");
        str = scanner.next();
        Stack stack = new Stack(); // будем пользоваться стеком
        HashMap<String, String> skobki = new HashMap(); // описываю коллекцию скобок
        skobki.put(")", "(");
        skobki.put("]", "[");
        skobki.put("}", "{");
        // разбираю введенную строку
        for (int i = 0; i < str.length() && flagError == 0; i++) {
            s += str.charAt(i); // посимвольно
            // если скобки открывающиеся, то пишем в стек
            if (s.equals("(") || s.equals("[") || s.equals("{")) {
                stack.setNumber(s);
                stackFull++;
            }
            // иначе - получаем из коллекции открывающую скобку, соответствующую закрывающей(ключ),
            // для сравнения со скобками из стека
            else if (s.equals(")") || s.equals("]") || s.equals("}")) {
                String s1 = skobki.get(s);
                Object s2 = stack.getNumber();
                stackFull--;
                if (!s1.equals(s2)) {
                    flagError = 1;
                }
            }
            s = "";
        }
        if (flagError == 0 && stackFull == 0) System.out.println("Скобочная последовательность правильная");
        if (stackFull != 0) System.out.println("Скобочная последовательность неправильная");
    }
}