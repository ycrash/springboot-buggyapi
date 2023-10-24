package com.ycrash.springboot.buggy.app.service.deadlock;

import org.springframework.stereotype.Service;

@Service
public class DeadLockDemoService {
	
	public void start() {
		
		System.out.println("App started");
		new ThreadA().start();
		new ThreadB().start();
	}
}
