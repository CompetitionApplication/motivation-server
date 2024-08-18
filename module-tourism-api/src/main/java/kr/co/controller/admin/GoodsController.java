package kr.co.controller.admin;


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

    @GetMapping("/list")
    public ResponseEntity<?> getGoodsList(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(goodsService.getGoodsList(page, size));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadGoods(@ModelAttribute GoodsUploadReqDto goodsUploadReqDto) {
        goodsService.uploadGoods(goodsUploadReqDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{goodsId}")
    public ResponseEntity<?> deleteGoods(@PathVariable(value = "goodsId") String goodsId) {
        goodsService.deleteGoods(goodsId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{goodsId}")
    public ResponseEntity<?> updateGoods(@PathVariable(value = "goodsId") String goodsId,
                                         @ModelAttribute GoodsUploadReqDto goodsUploadReqDto) {
        goodsService.updateGoods(goodsId, goodsUploadReqDto);
        return ResponseEntity.ok().build();
    }
}
