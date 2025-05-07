import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListWordSortCount {
    static int counter = 0; // Counter for primitive operations

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words;

        while (true) {
            System.out.println("Enter words separated by space (any length):");
            String input = scanner.nextLine().trim();
            counter += 3; // assignment
            words = input.toLowerCase().split("\\s+");
            counter += 3; // assignment + toLowerCase + split operation 
            counter++; // comparison
            if (words.length > 0) break;
            System.out.println("Error: No words entered. Please try again.");
        }

        // Pad words with spaces to match the longest word
        int maxLength = 0;
        counter++; // assignment
        for (String word : words) {
            counter++; // loop condition
            counter++; // comparison
            if (word.length() > maxLength) {
                maxLength = word.length();
                counter++; // assignment
            }
        }

        counter++; // assignment
        for (int i = 0; i < words.length; i++) {
            counter += 2; // loop condition + increment
            words[i] = String.format("%-" + maxLength + "s", words[i]); // padding
            counter += 2; // assignment + format operation
        }

        String[] sorted = radixSort(words, maxLength);
        counter += 2; // assignment + radixSort call

        // Final output (trim padding)
        System.out.println("\nFinal sorted result:");
        for (String word : sorted) {
            counter++; // loop condition
            System.out.print(word.trim() + " ");
        }

        System.out.println("\n\nTotal primitive operations: " + counter);
        scanner.close();
    }

    private static String[] radixSort(String[] words, int maxLength) {
        System.out.println("1. Initialization");

        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();
        counter += 2; // assignments

        counter++; // assignment
        for (int i = 0; i < 27; i++) {
            counter += 2; // loop condition + increment
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
            counter += 2; // add operations
        }

        System.out.println("2. Sorting");
        counter++; // assignment
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            counter += 2; // loop condition + decrement
            System.out.println("\nAfter processing character at index " + (digitIndex + 1));
            
            counter += 2; // comparison and arithmetic operation
            if (digitIndex == maxLength - 1) {
                for (String word : words) {
                    counter++; // loop condition
                    int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                    counter += 3; // assignment + getBucketIndex + charAt
                    Array1.get(bucketIndex).add(word);
                    counter += 2; // get operation + add operation
                }
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                counter += 4; // 2 substract + modulus + comparison
                counter++; // assignment
                for (int i = 0; i < 27; i++) {
                    counter += 2; // loop condition + increment
                    for (String word : Array1.get(i)) {
                        counter += 2; // nested loop + get operation
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                        counter += 2; // assignment + getBucketIndex call +charAt
                        Array2.get(bucketIndex).add(word);
                        counter += 2; // get operation + add operation
                    }
                }
                for (ArrayList<String> bucket : Array1) {
                    counter++; // loop
                    bucket.clear();
                    counter++; // clear operation
                }
            } else {
                counter += 4; // 2 substract + modulus + comparison
                counter++; //assignment
                for (int i = 0; i < 27; i++) {
                    counter += 2;
                    for (String word : Array2.get(i)) {
                        counter++; // nested loop
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                        counter += 3; // assignment + getBucketIndex call + charAt
                        Array1.get(bucketIndex).add(word);
                        counter += 2; // get operation + add operation
                    }
                }
                for (ArrayList<String> bucket : Array2) {
                    counter++; // loop
                    bucket.clear();
                    counter++; // clear operation
                }
            }
        }

        System.out.println("3. Reorder");
        String[] finalArray = new String[words.length];
        counter++; // assignment
        int index = 0;
        counter++; //assignment

        counter += 3; // substract, modulus, comparison
        if ((maxLength - 1) % 2 == 0) {
            counter++; // assignment
            for (int i = 0; i < 27; i++) {
                counter += 2; // loop condition + increment
                for (String word : Array1.get(i)) {
                    counter++; // nested loop
                    finalArray[index++] = word;
                    counter += 2; // assignment + increment
                }
            }
        } else {
            counter++; // assignment
            for (int i = 0; i < 27; i++) {
                counter += 2; // loop condition + increment
                for (String word : Array2.get(i)) {
                    counter++; // nested loop
                    finalArray[index++] = word;
                    counter += 2; // assignment + increment
                }
            }
        }

        counter++; // return
        return finalArray;
    }

    private static int getBucketIndex(char c) {
        counter += 2; // comparison + return
        if (c == ' ') return 0;
        counter += 2; // 2 arrithmetic operations
        return c - 'a' + 1;
    }

}