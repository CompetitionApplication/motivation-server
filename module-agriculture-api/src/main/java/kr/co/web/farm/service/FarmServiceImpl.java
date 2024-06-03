package kr.co.web.farm.service;

import kr.co.client.OpenApiClient;
import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import kr.co.mapper.MemberMapper;
import kr.co.mapper.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    final FarmMapper farmMapper;
    final MemberMapper mapper;
    final OpenApiClient openApiClient;

    @Override
    public List<FarmsResDto> farms(){
        List<FarmsResDto> r =  farmMapper.selectFarms();
        return r;
    }

    @Override
    public void openApi(){
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
                    String pw = RandomStringUtils.randomAlphanumeric(10);
                    farmMapper.insertFarm(row,id,pw);
                }
            }
        }
    }
}
