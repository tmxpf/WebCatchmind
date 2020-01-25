package com.springboot.catchmind.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Users {

    private String roomId;
    private String name;
    private String userId;

    public static Users createUser(String name) {
        Users user = new Users();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(name);

        return user;
    }

}
