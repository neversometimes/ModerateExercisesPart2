package org.example;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static Integer[] duplicatesArray = {10, 20, 10, 20, 40, 40, 30, 50, 50};
    public static int[][] testMaze = {{0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 1, 0, 1, 0, 1, 1},
                                      {0, 1, 0, 0, 0, 0, 0, 0},
                                      {0, 1, 0, 1, 1, 0, 1, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 1, 0, 1, 0, 1, 1, 0},
                                      {0, 1, 1, 0, 1, 1, 1, 0},
                                      {0, 0, 0, 0, 0, 0, 1, 0}};  //test maze


    public static void main(String[] args) {

   //     System.out.println (findSingleOccurrence(duplicatesArray)); //prints single instance number of array
   //     System.out.println("Distinct Traversals: " + distinctStairTraversals(6));   // expect: 11

          System.out.println("Number of Paths: " + findNumOfAllPossiblePaths(testMaze));

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

    public static int distinctStairTraversals(int stairsCount) {
        //ex2: find the number of distinct ways to climb n stairs, taking either 1 or 2 steps per traversal

        ArrayList<Integer> tries = new ArrayList<>();
        int totalWays = 0;

        if (stairsCount == 0) {return 0;}  //check for 0 stairs

        // create arraylist of '1's based on stairsCount
        for (int i = 0; i < stairsCount; i++) {
            tries.add(1);
        }
        totalWays++;
        //System.out.println(tries);          // all 1's traversal

        // start at arraylist[0] merges two consecutive 1s into a 2
        for (int m = 0; m < tries.size() - 1; m++) {
            if ((tries.get(m) == 1) && (tries.get(m + 1) == 1)) {
                tries.remove(m+1);
                tries.remove(m);
                tries.addFirst(2);
                totalWays++;                // count each first merge traversal
                //System.out.println(tries);
            }

            // break here if no more 1s found in arraylist
            if (!tries.contains(1)) { break; }

            // clone original arraylist to permutate traversal cases
            ArrayList<Integer> permutate = (ArrayList<Integer>)tries.clone();

            // create permutations
            for (int j = 0; j < permutate.size() - 1; j++) {

                //starting from end of copied arraylist, look for 2,1 pattern where elements will be swapped
                for (int i = permutate.size() - 1; i > 0; i--) {
                    if ((permutate.get(i - 1) == 2) && (permutate.get(i) == 1)) {
                        //swap using set to replace elements
                        permutate.set(i - 1, 1);
                        permutate.set(i, 2);
                        break;
                    }
                }
                totalWays++;                        // count each permutation after merge
                //System.out.println(permutate);
            }
        }
        //System.out.println(totalCombinations);
        return totalWays;
    }

    public static int findNumOfAllPossiblePaths(int[][] maze) {
        //ex3: find all possible paths of a 2D NXN square matrix, starting from top left to bottom right
        //     matrix with '0' is open, '1' is blocked; moves are down or right only
        // stack elements are the maze cell positions: ["x/y/dir"]
        //     x: 1st cell param, y: 2nd cell param, dir: R-right, D-down, E-either, N-neither
        int cheese=0;                       // number of successful paths
        String aStr, bStr, tmpStr = "";
        Integer a=0, b=0, x=0, y=0;

        Stack<String> stack = new Stack<>();
        stack.push("START");

        do {

            stack = traverseMaze(x, y, maze, stack);

            // cheese calculating variables
            a = (maze.length-2); b = maze.length-1;     // set perimeter values
            aStr = a.toString(); bStr = b.toString();   // convert int to string

            // successful paths reach cells with (perimeter-1, perimeter-2) or inverse values
            if ((stack.peek().contains(aStr+bStr)) || (stack.peek().contains(bStr+aStr))){
                cheese++;
            }
            // pop from stack until 'E' code or START
            while ((stack.peek().charAt(2) != 'E') && (!tmpStr.equals("START"))) {
                stack.pop();
                tmpStr = stack.peek(); //look ahead to prevent stack exception
            }
            //System.out.println(stack.peek());  //print out top of stack

            if (stack.peek().equals("START")) {  //break : no more paths to traverse; back at START(bottom) of stack
                break;
            } else {
                tmpStr = "" + stack.peek().charAt(0);
                x = Integer.parseInt(tmpStr);           // parse x-value from top of stack element
                tmpStr = "" + stack.peek().charAt(1);
                y = Integer.parseInt(tmpStr) + 1;        // incr y-value since down is option after fork-right

                tmpStr = stack.pop();                   // pop fork cell off stack to modify
                tmpStr = tmpStr.substring(0, 2) + "D";  // replaces 'E' with 'D'
                stack.push(tmpStr);                     // push modified item back onto stack for next traversal
            }

        } while (!stack.peek().equals("START"));  // keep iterating paths while top of stack != START

        return cheese;  // return number of successful paths to the cheese!
    }

    public static Stack<String> traverseMaze(int x, int y, int[][] travMaze, Stack<String> pile) {
        String tmpStr; String cell; String nextPts;
        boolean go = true;

        while (go) {
            cell = getCode(x, y, travMaze);     // getCode(): determines next cell path from current cell(x,y)
            //System.out.println(cell);     //*** enable this to see cells visited ***

            pile.push(cell);    // push cell+code onto stack

            nextPts = deCode(cell);         // deCode(): gets next cell's points
            //System.out.println(nextPts);

            // parse x and y integers from nextPts string
            tmpStr = "" + nextPts.charAt(0);
            x = Integer.parseInt(tmpStr);
            tmpStr = "" + nextPts.charAt(1);
            y = Integer.parseInt(tmpStr);
            tmpStr = "" + cell.charAt(2);

            if (((x == travMaze.length-1) && (y == travMaze.length-1)) || (tmpStr.equals("N"))){
                go = false;  // stop traversing maze - bottom right cell reached or Block found
            }
        } // end while

        return pile; // return new path traversal stack
    }

    public static String deCode (String codeStr) {
        // Based on codeStr 3rd char code, return next destination cell's points (via String)

        String s, points = "";
        Integer x, y;

        // if code = R (right) or E (either fork), add one to current x-value  (first option for E fork is right)
        if ((codeStr.charAt(2) == 'R')|| (codeStr.charAt(2) == 'E')) {
            s = ""+codeStr.charAt(0);
            x = Integer.parseInt(s);
            x++;
            points = x.toString() + codeStr.charAt(1);
        }
        // if code = D (down), add one to current y-value
        if (codeStr.charAt(2) == 'D') {
            s = ""+codeStr.charAt(1);
            y = Integer.parseInt(s);
            y++;
            points = codeStr.charAt(0) + y.toString();
        }
        // if N (neither), return x,y without any changes
        if (codeStr.charAt(2) == 'N') {
            points = codeStr.substring(0, 2);      // strip code from points string
        }

        return points;      // return points pair values
    }

    public static String getCode (Integer x, Integer y, int[][] mazeMap) {
        int perim = mazeMap.length - 1;
        String code = "N";  // default blocked
        String sX = "", sY = "";

        if ((x < perim) && (y < perim)) {  // check only up to the perimeter of the maze
            if ((mazeMap[y][x + 1] == 0) && (mazeMap[y + 1][x] == 0)) {
                code = "E";     // both R and D are 0
            } else if (mazeMap[y][x + 1] == 0) {
                code = "R";     // R is 0

            } else if (mazeMap[y + 1][x] == 0) {
                code = "D";     // D is 0

            };
        };

        // if x == perim, then only check down direction
        if ((x == perim) && (y < perim)){
            if (mazeMap[y+1][x] == 0){
                code = "D";  // if down = 0

            }
        }

        //if y == perim, then only check the right direction
        if ((y == perim) && (x < perim)) {
            if (mazeMap[y][x+1] == 0){
                code = "R";  // if right = 0

            }
        }
        sX = x.toString();      // int to String
        sY = y.toString();

        return sX + sY + code;
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