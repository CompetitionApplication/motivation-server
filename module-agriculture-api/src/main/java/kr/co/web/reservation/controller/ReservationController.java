package kr.co.web.reservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ObjectResult;
import kr.co.web.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "reservation", description = "예약")
@Slf4j
@RestController
@RequestMapping("/api/v1/web/reservation")
@RequiredArgsConstructor
public class ReservationController {

    final ReservationService reservationService;

    @Operation(summary = "농장 예약", description = "농장을 예약 합니다. (작업중)")
    @GetMapping("/farm")
    public ResponseEntity<?> reservationFarm(){
        return ObjectResult.ok();
    }

    @Operation(summary = "농장 예약 내역", description = "농장 예약 내역을 조회 합니다. (작업중)")
    @GetMapping("/farm-history")
    public ResponseEntity<?> reservationFarmHistory(){
        return ObjectResult.ok();
    }
}
