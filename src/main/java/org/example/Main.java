package org.example;

import java.util.*;
import java.lang.Math;

public class Main {
    public static Integer[] duplicatesArray = {2, 10, 20, 10, 20, 20, 40, 30, 50, 50, 10, 30, 30, 50, 5};

    public static int[][] testMaze = {{0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 1, 0, 1, 0, 1, 1},
                                      {0, 1, 0, 0, 0, 0, 0, 0},
                                      {0, 1, 0, 1, 1, 0, 1, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 1, 0, 1, 0, 1, 1, 0},
                                      {0, 1, 1, 0, 1, 1, 1, 0},
                                      {0, 0, 0, 0, 0, 0, 1, 0}};  //test maze - 5 solutions

    public static int[] intAsArray = {9, 9, 9, 9};

    public static int[][] matrix = {{2, 3, 5},
                                    {3, 2, 1},
                                    {1, 3, 5}};

    public static int[] circle1 = {2, 2, 1};    // center:(2,2) radius:1    no intersect, 1 inside 2
    public static int[] circle2 = {2, 2, 4};    // center:(2,2) radius:4
    public static int[] circle3 = {5, 3, 2};    // center:(5,3) radius:2    intersect one point
    public static int[] circle4 = {5, 9, 4};    // center:(5,9) radius:4
    public static int[] circle5 = {5, 4, 2};    // center:(5,4) radius:2    intersect one point, 1 inside 2
    public static int[] circle6 = {5, 6, 4};    // center:(5,6) radius:4
    public static int[] circle7 = {0, 0, 1};    // center:(1,1) radius:1    intersect at two points
    public static int[] circle8 = {0, 2, 2};    // center:(0,2) radius:2
    public static int[] circle9 = {1, 1, 1};    //  use as both circles - same circle

