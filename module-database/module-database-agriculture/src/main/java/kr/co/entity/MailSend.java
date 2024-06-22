package kr.co.entity;

import lombok.Data;

@Data
public class MailSend {

    private String mail_id;
    private String user_id;
    private String maile_title;
    private String maile_to_adress;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
