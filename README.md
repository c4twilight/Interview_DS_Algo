# Interview DS Algo

This repository stores solved data structures and algorithms problems by topic.

Current problem:

- `DP/1D DP/Rotated Digits.java`

Problem link: https://leetcode.com/problems/rotated-digits/description/

## Approach 1: Brute Force Digit Check

Check every number from `1` to `n`.

A number is good when:

- It has no invalid digits: `3`, `4`, `7`.
- It has at least one digit that changes after rotation: `2`, `5`, `6`, `9`.
- Digits `0`, `1`, `8` stay valid but do not make the number different by themselves.

Time complexity: `O(n * d)`, where `d` is the number of digits in `n`.

Space complexity: `O(1)`, because the digit sets have fixed size.

## Approach 2: Digit DP

Count valid numbers from left to right using whether the current prefix is tight to `n` and whether the number already has a changing digit.

Time complexity: `O(d * 10)`, where `d` is the number of digits in `n`.

Space complexity: `O(d)` for memoization and recursion depth.
