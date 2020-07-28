package com.west.business.pojo.pub;

import com.west.business.util.date.DateUtils;
import lombok.Data;

/**
 * description: [统一返回封装Bean]
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2019/4/22
 */
@Data
public class ResResult<T> {

	/**
	 * 操作成功返回提示信息
	 */
	private static final String FAILED_MESSAGE = "操作失败";
	
	/**
	 * 操作失败返回提示信息
	 */
	private static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 仅标识响应成功的返回提示信息,不建议标识本次操作结果
     */
    private static final String DEFAULT_MESSAGE = "响应成功";

	/**
	 * 返回状态码
	 */
	private Integer status;

    /**
     * 提示信息
     */
    private String message;

	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 操作结束时间
	 */
	private String dealTime;

	public static <T>  ResResult<T> result(boolean isSuccess) {
		return isSuccess ? success() : fail();
	}

	public static <T>  ResResult<T> resultAddData(boolean isSuccess, T data) {
		return isSuccess ? successAddData(data) : failAddData(data);
	}


	public static <T>  ResResult<T> success() {
		//返回一个只带200状态码的结果对象（请求成功对象）
		ResResult<T> result = new  ResResult<T>();
		result.setStatus(200);
		result.setMessage(SUCCESS_MESSAGE);
		result.setDealTime(DateUtils.getSysTime());
		return result;
	}

    public static <T>  ResResult<T> success(String message) {
	    // 为不同习惯的开发者而写
        return successAddMessage(message);
    }

    public static <T>  ResResult<T> success(String message, T data) {
        //返回一个带200状态码并且附带自定义信息的结果对象
        ResResult<T> result = success();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T>  ResResult<T> successAddMessage(String message) {
        //返回一个带200状态码并且附带失败信息的结果对象
        ResResult<T> result = success();
        result.setMessage(message);
        return result;
    }

    public static <T>  ResResult<T> successAddData(T data) {
        //返回一个带200状态码并且附带所请求数据的结果对象
        ResResult<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T>  ResResult<T> resResult(int code) {
        //返回一个附带自定义状态码的结果对象
        ResResult<T> result = new  ResResult<T>();
        result.setStatus(code);
        result.setMessage(DEFAULT_MESSAGE);
        result.setDealTime(DateUtils.getSysTime());
        return result;
    }

    public static <T>  ResResult<T> resResult(int code, T data) {
        //返回一个附带自定义状态码和数据的结果对象
        ResResult<T> result = resResult(code);
        result.setData(data);
        return result;
    }

    public static <T>  ResResult<T> resResult(int code, String message, T data) {
        //返回一个带200状态码并且附带自定义信息的结果对象
        ResResult<T> result = resResult(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

	public static <T>  ResResult<T> fail() {
		//返回一个只带500状态码的结果对象（请求失败对象）
		ResResult<T> result = new  ResResult<T>();
		result.setStatus(500);
		result.setMessage(FAILED_MESSAGE);
		result.setDealTime(DateUtils.getSysTime());
		return result;
	}

    private static <T>  ResResult<T> fail(T data) {
        //返回一个带500状态码的结果对象,并且附带数据
        return failAddData(data);
    }

    private static <T>  ResResult<T> fail(String message, T data) {
        //返回一个带500状态码的结果对象,并且附带自定义提示和数据
        ResResult<T> result = fail();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

	public static <T>  ResResult<T> failAddMessage(String message) {
		//返回一个带500状态码并且附带失败信息的结果对象
		ResResult<T> result = fail();
		result.setMessage(message);
		return result;
	}

    private static <T>  ResResult<T> failAddData(T data) {
        //返回一个带500状态码的结果对象,并且附带数据
        ResResult<T> result = fail();
        result.setData(data);
        return result;
    }


}
