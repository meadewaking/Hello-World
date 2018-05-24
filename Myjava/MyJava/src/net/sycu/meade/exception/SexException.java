package net.sycu.meade.exception;

public class SexException extends RuntimeException {		//同其他运行时异常
	public SexException() {
		super("该性别不合法");
	}
}
