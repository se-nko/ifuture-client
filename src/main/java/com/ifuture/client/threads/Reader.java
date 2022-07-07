package com.ifuture.client.threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Reader implements Runnable {

	private static final int SLEEP_TIME = 2;

	private final RestTemplate restTemplate;
	private final List<Integer> list;
	private final String url;

	public Reader(List<Integer> idList, RestTemplate restTemplate, String url) {
		this.list = Objects.requireNonNull(idList);
		this.restTemplate = Objects.requireNonNull(restTemplate);
		this.url = Objects.requireNonNull(url);
	}

	@SneakyThrows
	@Override
	public void run() {
		while (true) {
			list.forEach(this::getAmount);
			TimeUnit.SECONDS.sleep(SLEEP_TIME);
		}
	}

	private void getAmount(int id) {
		log.info("Request for get amount for id: {}", id);
		val amount = restTemplate.getForEntity(url + "/amount/" + id, Long.class);
	}

}
