package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushResDto {
    private String pushId;
    private String pushTitle;
    private String regDatetime;
}
