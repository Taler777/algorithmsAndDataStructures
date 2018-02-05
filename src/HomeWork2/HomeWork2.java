package HomeWork2;

import java.util.Scanner;

public class HomeWork2 {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
// реализую выбор заданий
        HomeWork2 homeWork2 = new HomeWork2();
        int sel = 0;
        do {
            homeWork2.menu();
            sel = homeWork2.scanner.nextInt();
            switch (sel) {
                case 1: {
                    homeWork2.solution1();
                    break;
                }
                case 2: {
                    homeWork2.solution2();
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

    // Task1. Реализовать функцию перевода из 10 системы в двоичную используя рекурсию.
    public void solution1() {
        int number;
        System.out.println("Введите число. Программа преобразует его в двоичное");
        number = scanner.nextInt();
        convertToBin(number);
    }

    public void convertToBin(int number) {
        int remainder = 0;
        if (number > 0) {
            remainder = number % 2; // нахожу остаток от деления на 2
            number /= 2; // нахожу частное от деления number на 2
            convertToBin(number); // рекусия
            System.out.print(remainder); // печатаю остаток от деления в обратном порядке по мере выхода из рекурсии
        }
        //короче
        //формулы запихнуть

    }

    //  Реализовать функцию возведения числа a в степень b:
//    a. без рекурсии;
//    b. рекурсивно;
//    c. *рекурсивно, используя свойство чётности степени.
    public void solution2() {
        // вариант а - без рекурсии
        int a, b, result = 1;
        System.out.println("Введите число a:");
        a = scanner.nextInt();
        System.out.println("Введите степень b, в которую возведем a:");
        b = scanner.nextInt();
        for (int i = 1; i <= b; i++) {
            result = result * a;
        }
        System.out.println("Обычный алгоритм: " + a + " в степени " + b + " равно " + result);

        // вариант b - рекурсивно
        System.out.println("Введите число a:");
        a = scanner.nextInt();
        System.out.println("Введите степень b, в которую возведем a:");
        b = scanner.nextInt();
        System.out.println("Рекурсивный алгоритм: " + a + " в степени " + b + " равно " + multiply(a, b));

        // *рекурсивно, используя свойство чётности степени
        System.out.println("Введите число a:");
        a = scanner.nextInt();
        System.out.println("Введите степень b, в которую возведем a:");
        b = scanner.nextInt();
        System.out.println("Рекурсивно с учетом четности степени b: " + a + " в степени " + b + " равно " + multiply2(a, b));
    }

    int multiply(int a, int b) {
        int result = 1;
        while (b > 0) {
            result *= a;
            b--;
            multiply(result, b);
            // можно короче если вычисления разместить как параметры при рекурсивном вызове
        }
        return result;
    }

    int multiply2(int a, int b) {
        int result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result *= a;
                b--;
            } else {
                a *= a;
                b /= 2;
            }
            multiply(result, b);
        }
        return result;
    }

    public void menu() {
        System.out.println();
        for (int i = 1; i < 3; i++) {
            System.out.println(i + " task");
        }
        System.out.println("0 - exit");
    }
}
