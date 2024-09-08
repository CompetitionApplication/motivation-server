package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.GiveLocalItemReqDto;
import kr.co.dto.GiveLocalItemResDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.service.admin.GiveLocalItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/list")
    public ResponseEntity<List<GiveLocalItemResDto>> getGiveLocalItemList(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ResponseEntity.ok(giveLocalItemService.getGiveLocalItemList(serviceUser));
    }

    @PostMapping("")
    public ResponseEntity<?> giveLocalItem(@AuthenticationPrincipal ServiceUser serviceUser,
                                           @RequestBody GiveLocalItemReqDto giveLocalItemReqDto) {
        giveLocalItemService.saveLocalItem(giveLocalItemReqDto,serviceUser);
        return ResponseEntity.ok().build();
    }

}
