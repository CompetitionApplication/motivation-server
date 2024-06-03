package kr.co.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String farm_id;
    private String farm_kind;
    private String farm_name;
    private String farm_zip;
    private String farm_zip_detail;
    private String farm_zip_code;
    private String farm_owner_name;
    private String farm_owner_tel;
    private String farm_main_image;
    private String farm_max_user_cnt;
    private String farm_use_amt;
    private String farm_use_day;
    private String farm_holi_day_yn;
    private String farm_use_time;
    private String farm_free_time;
    private String farm_account_no;
    private String farm_account_bank;
    private String farm_area;
    private String farm_fond_de;
    private String farm_intrcn;
    private String farm_latitude;
    private String farm_longitude;
    private String farm_page;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
