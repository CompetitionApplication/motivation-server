package kr.co.web.farm.service;

import kr.co.dto.web.farm.response.FarmDetailResDto;
import kr.co.dto.web.farm.response.FarmsResDto;

import java.util.List;

public interface FarmService {

    List<FarmsResDto> farms(String farmKind, String farmName, String farmUseDay, String farmMaxUserCnt, String orderByKind);

    FarmDetailResDto farmDetail(String farmId);
    void openApi() throws Exception;
}
