package com.campusactivity.common.exception;


import com.campusactivity.common.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.ws.WebServiceException;


/**
 * @Author q1hang
 * @Description 异常处理器
 * @create: 2019-12-12 21:18
 **/
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(CustomException.class)
	public JsonData<?> handleCustomException(CustomException e){
		if (e instanceof SSOLoginException) {
			log.error("SSO登录异常:{}",e.getMessage());
			return new JsonData<>(401, e.getMessage());
		} else {
			log.error("业务系统异常", e);
			return new JsonData<>(e.getCode(), e.getMessage());
		}
	}

	@ExceptionHandler(RuntimeException.class)
	public JsonData<?> handleException(RuntimeException e){
		if(e instanceof IndexOutOfBoundsException) {
			log.error("访问下标越界", e);
			return new JsonData<>(500, "下标越界：" + e.getMessage());
		} else if (e instanceof DuplicateKeyException) {
			log.error("数据库重复主键", e);
			return new JsonData<>(500, "数据库重复主键");
		}
		else if (e instanceof NullPointerException) {
			log.error("空指针异常", e);
			return new JsonData<>(500, "空指针异常");
		}else if (e instanceof WebServiceException) {
			log.error("WebService异常", e);
			return new JsonData<>(500, "BPM连接超时", null);
		} else {
			logger.error("运行时异常", e);
			return new JsonData<>(500, e.getMessage());
		}
	}

	@ExceptionHandler(Exception.class)
	public JsonData<?> handleException(Exception e){
		if (e instanceof HttpRequestMethodNotSupportedException) {
			log.error("method 方法不支持", e);
			return new JsonData<>(500, e.getMessage());
		} else if (e instanceof HttpMediaTypeNotSupportedException) {
			log.error("不支持媒体类型", e);
			return new JsonData<>(500, e.getMessage());
		} else {
			logger.error("后台异常", e);
			return new JsonData<>(500, e.getMessage());
		}
	}
	
}
