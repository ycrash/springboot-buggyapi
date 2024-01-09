package com.ycrash.springboot.buggy.app.service.fileconnectionleak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ycrash.springboot.buggy.app.service.dbconnectionleak.DBConnectionLeakService;

/**
 * Service to simulate file connection leak 
 *
 */
@Service
public class FileConnectionLeakService {
	private static final Logger log = LoggerFactory.getLogger(DBConnectionLeakService.class);


	private static final String SAMPLE_FILE_NAME = "buggyapp-samplefile.txt";

	/**
	 * Connects to a sample file and does it close it.
	 */
	public void connect() {
		BufferedReader reader = null;
		try {
			System.out.println("Leaking File connections new");
			reader = new BufferedReader(new FileReader(SAMPLE_FILE_NAME));
			String line;
			while ((line = reader.readLine()) != null) {
				//IO operations
			}
		} catch (Exception e) {
			log.error(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
            	log.error(element.toString());
            }
			
			e.printStackTrace();
		}

	}

	/**
	 * Creates a sample file
	 */
	public void createSampleFile() {
		try {

			FileWriter fileWriter = new FileWriter(new File(SAMPLE_FILE_NAME));
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 1; i <= 10; i++) {
				String line = "This is line " + i;
				bufferedWriter.write(line);
				bufferedWriter.newLine(); // Add a newline character
			}

			bufferedWriter.close();

			System.out.println("Sample file created ");
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	/**
	 * Connect and leak a configured number of times with a sleep, allowing the user to attach the process to an analyzer.
	 * @throws InterruptedException 
	 */
	public void start() throws InterruptedException {
		createSampleFile();
		Thread.sleep(1);
		while(true) {
			connect();
		}

	}

}
