/**
 * 
 */
package com.Ape.error;

/**
 * @author Miao Xu
 *
 */

public enum EnumBusError implements ComError{
	
	//common error type starts 10000.
	PARAMETER_VALIDATIOIN_ERROR(10001, "Invalid parameter"),
	UNKNOWN_ERROR(10002, "Unknown error"),
	
	//start with 20000 means error in users info.
	USER_NOT_EXISIS(20001, "User not exists"),
	USER_LOGIN_FAIL(2002, "Username or password not correct")
	;

	private int errorCode;
	private String errorMsg;
	
	private EnumBusError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	@Override
	public int getErrorCode() {

		return this.errorCode;
	}

	@Override
	public String getErrorMsg() {

		return this.errorMsg;
	}

	@Override
	public ComError setErrorMsg(String errorMsg) {

		this.errorMsg = errorMsg;
		return this;
	}

}
