package com.gmail.mosoft521.lambda.stream;

import java.util.concurrent.RecursiveTask;

public class ConcurrencyCalculator extends RecursiveTask<Integer> {
    private final int start;
    private final int end;
    private final static int THRESHOLD = 5;

    public ConcurrencyCalculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if ((end - start) < THRESHOLD) {
            for (int x = start; x < end; x++) {
                result += x;
            }
        } else {
            int middle = (start + end) / 2;
            ConcurrencyCalculator leftCalculator = new ConcurrencyCalculator(start, middle);
            ConcurrencyCalculator rightCalculator = new ConcurrencyCalculator(middle, end);
            leftCalculator.fork();
            rightCalculator.fork();
            result += (leftCalculator.join() + rightCalculator.join());
        }
        return result;
    }
}