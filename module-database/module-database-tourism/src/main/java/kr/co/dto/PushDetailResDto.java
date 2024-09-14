package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushDetailResDto {
    private String pushTitle;
    private String pushContent;
}
