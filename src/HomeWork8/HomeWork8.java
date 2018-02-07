package HomeWork8;
/**
 * Сыпченко Алексей
 * ДЗ №8
 *
 * 1. Реализовать​ ​сортировку​ ​подсчетом.
 *
 * 2. Реализовать​ ​сортировку​ ​Шелла.
 *
 * 3. *Реализовать​ ​​ ​сортировку​ ​слиянием.
 *
 * 4. Сравнить результаты сортировки для массивов разного размера
 */

import java.util.*;

public class HomeWork8 {
    public final static int RANGE = 3; // варианты размера массива
    public static int array[][] = new int[RANGE][]; // исходник
    public static int a[][] = new int[RANGE][]; // для методов
    public static int size = 100;
    public final static int MAXNUMBER = 10000;
    public static long result[] = new long[9];
    public static int countOfResult = 0;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Random random = new Random();
        // генерация массива массивов на 100, 10000 и 1000000 элементов
        for (int i = 0; i < array.length; i++) {
            array[i] = new int[size];
            for (int j = 0; j < size; j++) {
                array[i][j] = random.nextInt(10000);
            }
            size *= 100;
        }

        HomeWork8 homeWork8 = new HomeWork8();
        int sel = 0;
        do {
            homeWork8.menu();
            sel = homeWork8.scanner.nextInt();
            switch (sel) {
                case 1: {
                    homeWork8.solution1();
                    break;
                }
                case 2: {
                    homeWork8.solution2();
                    break;
                }
                case 3: {
                    homeWork8.solution3();
                    break;
                }
                case 4: {
                    homeWork8.solution4();
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
        for (int i = 1; i < 5; i++) {
            System.out.println(i + " task");
        }
        System.out.println("0 - exit");
    }

    public void arrayPreparation() {
        size = 100;
        for (int i = 0; i < array.length; i++) {
            a[i] = new int[size];
            System.arraycopy(array[i], 0, a[i], 0, size);
            size *= 100;
        }
    }

    // 1. Реализовать​ ​сортировку​ ​подсчетом.
    public void solution1() {
        // делаем копию массива для этого метода
        arrayPreparation();
        // сортируем
        sortData();
    }

    public void sortData() {
        int arrayOfCount[][] = new int[RANGE][MAXNUMBER]; // массив подсчета повторений элементов коллекции
        for (int i = 0; i < a.length; i++) {
            long t = System.currentTimeMillis();
            for (int j = 0; j < a[i].length; j++) {
                arrayOfCount[i][a[i][j]]++;// считаем количество повторений чисел (возраста) в коллекции
            }
            // перезаполняем коллекцию согласно правилу индекс элемента массива подсчета это число из коллекции
            Arrays.fill(a[i], 0); // предварительно очищаем
            for (int k = 0; k < MAXNUMBER; k++) {
                if (arrayOfCount[i][k] != 0) {
                    for (int j = 0; j < arrayOfCount[i][k]; j++) {
                        a[i][j] = k;
                    }
                }
            }
            t = System.currentTimeMillis() - t;
            result[countOfResult] = t;
            countOfResult++;
            System.out.println("Время работы сортировки подсчетом - " + t);
        }
    }

    //  2. Реализовать​ ​сортировку​ ​Шелла.
    public void solution2() {
        // делаем копию массива для этого метода
        arrayPreparation();
        for (int f = 0; f < a.length; f++) {
            int d = a[f].length / 2; // шаг сортировки
            int temp;
            int j;
            long t = System.currentTimeMillis();
            for (int k = d; k > 0; k /= 2) {
                for (int i = k; i < a[f].length; i++) {
                    temp = a[f][i];
                    for (j = i; j >= k; j -= k) {
                        if (temp < a[f][j - k]) {
                            a[f][j] = a[f][j - k];
                        } else break;
                    }
                    a[f][j] = temp;
                }
            }
            t = System.currentTimeMillis() - t;
            result[countOfResult] = t;
            countOfResult++;
            System.out.println("Время работы метода сортировки Шелла - " + t);
        }
    }

    // 3. *Реализовать​ ​​ ​сортировку​ ​слиянием.
    public void solution3() {
        // делаем копию массива для этого метода
        arrayPreparation();
        for (int i = 0; i < a.length; i++) {
            long t = System.currentTimeMillis();
            Sliyanie(a[i]);
            t = System.currentTimeMillis() - t;
            result[countOfResult] = t;
            countOfResult++;
            System.out.println("Время работы метода сортировки слиянием - " + t);
        }
    }

    public static void Sliyanie(int[] b) {
        int left, right, medium;
        int[] z = new int[b.length];
        left = 0;
        medium = Math.round(b.length / 2);
        right = medium;
        for (int p = left; p < right; p++) {
            for (int i = p + 1; i < right; i++) {
                if (b[i] < b[p]) {
                    b[p] = b[p] + b[i];
                    b[i] = b[p] - b[i];
                    b[p] = b[p] - b[i];
                }
            }
        }
        for (int p = right; p < b.length; p++) {
            for (int i = p + 1; i < b.length; i++) {
                if (b[i] < b[p]) {
                    b[p] = b[p] + b[i];
                    b[i] = b[p] - b[i];
                    b[p] = b[p] - b[i];
                }
            }
        }
        left = 0;
        right = medium;
        for (int i = 0; i < b.length; i++) {
            if (left >= medium) {
                z[i] = b[right];
                right++;
                continue;
            }
            if (right > b.length - 1) {
                z[i] = b[left];
                left++;
                continue;
            }
            if (b[left] < b[right]) {
                z[i] = b[left];
                left++;
            } else {
                z[i] = b[right];
                right++;
            }
        }
    }

    // 4. Сравнить результаты сортировки для массивов разного размера
    public void solution4() {
        System.out.println("Сравнительная таблица результатов сортировки");
        System.out.println("**************************************");
        System.out.format("%-7s %-8s %-8s %-8s \n", "Метод", "100", "10000", "1000000");
        System.out.format("%-7s %-8d %-8d %-8d \n", "Подсчет", result[0], result[1], result[2]);
        System.out.format("%-7s %-8d %-8d %-8d \n", "Шелл", result[3], result[4], result[5]);
        System.out.format("%-7s %-8d %-8d %-8d \n", "Слияние", result[6], result[7], result[8]);
    }
}
