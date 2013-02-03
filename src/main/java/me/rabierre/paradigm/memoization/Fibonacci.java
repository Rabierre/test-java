package me.rabierre.paradigm.memoization;

import java.util.Arrays;

/**
 * pilot code for test memoization.
 */
public class Fibonacci {
    private final Integer INVALID_VALUE = -1;

    private int recursiveCount = 0;
    private Integer[] reserved = null;

    public int computeRecursively(int n) {
        recursiveCount++;

        if (n < 2) return n;

        return computeRecursively(n - 1) + computeRecursively(n - 2);
    }

    public int computeWithMemoization(int n) {
        recursiveCount++;

        if (reserved == null) {
            initializeMemo(n);
        }

        if (reserved[n] != INVALID_VALUE) return reserved[n];

        reserved[n] = computeWithMemoization(n - 1) + computeWithMemoization(n - 2);

        return reserved[n];
    }

    private void initializeMemo(int n) {
        reserved = new Integer[n + 1];
        reserved[0] = 0;
        reserved[1] = 1;
        Arrays.fill(reserved, 2, reserved.length, -1);
    }

    public static void main(String[] args) {
        Fibonacci recursiveFibo = new Fibonacci();
        int fiboN = recursiveFibo.computeRecursively(10);
        System.out.println("result : " + fiboN + " recursive count : " + recursiveFibo.getRecursiveCount());

        Fibonacci memoizationFibo = new Fibonacci();
        fiboN = memoizationFibo.computeWithMemoization(10);
        System.out.println("result : " + fiboN + " recursive count : " + memoizationFibo.getRecursiveCount());
    }

    public int getRecursiveCount() {
        return recursiveCount;
    }
}
