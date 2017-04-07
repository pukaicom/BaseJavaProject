package com.pk.base;

public class Demo1 {
    private static int[] array = new int[]{300, 442, 331, 3, 11, 3, 4, 2, 5, 7, 243, 236, 75, 2342, 23523, 2626, 2737, 5845, 742, 4236, 36};

    public static void main(String[] args) {
        // sortMaopao(array);
        //sortKuaiSu(array);
        //sortMerge(array);
        sortHeap(array);
        outputArray(array);
    }

    /**
     * 经典的冒泡排序
     *
     * @param array
     */
    public static void sortMaopao(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 打印输出数组
     *
     * @param array
     */
    public static void outputArray(int[] array) {
        for (int i : array) {
            System.out.print(i + "  ");
        }
        System.out.println("");
    }

    /**
     * 快速排序
     *
     * @param array
     */
    public static void sortKuaiSu(int[] array) {
        subQuickSort(array, 0, array.length - 1);
    }

    /**
     * 快速排序的分割 将第一个数做为标注 将当前的数组 分为两部分 前一部小大于 value  后一部分大于 value
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        if (start >= end) {
            return start;
        }
        int i = start;
        int j = end;
        int value = array[start];
        while (i < j) {
            while (i < j && array[j] > value) {
                j--;
            }
            if (array[j] <= value) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
            while (i < j && array[i] <= value) {
                i++;
            }
            if (array[i] >= value) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        outputArray(array);
        return j;
    }

    /**
     * 快速排序的递归
     *
     * @param array
     * @param start
     * @param end
     */
    private static void subQuickSort(int[] array, int start, int end) {
        if (array == null || (end - start) < 1) {
            return;
        }
        int part = partition(array, start, end);
        subQuickSort(array, start, part - 1);
        subQuickSort(array, part + 1, end);
    }

    /**
     * 归并排序合并两个有序数组
     *
     * @param array
     * @param start
     * @param middle
     * @param end
     */
    public static void mergArray(int[] array, int start, int middle, int end) {
        int[] newArray = new int[end - start + 1];
        int i = start;
        int j = middle + 1;
        int k = 0;
        while (k <= end - start) {
            if (i <= middle && j <= end) {
                if (array[j] >= array[i]) {
                    newArray[k] = array[i];
                    i++;
                } else {
                    newArray[k] = array[j];
                    j++;
                }
                k++;
            } else {
                //将前面数组的剩余部分直接放到新的有序数组里
                while (i <= middle) {
                    newArray[k] = array[i];
                    i++;
                    k++;
                }
                //将后面数组的剩余部分直接放到新的有序数组里
                while (j <= end) {
                    newArray[k] = array[j];
                    j++;
                    k++;
                }
            }
        }
        for (int w = start; w <= end; w++) {
            array[w] = newArray[w - start];
        }
        outputArray(newArray);
    }

    /**
     * 归并排序的递归过程 分割成两部分 并合并两个数组
     * @param array
     * @param start
     * @param end
     */
    public static void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (end + start) / 2;
        if (start < middle) {
            mergeSort(array, start, middle);
        }
        if (middle + 1 < end) {
            mergeSort(array, middle + 1, end);
        }
        mergArray(array, start, middle, end);
    }

    /**
     * 归并排序
     * @param array
     */
    public static void sortMerge(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * 堆排序的 大顶堆实现，将当前的点下沉 构建大顶堆
     * @param array
     * @param total
     * @param current
     */
    public static void heapDown(int[] array, int total, int current) {
        int j, temp;
        temp = array[current];
        j = 2 * current + 1;
        while (j < total) {
            if (j + 1 < total && array[j + 1] > array[j]) //在左右孩子中找最小的
                j++;

            if (array[j] <= temp)
                break;

            array[current] = array[j];     //把较小的子结点往上移动,替换它的父结点
            current = j;
            j = 2 * current + 1;
        }
        array[current] = temp;
        outputArray(array);
    }

    /**
     * 利用顶点下沉的方法 构建大顶堆
     * @param array
     */
    public static void createHeap(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapDown(array, n, i);
    }

    /**
     * 堆排序的实现
     * @param array
     */
    public static void sortHeap(int[] array) {
        int length = array.length;
        createHeap(array);
        for (int i = 0; i < length; i++) {
            int temp = array[length - i - 1];
            array[length - i - 1] = array[0];
            array[0] = temp;
            heapDown(array, length - i - 1, 0);
        }
    }

}
