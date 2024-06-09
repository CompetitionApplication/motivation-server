package kr.co.web.clause.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.web.clause.service.ClauseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "clause", description = "약관")
@Slf4j
@RestController
@RequestMapping("/api/v1/web/clause")
@RequiredArgsConstructor
public class ClasuseController {

    final ClauseService clauseService;

    @Operation(summary = "개인정보 이용 약관 조회", description = "개인정보 이용 약관을 조회 합니다.")
    @GetMapping("/privacy")
    public ResponseEntity<?> privacyClause() throws Exception {
        return ObjectResult.build(clauseService.privacy());
    }

    @Operation(summary = "약관리스트", description = "약관 리스트를 조회 합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> clauseList() throws Exception {
        return ObjectResult.build(clauseService.clauseList());
    }
}
