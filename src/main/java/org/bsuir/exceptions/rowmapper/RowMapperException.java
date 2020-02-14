package org.bsuir.exceptions.rowmapper;

import java.sql.SQLException;

public class RowMapperException extends RuntimeException{
    public RowMapperException(SQLException e) {
    }
}
