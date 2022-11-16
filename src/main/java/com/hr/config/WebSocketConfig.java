package com.hr.config;

import com.hr.Interceptor.UserInterceptor;
import com.hr.wb.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    UserInterceptor userInterceptor;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(),"/chat/*")
                .setAllowedOrigins("*")
                .addInterceptors(userInterceptor);
    }

    @Bean
    public TextWebSocketHandler chatWebsocketHandler(){
        return new ChatWebSocketHandler();
    }
}
