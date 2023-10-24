package com.ycrash.springboot.buggy.app.service.metaspaceleak;

import org.springframework.stereotype.Service;

import javassist.ClassPool;

@Service
public class MetaspaceLeakService {

    public  void start() throws Exception {
        
    	long startTime = System.currentTimeMillis();
    	
        ClassPool classPool = ClassPool.getDefault();
        for (int i = 0; i < 750_000; i++) {
            
        	if (i % 50_000 == 0) {
        		
                System.out.println(i + " new classes created");
            }
        	
        	// Keep creating classes dynamically!
        	classPool.makeClass("com.buggyapp.metaspaceleak.MetaspaceObject" + i).toClass();
        }
        
        System.out.println("Program Exited: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
    }
}
