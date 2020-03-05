package org.bsuir.rowmappers;

import org.bsuir.dto.Group;
import org.bsuir.exceptions.rowmapper.RowMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {
   private static final String ID_COL_NAME ="id";
   private static final String NAME_COL = "name";
   private static final String SPECIALTY_COL = "specialty";

   @Override
    public Group map(ResultSet resultSet) {
       try {
           return new Group(resultSet.getLong(ID_COL_NAME),
                   resultSet.getString(NAME_COL),
                   resultSet.getString(SPECIALTY_COL));
       } catch (SQLException e) {
           throw new RowMapperException(e);
       }
   }

    @Override
    public Class<Group> getJsonMeta() {
        return null;
    }
}
