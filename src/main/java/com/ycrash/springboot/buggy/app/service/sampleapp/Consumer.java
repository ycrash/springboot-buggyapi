package com.ycrash.springboot.buggy.app.service.sampleapp;

public class Consumer extends Thread {

	@Override
	public void run() {
		
		String data = null;
		long counter = 0;
		while (true) {
			
			try {
			
				// 1 minute
				Thread.currentThread().sleep(1 * 60 * 1000);				
			} catch (Exception e) { e.printStackTrace();}
			
			SampleAppDemoService.s_map.clear();
			//if ((counter % THRESHOLD) == 0) {
			System.out.println("Consumer thread consumed all Objects");
				//s_map.clear();
			//}
		}		
	}
	
}
