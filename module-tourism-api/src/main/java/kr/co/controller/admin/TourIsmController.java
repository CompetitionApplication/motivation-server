package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.auth.AdminLoginUser;
import kr.co.dto.*;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.entity.AdminUser;
import kr.co.service.admin.AdminLoginService;
import kr.co.service.admin.TourismApiDetailResDto;
import kr.co.service.admin.TourismService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "tour-place", description = "관광지")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tourism")
@Slf4j
public class TourIsmController {

    private final TourismService tourismService;
    ;

    @Operation(summary = "관광지 목록 리스트", description = "관광지 목록 리스트 입니다.")
    @GetMapping("/list")
    public ResponseEntity<Page<TourPlaceResDto>> getTourismList(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "7") int size,
                                                                @AuthenticationPrincipal ServiceAdminUser serviceAdminUser) {
        return ResponseEntity.ok(tourismService.getTourismList(page, size, serviceAdminUser));
    }

    @Operation(summary = "지역코드 목록 리스트", description = "지역코드 목록 리스트 입니다.")
    @GetMapping("/area-code/list")
    public ResponseEntity<List<AreaCodeResDto>> areaCodeList() {
        return ResponseEntity.ok(tourismService.areaCodeList());
    }

    @Operation(summary = "관광지 상세코드리스트 조회", description = "관광지 상세코드 리스트를 조회 합니다.")
    @GetMapping("/detail-code/{areaCodeId}")
    public ResponseEntity<List<DetailAreaCodeResDto>> detailAreaCodeList(@PathVariable(value = "areaCodeId") String areaCodeId) {
        return ResponseEntity.ok(tourismService.detailAreaCodeList(areaCodeId));
    }

    @Operation(summary = "관광지 상세 조회", description = "관광지 상세 조회를 합니다.")
    @GetMapping("/list/{tourPlaceId}")
    public ResponseEntity<TourismApiDetailResDto> getTourismDetail(@PathVariable(value = "tourPlaceId") String tourPlaceId) {
        return ResponseEntity.ok(tourismService.getTourismDetail(tourPlaceId));
    }

    @Operation(summary = "관광지 삭제", description = "관광지를 삭제 합니다.")
    @DeleteMapping("/{tourPlaceId}")
    public ResponseEntity<?> deleteTourPlace(@PathVariable(value = "tourPlaceId") String tourPlaceId) {
        tourismService.deleteTourism(tourPlaceId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관광지 멀티 삭제", description = "관광지를 멀티 삭제 합니다.")
    @PostMapping("/multi-delete")
    public ResponseEntity<?> deleteMultiTourPlace(@RequestBody List<String> tourismIds) {
        tourismService.deleteMultiTourism(tourismIds);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "관광지 등록", description = "관광지 등록을 합니다.")
    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadGoods(@ModelAttribute TourismInsertDto tourismInsertDto, @AuthenticationPrincipal ServiceAdminUser serviceAdminUser) {
        tourismService.uploadTourPlace(new TourismUploadReqDto(tourismInsertDto), serviceAdminUser);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관광지 수정", description = "관광지 수정을 합니다.")
    @PutMapping(value = "/{tourismApiId}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateGoods(@PathVariable(value = "tourismApiId") String tourismApiId,
                                         @AuthenticationPrincipal ServiceAdminUser serviceAdminUser,
                                         @ModelAttribute TourismUpdateDto tourismUpdateDto) {
        tourismService.updateTourism(tourismApiId, new TourismUpdateReqDto(tourismUpdateDto), serviceAdminUser);
        return ResponseEntity.ok().build();
    }

}
