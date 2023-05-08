package com.udacity.jwdnd.course1.cloudstorage.exception;

public class CloudStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String[] arguments;

	public CloudStorageException() {
		super();
	}

	public CloudStorageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CloudStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public CloudStorageException(String message) {
		super(message);
	}

	public CloudStorageException(Throwable cause) {
		super(cause);
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
}
