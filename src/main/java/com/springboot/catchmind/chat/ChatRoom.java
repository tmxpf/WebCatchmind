package com.springboot.catchmind.chat;

import com.springboot.catchmind.dto.ChatMessageDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDTO message) {
        if(ChatMessageDTO.MessageType.ENTER.equals(message.getType())) {
            sessions.add(session);
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

//        sendMessage();
    }

//    public <T> void sendMessage(T message) {
//        sessions.parallelStream().forEach(session -> );
//    }

}
