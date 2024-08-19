package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
import kr.co.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FileGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "파일그룹키값")
    private String fileGroupId;

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    @OneToMany(mappedBy = "fileGroup")
    private List<File> files;

    public FileGroup(boolean delYn) {
        this.delYn = delYn;
    }


    public void deleteFileGroup(boolean delYn) {
        this.delYn = delYn;
    }
}
