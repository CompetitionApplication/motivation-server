package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBadgeResDto {
    private String tourismName;
    private String badgeName;
    private String badgeGetDatetime;
}
