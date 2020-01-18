//package com.springboot.catchmind.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.springboot.catchmind.chat.ChatRoom;
//import com.springboot.catchmind.chat.ChatService;
//import com.springboot.catchmind.dto.ChatMessageDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class WebSocket extends TextWebSocketHandler {
//
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("payload {}", payload);
//        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload, ChatMessageDTO.class);
//        ChatRoom chatRoom = chatService.findRoomById(chatMessageDTO.getRoomId());
//        chatRoom.handleActions(session, chatMessageDTO, chatService);
////        TextMessage textMessage = new TextMessage("welcome chatting server !");
////        session.sendMessage(textMessage);
//    }
//}
