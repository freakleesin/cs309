/**
 * 
 */
package com.Ape.error;

/**
 * @author Miao Xu
 *
 */
public class BusException extends Exception implements ComError{
	
	private ComError comError;
	
	//get parameter from EnumBusError to construct exception.
	public BusException(ComError comError) {
		
		super();
		this.comError = comError;
	}
	
	//get custom errorMsg to construct exception.
	public BusException(ComError comError, String errorMsg) {
		
		super();
		this.comError = comError;
		this.setErrorMsg(errorMsg);
	}
	
	@Override
	public int getErrorCode() {
	
		return this.comError.getErrorCode();
	}
	
	@Override
	public String getErrorMsg() {
	
		return this.comError.getErrorMsg();
	}
	
	@Override
	public ComError setErrorMsg(String errorMsg) {
	
		this.comError.setErrorMsg(errorMsg);
		return this;
	}
}
