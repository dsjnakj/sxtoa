package com.yjh.util;
/**
 * 创建自己的异常类
 * @author Administrator
 *
 */
public class MyException extends RuntimeException{

	//编写构造器调用父类
	public MyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
