package HomeWork7;
/**
 * Сыпченко Алексей
 * ДЗ №7
 *
 * 1. Написать функции, которые считывают матрицу смежности из файла и выводят ее на экран
 *
 * 2. Написать функцию обхода графа в ширину.
 */

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class HomeWork7 {
    Scanner scanner = new Scanner(System.in);
    final String FILE_PATH = "src\\HomeWork7\\graph.txt";
    final File file = new File(FILE_PATH);

    public static void main(String[] args) {
        HomeWork7 homeWork7 = new HomeWork7();
        int sel = 0;
        do {
            homeWork7.menu();
            sel = homeWork7.scanner.nextInt();
            switch (sel) {
                case 1: {
                    homeWork7.solution1();
                    break;
                }
                case 2: {
                    homeWork7.solution2();
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

    // 1. Написать функции, которые считывают матрицу смежности из файла и выводят ее на экран
    public void solution1() {
        // считываем матрицу смежности
        int[][] matrix = readMatrix();
        // вывод матрицы смежности в консоль
        printMatrix(matrix);
    }

    public int[][] readMatrix() {
        int[][] matrixFromFile; // объявление матрицы смежности
        int row = 0; // строка матрицы смежности
        int size = 0; // размер матрицы смежности
        BufferedReader br = null;
        String line = "";
        String delimiter = " "; // разделитель в файле

        try {
            br = new BufferedReader(new FileReader(file));
// считываем размер матрицы смежности
            line = br.readLine();
            System.out.println(line);
            String[] sizer = line.split(delimiter);  // строка чисел из файла
            try {
                size = Integer.parseInt(sizer[sizer.length - 1]);
            } catch (NumberFormatException e) {
                System.out.println("WTF");
            }
            matrixFromFile = new int[size][size];
            // считываем матрицу смежности
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(delimiter);
                for (int i = 0; i < size; i++) {
                    matrixFromFile[row][i] = Integer.parseInt(numbers[i]);
                }
                row++;
            }
            return matrixFromFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        return new int[0][0];
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 2. Написать функцию обхода графа в ширину.
    // Для обхода с вершины №1 результат будет:
    // [1, 3, 5, 6, 9, 2, 4, 8, 7]
    // Для обхода с вершины №6 результат будет:
    // [6, 3, 5, 1, 9, 2, 4, 8, 7]
    // Wave = 5

    public void solution2() {
        int[][] matrix2 = { //матрица смежности для 9 вершин
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 1, 0, 0, 1, 1},
                {3, 1, 0, 0, 0, 0, 1, 0, 0, 1},
                {4, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {5, 1, 1, 0, 0, 0, 1, 0, 0, 0},
                {6, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {7, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {8, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {9, 0, 1, 1, 1, 0, 0, 0, 0, 0}
        };
        LinkedList<Integer> stack = new LinkedList<Integer>(); // используем как стек для работы с вершинами
        LinkedList<Integer> peak = new LinkedList<Integer>(); // коллекция вершин
        int node = 6; // вершина, с которой начнем обход
        int wave = 1; // счетчик волн
        peak.add(node); // сохраняем вершину, от которой идет волна
        stack.add(node); // хранилище вершин, между которыми прошла волна
        goWave(wave, matrix2, peak, stack); // пускаем волну
    }

    public void goWave(int wave, int[][] matrix3, LinkedList<Integer> peak, LinkedList<Integer> stack) {
        while (peak.size() != matrix3.length - 1) { //пускаем волну, пока все вершины не будут пройдены волной

            int k = stack.size(); // сколько вершин пускают волну
            for (int e = 0; e < k; e++) { // идем от каждой вершины
                int p = stack.get(e);// получаем номер вершины для обхода матрицы смежности
                for (int i = 1; i < matrix3.length; i++) {
                    // проверяем наличие связи между вершинами и отсутствие вершины i
                    // в пройденных волной ранее и в настигнутых волной сейчас
                    if (matrix3[p][i] != 0 && !peak.contains(i) && !stack.contains(i)) {
                        stack.add(i);
                        peak.add(i);
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                stack.removeFirst(); // удаляем из импровизированного стека вершины, источники данной волны
            }
            wave++;
            // goWave(wave, matrix3, peak, stack); // для рекурсии
            // только тогда придется вывод массива вершин и количества волн переделать
        }
        System.out.println(Arrays.toString(peak.toArray()));
        System.out.println("Wave = " + wave);
        stack.clear();
    }
}