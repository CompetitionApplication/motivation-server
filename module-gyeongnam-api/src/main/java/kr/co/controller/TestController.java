package kr.co.controller;

import kr.co.entity.Member;
import kr.co.mapper.MemberMapper;
import kr.co.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MemberRepository memberRepository;
    private final MemberMapper mapper;

    @GetMapping("/jpa")
    public List<Member> jpa(){
        return memberRepository.findAll();
    }
    @PostMapping("/jpa")
    public void jpa2(){
        memberRepository.save(new Member("김영재","남자"));
    }
    @GetMapping("/mybatis")
    public List<Member> mybatis(){
        return mapper.findAllMember();
    }
    @PostMapping("/mybatis")
    public void mybatis2(){
        mapper.addMember(new Member("김영재","남자"));
    }
}
