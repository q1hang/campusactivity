package com.campusactivity.common.config;

import com.campusactivity.common.Interceptor.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @Author q1hang
 * @Description web配置
 * @create: 2019-12-12 20:46
 **/
@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {
    private final static List<String> EXCLUDE_PATH = Arrays.asList(
            "/login/login",
            "/test/get",
            "/test/post",
            "/test/getAllUser");
    /**
     * 注册拦截器
     * @param registry  注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("初始化拦截器.....");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
         }
}
