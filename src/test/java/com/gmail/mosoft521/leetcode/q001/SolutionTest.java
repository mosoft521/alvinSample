package com.gmail.mosoft521.leetcode.q001;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
// Increase these three for more accuracy:
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
public class SolutionTest {
    private int[] nums;
    private int target;

    @Setup
    public void setup() {
        nums = new int[]{2, 3, 4, 6, 7, 8, 9, 14};
        target = 13;
    }

    @Benchmark
    public void twoSum() {
        Solution.twoSum(nums, target);
    }

    @Benchmark
    public void twoSum2() {
        Solution.twoSum2(nums, target);
    }
}
