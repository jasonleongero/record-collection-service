package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jasongero.recordcollectionservice.entity.Format;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class FormatRepository extends BaseRepository {
    private static final String GET_FORMATS_QUERY = getSqlQuery("getFormats");
    private static final String CREATE_FORMAT_QUERY = getSqlQuery("createFormat");
    
    public static List<Format> getFormats(long releaseId) {
        return jdbcOperations.query(GET_FORMATS_QUERY, new Long [] {releaseId}, new FormatRowMapper());
    }
    
    private static class FormatRowMapper implements RowMapper<Format> {
        public Format mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Format(
                rs.getLong("release_id"),
                rs.getString("format_name"),
                rs.getLong("quantity"),
                rs.getString("notes")
            );
        }
    }
    
    public static void createFormat(long releaseId, String formatName, long quantity, String notes) {
        Object [] preparedQueryArguments = new Object [] {
            releaseId,
            formatName,
            quantity,
            notes
        };
        
        jdbcOperations.update(CREATE_FORMAT_QUERY, preparedQueryArguments);
    }
}
