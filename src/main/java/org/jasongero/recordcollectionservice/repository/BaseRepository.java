package org.jasongero.recordcollectionservice.repository;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.jdbc.core.JdbcOperations;

abstract public class BaseRepository {
    protected static JdbcOperations jdbcOperations;
    
    protected static String getSqlQuery(String sqlFile) {
        try {
            String filePath = String.format("repositoryqueries/%s.sql", sqlFile);
            byte[] encoded = Files.readAllBytes(Paths.get(ReleaseRepository.class.getResource(filePath).toURI()));
            
            return new String(encoded, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void setJdbcOperations(JdbcOperations jdbcOperations) {
        BaseRepository.jdbcOperations = jdbcOperations;
    }
}
