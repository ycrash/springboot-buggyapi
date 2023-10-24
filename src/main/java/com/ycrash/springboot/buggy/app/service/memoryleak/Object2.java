package com.ycrash.springboot.buggy.app.service.memoryleak;

public class Object2 {
	
	Object3 object3 = new Object3();

	public void grow() {

		object3.grow();
	}	
}
