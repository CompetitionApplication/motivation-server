package kr.co.entity;

import jakarta.persistence.*;
import kr.co.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "파일키값")
    private String fileId;
    @Column(name = "file_url", length = 100)
    private String fileUrl;

    @Column(name = "file_name", length = 50)
    private String fileName;

    @Column(name = "file_path", length = 50)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;
}
