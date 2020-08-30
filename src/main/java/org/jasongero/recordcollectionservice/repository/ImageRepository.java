package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jasongero.recordcollectionservice.entity.Image;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageRepository extends BaseRepository {
    private static final String GET_IMAGES_QUERY = getSqlQuery("getImages");
    private static final String GET_PRIMARY_IMAGE_QUERY = getSqlQuery("getPrimaryImage");
    
    public static List<Image> getImages(long artistId, long masterReleaseId, long releaseId) {
        return jdbcOperations.query(GET_IMAGES_QUERY, new Long [] {artistId, masterReleaseId, releaseId}, new ImageRowMapper());
    }
    
    public static Image getPrimaryImage(long artistId, long masterReleaseId, long releaseId) throws Exception {
        return jdbcOperations.queryForObject(GET_PRIMARY_IMAGE_QUERY, new Long [] {artistId, masterReleaseId, releaseId}, new ImageRowMapper());
    }
    
    private static class ImageRowMapper implements RowMapper<Image> {
        public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Image(
                    rs.getLong("artist_id"),
                    rs.getLong("master_release_id"),
                    rs.getLong("release_id"),
                    rs.getString("image_url"),
                    rs.getBoolean("is_primary")
            );
        }
    }
}
