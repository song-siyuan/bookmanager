package com.example.book.interceptor;
import com.example.book.enums.Code;
import com.example.book.exception.BusinessException;
import com.example.book.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author songsiyuan
 * @date 2023/8/21 09 28
 * discription
 */

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //获取token
        String token = request.getHeader("Authorization");
        //获取端口号
        int port = request.getRemotePort();
        //获取访问者ip
        String ipAddress = request.getRemoteAddr();
        //获取访问路径
        String path = request.getRequestURI();
        //获取访问者访问的方式
        String method = request.getMethod();
        if (token == null) {
            log.info("未携带token，访问被拒，访问路径[{}] 访问方式[{}], IP：{}, 端口：{}", path, method, ipAddress, port);
            throw new BusinessException(Code.BUSINESS_ERR ,"无token,请重新登陆");
        }
        if (JwtUtil.checkSign(token)) {
            log.info("Token验证成功，访问路径[{}] 访问方式[{}], IP：{}, 端口：{}", path, method, ipAddress, port);
            return true;
        }else {
            log.info("Token验证失败，访问被拒，访问路径[{}] 访问方式[{}], IP：{}, 端口：{}", path, method, ipAddress, port);
            throw new BusinessException(Code.BUSINESS_ERR ,"token 验证失败");
        }
    }
}

