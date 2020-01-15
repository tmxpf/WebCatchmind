package com.springboot.catchmind.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessageDTO {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;   // 메세지 타입
    private String roomId;  //방번호
    private String sender;  //메세지 보낸 이
    private String message; // 메세지

}
