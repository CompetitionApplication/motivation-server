package kr.co.common;


public enum CommonErrorCode {
    // common
    SUCCESS("SUCCESS", "정상처리"),
    FAIL("FAIL", "실패"),
    FAIL_SIGN_UP("FAIL", "서비스에 가입할 수 없습니다."),
    FAIL_ALREADY_ACCOUNT("FAIL","이미 서비스에 가입한 이력이 있습니다."),

    COMMON_FAIL("FAIL","값이 없습니다"),

    // token
    INVALID_TOKEN("E001", "유효하지 않은 사용자 정보입니다."),
    EXPIRED_TOKEN("E002", "로그인 유효 시간이 만료 되었습니다."),
    EXPIRED_TOKEN_SNS("E006", "너무 오랜 시간이 지나 유효 시간이 만료 되었습니다. 다시 시도하여 주세요."),
    AUTHENTICATION_FAILED("E003", "인증에 실패하였습니다."),
    WRONG_TOKEN("E004", "잘못된 사용자 정보입니다."),
    EMPTY_REFRESH_TOKEN("E005", "로그인 정보가 존재하지 않습니다."),

    // user
    NOT_FOUND_USER("C000","회원을 찾을 수 없습니다."),
    ALREADY_ACCOUNT("C001","이미 사용중인 아이디 입니다."),

    // 구분
    NOT_CATEGORY("A000","구분이 없습니다."),
    NOT_FOUND_SCRAPING_DATA("A001","스크리핑 데이터를 찾을수 없습니다."),

    // 통신
    FAIL_EXTERNAL_API("B000","외부요청 오류입니다."),

    //농축산(Y)
    FARM_NOT_FOUND("Y000","농장을 찾을 수 없습니다."),
    NOT_FOUND_LOGIN_ID("Y001","ID를 찾을수 없습니다."),
    NOT_FOUND_REFRESHTOKEN_ID("Y002","리프레시토큰 ID를 찾을수 없습니다."),
    DUPLICATION_ACCOUNT_ID("Y003","중복된 회원가입 ID 입니다."),
    NOT_FOUND_RESERVATION_ID("Y004","예약 ID를 찾을수 없습니다."),
    CHECK_FARM_USE_TIME_START_END("Y005","영업시간이 잘못되었습니다."),
    CHECK_FARM_USE_TIME("Y006","체험시간단위가 영업시간내 충족하지 못합니다. 체험시간 또는 엽업시간을 변경해주세요."),
    NOT_FOUND_FILE_GROUP_ID("Y007","파일그룹 ID를 찾을수 없습니다."),

    //경남(Z)
    ;





    private final String code;
    private final String message;

    CommonErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
