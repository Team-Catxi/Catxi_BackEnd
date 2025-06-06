package com.project.catxi.chat.config;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class StompEventListener {
	private final Set<String> sessions = ConcurrentHashMap.newKeySet();

	@EventListener
	public void connectHandle(SessionConnectEvent event){
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		sessions.add(accessor.getSessionId());
		System.out.println("connect session Id" + accessor.getSessionId());
		System.out.println("total session : "+sessions.size());
	}

	@EventListener
	public void disconnectHandle(SessionDisconnectEvent event){
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		sessions.remove(accessor.getSessionId());
		System.out.println("disconnect session Id" + accessor.getSessionId());
		System.out.println("total session : "+sessions.size());
	}
}


