package com.gmail.mosoft521.interview;

public class FindNextPrimeTest2 {
    public static int findNextPrime2(int i) {
        while (true) {
            ++i;

            boolean isPrime = true;
            double sqrt = Math.sqrt(i);
            for (int j = 2; j <= sqrt; j++) {
                if (0 == i % j)
                    isPrime = false;
            }

            if (isPrime) {
                return i;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 30; i++) {
            System.out.println(i + ":" + findNextPrime2(i));
        }
    }
}
