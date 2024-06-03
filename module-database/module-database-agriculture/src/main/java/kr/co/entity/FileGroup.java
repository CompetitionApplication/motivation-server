package kr.co.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
public class FileGroup {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private String file_group_id;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
