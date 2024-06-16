package kr.co.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String reservation_id;
    private String user_id;
    private String farm_id;
    private String reservation_name;
    private String reservation_email;
    private String reservation_tel;
    private String reservation_date;
    private int reservation_participants;
    private String reservation_status;
    private String reservation_start_time;
    private String reservation_end_time;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
