package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.LocalItemDetailResDto;
import kr.co.dto.LocalItemResDto;
import kr.co.dto.LocalItemUpdateReqDto;
import kr.co.dto.LocalItemUploadReqDto;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.service.admin.LocalItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "local-specialty", description = "특산품")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/local-item")
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

    @Operation(summary = "특산품 등록", description = "특산품 등록 합니다")
    @PostMapping(value = "",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadLocalItem(@ModelAttribute LocalItemUploadReqDto localItemUploadReqDto, @AuthenticationPrincipal ServiceAdminUser serviceAdminUser) {
        localItemService.uploadLocalItem(new LocalItemUploadReqDto(localItemUploadReqDto),serviceAdminUser);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특산품 수정", description = "특산품 수정을 합니다.")
    @PutMapping(value = "/{localItemId}",consumes = "multipart/form-data")
    public ResponseEntity<?> updateLocalItem(@PathVariable(value = "localItemId") String localItemId,
                                             @ModelAttribute LocalItemUpdateReqDto localItemUpdateReqDto,
                                             @AuthenticationPrincipal ServiceAdminUser serviceAdminUser) {
        localItemService.updateLocalItem(localItemId, new LocalItemUpdateReqDto(localItemUpdateReqDto),serviceAdminUser);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특산품 삭제", description = "특산품 삭제를 합니다.")
    @DeleteMapping("/{localItemId}")
    public ResponseEntity<?> deleteLocalItem(@PathVariable(value = "localItemId") String localItemId) {
        localItemService.deleteLocalItem(localItemId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특산품 멀티 삭제", description = "특산품를 멀티 삭제 합니다.")
    @PostMapping("/multi-delete")
    public ResponseEntity<?> deleteMultiLocalItem(@RequestBody List<String> localItemIds) {
        localItemService.deleteMultiLocalItem(localItemIds);
        return ResponseEntity.ok().build();
    }
}

