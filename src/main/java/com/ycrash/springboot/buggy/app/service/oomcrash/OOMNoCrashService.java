package com.ycrash.springboot.buggy.app.service.oomcrash;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Ram Lakshmanan
 */
@Service
public class OOMNoCrashService {

	
	public void start() throws Exception {
		
		try {
			
			Map<String, String> map = new HashMap<>();
			
			long counter = 0;
			while (true) {
			
				map.put("key-" + counter, "value-" + counter);
				
				counter++;				
				if (counter % 1000 == 0) {
					System.out.println("Added " + counter + " elements");
				}
			}
		} catch (Throwable e) {

			System.out.println(e.getClass() + " caught! Application will not crash.");
			doSomework();
		}		
	}
	
	public static void doSomework() {
		
		System.out.println("2 + 2 = " + (2 + 2));
	}
}
