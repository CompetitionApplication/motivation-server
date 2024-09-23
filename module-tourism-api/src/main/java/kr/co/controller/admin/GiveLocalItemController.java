package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.GiveLocalItemDetailResDto;
import kr.co.dto.GiveLocalItemReqDto;
import kr.co.dto.GiveLocalItemResDto;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.service.admin.GiveLocalItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "give-local-item", description = "특산품제공")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/give-local-item")
@Slf4j
public class GiveLocalItemController {

    private final GiveLocalItemService giveLocalItemService;

    @Operation(summary = "특산품 제공리스트 목록 조회", description = "특산품 제공리스트 목록 조회를 합니다.")
    @GetMapping("/list")
    public ResponseEntity<Page<GiveLocalItemResDto>> getGiveLocalItemList(@AuthenticationPrincipal ServiceAdminUser serviceAdminUser,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "7") int size) throws Exception {
        return ResponseEntity.ok(giveLocalItemService.getGiveLocalItemList(serviceAdminUser,page,size));
    }

    @Operation(summary = "특산품 제공 상세 조회", description = "특산품 제공 상세 조회를 합니다.")
    @GetMapping("/list/{giveLocalItemId}")
    public ResponseEntity<GiveLocalItemDetailResDto> getGiveLocalItemDetail(@PathVariable(value = "giveLocalItemId") String giveLocalItemId) {
        return ResponseEntity.ok(giveLocalItemService.getGiveLocalItemDetail(giveLocalItemId));
    }

    @Operation(summary = "특산품 제공 등록", description = "특산품 제공 등록을 합니다.")
    @PostMapping("")
    public ResponseEntity<?> giveLocalItem(@AuthenticationPrincipal ServiceAdminUser serviceAdminUser,
                                           @RequestBody GiveLocalItemReqDto giveLocalItemReqDto) {
        giveLocalItemService.saveLocalItem(giveLocalItemReqDto,serviceAdminUser);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특산품 제공 수정", description = "특산품 제공 수정을 합니다.")
    @PutMapping("/{giveLocalItemId}")
    public ResponseEntity<?> updateGiveLocalItem(@PathVariable(value = "giveLocalItemId") String giveLocalItemId,
                                                 @RequestBody GiveLocalItemReqDto giveLocalItemReqDto) {
        giveLocalItemService.updateLocalItem(giveLocalItemId, giveLocalItemReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특산품 제공 삭제", description = "특산품 제공 삭제를 합니다.")
    @DeleteMapping("/{giveLocalItemId}")
    public ResponseEntity<?> deleteGiveLocalItem(@PathVariable(value = "giveLocalItemId") String giveLocalItemId) {
        giveLocalItemService.deleteGiveLocalItem(giveLocalItemId);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "특산품 제공 멀티 삭제", description = "특산품 제공 멀티 삭제를 합니다.")
    @PostMapping("/multi-delete")
    public ResponseEntity<?> deleteMultiGiveLocalItem(@RequestBody List<String> giveLocalItemIds) {
        giveLocalItemService.deleteMultiLocalItem(giveLocalItemIds);
        return ResponseEntity.ok().build();
    }

}
