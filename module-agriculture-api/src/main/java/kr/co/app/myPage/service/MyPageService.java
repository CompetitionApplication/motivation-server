package kr.co.app.myPage.service;

import kr.co.dto.app.myPage.response.MyPageResDto;
import kr.co.entity.User;

public interface MyPageService {

    MyPageResDto info(User user);
}
