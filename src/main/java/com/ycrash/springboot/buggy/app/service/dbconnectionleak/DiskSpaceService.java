package com.ycrash.springboot.buggy.app.service.dbconnectionleak;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiskSpaceService {
	private static final Logger log = LoggerFactory.getLogger(DiskSpaceService.class);

	   public  void fillDiskSpace(String drive, String filePath, Integer percentageFill) {
	        // Create a File object with the specified file path
	        File file = new File(filePath);

	        // Check available space on the drive
	        long availableSpaceInBytes = getAvailableSpace(drive);
	        long fileSizeInBytes = (long) ((availableSpaceInBytes * percentageFill) /100);


	        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
	            // Set the file size by writing to it
	            randomAccessFile.setLength(fileSizeInBytes);

	            System.out.println("File created successfully at: " + file.getAbsolutePath() +
	                    " with size: " + fileSizeInBytes / (1024 * 1024) + " MB");
	        } catch (IOException e) {
	            System.err.println("Error creating the file: " + e.getMessage());
	        }
	    }

	    private static long getAvailableSpace(String drive) {
	        try {
	            FileStore fileStore = Files.getFileStore(FileSystems.getDefault().getPath(drive));
	            return fileStore.getUsableSpace();
	        } catch (IOException e) {
	            System.err.println("Error getting available space: " + e.getMessage());
	            return -1;
	        }
	    }

	

}
