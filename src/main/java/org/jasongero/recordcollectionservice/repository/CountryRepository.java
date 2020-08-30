package org.jasongero.recordcollectionservice.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasongero.recordcollectionservice.entity.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryRepository extends BaseRepository {
    private static final String GET_COUNTRY_QUERY = getSqlQuery("getCountry");
    
    public static Country getCountry(String countryIsoCode) throws Exception {
        return jdbcOperations.queryForObject(GET_COUNTRY_QUERY, new String [] {countryIsoCode}, new CountryRowMapper());
    }
    
    private static class CountryRowMapper implements RowMapper<Country> {
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Country(
                    rs.getString("country_iso_code"),
                    rs.getString("name")
            );
        }
    }
}
