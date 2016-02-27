package org.teapot.services;

public class TeapotServices {
	protected static TeapotServices instance = new TeapotServices();
	protected TeapotServices() {
		super();
	}
	public static TeapotServices getInstance() {
		return instance;
	}
}
