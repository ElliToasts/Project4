/* Programmed by Anthony Ellinos
   Assignment: Project 4
   MovieDriver class 
   Reads movies from a file, seperates them 
   from invalid entries, applies a discount, 
   and displays both lists */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDriver {

    public static void main(String[] args) {
        ArrayList<MovieItem> movies = new ArrayList<>(); // valid movies
        ArrayList<Exception> errors = new ArrayList<>(); // invalid records

        Scanner keyboard = new Scanner(System.in);
        Scanner input = null;
        String prompt = "Enter a file name: ";

        // use the file name from the command line if one was given
        if (args.length > 0) {
            input = openFile(args[0]);
            prompt = "Enter another file name: ";
        }

        // keep asking until a file opens
        while (input == null) {
            System.out.print(prompt);
            String filename = keyboard.nextLine().trim();
            input = openFile(filename);
            prompt = "Enter another file name: ";
        }

        // read the file one line at a time and keeping a line counter
        int lineNum = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            lineNum++;

            try {
                movies.add(new BluRayMovie(line));
            } catch (InvalidRecordException e) {
                // wrap the error with its line number
                errors.add(new Exception("Line #" + lineNum + ", " + e.getMessage()));
            }
        }
        input.close();

        // if file is empty end the program
        if (lineNum == 0) {
            System.out.println("ERROR: File is empty.");
            keyboard.close();
            return;
        }

        // apply the same discount to every movie that can be discounted
        double discount = promptDiscount(keyboard);
        // for each m in movies apply a discount if m is discountable
        for (MovieItem m : movies) {
            if (m instanceof Discountable) {
                ((Discountable) m).applyDiscount(discount);
            }
        }

        displayItems(movies);
        displayErrors(errors);
        keyboard.close();
    }

    // tries to open the file and returns a Scanner for it, 
    // or null if it can't be opened
    private static Scanner openFile(String filename) {
        try {
            return new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Cannot open " + filename + ".");
            return null;
        }
    }

    // prints every valid movie using its toString
    private static void displayItems(ArrayList<MovieItem> books) {
        System.out.println("-------------------");
        System.out.println("Movies");
        System.out.println("-------------------");

        if (books.isEmpty()) {
            System.out.println("No valid movies.");
            System.out.println("-------------------");
        }
        for (MovieItem m : books) {
            System.out.println(m);
            System.out.println("-------------------");
        }
    }

    // prints every error message
    private static void displayErrors(ArrayList<Exception> errors) {
        System.out.println("Lines with problems");
        System.out.println("-------------------");

        for (Exception e : errors) {
            System.out.println(e.getMessage());
        }
    }

    // keeps asking until the user enters a finite number that is 0 or more
    private static double promptDiscount(Scanner keyboard) {
        while (true) {
            System.out.print("Enter a discount percentage to apply (0 for none): ");
            String text = keyboard.nextLine().trim();

            try {
                double discount = Double.parseDouble(text);
                // rejects negatives, NaN, and Infinity
                if (discount >= 0 && Double.isFinite(discount)) {
                    return discount;
                }
            } catch (NumberFormatException e) {
                // nonnumeric or blank input falls through to the error below
            }
            System.out.println("ERROR: Enter a finite, non-negative number.");
        }
    }
}