package com.springboot.catchmind.chat;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name;
    private Map<String, Users> users;

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
