package kr.co.dto.web.farm.response;

import lombok.Data;

@Data
public class FarmReviewResDto {

    private String reviewerImage;
    private String reviewerName;
    private String revieweStarScore;
    private String revieweContent;

}