    public static void main(String[] args) {

        System.out.println ("Single Instance Values: " + findSingleOccurrence(duplicatesArray));
        System.out.println();

        System.out.println("Distinct Traversal Combinations: " + distinctStairTraversals(6));   // expect: 11
        System.out.println();

        System.out.println("Number of Unique Paths through test maze: " + findNumOfAllPossiblePaths(testMaze));
        System.out.println();

        System.out.println("1) All distinct 'abcdef123': " + checkAllDistinctChars("abcdef123"));
        System.out.println("2) All distinct 'abcdef123d': " + checkAllDistinctChars("abcdef123d"));
        System.out.println();

        System.out.println("Index of first unique char 'ababcbdedff': " + firstUniqueChar("ababcbdedff"));
        System.out.println();

        System.out.println ("S2 permutation of S1? " + checkSubstringPermutation("Aeede", "Deeea"));
        System.out.println ("S2 permutation of S1? " + checkSubstringPermutation("ab19C2", "bC92a"));
        System.out.println ("S2 permutation of S1: " + checkSubstringPermutation("194$", "194$"));
        System.out.println();

        System.out.println("Div by Subt Result: " + divideUsingSubtraction(-24, 7));
        System.out.println("Div by Subt Result: " + divideUsingSubtraction(24, 0));
        System.out.println("Div by Subt Result: " + divideUsingSubtraction(-225, -25));
        System.out.println();

        System.out.println("Result: " + reverseWords("Reverse the words in a sentence"));
        System.out.println();

        System.out.println("Result: " + ckSubString("abc", "abuvxyzc"));
        System.out.println("Result: " + ckSubString("abc",  "abcxyz"));
        System.out.println("Result: " + ckSubString("abc", "xyzabc"));
        System.out.println();

        addOneToArrayValue(intAsArray);
        System.out.println();

        System.out.println("Isomorphic: " + checkIsomorphic("welnwvvol", "ntrbnxx1r"));  // should be true
        System.out.println("Isomorphic: " + checkIsomorphic("welnwvvol", "ntrbcxx1r"));  // should be false
        System.out.println();

        System.out.println("Anagram Indices: " + findAnagramIndices("zyxwyxywxzxyz", "wxyz"));
        System.out.println();

        System.out.println("Result: " + orderAlphaSumNums("AND456HSE8"));
        System.out.println("Result: " + orderAlphaSumNums("99ZZD4H8SXE8"));
        System.out.println("Result: " + orderAlphaSumNums("ZYXCBA"));
        System.out.println("Result: " + orderAlphaSumNums("9328471"));
        System.out.println();

        findNumInMatrix(3, matrix);
        findNumInMatrix(5, matrix);
        findNumInMatrix(2, matrix);
        findNumInMatrix(7, matrix);
        System.out.println();

        createSpiralMatrix(2);
        createSpiralMatrix(3);
        createSpiralMatrix(4);
        createSpiralMatrix(7);
        System.out.println();

        System.out.println("Result: " + stringCompression("ZxxxxbbbbXddddYeeeeccccc"));
        System.out.println();

        twoCirclesTest(circle1, circle2);
        twoCirclesTest(circle3, circle4);
        twoCirclesTest(circle5, circle6);
        twoCirclesTest(circle7, circle8);
        twoCirclesTest(circle9, circle9);

    }

// *******************************************************************************
    public static void twoCirclesTest(int[] c1, int[] c2) {
        //ex17: given center points for two circles and their corresponding radii,
        //       determine if the circle circumferences:  1) don't intersect
        //                                                2) intersect at two points
        //                                                3) intersect at one point
        //                                                4) intersect one point and circle 1/2 inside 2/1
        //                                                5) don't intersect but circle 1/2 inside 2/1
        //                                                6) same circle
        // input format: int array [x, y, radius]

        System.out.print("C1:(" + c1[0] + "," + c1[1] + ")  Radius:" + c1[2] + "  ");
        System.out.println("C2:(" + c2[0] + "," + c2[1] + ")  Radius:" + c2[2]);

        double ptDist=0;
        int diffX = (c1[0]-c2[0]);
        int diffY = (c1[1]-c2[1]);
        int c1Rad = (c1[2]);
        int c2Rad = (c2[2]);

        // calculate distance between two points
        ptDist = Math.sqrt((diffX*diffX)+(diffY*diffY));
        //System.out.println(ptDist + "," + c1Rad + "," + c2Rad);

        if (ptDist == 0) {  // the center points are the same point
            if (c1Rad > c2Rad) {
                System.out.println("Circles don't intersect. Circle 2 inside Circle 1.");
            } else if (c2Rad > c1Rad) {
                System.out.println("Circles don't intersect. Circle 1 inside Circle 2.");
            } else { // c1Rad == c2Rad
                System.out.println("Circle 1 and Circle 2 are Coincident(the same circle).");
            }
        }else if (ptDist > (c1Rad+c2Rad)) {       // circles are separate
            System.out.println("Circles don't intersect.");
        } else if ((ptDist == c1Rad) && (2*ptDist == c2Rad)) {
                System.out.println("Circles intersect at 1 point. Circle 1 is inside Circle 2.");
        } else if ((ptDist == c2Rad) && (2*ptDist == c1Rad)) {
                System.out.println("Circles intersect at 1 point. Circle 2 is inside Circle 1");
        } else if (ptDist == (c1Rad + c2Rad)) {
                System.out.println("Circles intersect at 1 point.");
        } else if (ptDist < (c1Rad+c2Rad)) {
            System.out.println("Circles intersect at 2 points.");
        }

    }


// *******************************************************************************
    public static String stringCompression(String str) {
        //ex16: given string with duplcate chars, remove and replace duplicates with dupe count
        //   e.g.  given: "ZxxxxbbbbXddddYeeeeccccc"  result: "Zx4b4Xd4Ye4c5"

        System.out.println("Given: " + str);
        String outStr=""; Integer cntr=1; char chr2;

        for (int i=0; i< str.length(); i++) {
            if (i < str.length() - 1) {         // this check protects reading past input str length
                chr2 = str.charAt(i + 1);
            } else {
                chr2 = '\00'; // set chr2 to NUL so it reads a known char beyond str.length
            }
            if (str.charAt(i) == chr2) {    //checks for successive chars in the string
                cntr++;                     // increment chr counter
            } else {
                outStr += str.charAt(i);    // append char to output string
                if (cntr > 1) {                 // only add lengths > 1 to output string
                    outStr += cntr.toString();
                }
                cntr = 1;       // reset repeat char counter
            }
        }
        return outStr;
    }

// *******************************************************************************
    public static void createSpiralMatrix(int size) {
        //ex15: create a square 2D matrix of size n x n with increasing values in spiral
        //
        //   e.g. n=4  expected output:   {{1,  2,  3,  4},
        //                                {12, 13, 14,  5},
        //                                {11, 16, 15,  6},
        //                                {10,  9,  8,  7}};


        int[][] matrix = new int[size][size];
        LinkedList<LinkedList<Integer>> resultList = new LinkedList<>();
        LinkedList<Integer> subList = new LinkedList<>();

        int carX= -1;  int carY= 0;             // carry forward cell x,y points
        int sqr = size * size;
        boolean moreSides = true;

        while (moreSides) { // while loop breaks when pair(x,y) point count equals matrix size*size

            resultList = incrX(carX, carY, size, resultList);
            subList = resultList.getLast();         // the last points in list carry forward to next method
            carX = subList.getFirst();
            carY = subList.getLast();
            if (resultList.size()== sqr) { break; }    // break if last cell found

            resultList = incrY(carX, carY, --size, resultList); //size of sublist reduced
            subList = resultList.getLast();
            carX = subList.getFirst();
            carY = subList.getLast();
            if (resultList.size()== sqr) { break; }

            resultList = decrX(carX, carY, size, resultList);
            subList = resultList.getLast();
            carX = subList.getFirst();
            carY = subList.getLast();
            if (resultList.size()== sqr) { break; }

            resultList = decrY(carX, carY, --size, resultList);  //size of sublist reduced
            subList = resultList.getLast();
            carX = subList.getFirst();
            carY = subList.getLast();
            if (resultList.size()== sqr) { break; }
        }
        // at this point, the resultlist contains all of the points of the spiral in sequence
        //     (in clockwise order starting from 0,0)

        for (int i=0; i < sqr; i++) {
            subList = resultList.get(i);    // at each (x,y)-point element in the resultList
            carX = subList.getFirst();
            carY = subList.getLast();
            matrix[carY][carX] = i+1;   //add the corresponding cell value(i + 1) to the 2D matrix
        }

        for (int[] row : matrix) {              // print out the 2D spiral matrix values, by row
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    //  ********************************************************************************************
    // The below static methods incrX(), incrY(), decrX(), decrY(), generate the cell(x,y) points by
    //  incrementing/decrementing each side of the matrix where the passed in point x or y will
    //  necessarily increase or decrease to generate the spiral effect. Once calculated, each point
    //  pair(x,y) is added to the resultList for later processing to generate the spiral matrix.

    public static LinkedList<LinkedList<Integer>> incrX (int xPt, int yPt, int len,
                                                         LinkedList<LinkedList<Integer>> bigList) {
        for (int i = 0; i < len; i++) {
            LinkedList<Integer> cells = new LinkedList<>();
            cells.add(++xPt); cells.add(yPt);
            bigList.add(cells);
        }
        return bigList;
    }
    public static LinkedList<LinkedList<Integer>> incrY (int xPt, int yPt, int len,
                                                         LinkedList<LinkedList<Integer>> bigList) {
        for (int i=0; i < len; i++){
            LinkedList<Integer> cells = new LinkedList<>();
            cells.add(xPt); cells.add(++yPt);
            bigList.add(cells);
        }
        return bigList;
    }
    public static LinkedList<LinkedList<Integer>> decrX (int xPt, int yPt, int len,
                                                         LinkedList<LinkedList<Integer>> bigList) {
        for (int i=len; i > 0; i--){
            LinkedList<Integer> cells = new LinkedList<>();
            cells.add(--xPt); cells.add(yPt);
            bigList.add(cells);
        }
        return bigList;
    }
    public static LinkedList<LinkedList<Integer>> decrY (int xPt, int yPt, int len,
                                                         LinkedList<LinkedList<Integer>> bigList) {
        for (int i=len; i > 0; i--){
            LinkedList<Integer> cells = new LinkedList<>();
            cells.add(xPt); cells.add(--yPt);
            bigList.add(cells);
        }
        return bigList;
    }


// *******************************************************************************
    public static void findNumInMatrix(int num, int[][] matrix) {
        //ex14: given an 2D MxN matrix and number value, find all instances of that value in the matrix
        //   e.g. given: n= 3 and {2, 3, 5,    result: [(1,0), (0,1), (1,2)]  (row, column)
        //                         3, 2, 1,
        //                         1, 3, 5}

        System.out.println("Given number to find in matrix: " + num);
        for (int[] row : matrix) {              // print out the 2D matrix
            System.out.println(Arrays.toString(row));
        }

        LinkedList<LinkedList<Integer>> resultList = new LinkedList<>();

        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix.length; j++) {
                if (matrix[i][j] == num) {                      // if the number is found:
                    LinkedList<Integer> subList = new LinkedList<>(); // create new subList object
                    subList.add(j); subList.add(i);     // add the values to the subList
                    resultList.add(subList);        // add the subList to the resultList
                }
            }
        }
        if (resultList.isEmpty()) {
            System.out.println("Number " + num + " not found.");
        } else {
            System.out.println("Result Found Cells[m,n]: " + resultList);
        }
        System.out.println();
    }


// *******************************************************************************
    public static String orderAlphaSumNums(String str) {
        //ex13: given string with mixed alpha(uppercase)/numeric chars,
        //      order alpha, sum num and append to alpha string
        //    e.g. given: "AND456HSE8"  result: "ADEHNS23"  (4+5+6+8 = 23)

        System.out.print("Given: " + str + "   ");

        ArrayList<Character> numList = new ArrayList<>();
        ArrayList<Character> alphaList = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= '0') && (c <= '9')){
                numList.add(c);             // create list of numbers
            }else {
                alphaList.add(c);           // create list of alpha chars
            }
        }

        Collections.sort(alphaList);        // sort the alphaList

        Integer sumInt=0;
        for (char c: numList) {
            Integer tmpInt = Integer.parseInt("" + c);
            sumInt += tmpInt;
        }

        String sumStr = String.valueOf(sumInt);
        if (sumStr.equals("0")) {
            sumStr = "";
        }

        String acc="";
        for (char c: alphaList) {
            String tmpStr = String.valueOf(c);
            acc += tmpStr;
        }
                 //System.out.println(sumStr);
                 //System.out.println(numList);
        return acc+sumStr;
    }

