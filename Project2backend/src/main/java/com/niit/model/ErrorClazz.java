package com.niit.model;

public class ErrorClazz 
{
	
	
	public ErrorClazz(int errorCode, String errorMsg) {
		super();
		ErrorCode = errorCode;
		ErrorMsg = errorMsg;
	}
	private int ErrorCode;
	private String ErrorMsg;
	public int getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

}
