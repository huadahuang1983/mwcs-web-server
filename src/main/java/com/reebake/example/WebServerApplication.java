package com.reebake.example;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableGracefulResponse
public class WebServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebServerApplication.class, args);
    }
}
