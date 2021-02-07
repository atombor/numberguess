package com.tombor.numberguess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class NumberguessApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberguessApplication.class, args);
    }
//
//    @PostConstruct
//    private void initDb() {
//        String sqlStatements[] = {
//                "insert into employees(first_name, last_name) values('Donald','Trump')",
//                "insert into employees(first_name, last_name) values('Barack','Obama')"
//        };
//
//        Arrays.asList(sqlStatements).forEach(sql -> {
//            jdbcTemplate.execute(sql);
//        });
//
//        // Fetch data using SELECT statement and print results
//    }

}
