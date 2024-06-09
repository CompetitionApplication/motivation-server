package kr.co.web.clause.service;

import kr.co.dto.web.clause.response.ClauseListResDto;
import kr.co.dto.web.clause.response.ClauseResDto;
import kr.co.dto.web.clause.response.PrivacyClauseResDto;
import kr.co.mapper.web.ClauseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ClauseListResDto clauseList(){
        ClauseListResDto r = new ClauseListResDto();

        List<ClauseResDto> clauseResDtoList = clauseMapper.selectClause();
        r.setClauseList(clauseResDtoList);

        return r;
    }
}
