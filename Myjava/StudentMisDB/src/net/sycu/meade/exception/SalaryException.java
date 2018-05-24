package net.sycu.meade.exception;

public class SalaryException extends RuntimeException {		//运行时异常都是RuntimeException的派生类，这种异常不需要显式处理；

	public SalaryException() {
		super("工资错误");
		// TODO Auto-generated constructor stub
	}

}
