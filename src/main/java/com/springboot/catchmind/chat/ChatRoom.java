package com.springboot.catchmind.chat;

import com.springboot.catchmind.dto.ChatMessageDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class ChatRoom {

    private String roomId;
    private String name;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;

        return chatRoom;
    }

//    private Set<WebSocketSession> sessions = new HashSet<>();

//    @Builder
//    public ChatRoom(String roomId, String name) {
//        this.roomId = roomId;
//        this.name = name;
//    }
//
//    public void handleActions(WebSocketSession session, ChatMessageDTO chatMessageDTO, ChatService chatService) {
//        if(ChatMessageDTO.MessageType.ENTER.equals(chatMessageDTO.getType())) {
//            sessions.add(session);
//            chatMessageDTO.setMessage(chatMessageDTO.getSender() + "님이 입장하셨습니다.");
//        }
//
//        sendMessage(chatMessageDTO, chatService);
//    }
//
//    public <T> void sendMessage(T message, ChatService chatService) {
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
//    }

}
