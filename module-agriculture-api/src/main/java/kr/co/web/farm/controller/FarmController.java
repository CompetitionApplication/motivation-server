package kr.co.web.farm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.web.farm.service.FarmService;
import kr.co.dto.web.farm.response.FarmsResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "farm", description = "농장")
@Slf4j
@RestController
@RequestMapping("/api/v1/web/farm")
@RequiredArgsConstructor
public class FarmController {

    final FarmService farmService;

    @Operation(summary = "농장 메인 리스트 조회", description = "농장 메인 리스트를 조회 합니다.<br><br>" +
                                                            "[param info]<br>" +
                                                            "* farmKind(농장종류)<br>" +
                                                            "01 : 농장<br>" +
                                                            "02 : 목장<br>" +
                                                            "03 : 체험")
    @GetMapping("/list")
    public ResponseEntity<?> farms(@Parameter(description = "농장종류", example = "01") @RequestParam(required = true) String farmKind,
                                   @Parameter(description = "농장이름", example = "다래목장") @RequestParam(required = false) String farmName,
                                   @Parameter(description = "이용요일", example = "월") @RequestParam(required = false) String farmUseDay,
                                   @Parameter(description = "이용인원", example = "3") @RequestParam(required = false) String farmMaxUserCnt){

        return ListResult.build(farmService.farms(farmKind,farmName,farmUseDay,farmMaxUserCnt));
    }

    @Operation(summary = "농장 상세 조회", description = "농장 상세 정보를 조회 합니다.")
    @GetMapping("/detail")
    public ResponseEntity<?> farmDetail(@Parameter(description = "농장ID", example = "94a5de3d-2165-11ef-81e3-02001701d75b") @RequestParam(required = true) String farmId){
        return ObjectResult.build(farmService.farmDetail(farmId));
    }

    @Operation(summary = "농장 오픈 api", description = "농림축산식품 공공데이터 api를 호출 후 저장 합니다.")
    @PostMapping("/open-api")
    public ResponseEntity<?> openApi(){
        farmService.openApi();
        return ObjectResult.ok();
    }
}
