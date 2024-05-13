package kr.co.mapper;

import kr.co.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> findAllMember();

    void addMember(Member member);

}
