package kr.co.mapper.web;

import kr.co.dto.web.clause.response.ClauseResDto;
import kr.co.dto.web.clause.response.PrivacyClauseResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClauseMapper {

    PrivacyClauseResDto selectPrivacyClause();
    List<ClauseResDto> selectClause();

}
