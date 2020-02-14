package org.bsuir.rowmappers;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T map(ResultSet resultSet);
}
