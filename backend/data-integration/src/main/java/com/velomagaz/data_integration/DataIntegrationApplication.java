package com.velomagaz.data_integration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataIntegrationApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DataIntegrationApplication.class, args);
	}

    @Override
    public void run(String... args) {
        System.out.println("Started with CommandLineRunner");
    }
}
