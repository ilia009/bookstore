package com.bookshop.bookshop.main.users.dtos;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
