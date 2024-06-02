package kr.co.dto.web.openApiClient.response;

import lombok.Data;

import java.util.List;

@Data
public class FarmClientResDto {

    private Grid Grid_20150407000000000218_1;

    @Data
    public static class Grid {
        private int totalCnt;
        private int startRow;
        private int endRow;
        private Result result;
        private List<Row> row;
    }

    @Data
    public static class Result {
        private String code;
        private String message;
    }

    @Data
    public static class Row {
        private int ROWNUM;
        private String AREA;
        private String FARM_NM;
        private String RPRSNTV;
        private String FOND_DE;
        private int FRAM_AR;
        private String BRD_LVSTCK_CO;
        private int PRDCTN_QY;
        private String FARM_INTRCN;
        private String ADDR;
        private String TLPHON_NO;
        private String HMPG;
        private String RDNMADR;
        private String NW_ZIP;
        private double LA;
        private double LO;
    }
}