// *******************************************************************************
    public static List<Integer> findAnagramIndices(String baseStr, String anagram) {
        //ex12: find indices of all instances of given anagram within given baseStr
        //     e.g. baseStr: zyxwyxywxzxyz anagram: wxyz   result: indices: [0, 6]

        System.out.print("Anagram Str: " + anagram);
        System.out.println("   Base Str: " + baseStr);

        List<String> permList;
        List<Integer> indicesList = new ArrayList<>();

        permList = getPermutationList(anagram);     // get the set of permutations for anagram string
        System.out.println("Anagram Permutations: " + permList);

        for (String perm: permList) {               // for each permutation in permList
            if (baseStr.contains(perm)) {               // if that permutation is found in baseStr
                indicesList.add(baseStr.indexOf(perm));     //  add it to the indices list
            }
        }

        return indicesList;
    }

    public static List<String> getPermutationList(String str) {
        // base case
        if (str == null || str.isEmpty()) {
            return null;
        }

        // create an empty ArrayList to store (partial) permutations
        List<String> partial = new ArrayList<>();

        // initialize the list with the first character of the string
        partial.add(String.valueOf(str.charAt(0)));

        // do for every character of the specified string
        for (int i = 1; i < str.length(); i++) {
            // consider previously constructed partial permutation one by one

            // (iterate backward to avoid ConcurrentModificationException)
            for (int j = partial.size() - 1; j >= 0; j--) {
                // remove current partial permutation from the ArrayList
                String s = partial.remove(j);

                // Insert the next character of the specified string at all
                // possible positions of current partial permutation. Then
                // insert each of these newly constructed strings in the list

                for (int k = 0; k <= s.length(); k++) {
                    // Advice: use StringBuilder for concatenation
                    partial.add(s.substring(0, k) + str.charAt(i) + s.substring(k));
                }
            }
        }
        return partial;
    }

