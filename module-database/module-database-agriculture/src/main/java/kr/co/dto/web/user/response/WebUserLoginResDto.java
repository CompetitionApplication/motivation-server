package kr.co.dto.web.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebUserLoginResDto implements Serializable {

    private String accessToken;
    private String refreshTokenId;

}
