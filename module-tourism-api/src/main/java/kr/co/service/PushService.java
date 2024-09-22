package kr.co.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.PushDetailResDto;
import kr.co.dto.PushReqDto;
import kr.co.dto.PushResDto;
import kr.co.entity.Push;
import kr.co.repository.PushRepository;
import kr.co.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.co.util.PushContentUtils.makeMultiMessages;

@Service
@RequiredArgsConstructor
@Slf4j
public class PushService {

    private final FirebaseMessaging firebaseMessaging;
    private final PushRepository pushRepository;
    private final UserRepository userRepository;

    @Transactional
    public void sendPush(PushReqDto pushReqDto) throws FirebaseMessagingException {
        List<String> fcmTokens = userRepository.findAll().stream()
                .map(user -> user.getAppDeviceToken())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        String pushTitle = pushReqDto.getPushTitle() != null ? pushReqDto.getPushTitle() : null;
        String pushContent = pushReqDto.getPushContent();

        // 제목 글자 수 제한
        if (pushReqDto.getPushTitle() != null && pushTitle.length() > 50) {
            pushTitle = pushReqDto.getPushTitle().substring(0, 50) + "...";
        }

        // 본문 글자 수 제한
        if (pushReqDto.getPushContent().length() > 300) {
            pushContent = pushReqDto.getPushContent().substring(0, 300) + "...";
        }

        List<MulticastMessage> multicastMessages = makeMultiMessages(pushTitle, pushContent, fcmTokens);

        //푸시전송
        sendPushForUser(multicastMessages);

        //푸시전송 후 DB에 저장
        pushRepository.save(new Push(pushReqDto));
    }

    private void sendPushForUser(List<MulticastMessage> multicastMessages) throws FirebaseMessagingException {
        for (MulticastMessage multicastMessage : multicastMessages) {
            firebaseMessaging.sendEachForMulticast(multicastMessage);
        }
    }

    @Transactional(readOnly = true)
    public List<PushResDto> getPushList() {
        return pushRepository.findTop7ByOrderByRegDatetimeDesc().stream()
                .map(push -> PushResDto.builder()
                        .pushId(push.getPushId())
                        .pushTitle(push.getPushTitle())
                        .regDatetime(push.getRegDatetime())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PushDetailResDto getPushDetail(String pushId) {
        Push pushInfo = pushRepository.findById(pushId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_PUSH.getCode(), CommonErrorCode.NOT_FOUND_PUSH.getMessage()));

                return PushDetailResDto.builder()
                        .pushTitle(pushInfo.getPushTitle())
                        .pushContent(pushInfo.getPushContent())
                        .build();
    }
}
