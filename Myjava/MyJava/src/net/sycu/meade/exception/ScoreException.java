package net.sycu.meade.exception;

public class ScoreException extends RuntimeException {		//同其他运行时异常
	public ScoreException() {
		super("成绩输入错误");
	}
}
