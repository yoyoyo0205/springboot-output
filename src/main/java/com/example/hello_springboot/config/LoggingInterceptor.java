package com.example.hello_springboot.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();

        // パラメータをMapから展開
        StringBuilder params = new StringBuilder();
        request.getParameterMap().forEach((key, values) -> {
            for (String value : values) {
                params.append(key).append("=").append(value).append("&");
            }
        });

        // 末尾の「&」を削除
        if (params.length() > 0) {
            params.setLength(params.length() - 1);
//            params.setCharAt(0,'?');
            params.insert(0,'?');
        }
        logger.info("[access] {} {}{} from {}", method, uri, params.toString(), ip);

        return true;
    }
}
