import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * LeetCode 788: Rotated Digits
 * Problem: https://leetcode.com/problems/rotated-digits/description/
 */

// Approach 1: Brute Force Digit Check
// Time Complexity: O(n * d), where d is the number of digits in n.
// Space Complexity: O(1), because the good/bad digit sets have fixed size.
class Solution {
    private static final Set<Integer> GOOD = new HashSet<>(Arrays.asList(2, 5, 6, 9));
    private static final Set<Integer> BAD = new HashSet<>(Arrays.asList(3, 4, 7));

    public int rotatedDigits(int n) {
        int count = 0;

        for (int num = 1; num <= n; num++) {
            if (isGood(num)) {
                count++;
            }
        }

        return count;
    }

    private boolean isGood(int num) {
        boolean hasValidRotation = false;

        while (num != 0) {
            int digit = num % 10;
            num /= 10;

            if (BAD.contains(digit)) {
                return false;
            }

            if (GOOD.contains(digit)) {
                hasValidRotation = true;
            }
        }

        return hasValidRotation;
    }
}

// Approach 2: Digit DP
// Time Complexity: O(d * 10), where d is the number of digits in n.
// Space Complexity: O(d), for memoization and recursion depth.
class SolutionDigitDP {
    private char[] digits;
    private Integer[][] memo;

    public int rotatedDigits(int n) {
        digits = String.valueOf(n).toCharArray();
        memo = new Integer[digits.length][2];

        return countGoodNumbers(0, true, false);
    }

    private int countGoodNumbers(int index, boolean tight, boolean hasValidRotation) {
        if (index == digits.length) {
            return hasValidRotation ? 1 : 0;
        }

        int rotationState = hasValidRotation ? 1 : 0;
        if (!tight && memo[index][rotationState] != null) {
            return memo[index][rotationState];
        }

        int limit = tight ? digits[index] - '0' : 9;
        int total = 0;

        for (int digit = 0; digit <= limit; digit++) {
            if (isInvalid(digit)) {
                continue;
            }

            boolean nextTight = tight && digit == limit;
            boolean nextHasValidRotation = hasValidRotation || changesAfterRotation(digit);
            total += countGoodNumbers(index + 1, nextTight, nextHasValidRotation);
        }

        if (!tight) {
            memo[index][rotationState] = total;
        }

        return total;
    }

    private boolean isInvalid(int digit) {
        return digit == 3 || digit == 4 || digit == 7;
    }

    private boolean changesAfterRotation(int digit) {
        return digit == 2 || digit == 5 || digit == 6 || digit == 9;
    }
}
