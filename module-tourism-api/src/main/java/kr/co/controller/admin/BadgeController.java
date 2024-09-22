package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.service.admin.BadgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "badge", description = "뱃지")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/badge")
@Slf4j
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("/list")
    public ResponseEntity<?> getBadgeList() {
        return ResponseEntity.ok(badgeService.getBadgeList());
    }

}
