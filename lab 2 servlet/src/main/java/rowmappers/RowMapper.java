package rowmappers;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T map(ResultSet resultSet);
}
