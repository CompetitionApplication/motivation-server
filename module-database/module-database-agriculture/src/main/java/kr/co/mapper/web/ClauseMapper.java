package kr.co.mapper.web;

import kr.co.dto.web.clause.response.PrivacyClauseResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClauseMapper {

    PrivacyClauseResDto selectPrivacyClause();

}
