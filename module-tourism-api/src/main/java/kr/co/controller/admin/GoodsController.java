package kr.co.controller.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.GoodsUploadReqDto;
import kr.co.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "goods", description = "굿즈")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/goods")
@Slf4j
public class GoodsController {
    private final GoodsService goodsService;

    @Operation(summary = "굿즈 목록 리스트", description = "굿즈 목록 리스트 입니다.")
    @GetMapping("/list")
    public ResponseEntity<?> getGoodsList(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(goodsService.getGoodsList(page, size));
    }

    @Operation(summary = "굿즈 상세 조회", description = "굿즈 상세 조회를 합니다.")
    @GetMapping("/list/{goodsId}")
    public ResponseEntity<?> getGoodsDetail(@PathVariable(value = "goodsId") String goodsId) {
        return ResponseEntity.ok(goodsService.getGoodsDetail(goodsId));
    }


    @Operation(summary = "굿즈 등록", description = "굿즈 등록을 합니다.")
    @PostMapping("")
    public ResponseEntity<?> uploadGoods(@ModelAttribute GoodsUploadReqDto goodsUploadReqDto) {
        goodsService.uploadGoods(goodsUploadReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "굿즈 삭제", description = "굿즈 삭제를 합니다.")
    @DeleteMapping("/{goodsId}")
    public ResponseEntity<?> deleteGoods(@PathVariable(value = "goodsId") String goodsId) {
        goodsService.deleteGoods(goodsId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "굿즈 수정", description = "굿즈 수정을 합니다.")
    @PutMapping("/{goodsId}")
    public ResponseEntity<?> updateGoods(@PathVariable(value = "goodsId") String goodsId,
                                         @ModelAttribute GoodsUploadReqDto goodsUploadReqDto) {
        goodsService.updateGoods(goodsId, goodsUploadReqDto);
        return ResponseEntity.ok().build();
    }
}
