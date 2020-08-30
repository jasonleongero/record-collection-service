package org.jasongero.recordcollectionservice.springconfiguration;

import javax.annotation.PostConstruct;

import org.jasongero.recordcollectionservice.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
public class StaticFieldInjectionConfiguration {
    @Autowired
    private JdbcOperations jdbcOperations;
    
    @PostConstruct
    private void init() {        
        BaseRepository.setJdbcOperations(jdbcOperations);
    }
}
