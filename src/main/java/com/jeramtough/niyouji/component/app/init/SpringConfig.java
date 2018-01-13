package com.jeramtough.niyouji.component.app.init;

import com.jeramtough.niyouji.entrance.handler.TestHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@ComponentScan(basePackages = "com.jeramtough.niyouji")
@EnableWebMvc
@EnableWebSocket
public class SpringConfig implements WebSocketConfigurer
{
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
	{
		registry.addHandler(new TestHandler(), "/testHandler.do ");
	}
}
