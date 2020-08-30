package org.jasongero.recordcollectionservice.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jasongero.recordcollectionservice.entity.Release;
import org.jasongero.recordcollectionservice.entity.ReleaseWithArtist;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class ReleaseRepository extends BaseRepository {
    private static final String GET_RELEASES_WITH_ARTIST_QUERY = getSqlQuery("getReleasesWithArtist");
    private static final String GET_RELEASE_QUERY = getSqlQuery("getRelease");
    private static final String GET_RELEASES_BY_MASTER_RELEASE_QUERY = getSqlQuery("getReleasesByMasterRelease");
    private static final String CREATE_RELEASE_QUERY = getSqlQuery("createRelease");

    public static List<ReleaseWithArtist> getReleases() throws Exception {        
        return jdbcOperations.query(GET_RELEASES_WITH_ARTIST_QUERY, new ReleaseWithArtistRowMapper());
    }
    
    public static Release getRelease(long releaseId) throws Exception {        
        return jdbcOperations.queryForObject(GET_RELEASE_QUERY, new Long [] { releaseId }, new ReleaseRowMapper());
    }
    
    public static List<Release> getReleasesByMasterRelease(long masterReleaseId) {        
        return jdbcOperations.query(GET_RELEASES_BY_MASTER_RELEASE_QUERY,  new Long [] {masterReleaseId}, new ReleaseRowMapper());
    }
    
    private static class ReleaseRowMapper implements RowMapper<Release> {
        public Release mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Release(
                    rs.getLong("artist_id"),
                    rs.getLong("master_release_id"),
                    rs.getLong("release_id"),
                    rs.getString("title"),
                    rs.getString("catalog_number"),
                    rs.getString("record_label_name"),
                    rs.getString("country_name"),
                    rs.getByte("release_month"),
                    rs.getByte("release_day"),
                    rs.getShort("release_year"),
                    rs.getString("primary_image_url")
            );
        }
    }
    
    private static class ReleaseWithArtistRowMapper implements RowMapper<ReleaseWithArtist> {
        public ReleaseWithArtist mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReleaseWithArtist(
                    rs.getLong("artist_id"),
                    rs.getLong("master_release_id"),
                    rs.getLong("release_id"),
                    rs.getString("title"),
                    rs.getString("catalog_number"),
                    rs.getString("record_label_name"),
                    rs.getString("country_name"),
                    rs.getByte("release_month"),
                    rs.getByte("release_day"),
                    rs.getShort("release_year"),
                    rs.getString("primary_image_url"),
                    rs.getString("artist_name")
            );
        }
    }
    
    public static long createRelease(
        long masterReleaseId,
        String title,
        long releaseMonth,
        long releaseDay,
        long releaseYear,
        String catalogNumber,
        long recordLabelId,
        String countryIsoCode,
        String imageUrl,
        boolean isKey
    ) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcOperations.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(CREATE_RELEASE_QUERY, new String [] {"release_id"});
                
                ps.setLong(1, releaseYear);
                ps.setLong(2, releaseMonth);
                ps.setLong(3, releaseDay);
                ps.setString(4, title);
                ps.setString(5, catalogNumber);
                ps.setString(6, countryIsoCode);
                ps.setLong(7,  recordLabelId);
                ps.setInt(8, isKey ? 1 : 0);
                ps.setLong(9, masterReleaseId);
                
                return ps;
            }
        }, keyHolder);
        
        return keyHolder.getKey().longValue();
    }
}
