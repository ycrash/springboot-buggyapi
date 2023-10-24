package com.ycrash.springboot.buggy.app.service.memoryleak;

public class Object3 {

	MapManager mapManager = new MapManager();

	public void grow() {
		
		mapManager.grow();
	}		
}
