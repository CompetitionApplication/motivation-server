package kr.co.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto implements Serializable {

    private String accessToken;
    private String refreshTokenId;

}
