package kr.co.dto.common.push;

import com.google.firebase.messaging.*;

public class PushContent {

    public static Message makeMessage(String targetToken, String title, String body, int alarmCnt) {
        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();
        return Message
                .builder()
                .setNotification(notification)
                .setToken(targetToken)
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setBadge(alarmCnt)
                                .build())
                        .build())
                .build();
    }

}
