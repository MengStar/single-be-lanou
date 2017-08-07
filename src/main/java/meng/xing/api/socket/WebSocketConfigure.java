package meng.xing.api.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigure extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 配置后台websocket的切入点
     * 前端js通过 var socket = new SockJS('/gs-guide-websocket') 创建连接
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("back-end-websocket").withSockJS();
    }

    /**
     * 配置可注册的socket类型
     * 配置消息路径前缀

     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //对应@SendTo("/topic/**")
        config.enableSimpleBroker("/topic");
        // "/ws/hello"对应 @MessageMapping("/hello")
        config.setApplicationDestinationPrefixes("/ws");
    }
}
