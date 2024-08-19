package kr.co.common;

public enum MtnErrorCode {
    // common
    COMMON_FAIL("E000", "실패"),
    METHOD_NOT_ALLOWED("E010", "허용되지 않은 Method로 접근 하셨습니다."),
    FORBIDDEN("E011", "접근 권한이 없습니다."),
    NOT_FOUND("E012", "정보를 찾을 수 없습니다."),
    WRONG_VALUE("E013", "값이 없거나 잘못되었습니다."),
    COMMON_FAIL_DUPLICATE("E014", "회원정보 처리 중 오류가 발생하였습니다."),
    FILE_ERROR("E015", "파일 처리 중 오류가 발생하였습니다."),
    ENCRYPT_ERROR("E016", "암호화 처리 중 오류가 발생하였습니다."),
    DECRYPT_ERROR("E017", "복호화 처리 중 오류가 발생하였습니다."),
    IO_ERROR("E018", "입출력 오류가 발생하였습니다."),
    IMAGE_FILE_ERROR("E019", "이미지 파일이 아닙니다."),
    MISSING_COOKIE("E020", "쿠키 정보가 없습니다."),
    FILE_NOT_FOUND("E021", "파일을 찾을 수 없습니다."),
    ARTICLE_NOT_FOUND("E022", "존재하지 않는 게시물입니다."),
    EVENT_NOT_FOUND("E023", "존재하지 않는 행사입니다."),
    POPBILL_ERROR("E024", "팝빌 통신중 오류가 발생하였습니다."),
    EVENT_JOIN_NOT_FOUND("E025", "신청 정보를 찾을 수 없습니다."),
    EXISTS_DATA("E026", "이미 존재하는 데이터입니다."),


    // token
    INVALID_TOKEN("E001", "유효하지 않은 사용자 정보입니다."),
    EXPIRED_TOKEN("E002", "로그인 유효 시간이 만료 되었습니다."),
    EXPIRED_TOKEN_SNS("E006", "너무 오랜 시간이 지나 유효 시간이 만료 되었습니다. 다시 시도하여 주세요."),
    AUTHENTICATION_FAILED("E003", "인증에 실패하였습니다."),
    WRONG_TOKEN("E004", "잘못된 사용자 정보입니다."),
    EMPTY_REFRESH_TOKEN("E005", "로그인 정보가 존재하지 않습니다."),
    NOT_FOUND_TOKEN("E007", "인증 정보가 존재하지 않습니다."),

    // user
    USER_NOT_FOUND("E100", "존재하지 않는 사용자입니다."),
    INVALID_USER_INFO("E101", "유효하지 않은 사용자 정보입니다."),
    UNAUTHORIZED("E102", "승인된 계정이 아닙니다."),
    PASSWORD_NOT_MATCH("E103", "아이디 또는 비밀번호가 일치하지 않습니다."),
    LOCKED_USER("E104", "보안상의 이유로 잠긴 상태의 계정입니다."),
    EXPIRED_USER("E105", "사용 기간이 만료된 계정입니다."),
    EXPIRED_PASSWORD("E106", "비밀번호가 만료되었습니다."),
    USER_ALREADY_EXIST("E107", "이미 존재하는 사용자입니다."),
    USER_IS_NULL("E108", "회원 정보가 필요합니다."),
    USED_PASSWORD("E109", "기존 비밀번호와 일치합니다."),
    NOT_SAME_PERSON("E110", "본인인증 정보가 일치하지 않습니다."), // 동일 인물이 아님
    USER_QUIT("E111", "탈퇴한 사용자입니다."),
    USER_BLOCK("E112", "차단된 사용자입니다."),
    USER_STOP("E113", "정지된 사용자입니다."),
    USER_DELETE("E114", "삭제된 사용자입니다."),
    USER_ALREADY_EXIST_BY_CI("E115", "이미 존재하는 사용자입니다."),
    PREV_PASSWORD_FAIL_DUPLICATE("E116", "직전에 사용했던 비밀번호는 재사용이 불가능 합니다."),
    USER_NOT_MERGED("E117", "통합 되지 않은 사용자입니다. 로그인 후 회원 통합을 먼저 진행해주세요."),
    DUPLICATE_USER("E118", "이미 가입된 사용자입니다."),

    // niceId
    NICE_ID_COMMON_FAIL("E200", "NICE ID 오류"),
    INVALID_CERT("E201", "유효한 인증정보가 존재하지 않습니다."),
    EXPIRED_CERT_TIME("E202", "인증 시간이 만료되었습니다."),
    INVALID_CERT_NO("E203", "인증번호가 일치하지 않습니다."),

