package model;

public class StudentExistsException extends Exception { 
    public StudentExistsException(String errorMessage) {
        super(errorMessage);
    }
}
