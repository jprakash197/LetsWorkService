package com.mindtree.letswork.exception;

public class LetsWorkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LetsWorkException() {
		super();
	}

	public LetsWorkException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public LetsWorkException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LetsWorkException(String arg0) {
		super(arg0);
	}

	public LetsWorkException(Throwable arg0) {
		super(arg0);
	}

}
