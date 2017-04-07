package com.pk.base;

/**
 * Created by pukai on 17/4/5.
 */

public class FindByWhat {
    private static final int[] array = new int[]{300, 442, 331, 3, 11, 3, 4, 2, 5, 7, 243, 236, 75, 2342, 23523, 2626, 2737, 5845, 742, 4236, 36};

    public static void main(String[] args) {
        Demo1.sortHeap(array);
        System.out.println(findOneNumber(array,0,array.length-1,23523));
    }

    public static int findOneNumber(int[] array,int start,int end,int value){
        System.out.print(""+start+"   "+end+"   ");
        if(start == end){
            if(array[start] == value){
                return start;
            }else{
                return -1;
            }
        }
        int middle = (start+end)/2;
        System.out.println(middle);
        if(array[middle] == value){
            return middle;
        }else if(array[middle]>value){
            return findOneNumber(array,start,middle-1,value);
        }else{
            return findOneNumber(array,middle+1,end,value);
        }
    }
}
