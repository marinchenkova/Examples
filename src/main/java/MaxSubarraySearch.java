import java.util.Scanner;

/**
 * Поиск максимального подмассива.
 */
public class MaxSubarraySearch {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int num;
        int arr[];
        int delta[];

        System.out.print("1. Enter size of random array: ");
        num = scan.nextInt();
        arr = randArray(num, -10, 10);
        printArray(arr);

        if(arr.length < 2) throw new Exception("Not enough arguments");
        else if(arr.length == 2) printHighlightedArray(arr, 0, 1);
        else {
            System.out.println("\n2. Delta array:");
            delta = getDelta(arr);
            printArray(delta);

            int half1[] = new int[delta.length / 2];
            int half2[] = new int[delta.length - half1.length];

            for (int i = 0; i < delta.length; i++) {
                if (i < delta.length / 2) half1[i] = delta[i];
                else half2[i - half1.length] = delta[i];
            }

            System.out.println("\n3. Delta array divided:");
            printArray(half1);
            printArray(half2);

            System.out.println("\n4. Half1 max subarray:");
            int max1Index[] = findMaxSubIndexes(half1);
            int max1[] = new int[max1Index[1] - max1Index[0] + 1];
            System.arraycopy(delta, max1Index[0], max1, 0, max1Index[1] - max1Index[0] + 1);
            printArray(max1);

            System.out.println("\n5. Half2 max subarray:");
            int max2Index[] = findMaxSubIndexes(half2);
            int max2[] = new int[max2Index[1] - max2Index[0] + 1];
            System.arraycopy(delta, max2Index[0] + half1.length, max2, 0, max2Index[1] - max2Index[0] + 1);
            printArray(max2);

            System.out.println("\n6. Subarray covers max1 and max2 indexes:");
            int cover[] = new int[max2Index[1] + half1.length - max1Index[0] + 1];
            System.arraycopy(delta, max1Index[0], cover, 0, max2Index[1] + half1.length - max1Index[0] + 1);
            printArray(cover);

            System.out.println("\n7. Cover max subarray:");
            int max3Index[] = findMaxSubIndexes(cover);
            int max3[] = new int[max3Index[1] - max3Index[0] + 1];
            System.arraycopy(delta, max3Index[0] + max1Index[0], max3, 0, max3Index[1] - max3Index[0] + 1);
            printArray(max3);

            System.out.println("\nAnswer:");
            int i = max3Index[0] + max1Index[0];
            int j = max3Index[1] + max1Index[0] + 1;
            printHighlightedArray(arr, i, j);
        }
    }

    /**
     * Поиск максимального непрерывного подмассива.
     * @param arr входной массив
     * @return массив с двумя значениями: начальный и конечный индекс максимального подмассива
     */
    public static int[] findMaxSubIndexes(int[] arr){
        int maxSum = arr[0];
        int maxStart = 0;
        int maxEnd = 0;
        int lastSum = arr[0];
        int lastStart = 0;

        for(int i = 1; i < arr.length; i++){
            lastSum += arr[i];

            if (lastSum < arr[i]) {
                lastSum = arr[i];
                lastStart = i;
            }

            if (maxSum < lastSum) {
                maxStart = lastStart;
                maxEnd = i;
                maxSum = lastSum;
            }
        }

        int result[] = new int[2];
        result[0] = maxStart;
        result[1] = maxEnd;

        return result;
    }

    /**
     * Массив разниц между соседними элементами.
     * @param arr входной массив
     * @return массив разниц
     */
    public static int[] getDelta(int[] arr){
        int delta[] = new int[arr.length - 1];

        for(int i = 0; i < delta.length; i++){
            delta[i] = arr[i + 1] - arr[i];
        }

        return delta;
    }

    /**
     * Генерация массива случайных целых чисел в заданном диапазоне.
     * @param size размер массива
     * @param max верхняя граница диапазона
     * @param min нижняя граница диапазона
     * @return сгенерированный массив
     */
    public static int[] randArray(int size, int max, int min){
        int arr[] = new int[size];
        max -= min;

        for(int i = 0; i < size; i++)
            arr[i] = (int) (Math.random() * ++max) + min;

        return arr;
    }

    /**
     * Печать массива в строку.
     * @param arr массив
     */
    public static void printArray(int[] arr){
        for(int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    /**
     * Печать массива в строку с выделением двух элементов.
     * @param arr массив
     * @param i элемент для выделения
     * @param j элемент для выделения
     */
    public static void printHighlightedArray(int[] arr, int i, int j){
        for(int k = 0; k < arr.length; k++){
            if(k == i || k == j) {
                System.out.print("{" + arr[k] + "} ");
            } else System.out.print(arr[k] + " ");
        }
        System.out.println();
    }
}
