package kr.co.dto.web.notice.response;

import lombok.Data;

@Data
public class NoticeResDto {
    private String noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
}
