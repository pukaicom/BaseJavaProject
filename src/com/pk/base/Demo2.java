package com.pk.base;

/**
 * Created by pukai on 17/4/6.
 */

public class Demo2 {
    public static int[] array = new int[]{1, 3, 4, 5, 6, 7, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1};

    public static void main(String[] args) {
        System.out.print(getNumber(array));
    }

    /**
     * 找出一个长度为n的数组中出现次数大于n/2的数
     *
     * @param array
     * @return
     */
    public static int getNumber(int[] array) {
        int count = 0;
        int temp = array[0];
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (temp == array[i]) {
                count++;
            } else {
                count--;
                if (count <= 0) {
                    temp = array[i];
                    count = 0;
                }
            }
        }
        return temp;
    }
}
