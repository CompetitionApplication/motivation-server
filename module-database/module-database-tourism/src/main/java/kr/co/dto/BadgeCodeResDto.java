package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeCodeResDto {
    private String badgeCode;
    private String badgeCodeType;
}
