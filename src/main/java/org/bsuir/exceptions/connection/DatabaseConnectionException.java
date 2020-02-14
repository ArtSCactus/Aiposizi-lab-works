package org.bsuir.exceptions.connection;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() {
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(Exception e){
        super(e);
    }

    public String getMessage() {
        return super.getMessage();
    }

}
