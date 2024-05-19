package se.kth.iv1350.sem3.integration;

/**
 * A custom exception class indicating something has gone wrong when connecting to the database when running the program.
 */
public class DatabaseConnectionException extends Exception{

    /**
     * A constructor for the {@link DatabaseConnectionException} class.
     * Is called upon when other methods throws this exception.
     * @param message The error message given from the method throwing the exception.
     */
    public DatabaseConnectionException(String message) {
        super(message);
        //System.out.println("This is the e.getMessage() for DatabaseCalleException: " + message);
        //System.out.println("You have reached the DataBaseCallException constructor");
    }
}