// *******************************************************************************
    public static boolean checkIsomorphic (CharSequence cs1, CharSequence cs2) {
        //ex11: check if cs1 and cs2 are isomorphic
        //      isomorphic means each char in cs1 has an exclusive 1:1 correspondence with the same
        //      char in cs2 for every instance and location of those chars in both strings
        //      e.g. cs1: abca  cs2: abxa   Isomorphic? result: yes.
        //        cs1: abcbcac  cs2: xfgfgxg   Isomorphic? result: yes.
        //        cs1: abcbacgg  cs2: nprpndbb     Isomorphic? result: no. 'c' mapped to 'r' and 'd'

        System.out.print("cs1: " + cs1 + "   ");
        System.out.print("cs2: " + cs2 + "   ");

        // create hashmap with key value pairs <char,char>
        HashMap<Character, Character> hashMap = new HashMap<>();

        // lengths of strings must be equal to be considered Isomorphic
        if (cs1.length() != cs2.length()) { return false; }

        hashMap.put(cs1.charAt(0), cs2.charAt(0));       // add first two <key,value> pair from input

        for (int i = 1; i < cs1.length(); i++) {
            if (hashMap.containsKey(cs1.charAt(i))) {               // if this cs1 char in hashmap
                if (cs2.charAt(i) != hashMap.get(cs1.charAt(i))) {    // if cs2 char NOT same as mapped cs1 value
                        return false;                               // cs1 and cs2 are not Isomorphic
                }
            } else {
                hashMap.put(cs1.charAt(i), cs2.charAt(i));    // add cs1,cs2 chars as <key,value> pair in hashmap
            }
        }

        return true;
    }

