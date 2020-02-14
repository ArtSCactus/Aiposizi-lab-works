package org.bsuir.exceptions.connection;

public class DriverNotFoundException extends Exception {

    public DriverNotFoundException() {
        super();
    }

    public DriverNotFoundException(String message) {
        super(message);
    }

    public DriverNotFoundException(Exception e){
        super(e);
    }

    @Override
    public String toString() {
        return "DriverNotFoundException{" +
                "message='" + getMessage() + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
