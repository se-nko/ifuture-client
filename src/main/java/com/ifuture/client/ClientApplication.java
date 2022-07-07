package com.ifuture.client;

import com.ifuture.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ClientApplication implements CommandLineRunner {

	private final ClientService service;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}


	@Override
	public void run(String... args) {
		log.info("Client has started");
		service.start();
	}
}
