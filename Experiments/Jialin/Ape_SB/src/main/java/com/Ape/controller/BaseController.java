/**
 * 
 */
package com.Ape.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.response.ComReturnType;

/**
 * @author Miao Xu
 *
 */
public class BaseController {

	public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
	public static final String CONTENT_TYPE_JSON = "application/json";//;charset=UTF-8
	
	//deal exception doesn't handle by controller.
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object handlerException(HttpServletRequest request, Exception e) {
		
		Map<String, Object> responseData = new HashMap<>();
		if(e instanceof BusException) {
			BusException busException = (BusException)e;
			responseData.put("errorCode", busException.getErrorCode());
			responseData.put("errorMsg", busException.getErrorMsg());
		}else {
			responseData.put("errorCode", EnumBusError.UNKNOWN_ERROR.getErrorCode());
			responseData.put("errorMsg", EnumBusError.UNKNOWN_ERROR.getErrorMsg());
		}
		return ComReturnType.create("fail", responseData);
	}
}
