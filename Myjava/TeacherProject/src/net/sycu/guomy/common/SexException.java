package net.sycu.guomy.common;

public class SexException extends RuntimeException {

	public SexException() {
		super("性别应该是“男”或“女”。");
	}

}
