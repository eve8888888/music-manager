package com.bittech.musicmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class MusicManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicManagerApplication.class, args);
    }

}
