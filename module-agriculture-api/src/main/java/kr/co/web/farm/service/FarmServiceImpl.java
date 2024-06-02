package kr.co.web.farm.service;

import kr.co.client.OpenApiClient;
import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.mapper.MemberMapper;
import kr.co.mapper.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        List<FarmsResDto> r =  farmMapper.farms();
        return r;
    }

    @Override
    public void test(){
        openApiClient.test();
    }
}
