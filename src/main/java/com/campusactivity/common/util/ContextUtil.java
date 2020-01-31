package com.campusactivity.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @Author q1hang
 * @Description 获取上下文session
 * @create: 2020-01-14 21:42
 **/
public class ContextUtil {

    /**
     * @Description: 获取当前登录用户的主键id
     * @return 返回Long类型用户id
     */
    public static Integer getCurrentUserId(){
        return (Integer)getFromSession("userId");
    }


//    /**
//     * 获取登录用户实体
//     * @return
//     */
//    public static LoginUserInfo getUserInfo() {
//        String userInfoJson = (String) getFromSession("userInfoJson");
//        return userInfoJson == null ? null :JSON.parseObject(userInfoJson, LoginUserInfo.class);
//    }

    /**
     * @Description: 根据key从session获取信息
     * @param key
     * @return
     */
    public static Object getFromSession (String key) {
        HttpSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getAttribute(key);
    }

    public static HttpServletRequest getRequest() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getSession();
    }

}

