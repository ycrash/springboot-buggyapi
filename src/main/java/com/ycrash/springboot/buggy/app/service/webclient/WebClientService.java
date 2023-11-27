package com.ycrash.springboot.buggy.app.service.webclient;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

	public void loadWebClientCalls(Integer noOfCalls, String url) {
		int i = 0;
		while (i < noOfCalls) {
			webClientCall(i,url);
			i++;
		}
	}

	public void loadWebClientCalls(Integer noOfCalls, String url, String imagePath) {
		int i = 0;
		while (i < noOfCalls) {
			webHeavyClientCall(i,url, imagePath);
			i++;
		}
	}

	public void webClientCall(Integer id, String url) {
		WebClient webClient = WebClient.create(url);

		webClient.get().retrieve().bodyToMono(String.class).subscribe(result -> {
			// Handle the response
			System.out.println("Response: " + result);
		}, error -> {
			// Handle errors
			System.err.println("Error: " + error.getMessage());
		}, () -> {
			// This block is called when the response is successfully processed
			System.out.println("Request completed "+id);
		});
		System.out.println("waiting...");

	}

	public void webHeavyClientCall(Integer id,String url, String imagePath) {

		// Create a WebClient instance
		WebClient webClient = WebClient.create();

		// Prepare the image file
		File imageFile = new File(imagePath);

		// Perform the POST request with the image as a part of the request body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(imageFile));

		webClient.post().uri(url).contentType(MediaType.MULTIPART_FORM_DATA).body(BodyInserters.fromMultipartData(body))
				.retrieve().bodyToMono(String.class).subscribe(response -> {
					System.out.println("Response Id"+id+ ":" + response);
				});
	}
}
