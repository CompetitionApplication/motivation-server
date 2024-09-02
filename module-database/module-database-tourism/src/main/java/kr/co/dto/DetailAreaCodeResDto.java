package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailAreaCodeResDto {
    private String detailAreaCodeId;
    private String detailAreaCode;
    private String detailAreaCodeName;
}
