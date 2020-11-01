package com.leron.exceptions;

public class ReimbursementNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ReimbursementNotFoundException() {
		super();
	}

	public ReimbursementNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReimbursementNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReimbursementNotFoundException(String message) {
		super(message);
	}

	public ReimbursementNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
