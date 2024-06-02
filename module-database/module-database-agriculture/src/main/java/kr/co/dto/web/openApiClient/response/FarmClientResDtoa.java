package kr.co.dto.web.openApiClient.response;

import lombok.Data;

import java.util.List;

@Data
public class FarmClientResDtoa {

    private Result result;

    @Data
    public static class Result {
        private String code;
    }
}
