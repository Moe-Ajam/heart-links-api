package com.heartlink.heartlinkapi.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
