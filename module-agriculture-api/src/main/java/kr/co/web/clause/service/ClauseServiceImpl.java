package kr.co.web.clause.service;

import kr.co.dto.web.clause.response.PrivacyClauseResDto;
import kr.co.mapper.ClauseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClauseServiceImpl implements ClauseService{

    final ClauseMapper clauseMapper;

    @Override
    public PrivacyClauseResDto privacy(){
        PrivacyClauseResDto r = clauseMapper.selectPrivacyClause();
        return r;
    }
}
