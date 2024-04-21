package ru.msu.cmc.webprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebApp {

    public static void main(String[] args) {

        try {
            SpringApplication.run(WebApp.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Here!");
    }
}