package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.TourPlaceResDto;
import kr.co.dto.TourismUploadReqDto;
import kr.co.service.admin.TourismApiDetailResDto;
import kr.co.service.admin.TourismService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
                                                                  @RequestParam(defaultValue = "7") int size) {
        return ResponseEntity.ok(tourismService.getTourismList(page, size));
    }

    @Operation(summary = "지역코드 목록 리스트", description = "지역코드 목록 리스트 입니다.")
    @GetMapping("/area-code/list")
    public ResponseEntity<?> areaCodeList(){
        return ResponseEntity.ok(tourismService.areaCodeList());
    }

    @Operation(summary = "관광지 상세코드리스트 조회", description = "관광지 상세코드 리스트를 조회 합니다.")
    @GetMapping("/detail-code/{areaCodeId}")
    public ResponseEntity<?> detailAreaCodeList(@PathVariable(value = "areaCodeId") String areaCodeId){
        return ResponseEntity.ok(tourismService.detailAreaCodeList(areaCodeId));
    }

    @Operation(summary = "관광지 상세 조회", description = "관광지 상세 조회를 합니다.")
    @GetMapping("/list/{tourismId}")
    public ResponseEntity<TourismApiDetailResDto> getTourismDetail(@PathVariable(value = "tourismId") String tourismId) {
        return ResponseEntity.ok(tourismService.getTourismDetail(tourismId));
    }

    @Operation(summary = "관광지 삭제", description = "관광지를 삭제 합니다.")
    @DeleteMapping("/{tourismId}")
    public ResponseEntity<?> deleteTourPlace(@PathVariable(value = "tourismId") String tourismId) {
        tourismService.deleteTourism(tourismId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관광지 등록", description = "관광지 등록을 합니다.")
    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadGoods(@RequestParam("tourismName") String tourismName,
                                         @RequestParam("tourismAddress") String tourismAddress,
                                         @RequestParam("tourismLink") String tourismLink,
                                         @RequestParam("tourismContact") String tourismContact,
                                         @RequestParam("areaCode") String areaCode,
                                         @RequestParam("detailAreaCode") String detailAreaCode,
                                         @RequestPart List<MultipartFile> tourismImages) {
        tourismService.uploadTourPlace(
                new TourismUploadReqDto(tourismName, tourismAddress, tourismLink, tourismContact,areaCode,detailAreaCode),
                tourismImages);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관광지 수정", description = "관광지 수정을 합니다.")
    @PutMapping(value = "/{tourismApiId}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateGoods(@PathVariable(value = "tourismApiId") String tourismApiId,
                                         @RequestParam("tourPlaceName") String tourPlaceName,
                                         @RequestParam("tourPlaceAddress") String tourPlaceAddress,
                                         @RequestParam("tourPlaceLink") String tourPlaceLink,
                                         @RequestParam("tourPlaceContact") String tourPlaceContact,
                                         @RequestParam("areaCode") String areaCode,
                                         @RequestParam("detailAreaCode") String detailAreaCode,
                                         @RequestPart List<MultipartFile> tourPlaceImages) {
        tourismService.updateTourism(tourismApiId, new TourismUploadReqDto(tourPlaceName, tourPlaceAddress, tourPlaceLink, tourPlaceContact,areaCode,detailAreaCode),
                tourPlaceImages);
        return ResponseEntity.ok().build();
    }

}