package kr.co.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Alarm {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String alarm_id;
    private String farm_id;
    private String alarm_kind;
    private String alarm_title;
    private String alarm_content;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
