package com.campusactivity.common.Interceptor;

import com.campusactivity.common.exception.SSOLoginException;
import com.campusactivity.common.util.Constant;
import com.campusactivity.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author q1hang
 * @Description 登录拦截器
 * @create: 2019-12-12 20:47
 **/
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private List<String> whitelist = new ArrayList<>();
    {
//        addWhitelistUrl("/activity");
    }
    private void addWhitelistUrl(String whitelistItem) {
        whitelist.add(whitelistItem);
    }

    private boolean testWhiteList(String servletPath) {
        for (String whitelistItem : whitelist) {
            if (servletPath.startsWith(whitelistItem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if (testWhiteList(servletPath)) {
            return true;
        }

        // 验证请求里面是否有token
        String token = request.getHeader("token");
        log.info("token:" + token);
        Claims claims = JwtUtil.parseJWT(token);
        if (claims == null) {
            throw new SSOLoginException("请先登录接口后台", Constant.TOKEN_INVALID);
        }
        Integer userId = claims.get("userId", Integer.class);
        request.getSession().setAttribute("userId", userId);
        return true;
    }
}
