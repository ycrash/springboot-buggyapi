package com.ycrash.springboot.buggy.app.service.oomcrash;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Ram Lakshmanan
 */
@Service
public class OOMCrashService {

	
	public void start() {
		
		Map<String, String> map = new HashMap<>();
			
		long counter = 0;
		while (true) {
		
			counter++;
			map.put("key-" + counter, "Large stringgggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg"
		               + "ggggggggggggggggggggggggggggggggggggggggggggggggggggg" + counter);
			
			if (counter % 1000 == 0) {
				System.out.println("Added " + counter + " elements");
			}
		}
		
	}
}
