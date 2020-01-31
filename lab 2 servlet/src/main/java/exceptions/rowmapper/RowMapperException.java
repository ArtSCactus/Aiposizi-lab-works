package exceptions.rowmapper;

import java.sql.SQLException;

public class RowMapperException extends Throwable {
    public RowMapperException(SQLException e) {
    }
}
