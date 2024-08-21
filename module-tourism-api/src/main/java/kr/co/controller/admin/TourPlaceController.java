package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.GoodsUploadReqDto;
import kr.co.dto.TourPlaceUploadReqDto;
import kr.co.service.admin.TourPlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "tour-place", description = "관광지")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tour-place")
@Slf4j
public class TourPlaceController {

    private final TourPlaceService tourPlaceService;
    ;

    @Operation(summary = "관광지 목록 리스트", description = "관광지 목록 리스트 입니다.")
    @GetMapping("/list")
    public ResponseEntity<?> getTourPlaceList(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tourPlaceService.getTourPlaceList(page, size));
    }

    @Operation(summary = "관광지 상세 조회", description = "관광지 상세 조회를 합니다.")
    @GetMapping("/list/{tourPlaceId}")
    public ResponseEntity<?> getTourPlaceDetail(@PathVariable(value = "tourPlaceId") String tourPlaceId) {
        return ResponseEntity.ok(tourPlaceService.getTourPlaceDetail(tourPlaceId));
    }

    @Operation(summary = "관광지 삭제", description = "관광지를 삭제 합니다.")
    @DeleteMapping("/{tourPlaceId}")
    public ResponseEntity<?> deleteTourPlace(@PathVariable(value = "tourPlaceId") String tourPlaceId) {
        tourPlaceService.deleteTourPlace(tourPlaceId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관광지 등록", description = "관광지 등록을 합니다.")
    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadGoods(@RequestParam("tourPlaceName") String tourPlaceName,
                                         @RequestParam("tourPlaceAddress") String tourPlaceAddress,
                                         @RequestParam("tourPlaceLink") String tourPlaceLink,
                                         @RequestParam("tourPlaceContact") String tourPlaceContact,
                                         @RequestPart List<MultipartFile> tourPlaceImages) {
        tourPlaceService.uploadTourPlace(
                new TourPlaceUploadReqDto(tourPlaceName, tourPlaceAddress, tourPlaceLink, tourPlaceContact),
                tourPlaceImages);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관광지 수정", description = "관광지 수정을 합니다.")
    @PutMapping(value = "/{tourPlaceId}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateGoods(@PathVariable(value = "goodsId") String tourPlaceId,
                                         @RequestParam("tourPlaceName") String tourPlaceName,
                                         @RequestParam("tourPlaceAddress") String tourPlaceAddress,
                                         @RequestParam("tourPlaceLink") String tourPlaceLink,
                                         @RequestParam("tourPlaceContact") String tourPlaceContact,
                                         @RequestPart List<MultipartFile> tourPlaceImages) {
        tourPlaceService.updateTourPlace(tourPlaceId,
                new TourPlaceUploadReqDto(tourPlaceName, tourPlaceAddress, tourPlaceLink, tourPlaceContact),
                tourPlaceImages);
        return ResponseEntity.ok().build();
    }

}
