package com.bs.bs_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class BsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsSystemApplication.class, args);
    }

}
