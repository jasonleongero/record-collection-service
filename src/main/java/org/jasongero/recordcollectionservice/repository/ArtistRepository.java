package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jasongero.recordcollectionservice.entity.Artist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ArtistRepository extends BaseRepository {
    static final String GET_ARTIST_QUERY = getSqlQuery("getArtist");
    static final String GET_ARTISTS_QUERY = getSqlQuery("getArtists");
    static final String CREATE_ARTIST_QUERY = getSqlQuery("createArtist");
    
    public static Artist getArtistById(long artistId) throws Exception {                
        return jdbcOperations.queryForObject(GET_ARTIST_QUERY, new Long [] {artistId}, new ArtistRowMapper());
    }
    
    public static List<Artist> getArtists() {        
        return jdbcOperations.query(GET_ARTISTS_QUERY, new ArtistRowMapper());
    }
    
    private static class ArtistRowMapper implements RowMapper<Artist> {
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Artist(rs.getLong("artist_id"), rs.getString("name"));
        }
    }
    
    public static void createArtist(String artistName) {
        jdbcOperations.update(CREATE_ARTIST_QUERY, new Object [] {artistName});
    }
}
