package kr.co.dto.web.openApiClient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FarmClientResDto {

    @JsonProperty("Grid_20150407000000000218_1")
    private Grid grid;

    @Data
    public static class Grid {
        @JsonProperty("totalCnt")
        private int totalCnt;
        @JsonProperty("startRow")
        private int startRow;
        @JsonProperty("endRow")
        private int endRow;
        @JsonProperty("result")
        private Result result;
        @JsonProperty("row")
        private List<Row> row;
    }

    @Data
    public static class Result {
        @JsonProperty("code")
        private String code;
        @JsonProperty("message")
        private String message;
    }

    @Data
    public static class Row {
        @JsonProperty("ROWNUM")
        private int ROWNUM;
        @JsonProperty("AREA")
        private String AREA;
        @JsonProperty("FARM_NM")
        private String FARM_NM;
        @JsonProperty("RPRSNTV")
        private String RPRSNTV;
        @JsonProperty("FOND_DE")
        private String FOND_DE;
        @JsonProperty("FRAM_AR")
        private int FRAM_AR;
        @JsonProperty("BRD_LVSTCK_CO")
        private String BRD_LVSTCK_CO;
        @JsonProperty("PRDCTN_QY")
        private int PRDCTN_QY;
        @JsonProperty("FARM_INTRCN")
        private String FARM_INTRCN;
        @JsonProperty("ADDR")
        private String ADDR;
        @JsonProperty("TLPHON_NO")
        private String TLPHON_NO;
        @JsonProperty("HMPG")
        private String HMPG;
        @JsonProperty("RDNMADR")
        private String RDNMADR;
        @JsonProperty("NW_ZIP")
        private String NW_ZIP;
        @JsonProperty("LA")
        private double LA;
        @JsonProperty("LO")
        private double LO;
    }
}
