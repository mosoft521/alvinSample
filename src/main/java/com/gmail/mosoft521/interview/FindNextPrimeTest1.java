package com.gmail.mosoft521.interview;

public class FindNextPrimeTest1 {

    public static boolean testIsPrime3(int n) {
        if (n <= 3) {
            return n > 1;
        }

        for (int j = 2; j <= Math.sqrt(n); j++) {
            if (n % j == 0)
                return false;
        }
        return true;
    }

    public static int findNextPrime(int i) {
        while (true) {
            if (testIsPrime3(i)) {
                return i;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        for (int i = 2; i < 10; i++) {
            System.out.println(findNextPrime(i));
        }
    }
}

