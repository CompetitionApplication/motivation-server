package kr.co.dto.app.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserLoginResDto implements Serializable {

    private String accessToken;
    private String refreshTokenId;

}
