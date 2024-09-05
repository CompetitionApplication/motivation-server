package kr.co.dto.app.home.response;

import lombok.Data;

import java.util.List;

@Data
public class CollectBadgeResDto {

    private String totalCollectBadgeCnt;

    private List<CollectBadgeDto> collectBadgeDtoList;
}
