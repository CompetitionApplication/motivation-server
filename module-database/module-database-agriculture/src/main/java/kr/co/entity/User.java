package kr.co.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String user_id;
    private String user_web_id;
    private String user_web_pw;
    private String user_email;
    private String user_name;
    private String user_tel;
    private String user_birth;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
