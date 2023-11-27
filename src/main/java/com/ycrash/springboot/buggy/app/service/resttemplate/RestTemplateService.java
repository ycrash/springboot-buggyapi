package com.ycrash.springboot.buggy.app.service.resttemplate;

import java.io.File;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {


	public void loadRestClientCalls(Integer noOfCalls, String url, String imagePath) {
		int i = 0;
		while (i < noOfCalls) {
			restClientCall(i,url, imagePath);
			i++;
		}
	}

	public void restClientCall(Integer id, String url,String imagePath) {

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Prepare the image file
        File imageFile = new File(imagePath);

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Prepare the request body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new org.springframework.core.io.FileSystemResource(imageFile));

        // Create the HTTP entity with headers and the multipart body
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Perform the POST request
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        // Print the response status code and body
        System.out.println("Response Code id "+id +":"+ responseEntity.getStatusCode());
        System.out.println("Response Body: id "+id +":"+  responseEntity.getBody());
	}
}
