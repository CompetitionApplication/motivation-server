package kr.co.dto.web.openApiClient.request;

import lombok.Data;

@Data
public class FarmClientReqDto {

    private String API_KEY;
    private String TYPE;
    private String API_URL;
    private String START_INDEX;
    private String END_INDEX;
    private String AREA;
    private String FARM_NM;

}
