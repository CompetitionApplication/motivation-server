package kr.co.entity;

import jakarta.persistence.*;
import kr.co.dto.FileSaveDto;
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
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "파일키값")
    private String fileId;
    @Comment(value = "업로드된 파일명")
    private String originalName;
    @Comment(value = "저장될때 파일명")
    private String storedName;
    @Comment(value = "파일의 MIME 타입")
    private String fileType;
    @Comment(value = "파일사이즈")
    private long fileSize;
    @Comment(value = "파일경로")
    private String filePath;
    @Comment(value = "파일url")
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;


    public File(FileSaveDto fileSaveDto, FileGroup fileGroup) {
        this.originalName = fileSaveDto.getOriginalName();
        this.storedName = fileSaveDto.getStoredName();
        this.fileType = fileSaveDto.getFileType();
        this.fileSize = fileSaveDto.getFileSize();
        this.filePath = fileSaveDto.getFilePath();
        this.fileGroup = fileGroup;
        this.fileUrl = fileSaveDto.getFileUrl();
    }

    public void saveFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
