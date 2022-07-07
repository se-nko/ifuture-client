package com.ifuture.client.service;

import com.ifuture.client.threads.Reader;
import com.ifuture.client.threads.Writer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

	@Value("${service.url}") private String url;
	@Value("${rCount}") private Integer rCount;
	@Value("${wCount}") private Integer wCount;
	@Value("${idList}") private List<Integer> idList; // List is read only. No threads add new data.

	public void start() {
		val restTemplate = new RestTemplate();
		while (rCount-- != 0)
			ForkJoinPool.commonPool().execute(new Reader(idList, restTemplate, url));
		while (wCount-- != 0)
			ForkJoinPool.commonPool().execute(new Writer(idList, restTemplate, url));
	}

}
