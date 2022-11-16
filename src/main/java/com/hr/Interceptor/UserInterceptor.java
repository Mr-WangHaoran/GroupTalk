package com.hr.Interceptor;

import com.hr.pojos.Group;
import com.hr.utils.Names;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 拦截websocket请求，拦截第一次握手
 */
@Slf4j
public class UserInterceptor extends HttpSessionHandshakeInterceptor {

    @Autowired
    Group group;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        HttpServletRequest request1 = serverHttpRequest.getServletRequest();
        String username = request1.getParameter("username");
//        log.info("username="+username);
        if(username.length()<2){
            return false;
        }
        //通过此参数能将参数传递过去
        attributes.put("group",group);
        attributes.put("username",username);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
