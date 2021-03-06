package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		System.out.println(restTemplate.getForEntity("http://localhost:2000/greeting", String.class).getBody());
	}

}
