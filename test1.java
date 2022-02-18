package com.wilson.first;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        System.out.println("Please input a number: ");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int tmp = n;
        int count = 0;
        while (tmp > 0) {
            tmp /= 10;
            count++;
        }

        int[] arr = new int[count];
        tmp = n;
        for (int i = count-1; i >= 0; i--) {
            arr[i] = tmp % 10;
            tmp /= 10;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] + 5) % 10;
        }

        int pL = 0;
        int pR = arr.length - 1;
        while (pR > pL) {
            tmp = arr[pL];
            arr[pL++] = arr[pR];
            arr[pR--] = tmp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
