package com.ycrash.springboot.buggy.app.service.memoryleak;

public class Object1 {

	Object2 object2 = new Object2();
	
	public void grow() {
	
		object2.grow();
	}
}
