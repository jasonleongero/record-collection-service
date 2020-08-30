package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jasongero.recordcollectionservice.entity.MasterRelease;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MasterReleaseRepository extends BaseRepository {
    private static final String GET_MASTER_RELEASE_QUERY = getSqlQuery("getMasterRelease");
    private static final String GET_MASTER_RELEASES_BY_ARTIST_QUERY = getSqlQuery("getMasterReleasesByArtist");
    private static final String CREATE_MASTER_RELEASE_QUERY = getSqlQuery("createMasterRelease");
    
    public static MasterRelease getMasterRelease(long masterReleaseId) throws Exception {
        return jdbcOperations.queryForObject(GET_MASTER_RELEASE_QUERY, new Long [] {masterReleaseId}, new MasterReleaseRowMapper());
    }
     
    public static List<MasterRelease> getMasterReleasesByArtist(long artistId) {
        return jdbcOperations.query(GET_MASTER_RELEASES_BY_ARTIST_QUERY, new Long [] {artistId}, new MasterReleaseRowMapper());
    }
    
    private static class MasterReleaseRowMapper implements RowMapper<MasterRelease> {
        public MasterRelease mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MasterRelease(
                rs.getLong("artist_id"),
                rs.getLong("master_release_id"),
                rs.getString("title"),
                rs.getString("primary_image_url"),
                rs.getLong("release_versions_count")
            );
        }
    }
    
    public static void createMasterRelease(String masterReleaseTitle, long artistId) {        
        jdbcOperations.update(CREATE_MASTER_RELEASE_QUERY, new Object [] {masterReleaseTitle, artistId});
    }
}
