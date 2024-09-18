package kr.co.dto;

import lombok.Data;

@Data
public class AdminLoginUserDto {
    private String accessToken;

    public AdminLoginUserDto(String accessToken){
        this.accessToken = accessToken;
    }
}
