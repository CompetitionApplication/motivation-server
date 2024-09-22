package kr.co.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class GoodsUpdateDto {
    private String goodsPrice;
    private String goodsName;
    private Integer badgeOpenCount;
    private String goodsColor;
    private String goodsSize;
    private String goodsFrom;
    private String goodsReleaseDate;
    private String goodsDeliveryDate;
    private String areaCodeId;
    private String detailAreaCodeId;
    private List<MultipartFile> goodsImages;


}
