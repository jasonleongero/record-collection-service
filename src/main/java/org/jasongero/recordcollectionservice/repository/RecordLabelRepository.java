package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasongero.recordcollectionservice.entity.RecordLabel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RecordLabelRepository extends BaseRepository {
    private static final String GET_RECORD_LABEL_QUERY = getSqlQuery("getRecordLabel");
    
    public static RecordLabel getRecordLabelById(long recordLabelId) throws Exception {
        return jdbcOperations.queryForObject(GET_RECORD_LABEL_QUERY, new Long [] {recordLabelId}, new RecordLabelRowMapper());
    }
    
    private static class RecordLabelRowMapper implements RowMapper<RecordLabel> {
        public RecordLabel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new RecordLabel(
                    rs.getLong("record_label_id"),
                    rs.getString("name")
            );
        }
    }
}
