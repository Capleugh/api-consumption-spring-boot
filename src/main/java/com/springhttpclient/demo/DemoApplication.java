package com.springhttpclient.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	private static final String POSTS_API_URL = "http://jsonplaceholder.typicode.com/posts";

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.header("accept", "application/json")
				.uri(URI.create(POSTS_API_URL))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//	parse JSON into objects
		ObjectMapper mapper = new ObjectMapper();
		List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {});

		posts.forEach(System.out::println);
	}
}