// *******************************************************************************
    public static void addOneToArrayValue(int[] digitArray) {
        //ex10: add one to the value of an array of single digit integers
        //    e.g. given: [9, 9, 9] result: [1, 0, 0, 0]

        System.out.print("Given Int Array:");
        for (int h : digitArray) {                  // print out input array
            System.out.print(h);
        }

        Integer intVal = 0; int ten = 1;
        String tmpNumStr, tmpIntStr;

        for (int i = digitArray.length -1 ; i >= 0; i--) {  // start at end of array
            intVal += digitArray[i] * ten;                  // multiply int value x 10
            ten *= 10;                                      // update digit multiplier
        }

        intVal++;                               // add one to Num value
        tmpNumStr = intVal.toString();          // convert Num Integer back to String

        int[] arrayPlusOne = new int[tmpNumStr.length()];       //  set output array size = Num string length

        for (int j = 0; j < tmpNumStr.length(); j++) {          //
            tmpIntStr = "" + tmpNumStr.charAt(j);           // the '+' makes the single chr a str
            arrayPlusOne[j] = Integer.parseInt(tmpIntStr);  // set values in Int array
        }

        System.out.print("   +1 Result:");
        for (int k : arrayPlusOne) {            // print out result array
            System.out.print(k);
        }
        System.out.println();
    }

