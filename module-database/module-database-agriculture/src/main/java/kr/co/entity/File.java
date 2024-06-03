package kr.co.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
public class File {

    private String file_id;
    private String file_group_id;
    private String file_url;
    private String file_name;
    private String file_path;
    private String created_id;
    private String created_date;
    private String mod_id;
    private String mod_date;
}
