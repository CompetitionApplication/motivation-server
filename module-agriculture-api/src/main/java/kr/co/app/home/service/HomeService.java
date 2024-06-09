package kr.co.app.home.service;

import kr.co.dto.app.home.response.HomeResDto;

import java.util.List;

public interface HomeService {

    List<HomeResDto> homtList(String homeTab);
}