// *******************************************************************************
    public static boolean ckSubString (String str1, String str2) {
        //ex9: check if str1 is a substring of str2
        //     **  rewrite "str2.contains(str1)"  **

        System.out.println("Check Substring1 in String2");
        System.out.println("Str1: " + str1);    // print out input values
        System.out.println("Str2: " + str2);

        int ptr1=0, ptr2=0;  // string pointers

        // failfast - not a substring if str1 longer than str2
        if ( str1.length() > str2.length()) { return false; }

        while (ptr2 < str2.length()){               // loop boundary is length of str2
            if (str1.charAt(ptr1) == str2.charAt(ptr2)) {
                ptr1++;                 // if chars in strings are equal, advance ptr1 only
            } else {
                ptr1 = 0;               // else return ptr1 to 0 index
            }
            if (ptr1 >= str1.length()) { // if ptr1 past last chr in str1, str1 is a substring
                return true;
            }
            ptr2++;                     // advance ptr2
        }
        return false;       // default is false
    }

// *******************************************************************************
    public static String reverseWords (String str) {
        //ex8: Reverse the words in a sentence
        //     e.g. sentence a in words the Reverse

        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        Stack<String> stack = new Stack<>();
        String revStr=""; String space=" ";

        System.out.println("Given String: " + str);       // input string

        while (tokenizer.hasMoreElements()) {       // "tokenize" string and push onto a stack
            String word = tokenizer.nextToken();
            //System.out.println(word);
            stack.push(word);
        }
        while(!stack.isEmpty()) {               // pop words off stack (is in reverse order)
            if (stack.size() == 1) {            //  and concatenate to build result
                revStr += stack.pop();          //  reversed sentence
            } else {
                revStr += stack.pop() + space;  // add spaces between words only
            }
        }
        return revStr;
    }

// *******************************************************************************
    public static String divideUsingSubtraction (Integer dividend, Integer divisor) {
        //ex7: divide two integers using subtraction operator (no div - multiply ok);
        //     one decimal accuracy; include negative integers and handle divide by zero error
        //      note: use of '*' necessary to handle negative input


        System.out.print("Dividend:" + dividend + "  ");
        System.out.println ("Divisor:" + divisor);

        String q, f;    // string equivalents of integer values
        Integer quotient = 0, fraction = 0;
        boolean negOne = false;     //flag if need to add '-' to output

        //  verify divisor > 0
        if (divisor == 0) { return "Divide by 0 Error"; }  // punt on divide by zero nonsense

        // manage negative values here
        if ((dividend < 0) && (divisor < 0)) {
            dividend *= -1;
            divisor *= -1;
        } else if (dividend < 0) {
                    negOne = true;
                    dividend *= -1;
        } else if (divisor < 0) {
                    negOne = true;
                    divisor *= -1;
        }

        while (dividend >= divisor) {   // calculate quotient using subtraction
            dividend -= divisor;
            quotient++;
        }

        dividend *= 10;
        while (dividend >= divisor) {   // calculate fraction using subtraction
            dividend -= divisor;
            fraction++;
        }

        q = quotient.toString();    // convert Integers to strings
        f = fraction.toString();

        if (negOne) {
            return "-" + q + "." + f;
        } else {
            return q + "." + f;
        }
    }

// *******************************************************************************
    public static boolean checkSubstringPermutation(String str1, String str2) {
        //ex6: check if str1 is a permutation of str2, case ignored
        // My solution: ensure equal string lengths; set each string to lower case
        //      Check str2 for every chr in str1
        //      when true, remove that chr instance from str2.  If permutation is true,
        //       str2 will be empty string "", confirming 1:1 chr correspondence

        System.out.print("S1: " + str1 + "  ");
        System.out.println("S2: " + str2);

        boolean isSub = false;

        if (str1.length() == str2.length()) {               // must have equal lengths if str1 is perm of str2
        str1 = str1.toLowerCase();              // set both strings to lower case
        str2 = str2.toLowerCase();              // this allows case to be ignored

            for (int i = 0; i < str1.length(); i++) {
                if (str2.contains(str1.substring(i, i+1))) {
                    str2 = str2.replace(str1.substring(i, i+1), "");
                }
            }
            if (str2.isEmpty()) { isSub = true; }
        }

        return isSub;
    }

