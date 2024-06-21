package kr.co.app.home.service;

import kr.co.dto.app.home.request.StatusChangeReqDto;
import kr.co.dto.app.home.response.HomeResDto;
import kr.co.entity.User;

import java.util.List;

public interface HomeService {

    List<HomeResDto> homtList(String homeTab, User user);
    void statusChange(StatusChangeReqDto statusChangeReqDto, User user) throws Exception;
}
