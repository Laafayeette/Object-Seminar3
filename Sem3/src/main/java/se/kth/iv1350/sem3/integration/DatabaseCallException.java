package se.kth.iv1350.sem3.integration;

public class DatabaseCallException extends Exception{

    public DatabaseCallException(String message) {
        super(message);
        System.out.println("This is the e.getMessage() for DatabaseCalleException: " + message);
        System.out.println("You have reached the DataBaseCallException constructor");
    }
}
