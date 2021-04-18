package com.between.prueba.util;

import com.between.prueba.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class Login {

	@Value("${app.testing.uri}")
	private String uriMs;
	@Value("${app.testing.user}")
	private String user;
	@Value("${app.testing.password}")
	private String password;

	@Autowired
	private TestRestTemplate testRestTemplate;

	public String getToken() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> formEntity = new HttpEntity<>(null, headers);
		String token = testRestTemplate.exchange(uriMs + "/token" + "/" + user + "/" + password,
				HttpMethod.GET, formEntity, TokenResponse.class)
				.getBody().getAccessToken();
		return token;
	}

	public HttpHeaders obtenerHeaders(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		return headers;
	}

	public String getUriMs() {
		return uriMs;
	}

}
