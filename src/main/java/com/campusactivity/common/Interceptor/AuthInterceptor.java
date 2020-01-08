package com.campusactivity.common.Interceptor;

import com.campusactivity.common.exception.SSOLoginException;
import com.campusactivity.common.util.Constant;
import com.campusactivity.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author q1hang
 * @Description 登录拦截器
 * @create: 2019-12-12 20:47
 **/
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证请求里面是否有token
        String token = request.getHeader("token");
        log.info("token:" + token);
        Claims claims = JwtUtil.parseJWT(token);
        if (claims == null) {
            throw new SSOLoginException("请先登录接口后台", Constant.TOKEN_INVALID);
        }
        return true;
    }
}