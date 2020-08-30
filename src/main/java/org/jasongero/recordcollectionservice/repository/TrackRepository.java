package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jasongero.recordcollectionservice.entity.Track;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TrackRepository extends BaseRepository {
    private static final String GET_TRACKS_BY_RELEASE_QUERY = getSqlQuery("getTracksByRelease");
    private static final String CREATE_TRACK_QUERY = getSqlQuery("createTrack");
    
    public static List<Track> getTracksByRelease(long releaseId) {
        return jdbcOperations.query(GET_TRACKS_BY_RELEASE_QUERY, new Long [] {releaseId}, new TrackRowMapper());
    }
    
    private static class TrackRowMapper implements RowMapper<Track> {
        public Track mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Track(
                    rs.getLong("release_id"),
                    rs.getLong("order"),
                    rs.getString("track_number"),
                    rs.getLong("duration"),
                    rs.getString("title")
            );
        }
    }
    
    public static void createTrack(long releaseId, long order, String trackNumber, long duration, String title) {
        Object [] preparedQueryArguments = new Object [] {
            releaseId,
            order,
            trackNumber,
            duration,
            title
        };
        
        jdbcOperations.update(CREATE_TRACK_QUERY, preparedQueryArguments);
    }
}