// *******************************************************************************
    public static Integer firstUniqueChar (String str) {
        //ex5: find the index of the first non-repeated (unique) character in a given string

        //System.out.println(str);
        HashMap<String, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            // is chr a key in the hashmap?
            if (hashmap.containsKey (str.substring(i, i+1))) {
                //replace existing key value = -1
                hashmap.replace(str.substring(i, i+1), -1 );
            } else{
                // add new key with value = string index of chr
                hashmap.put(str.substring(i, i+1), i);
            }
        }
        // System.out.println(hashmap);

        Integer minIndex = str.length() - 1;  // initial minIndex = max index of str

        // Iterate through hashmap values; identify single instance elements; set minIndex
        for (Integer value: hashmap.values()) {
           if  ((value > 0) && (value < minIndex)) {
               minIndex = value;
           }
        }
        return minIndex;
    }

// *******************************************************************************
    public static boolean checkAllDistinctChars (String str) {
        //ex4: check if a given string has all distinct characters (one instance only for all chars)
        //  e.g. "xyyz" result: false  "xuram"  result: true

        //  My solution uses a hashmap which does not hold duplicate key/value pairs.
        //  Therefore, any duplicate chars found in the given string will not be added
        //  to the hashmap.
        //  If the size of the hashmap = length of given string, then all chars must be distinct.

        boolean distinctChars = false;
        HashMap<String, String> hashmap = new HashMap<>();

        for (Integer i = 0; i < str.length(); i++) {  // loop to insert each char of str into hashmap
            hashmap.put(str.substring(i, i+1), i.toString());
        }

        if ((hashmap.size() == str.length())) {  //check size of hashmap = length of given string
            distinctChars = true;
        }

        return distinctChars;
    }

// *******************************************************************************
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
        String sX, sY;

        if ((x < perim) && (y < perim)) {  // check only up to the perimeter of the maze
            if ((mazeMap[y][x + 1] == 0) && (mazeMap[y + 1][x] == 0)) {
                code = "E";     // both R and D are 0
            } else if (mazeMap[y][x + 1] == 0) {
                code = "R";     // R is 0

            } else if (mazeMap[y + 1][x] == 0) {
                code = "D";     // D is 0

            }
        }

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

// *******************************************************************************
    public static int distinctStairTraversals(int stairsCount) {
        //ex2: find the number of distinct ways to climb n stairs, taking either 1 or 2 steps per traversal

        System.out.println("Number of stairs: " + stairsCount);
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
            ArrayList<Integer> permutate = (ArrayList<Integer>) tries.clone();

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

// *******************************************************************************
    public static List<Integer> findSingleOccurrence (Integer[] numbers) {
        //ex1: (updated)
        // find _all_numbers_ that appear only once in a given array

        //  e.g. input: {10, 20, 10, 20, 20, 30, 40, 40, 30, 50, 60, 60, 5, 10}   result: 50, 5

        System.out.println("Given array: " + Arrays.toString(numbers));

        ArrayList<Integer> nonDuplicate = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer num: numbers) {        // for each number in the given array of numbers
            if (map.containsKey(num)) {               // if the map already contains the number/key
                map.replace(num, map.get(num)+1);     // replace the corresponding value with existing value+1
            } else {
                map.put(num, 1);          //  if not in map, add num and value = 1
            }
        }

      for (Integer key: map.keySet()) {     // for each key/value pair in the map
          if (map.get(key) == 1) {              // if the value = 1
              nonDuplicate.addFirst(key);       //  add the key to the nonDuplicate array
          }
      }

        return nonDuplicate;    // return the non-duplicate list containing the only non-duplicate number

    }

}