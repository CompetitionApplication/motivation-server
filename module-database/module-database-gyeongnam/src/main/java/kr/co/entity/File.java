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

    @Comment(value = "파일URL")
    @Column(length = 100)
    private String fileUrl;
    @Comment(value = "파일이름")
    @Column(length = 50)
    private String fileName;
    @Comment(value = "파일경로")
    @Column(length = 50)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;
}
