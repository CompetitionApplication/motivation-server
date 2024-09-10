package kr.co.dto.app.qr;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QrReqDto {
    private String userId;
    private String tourismApiId;
    private LocalDateTime qrTime;
}
