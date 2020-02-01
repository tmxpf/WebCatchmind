package com.springboot.catchmind.controller;

import com.springboot.catchmind.chat.ChatRoom;
import com.springboot.catchmind.chat.ChatRoomRepository;
import com.springboot.catchmind.dto.ChatMessageDTO;
import com.springboot.catchmind.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;
    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message) {
        if(message.getType().equals(ChatMessageDTO.MessageType.ENTER)) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
//        messageSendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    //채팅 리스트 화면
    @GetMapping("/chat/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/chat/rooms")
    @ResponseBody
    public List<ChatRoom> getRooms() {
        return chatRoomRepository.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/chat/createRoom")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    // 채팅방 입장 화면
    @GetMapping("/chat/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        ChatRoom room =  chatRoomRepository.findRoomById(roomId);

        model.addAttribute("roomId", room.getRoomId());
        model.addAttribute("roomName", room.getName());

        return "/chat/roomdetail";
    }

    // 특정 채팅방 조회
    @GetMapping("/chat/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

}
