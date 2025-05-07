import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListWordSort {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String[] words;

        // Input loop to ensure the user enters at least one word
        while (true) {
            System.out.println("Enter words separated by space (any length):");
            String input = scanner.nextLine().trim();
            words = input.toLowerCase().split("\\s+"); // Split input into words, convert to lowercase for consistency
            if (words.length > 0) break; // Exit the loop if at least one word is entered
            System.out.println("Error: No words entered. Please try again.");
        }

        // Pad words with spaces to match the longest word in the list
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) maxLength = word.length(); // Find the length of the longest word
        }

        // Pad shorter words with spaces to ensure equal length for sorting
        for (int i = 0; i < words.length; i++) {
            words[i] = String.format("%-" + maxLength + "s", words[i]); // Pad each word with spaces to maxLength
        }

        // Call the radixSort method to sort the words based on characters from last to first
        String[] sorted = radixSort(words, maxLength);

        // Final output (trim the padding spaces before displaying)
        System.out.println("\nFinal sorted result:");
        for (String word : sorted) {
            System.out.print(word.trim() + " ");  // Trim padded spaces and print each sorted word
        }

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    private static String[] radixSort(String[] words, int maxLength) {
        System.out.println("1. Initialization");

        // Create two sets of 27 buckets (one for each letter of the alphabet + space)
        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();

        // Initialize 27 buckets, one for each letter of the alphabet and one for spaces
        for (int i = 0; i < 27; i++) { 
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
        }

        // Print output for 1. Initialization
        System.out.println("The inputs had been initialized into buckets.");
        System.out.println();

        System.out.println("2. Sorting");

        // Perform sorting based on each character in the words, starting from the last character
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            System.out.println("\nAfter processing character at index " + (digitIndex + 1));

            // First pass: Distribute words based on the character at the current position
            if (digitIndex == maxLength - 1) {
                for (String word : words) {
                    int bucketIndex = getBucketIndex(word.charAt(digitIndex));  // Get the bucket index based on character
                    Array1.get(bucketIndex).add(word);  // Add word to the corresponding bucket in Array1
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1); // Print the contents of the buckets after this pass
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) { // Odd passes: Move words from Array1 to Array2 based on the current character
                for (int i = 0; i < 27; i++) {
                    for (String word : Array1.get(i)) {
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex)); // Get the bucket index
                        Array2.get(bucketIndex).add(word);  // Add word to the corresponding bucket in Array2
                    }
                }
                System.out.println("Array2 after pass:");
                printBuckets(Array2); // Print the contents of Array2 after this pass
                
                // Clear Array1 for the next round of distribution
                for (ArrayList<String> bucket : Array1) bucket.clear();
            } else {
                // Even passes: Move words from Array2 to Array1 based on the current character
                for (int i = 0; i < 27; i++) {
                    for (String word : Array2.get(i)) {
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex)); // Get the bucket index
                        Array1.get(bucketIndex).add(word); // Add word to the corresponding bucket in Array1
                    }
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
                // Clear Array2 for the next round of distribution
                for (ArrayList<String> bucket : Array2) bucket.clear();
            }
        }

        System.out.println("3. Reorder");
        // Reorder the words into a final sorted array
        String[] finalArray = new String[words.length];
        int index = 0;

        // If the last pass used Array1, collect words from Array1, else collect from Array2
        if ((maxLength - 1) % 2 == 0) {
            for (int i = 0; i < 27; i++) {
                for (String word : Array1.get(i)) {
                    finalArray[index++] = word; // Add word to the final sorted array
                }
            }
        } else {
            for (int i = 0; i < 27; i++) {
                for (String word : Array2.get(i)) {
                    finalArray[index++] = word; // Add word to the final sorted array
                }
            }
        }

        return finalArray;
    }

    // Method to determine the bucket index for a character (space gets index 0, 'a' to 'z' gets index 1 to 26)
    private static int getBucketIndex(char c) {
        if (c == ' ') return 0; // If the character is a space, assign it to bucket 0
        return c - 'a' + 1; // For letters 'a' to 'z', assign them to buckets 1 to 26
    }

    // Method to print the contents of the buckets after each pass of the sorting process
    public static void printBuckets(ArrayList<ArrayList<String>> buckets) {
        // Loop through each bucket and print its contents
        for (int i = 0; i < buckets.size(); i++) {
            if (!buckets.get(i).isEmpty()) { // Only print non-empty buckets
                if (i == 0) {
                    System.out.print("Bucket [space]: "); // Special case for space
                } else {
                    char label = (char) ('A' + i - 1); // Convert index to character ('A' to 'Z')
                    System.out.print("Bucket " + label + ": "); // Print bucket label
                }
                 // Print each word in the current bucket
                for (String word : buckets.get(i)) {
                    System.out.print(word.trim() + " ");  // Trim padding and print the word
                }
                System.out.println(); // Print a new line after each bucket's contents
            }
        }
        System.out.println();
    }
}