package net.sycu.guomy.common;

public class SalaryException extends RuntimeException {

	public SalaryException() {
		super("无效工资数据。");
	}

}
