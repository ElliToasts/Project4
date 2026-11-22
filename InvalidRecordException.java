/* Programmed by Anthony Ellinos
   Assignment: Project 4
   InvalidRecordException class 
   Checked exception is thrown when a line 
   in the file has invalid/missing data */

public class InvalidRecordException extends Exception {
    public InvalidRecordException() {
        super();
    }

    // adds a prefix to every message thrown
    public InvalidRecordException(String err) {
        super("Invalid record: " + err);
    }
}
