package kr.co.dto.app.myPage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MyPageInfoSetReqDto {

    @NotNull(message = "농장ID늘 필수입력값입니다.")
    @Schema(description = "농장ID", example = "94a5de3d-2165-11ef-81e3-02001701d75b")
    private String farmId;

    @NotNull(message = "농장대표이미지ID는 필수입력값입니다.")
    @Schema(description = "농장대표이미지ID", example = "4e1ef790-217c-11ef-81e3-02001701d75b")
    private String farmMainImageId;

    @NotNull(message = "농장배너이미지ID는 필수입력값입니다.")
    @Schema(description = "농장배너이미지ID", example = "d9d362f0-21bf-11ef-81e3-02001701d75b")
    private String farmBannerImageId;

    @NotNull(message = "농장이름은 필수입력값입니다.")
    @Schema(description = "농장이름", example = "미르영농조합법인")
    private String farmName;

    @NotNull(message = "농장소개는 필수입력값입니다.")
    @Schema(description = "농장소개", example = "‘영광엔 굴비만 있는 것이 아니다.’ 영광하면 굴비라 생각하시는 분들이 많겠지만 영광엔 낙농체험을 즐길 수 있는 목장이 있습니다. 미르낙농체험목장은 오감을 만족시켜 줄 수 있는 다양한 낙농체험 프로그램과 산양과 토종닭까지 볼 수 있는 시설을 갖췄습니다. 하루에 무려 2톤이나 되는 우유를 생산하고 있는 목장입니다. 또한 토종닭이 낳은 계란도 직접 채집할 수 있는 재미있는 프로그램이 있습니다.\n" +
            "목장 대표이신 김용철 목장주는 우리나라 농촌에 대한 특색을 알리는데 큰 역할을 하고 있습니다.")
    private String farmIntrcn;

    @NotNull(message = "농장구분은 필수입력값입니다.")
    @Schema(description = "농장구분", example = "01")
    private String farmKind;

    @NotNull(message = "농장주소는 필수입력값입니다.")
    @Schema(description = "농장주소", example = "전남 영광군 법성면 대덕리 57-27")
    private String farmZip;

    @NotNull(message = "농장연락처는 필수입력값입니다.")
    @Schema(description = "농장연락처", example = "061-356-3650")
    private String farmOwnerTel;

    @NotNull(message = "대표자이름은 필수입력값입니다.")
    @Schema(description = "대표자이름", example = "김용철 대표")
    private String farmOwnerName;

    @NotNull(message = "대표자계좌번호는 필수입력값입니다.")
    @Schema(description = "대표자계좌번호", example = "신한 4253014492621111 홍길이")
    private String farmAccountNo;

    @NotNull(message = "운영요일은 필수입력값입니다.")
    @Schema(description = "운영요일", example = "월,수,금")
    private String farmUseDay;

    @NotNull(message = "운영시작시간은 필수입력값입니다.")
    @Schema(description = "운영시작시간", example = "13:00")
    private String farmUseStartTime;

    @NotNull(message = "운영종료시간 필수입력값입니다.")
    @Schema(description = "운영종료시간", example = "17:00")
    private String farmUseEndTime;

    @NotNull(message = "체험시간은 필수입력값입니다.")
    @Schema(description = "체험시간", example = "1.5")
    private String farmUseTimeDetail;

    @NotNull(message = "체험가격은 필수입력값입니다.")
    @Schema(description = "체험가격", example = "5000")
    private String farmUseAmt;

}
