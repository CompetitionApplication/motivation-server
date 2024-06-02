package kr.co.web.farm.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ListResult;
import kr.co.web.farm.service.FarmService;
import kr.co.dto.web.farm.response.FarmsResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "web", description = "웹")
@Slf4j
@RestController
@RequestMapping("/api/v1/web")
@RequiredArgsConstructor
public class FarmController {

    final FarmService farmService;

    @GetMapping("/farms")
    public ResponseEntity<?> farms(){
        return ListResult.build(farmService.farms());
    }
}
