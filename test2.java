package com.wilson.first;

import java.util.Random;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        int[] arr1 = luckNum();
        printArray(arr1);

        int[] arr2 = buyNum();
        printArray(arr2);

        boolean ret = checkNum(arr1,arr2);
        if(ret){
            System.out.println("You are so lucky!");
        }else {
            System.out.println("Sorry");
        }
    }

    public static int[] luckNum() {
        int[] arr = new int[7];
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            boolean j;
            int n = 0;
            do {
                j = false;
                n = r.nextInt(33) + 1;
                int p = 0;
                while (p < i) {
                    if (arr[p++] == n) {
                        j = true;
                        break;
                    }
                }
            } while (j);
            arr[i] = n;
        }
        arr[6] = r.nextInt(16) + 1;

        return arr;
    }

    public static int[] buyNum() {
        int[] arr = new int[7];
        Scanner sc = new Scanner(System.in);
        int input = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            boolean j;
            do {
                j = false;
                System.out.println("Please input No." + (i + 1) + "(1-33): ");
                input = sc.nextInt();
                if (input < 1 || input > 33) {
                    j = true;
                }
                int p = 0;
                while (p < i) {
                    if (arr[p++] == input) {
                        j = true;
                        break;
                    }
                }
            } while (j);
            arr[i] = input;
        }

        boolean j;
        do {
            j = false;
            System.out.println("Please input No." + (arr.length) + "(1-16): ");
            input = sc.nextInt();
            if (input < 1 || input > 16) {
                j = true;
            }
        } while (j);
        arr[arr.length - 1] = input;

        return arr;
    }

    public static boolean checkNum(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null)
            return false;
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}



