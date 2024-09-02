package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AreaCodeResDto {
    private String areaCodeId;
    private String areaCode;
    private String areaCodeName;
}
