package com.campusactivity.common.config;



import com.campusactivity.common.util.JsonData;
import com.campusactivity.common.util.JsonMapper;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Aspect
@RestControllerAdvice(value = "com.campusactivity.core")
public class DataResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof JsonData) {
            return o;
        } else if (o instanceof String) {
            JsonData<Object> dataResponse = new JsonData<>(200, "success", o);
            return JsonMapper.obj2String(dataResponse);
        } else {
            return new JsonData<>(200, "success", o);
        }
    }
}
