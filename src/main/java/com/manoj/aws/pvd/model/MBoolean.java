package com.manoj.aws.pvd.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MBoolean {
	private boolean value;
	private String message;

	/**
	 * @param value
	 * @param message
	 */
	private MBoolean(boolean value, String... message) {
		this.value = value;
		this.message = Stream.of(message).collect(Collectors.joining(" "));
		;
	}

	public static MBoolean newTrue() {
		return new MBoolean(true);
	}

	public static MBoolean newTrue(String... message) {
		return new MBoolean(true, message);
	}

	public static MBoolean newFalse() {
		return new MBoolean(false);
	}

	public static MBoolean newFalse(String... message) {
		return new MBoolean(false, message);
	}

	/**
	 * @return the value
	 */
	public boolean value() {
		return value;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	public void setTrue() {
		this.value = true;
	}

	public void setTrue(String... message) {
		this.value = true;
		this.message = Stream.of(message).collect(Collectors.joining(" "));
	}

	public void setFalse() {
		this.value = false;
	}

	public void setFalse(String... message) {
		this.value = false;
		this.message = Stream.of(message).collect(Collectors.joining(" "));
	}

}
