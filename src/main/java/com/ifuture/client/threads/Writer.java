package com.ifuture.client.threads;

import com.ifuture.client.dto.AmountRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Writer implements Runnable {

	private static final int SLEEP_TIME = 3;
	private static final Random RANDOM = new Random();

	private final RestTemplate restTemplate;
	private final List<Integer> list;
	private final String url;

	public Writer(List<Integer> idList, RestTemplate restTemplate, String url) {
		this.list = Objects.requireNonNull(idList);
		this.restTemplate = Objects.requireNonNull(restTemplate);
		this.url = Objects.requireNonNull(url);
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			try {
				list.forEach(id -> addAmount(id, RANDOM.nextLong()));
				TimeUnit.SECONDS.sleep(SLEEP_TIME);
			} catch (Exception ex) {
			}
		}
	}

	private void addAmount(int id, long amount) {
		val request = AmountRequest.builder()
				.id(id)
				.value(amount)
				.build();

		log.info("Request for create id: {}, amount: {}", id, amount);
		restTemplate.postForEntity(URI.create(url + "/amount/add"), request, Map.class);
	}

}
