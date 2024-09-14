package kr.co.util;

import com.google.firebase.messaging.*;

import java.util.ArrayList;
import java.util.List;

public class PushContentUtils {
    public static List<MulticastMessage> makeMultiMessages(String title, String body, List<String> targetToken) {
        List<MulticastMessage> multicastMessages = new ArrayList<>();
        // 500개씩 자르기 위한 루프
        int batchSize = 500;
        for (int i = 0; i < targetToken.size(); i += batchSize) {
            List<String> batchTokens = targetToken.subList(i, Math.min(i + batchSize, targetToken.size()));

            // 잘라낸 토큰들로 MulticastMessage 생성
            MulticastMessage message = makeMultiMessage(title, body, batchTokens);
            multicastMessages.add(message);
        }

        return multicastMessages;
    }


    public static MulticastMessage makeMultiMessage(String title, String body, List<String> targetToken) {
        Aps aps = Aps.builder()
                .putCustomData("sound", "default")
                .putCustomData("vibrate", "default")
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(aps)
                .build();

        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();

        return MulticastMessage.builder()
                .setNotification(notification)
                .setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build())
                .setApnsConfig(apnsConfig)
                .putData("title", title == null ? "" : title)
                .putData("body", body)
                .addAllTokens(targetToken)
                .build();
    }


    public static Message makeMessage(String title, String body, String targetToken, String msgGroupId, String advisorCode) {
        Aps aps = Aps.builder()
                .putCustomData("sound", "default")
                .putCustomData("vibrate", "default")
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(aps)
                .build();

        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();


        return Message
                .builder()
                .setNotification(notification)
                .setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build())
                .setApnsConfig(apnsConfig)
                .putData("msgGroupId", msgGroupId)
                .putData("advisorCode", advisorCode == null ? "" : advisorCode)
                .setToken(targetToken)
                .build();
    }
}
