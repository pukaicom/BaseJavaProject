package com.pk.base;

public class Demo0 {
	public static void main(String[] args){
		outPutRect(1,10);
	}
	
	public static void outPutRect(int i, int j){
		for(int k = 0;k <= j-i;k++){
			for(int m = 0;m <=k; m++){
				System.out.print(i+m);
			}
			System.out.println();
		}
	}
}
