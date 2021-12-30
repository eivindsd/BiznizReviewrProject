package it.unipi.lsmsdb.biznizreviewrproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class BiznizReviewrProjectApplication {
    public BiznizReviewrProjectApplication(){}



    public static void main(String[] args) {
        SpringApplication.run(BiznizReviewrProjectApplication.class, args);
    }

}
