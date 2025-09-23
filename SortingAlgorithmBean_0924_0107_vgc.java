// 代码生成时间: 2025-09-24 01:07:58
 * This class provides methods to sort an array of integers using different sorting algorithms.
 *
 * @author YourName
 * @version 1.0
 */
public class SortingAlgorithmBean {

    // Method to sort an array using bubble sort algorithm
    public int[] bubbleSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[j]) {
                    // Swap the elements
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    // Method to sort an array using insertion sort algorithm
    public int[] insertionSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    // Method to sort an array using selection sort algorithm
    public int[] selectionSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element of the unsorted part
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    // Method to sort an array using quick sort algorithm
    public int[] quickSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        return quickSortRecursive(array, 0, array.length - 1);
    }

    private int[] quickSortRecursive(int[] array, int left, int right) {
        if (left >= right) {
            return array;
        }

        int pivotIndex = partition(array, left, right);
        quickSortRecursive(array, left, pivotIndex - 1);
        quickSortRecursive(array, pivotIndex + 1, right);
        return array;
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        return i + 1;
    }

    // Main method for testing the sorting algorithms
    public static void main(String[] args) {
        SortingAlgorithmBean bean = new SortingAlgorithmBean();
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Original array: ");
        printArray(array);

        System.out.println("Sorted array using bubble sort: ");
        int[] bubbleSortedArray = bean.bubbleSort(array.clone());
        printArray(bubbleSortedArray);

        System.out.println("Sorted array using insertion sort: ");
        int[] insertionSortedArray = bean.insertionSort(array.clone());
        printArray(insertionSortedArray);

        System.out.println("Sorted array using selection sort: ");
        int[] selectionSortedArray = bean.selectionSort(array.clone());
        printArray(selectionSortedArray);

        System.out.println("Sorted array using quick sort: ");
        int[] quickSortedArray = bean.quickSort(array.clone());
        printArray(quickSortedArray);
    }

    // Helper method to print an array
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}