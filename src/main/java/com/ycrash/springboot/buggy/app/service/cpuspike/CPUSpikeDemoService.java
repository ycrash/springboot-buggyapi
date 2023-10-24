package com.ycrash.springboot.buggy.app.service.cpuspike;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Ram Lakshmanan
 */

@Service
public class CPUSpikeDemoService {

	public void start() {
		
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
		new CPUSpikerThread().start();
	}
}
