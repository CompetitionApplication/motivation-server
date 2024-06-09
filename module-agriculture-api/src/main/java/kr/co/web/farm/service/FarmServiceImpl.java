package kr.co.web.farm.service;

import kr.co.client.OpenApiClient;
import kr.co.common.AES256Util;
import kr.co.dto.web.farm.response.FarmBannerResDto;
import kr.co.dto.web.farm.response.FarmDetailResDto;
import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import kr.co.entity.Farm;
import kr.co.mapper.web.MemberMapper;
import kr.co.mapper.web.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
    @Value("${aes.key}")
    private String aesKEY;

    @Value("${aes.iv}")
    private String aesIV;
    final FarmMapper farmMapper;
    final MemberMapper mapper;
    final OpenApiClient openApiClient;

    @Override
    public List<FarmsResDto> farms(String farmKind, String farmName, String farmUseDay, String farmMaxUserCnt){
        List<FarmsResDto> r =  farmMapper.selectFarms(farmKind,farmName,farmUseDay,farmMaxUserCnt);
        return r;
    }

    @Override
    public FarmDetailResDto farmDetail(String farmId){
        FarmDetailResDto r = farmMapper.selectFarmByFarmId(farmId);
        List<FarmBannerResDto> bannerImage = farmMapper.selectFarmBannerImage(farmId);
        r.setBannerImageList(bannerImage);
        return r;
    }

    @Override
    public void openApi() throws Exception{
        String[] area = {"충청","경기","제주","영남","호남","전남","경남"};
        for(int i=0; i<area.length; i++){
            FarmClientResDto farmClientResDto = openApiClient.openApi(area[i]);
            if(farmClientResDto.getGrid() == null){
                return;
            }
            if(!farmClientResDto.getGrid().getResult().getCode().equals("INFO-000")){
                return;
            }
            for(FarmClientResDto.Row row: farmClientResDto.getGrid().getRow()){
                int chk = farmMapper.selectFarm(row.getFARM_NM());
                if(chk == 0){
                    String id = RandomStringUtils.randomAlphanumeric(5);
                    String pw = AES256Util.AES256encrypt(RandomStringUtils.randomAlphanumeric(10),aesKEY,aesIV);
                    farmMapper.insertFarm(row,id,pw);
                }
            }
        }
    }
}
