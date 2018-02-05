package HomeWork3;
/**
 * Сыпченко Алексей
 * ДЗ №3
 * <p>
 * 1. Попробовать оптимизировать пузырьковую сортировку.
 * Сравнить количество операций сравнения оптимизированной и не оптимизированной программы.
 * Написать функции сортировки, которые возвращают количество операций.
 * <p>
 * 2. *Реализовать шейкерную сортировку.
 * <p>
 * 3. Реализовать бинарный алгоритм поиска в виде функции,
 * которой передается отсортированный массив.
 * Функция возвращает индекс найденного элемента или -1, если элемент не найден.
 * <p>
 * 4. *Подсчитать количество операций для каждой из сортировок и
 * сравнить его с асимптотической сложностью алгоритма.
 */

import java.util.Arrays;
import java.util.Scanner;

public class HomeWork3 {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
// реализую выбор заданий
        HomeWork3 homeWork3 = new HomeWork3();
        int sel = 0;
        do {
            homeWork3.menu();
            sel = homeWork3.scanner.nextInt();
            switch (sel) {
                case 1: {
                    homeWork3.solution1();
                    break;
                }
                case 2: {
                    homeWork3.solution2();
                    break;
                }
                case 3: {
                    homeWork3.solution3();
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

    // пузырьковая улучшенная и обычная
    public void solution1() {
        int arr[] = new int[]{3, 6, 9, 13, 16, 18, 28, 2, 17, 24, 16, 37, 24, 4, 11};
        int arr2[] = new int[]{3, 6, 9, 13, 16, 18, 28, 2, 17, 24, 16, 37, 24, 4, 11};
        // улучшенная пузырьковая сортировка
        int counter = 0; // счетчик операций
        long start = System.nanoTime(); // засекаем время
        int change; // счетчик перестановок
        do {
            change = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                counter++;
                if (arr[i] > arr[i + 1]) {
                    int max = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = max;
                    change++;
                }
            }
        } while (change > 0);
        long stop = System.nanoTime();
        System.out.println(Arrays.toString(arr) + " улучшенная пузырьковая сортировка - длительность = " + (stop - start));
        System.out.println("Операций выполнено: " + counter);
        // обычная пузырьковая сортировка
        counter = 0; // счетчик операций
        start = System.nanoTime(); // засекаем время
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = 0; j < arr2.length - 1; j++) {
                counter++;
                if (arr[j] > arr[j + 1]) {
                    int max = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = max;
                }
            }
        }
        stop = System.nanoTime();
        System.out.println(Arrays.toString(arr) + " обычная пузырьковая сортировка - длительность = " + (stop - start));
        System.out.println("Операций выполнено: " + counter);
    }

    // шейкерная сортировка
    public void solution2() {
        int arr[] = new int[]{3, 6, 9, 13, 16, 18, 28, 2, 17, 24, 16, 37, 24, 4, 11};
        int right = arr.length - 1; // правая граница
        int left = 0; // левая граница
        int max;
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(arr));
        int counter = 0; // счетчик операций
        long start = System.nanoTime(); // засекаем время
        do {
            for (int i = left; i < right; i++) {
                counter++;
                if (arr[i] > arr[i + 1]) {
                    max = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = max;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                counter++;
                if (arr[i] < arr[i - 1]) {
                    max = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = max;
                }
            }
            left++;
        } while (left < right);
        long stop = System.nanoTime();
        System.out.println("Шейкерная сортировка - длительность = " + (stop - start));
        System.out.println("Итог: " + Arrays.toString(arr));
        System.out.println("Операций выполнено: " + counter);
    }

    // бинарный алгоритм поиска в виде функции, которой передается отсортированный массив
    public void solution3() {
        int arr[] = new int[100];
        // инициализируем массив
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        //передаем в функцию finder массив
        System.out.println(finder(arr));
    }

    public String finder(int[] arr) {
// границы диапазона поиска
        int right = arr.length - 1;
        int left = 0;

        System.out.println("Какое число будем искать?");
        int x = scanner.nextInt();
        int border = left + (right - left) / 2;
        int counter = 0;
        while (left <= right && x != arr[border]) {
            if (x < arr[border]) {
                right = border - 1;
            } else if (x > arr[border]) {
                left = border + 1;
            }
            //border = left + (right - left) / 2;
            // формула выше приводит к ошибке, если мы ищем число больше, чем имеющиеся в массиве
            border = (left + (right - left) / 2) != arr.length ? (left + (right - left) / 2) : (left + (right - left) / 2 - 1);
            counter++;
        }
        if (x == arr[border])
            return "искомое число " + x + " имеет индекс " + border + ". Операций " + counter;
        else return "-1" + " Операций " + counter;
    }
}

