/**
 * 
 */
package com.Ape.error;

/**
 * @author Miao Xu
 *
 */
public interface ComError {

	public int getErrorCode();
	
	public String getErrorMsg();
	
	public ComError setErrorMsg(String errorMsg);
}
