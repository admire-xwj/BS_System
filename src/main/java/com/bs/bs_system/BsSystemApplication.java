package com.bs.bs_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.bs.bs_system.mapper")
public class BsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsSystemApplication.class, args);
    }

}