    // 휴면 회원 복구
    USER_NOT_FOUND_SLEEP1("E300", "존재하지 않는 사용자입니다."),
    USER_NOT_FOUND_SLEEP2("E301", "존재하지 않는 사용자입니다."),

    // 통신 오류
    MALFORMED_URL("E400", "잘못된 URL입니다."),
    REQUEST_IO_EXCEPTION("E401", "통신 과정 중 오류가 발생하였습니다."),
    JSON_PARSE_EXCEPTION("E402", "JSON 파싱 중 오류가 발생하였습니다."),
    REQUEST_NO_SUCH_ALGORITHM_EXCEPTION("E403", "서버 인증서 처리 중 오류가 발생혔습니다."),
    REQUEST_KEY_MANAGEMENT_EXCEPTION("E404", "서버 인증서 처리 중 오류가 발생혔습니다."),

    // admin
    DEPT_NOT_FOUND("ZADM001", "부서 정보를 찾을 수 없습니다."),
    AUTH_GROUP_NOT_FOUND("ZADM002", "권한 그룹 정보를 찾을 수 없습니다."),
    AUTHORITY_ERROR("ZADM003", "시스템 관리자는 시스템 관리자만 권한을 줄 수 있습니다."),

    // advisor
    ADVISOR_NOT_FOUND("ZAVD001", "어드바이저를 찾을 수 없습니다."),
    ADVISOR_SERVICE_NOT_FOUND("ZAVD002", "어드바이저 상품명을 찾을 수 없습니다."),

    // advisor user
    NOT_ADVISOR("ZAU001", "이 계정은 어드바이저가 아니거나 연결되지 않았습니다."),

    // PG, billing, order
    PG_INFO_NOT_FOUND("E700", "PG를 찾을 수 없습니다."),
    PG_SHOP_NOT_FOUND("E701", "PG 상점 정보를 찾을 수 없습니다."),
    PAY_DISPLAY_METHOD_NOT_FOUND("E702", "결제 방법을 찾을 수 없습니다."),
    ORDER_NOT_FOUND("E703", "주문 정보를 찾을 수 없습니다."),
    MICE_PRODUCT_PAY_NOT_FOUND("E704", "결제 정보를 찾을 수 없습니다."),
    SETTLE_RESULT_UNMATCHED("E705", "결제 결과가 일치하지 않습니다."),
    REFUND_COST_OVER("E706", "환불 금액이 결제 금액을 초과하였습니다."),
    SETTLE_REFUND_RESPONSE_ERROR("E707", "환불 요청에 실패하였습니다."),
    SETTLE_REFUND_ERROR("E708", "환불 처리에 실패하였습니다."),
    SETTLE_REFUND_ERROR2("E709", "환불 처리에 실패하였습니다."),
    MICE_PRODUCT_ORDER_NOT_FOUND("E710", "주문 정보를 찾을 수 없습니다."),
    MICE_PRODUCT_REFUND_NOT_FOUND("E711", "환불 정보를 찾을 수 없습니다."),
    MICE_JOIN_ALREADY_EXISTS("E712", "이미 신청한 행사입니다."),


    // internal api
    BAD_REQUEST("E800", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("E801", "서버 오류가 발생하였습니다."),

    NEED_START_DATE_TIME("E900", "행사 시작일시가 입력되지 않았습니다."),
    NEED_END_DATE_TIME("E900", "행사 종료일시가 입력되지 않았습니다."),
    NEED_JOIN_START_DATE_TIME("E900", "참가 시작일시가 입력되지 않았습니다."),
    NEED_JOIN_END_DATE_TIME("E900", "참가 종료일시가 입력되지 않았습니다."),

    // 2FA
    VERIFY_CODE_NOT_FOUND("E1000", "인증번호를 찾을 수 없습니다."),
    VERIFY_CODE_NOT_MATCH("E1001", "인증번호가 일치하지 않습니다."),
    PHONE_NO_NOT_FOUND("E1002", "인증에 필요한 휴대폰 번호가 설정되어 있지 않습니다."),
    PHONE_NO_WRONG("E1003", "휴대폰 정보가 잘못되었습니다."),

    //msg
    RESERVATION_ERROR("REE", "예약 취소가 불가 합니다."),
    RESERVATION_TIME_ERROR("RTE", "현재시간보다 미래시간이여야합니다."),

    // etc
    INVALID_DATE_FORMAT("ZIDF001", "날짜 형식이 잘못되었습니다."),

    // crypt
    MICE_PRODUCT_DECRYPT_ERROR("ZMPS001", "주문서 목록 복호화 중 오류가 발생했습니다."),

    ;
    private final String code;
    private final String message;

    MtnErrorCode(final String code, final String message) {
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
