package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.LocalItemDetailResDto;
import kr.co.dto.LocalItemResDto;
import kr.co.service.LocalItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "local-specialty", description = "특산품")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/local-specialty")
@Slf4j
public class LocalItemController {

    private final LocalItemService localItemService;

    @Operation(summary = "특산품 목록 리스트", description = "특산품 목록 리스트 입니다.")
    @GetMapping("/list")
    public ResponseEntity<Page<LocalItemResDto>> getLocalItemList(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "7") int size) {
        return ResponseEntity.ok(localItemService.getLocalItemList(page, size));
    }
    @Operation(summary = "특산품 목록 상세 조회", description = "특산품 목록 상세 조회를 합니다.")
    @GetMapping("/list/{localSpecialtyId}")
    public ResponseEntity<LocalItemDetailResDto> getLocalItemDetail(@PathVariable(value = "localSpecialtyId") String localItemId) {
        return ResponseEntity.ok(localItemService.getLocalItemDetail(localItemId));
    }
}
