package com.ycrash.springboot.buggy.app.service.filenio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;

@Service
public class FileNIOService {
	
	public void loadWebClientCalls(Integer noOfCalls, String url) {
		int i =0;
		while( i < noOfCalls) {
			fileClientCall(url);
			i++;
		}
	}

	public  void fileClientCall(String url) {
        // Specify the path to the file
        Path filePath = Paths.get("path/to/your/file.txt");

        // Check if the file exists
        if (Files.exists(filePath)) {
            try {
                // Read all lines from the file
                Files.lines(filePath).forEach(System.out::println);

                // Alternatively, you can read all bytes from the file
                byte[] fileBytes = Files.readAllBytes(filePath);
                System.out.println(new String(fileBytes));

                // Write content to a file
                String contentToWrite = "Hello, NIO!";
                Files.write(filePath, contentToWrite.getBytes(), StandardOpenOption.WRITE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist.");
        }
	}
}
