/**
 * 
 */
package com.Ape.response;

/**
 * @author Miao Xu
 *
 */
public class ComReturnType {

	//"success" or "fail".
	private String status;
	
	//if status == "success", return json data.
	//else return errorMsg.
	private Object data;
	
	public static ComReturnType create(Object result) {

		return ComReturnType.create("success", result);
	}
	
	public static ComReturnType create(String status, Object result) {

		ComReturnType type = new ComReturnType();
		type.setStatus(status);
		type.setData(result);
		return type;
	}
	
	public void setStatus(String status) {

		this.status = status;
	}
	
	public String getStatus() {

		return status;
	}
	
	public void setData(Object data) {

		this.data = data;
	}
	
	public Object getData() {

		return data;
	}
}
