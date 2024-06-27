package org.example;

import java.util.ArrayList;

public class Main {
    public static Integer[] duplicatesArray = {10, 20, 10, 20, 40, 40, 30, 50, 50};

    public static void main(String[] args) {

        System.out.println (findSingleOccurrence(duplicatesArray)); //prints single instance number of array

    }

    public static ArrayList<Integer> findSingleOccurrence (Integer[] numbers) {
        //ex1: Find a number that appears only once in a given array.  All others appear twice.
        //  e.g. input: {10, 20, 10, 20, 30, 40, 40, 30, 50}   result: 50

        ArrayList<Integer> nonDuplicate = new ArrayList<>();
        nonDuplicate.add(numbers[0]);                         // add first number in array to Nonduplicate ArrayList

        for (int i = 1; i < numbers.length; i++) {      //  starting at 2nd number of array
            if (nonDuplicate.contains(numbers[i])) {          // if the ArrayList has the number, remove it
                nonDuplicate.remove(numbers[i]);
            } else {
                nonDuplicate.add(numbers[i]);                 // else add it to the ArrayList
            }
        }
        return nonDuplicate;    // return the ArrayList containing the identified non-duplicate number
    }

    public static int distinctStairTraversals(int stairs) {
        //ex2: find number of distinct ways to climb n stairs, taking 1 or 2 steps

        return 0;
    }

    public static int findNumOfAllPossiblePaths(int[] maze) {
        //ex3: find all possible paths of an m x n matrix, starting from top left to bottom right
        //     matrix with '0' is open, '1' is blocked; moves are down or right only

        return 0;
    }

    public static boolean checkAllDistinctChars (String str) {
        //ex4: check if a string has all distinct characters

        return false;
    }

    public static int firstUniqueChar (String str) {
        //ex5: find the index of the first unique character in a given string

        return 0;
    }

    public static boolean checkSubstringPermutation(String str1, String str2) {
        //ex6: check if str1 is a permutation of str2

        return true;
    }

    public static double divideUsingSubtraction (int num1, int num2) {
        //ex7: divide two integers using subtraction operator

        return 0.0;
    }

    public static String reverseWordsInString (String str) {
        //ex8: Reverse the words in a sentence
        //     e.g. sentence a in words the Reverse

        return "";
    }

    public static boolean ckSubString (String str1, String str2) {
        //ex9: check if str1 is a substring of str2

        return false;
    }

    public static int[] addOneToArrayValue(int[] digitArray) {
        //ex10: add one to the value of an array of single digit integers
        //    e.g. given: [9, 9, 9] result: [1, 0, 0, 0]

        return digitArray;
    }

    public static boolean checkIsomorphicStrings (String str1, String str2) {
        //ex11: check if str1 is isomorphic with str2
        //      e.g. is abca isomorphic with abxz?  yes.

        return false;
    }

    public static int[] findAnagramIndices(String baseStr, String anagram) {
        //ex12: find the indices of all of the instances of anagram within baseStr
        //     e.g. baseStr:"zyxwyxyxzxyz"  result indices: [0, 6, 10]

        return new int[]{0,0};
    }

    public static String orderAlphaSumNums(String str) {
        //ex13: given string with mixed alpha(uppercase)/numeric chars,
        //      order alpha, sum num and append to alpha string
        //    e.g. given: "AND456HSE8"  result: "ADEHNS23"  (4+5+6+8 = 23)

        return "";
    }

    public static int[][] findNumInMatrix(int num, int[][] matrix) {
        //ex14: given an 2D MxN matrix and number value, find all instances of that value in the matrix
        //   e.g. given: n= 3 and {2, 3, 5,    result: [(0,2), (1,0), (2,1)]  (row, column)
        //                         3, 2, 1,
        //                         1, 3, 5}

        return matrix;
    }

    public static void createSpiralMatrix(int n) {
        //ex15: create a square 2D matrix of size n x n with increasing values in spiral
        //
        //   e.g. n=4  expected output:   {1,  2,  3,  4,
        //                                12, 13, 14,  5,
        //                                11, 16, 15,  6,
        //                                10,  9,  8,  7}
    }

    public static String stringCompression(String str) {
        //ex16: given string with duplcate chars, remove and replace duplicates with dupe count
        //   e.g.  given: "aaaabbbbcccccddddeeee"  result: "a4b4c5d4e4"

        return "";
    }

    public static void twoCirclesTest() {
        //ex17: given center points for two circles and their corresponding radii,
        //       determine if the circle circumferences:  1) C1 is inside C2
        //                                                2) C2 is inside C1
        //                                                3) C1 and C2 circumferences intersect
        //                                                3) C1 and C2 circumferences don't overlap
        //                                                4) C1 and C2 overlap exactly (same circle)
    }

}