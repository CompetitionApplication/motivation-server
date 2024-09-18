package kr.co.dto;

import lombok.Data;

@Data
public class AdminLoginReqDto {
    private String adminUserEmail;
    private String password;
}
