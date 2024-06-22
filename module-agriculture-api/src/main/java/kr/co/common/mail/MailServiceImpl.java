package kr.co.common.mail;

import jakarta.mail.internet.MimeMessage;
import kr.co.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    final JavaMailSender javaMailSender;
    final SpringTemplateEngine templateEngine;

    @Override
    public void sendReservationMail(ReservationMailDto reservationMailDto) throws Exception{

        log.info("=======MAIL SEND INFO=======");
        log.info("reservationMailDto ::: {}", reservationMailDto);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //메일 제목 설정
        helper.setSubject(reservationMailDto.getTitle());

        // 발신자 설정
        helper.setFrom("weFarm@s2it.com");

        //수신자 설정
        helper.setTo(reservationMailDto.getToMail());

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariable("reservationId", reservationMailDto.getReservationId());
        context.setVariable("farmName", reservationMailDto.getFarmName());
        context.setVariable("reservationDate", reservationMailDto.getReservationDate());
        context.setVariable("totalAmount", reservationMailDto.getTotalAmount());
        context.setVariable("userName", reservationMailDto.getUserName());
        context.setVariable("userTel", reservationMailDto.getUserTel());
        context.setVariable("middleTitle", reservationMailDto.getMiddleTitle());
        context.setVariable("smallTitle", reservationMailDto.getSmallTitle());

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("mail",context);
        helper.setText(html, true);

        //메일 보내기
        javaMailSender.send(message);

        //메일 발송이력 저장

    }
}
