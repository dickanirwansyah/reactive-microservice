/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.service;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

/**
 *
 * @author dickanirwansyah
 */

@Service
@Slf4j
public class DataSetupService implements CommandLineRunner{

    @Value("classpath:h2/init.sql")
    private Resource initSql;
    
    @Autowired
    private R2dbcEntityTemplate entityTemplate;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("started setup data service..");
        String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
        log.info("query -> {} ",query);
        this.entityTemplate.getDatabaseClient()
                .sql(query)
                .then()
                .subscribe();
    }
    
    
    
}
