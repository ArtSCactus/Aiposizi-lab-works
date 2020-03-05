package org.bsuir.rowmappers;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public interface RowMapper<T> {
    T map(ResultSet resultSet);

    Type getJsonMeta();

}
