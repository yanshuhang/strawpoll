package com.ysh.strawpoll;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ysh.strawpoll.dao")
public class StrawpollApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawpollApplication.class, args);
    }

}
