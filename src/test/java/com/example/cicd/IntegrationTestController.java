package com.example.cicd;

import com.example.cicd.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CicdApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTestController {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@DisplayName("Integration tests")
	@Test
	void contextLoads() {

		User user = this.restTemplate
				.getForObject("http://localhost:" + port + "/vehicles/user/1", User.class);
		assertTrue(user.getId()==1);

		User userNegativeTest = this.restTemplate
				.getForObject("http://localhost:" + port + "/vehicles/user/1", User.class);

		assertFalse(userNegativeTest.getId() == 3);
	}

}
