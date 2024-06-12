package kr.co.web.reservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.dto.web.reservation.request.ReservationCancelReqDto;
import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.entity.User;
import kr.co.web.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "reservation", description = "예약")
@Slf4j
@RestController
@RequestMapping("/api/v1/web/reservation")
@RequiredArgsConstructor
public class ReservationController {

    final ReservationService reservationService;

    @Operation(summary = "농장 예약", description = "농장을 예약 합니다.")
    @PostMapping("/farm")
    public ResponseEntity<?> reservationFarm(@Valid @RequestBody ReservationReqDto reservationReqDto, @AuthenticationPrincipal User user){
        return ObjectResult.build(reservationService.reservationFarm(reservationReqDto,user));
    }

    @Operation(summary = "농장 예약 내역 리스트 조회", description = "농장 예약 내역을 조회 합니다.")
    @GetMapping("/farm-history")
    public ResponseEntity<?> reservationFarmHistory(@AuthenticationPrincipal User user){
        return ListResult.build(reservationService.reservationFarmHistory(user));
    }

    @Operation(summary = "농장 예약 취소", description = "농장 예약을 취소 합니다.")
    @PutMapping("/farm-cancel")
    public ResponseEntity<?> reservationFarmCancel(@Valid @RequestBody ReservationCancelReqDto reservationCancelReqDto, @AuthenticationPrincipal User user){
        reservationService.reservationFarmCancel(reservationCancelReqDto,user);
        return ObjectResult.ok();
    }
}
