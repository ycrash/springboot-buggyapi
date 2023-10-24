package com.ycrash.springboot.buggy.app.service.httpconnectionleak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class HttpConnectionLeak {
	private final String HTTP_CONNECTION_URL = "https://tier1app.com/"; // Replace with the URL you want to fetch

	/**
	 * Connects to a configured http url and does it close it.
	 */
	public void connect() {
		try {
			URL webUrl = new URL(HTTP_CONNECTION_URL);

			HttpURLConnection connection = (HttpURLConnection) webUrl.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			System.out.println("Leaking http connection");
			System.out.println("Response Code: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = reader.readLine();
			} else {
				System.out.println("Failed to fetch content.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connectineoly leaking http connections 
	 */
	public void start() {
		while(true) {
			connect();
		}
	}
}