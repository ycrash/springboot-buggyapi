package com.ycrash.springboot.buggy.app.service.stackoverflow;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ycrash.springboot.buggy.app.service.threadleak.ThreadLeakDemoService;

@Service
public class StackOverflowDemoService {

	public int counter = 1000;
	
	private List<SimpleObject> objects = new ArrayList<>(); 
	private static final Logger log = LoggerFactory.getLogger(StackOverflowDemoService.class);

	public void start() {
		
		++counter;

		SimpleObject so0 = new SimpleObject("Simple Object created");
		SimpleObject so1 = new SimpleObject("Simple Object created");
		SimpleObject so2 = new SimpleObject("Simple Object created");
		SimpleObject so3 = new SimpleObject("Simple Object created");
		SimpleObject so4 = new SimpleObject("Simple Object created");
		SimpleObject so5 = new SimpleObject("Simple Object created");
		SimpleObject so6 = new SimpleObject("Simple Object created");
		SimpleObject so7 = new SimpleObject("Simple Object created");
		SimpleObject so8 = new SimpleObject("Simple Object created");
		SimpleObject so9 = new SimpleObject("Simple Object created");

		/*SimpleObject so10 = new SimpleObject("Simple Object created");
		SimpleObject so11 = new SimpleObject("Simple Object created");
		SimpleObject so12 = new SimpleObject("Simple Object created");
		SimpleObject so13 = new SimpleObject("Simple Object created");
		SimpleObject so14 = new SimpleObject("Simple Object created");
		SimpleObject so15 = new SimpleObject("Simple Object created");
		SimpleObject so16 = new SimpleObject("Simple Object created");
		SimpleObject so17 = new SimpleObject("Simple Object created");
		SimpleObject so18 = new SimpleObject("Simple Object created");
		SimpleObject so19 = new SimpleObject("Simple Object created");

		SimpleObject so20 = new SimpleObject("Simple Object created");
		SimpleObject so21 = new SimpleObject("Simple Object created");
		SimpleObject so22 = new SimpleObject("Simple Object created");
		SimpleObject so23 = new SimpleObject("Simple Object created");
		SimpleObject so24 = new SimpleObject("Simple Object created");
		SimpleObject so25 = new SimpleObject("Simple Object created");
		SimpleObject so26 = new SimpleObject("Simple Object created");
		SimpleObject so27 = new SimpleObject("Simple Object created");
		SimpleObject so28 = new SimpleObject("Simple Object created");
		SimpleObject so29 = new SimpleObject("Simple Object created");
		
		SimpleObject so30 = new SimpleObject("Simple Object created");
		SimpleObject so31 = new SimpleObject("Simple Object created");
		SimpleObject so32 = new SimpleObject("Simple Object created");
		SimpleObject so33 = new SimpleObject("Simple Object created");
		SimpleObject so34 = new SimpleObject("Simple Object created");
		SimpleObject so35 = new SimpleObject("Simple Object created");
		SimpleObject so36 = new SimpleObject("Simple Object created");
		SimpleObject so37 = new SimpleObject("Simple Object created");
		SimpleObject so38 = new SimpleObject("Simple Object created");
		SimpleObject so39 = new SimpleObject("Simple Object created");*/

		if (counter % 1000 == 0) {	
			log.info("Looped " + counter + " times");
		}
		counter++;
		
		start();		
	}
	
}
