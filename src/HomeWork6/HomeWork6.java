package HomeWork6;
/**
 * Сыпченко Алексей
 * ДЗ №6
 *
 * 1. Реализовать простейшую хэш-функцию.
 * На вход функции подается строка, на выходе сумма кодов символов.
 * <
 * 2. *Разработать базу данных студентов из трёх полей “Имя”, “Возраст”, “Табельный номер”,
 * в которой использовать все знания, полученные на уроках.
 * Табельный номер - это просто номер студента при считывании из файла.
 * а) считайте данные в двоичное дерево поиска с ключом в виде хэш-функции по имени студента.
 * Реализуйте поиск по какому-нибудь полю базы данных, например возраст или номер
 */

import java.io.*;
import java.util.Scanner;

public class HomeWork6 {
    Scanner scanner = new Scanner(System.in);
    final String FILE_PATH = "src\\HomeWork6\\students_1.csv";
    final File file = new File(FILE_PATH);

    public static void main(String[] args) {
        HomeWork6 homeWork6 = new HomeWork6();
        int sel = 0;
        do {
            homeWork6.menu();
            sel = homeWork6.scanner.nextInt();
            switch (sel) {
                case 1: {
                    homeWork6.solution1();
                    break;
                }
                case 2: {
                    homeWork6.solution2();
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
        for (int i = 1; i < 3; i++) {
            System.out.println(i + " task");
        }
        System.out.println("0 - exit");
    }

    // 1. Реализовать простейшую хэш-функцию.
    // На вход функции подается строка, на выходе сумма кодов символов.
    public void solution1() {
        String s;
        int md5Int = 0;
        char c[], md5 = '\u0000';
        System.out.println("Введите строку:");
        s = scanner.next();
        c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) { // разбиваю строку на символы
            c[i] = s.charAt(i);
            md5Int += (int) s.charAt(i);
            System.out.println("Код символа " + c[i] + " " + (int) c[i]);
        }
        System.out.println("Сумма кодов символов = " + md5Int);
    }

    // 2. *Разработать базу данных студентов из трёх полей “Имя”, “Возраст”, “Табельный номер”,
// в которой использовать все знания, полученные на уроках.
// Табельный номер - это просто номер студента при считывании из файла.
    //а) считайте данные в двоичное дерево поиска с ключом в виде хэш-функции по имени студента.
    // Реализуйте поиск по какому-нибудь полю базы данных, например возраст или номер
    public void solution2() {
// массив из студентов (см. урок java 1 урок 5 и 7)
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";"; // разделитель в файле
        int ageForSearch = 0;
        List ml = new List();
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                // разбиваю считанную строку на слова
                String[] studentFromFile = line.split(cvsSplitBy);
//                  Структура файла:
//                [0] - firstName
//                [1] - secondName
//                [2] - university
//                [3] - faculty
//                [4] - department
//                [5] - age
//                [6] - course
//                [7] - group
//                [8] - city
                try {
                    try {
//                        String str = studentFromFile[0]+studentFromFile[1];
//                        System.out.println(str.hashCode()); // получаю хэшкод для ФИО
                        // делаю односвязанный список
                        ml.addBack(studentFromFile[0], studentFromFile[1], Integer.parseInt(studentFromFile[5].toString()));
                    } catch (OutOfMemoryError e) {
                        System.out.println("Не хватает памяти");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Первая строка в файле содержит заголовки столбцов. Далее все будет хорошо.");
                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

// поиск по возрасту
        System.out.println("Введите возраст студента:");
        ageForSearch = scanner.nextInt();
        ml.search(ageForSearch);
    }
}