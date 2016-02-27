package org.teapot.servlet;

public class TeapotException extends Exception {

	private Throwable nested = null;

	public TeapotException() {
		super();
	}

	public TeapotException(String msg) {
		super(msg);
	}

	public TeapotException(Throwable nested) {
		super();
		this.nested = nested;
	}

	public TeapotException(String msg, Throwable nested) {
        super(msg);
        this.nested = nested;
    }
}
