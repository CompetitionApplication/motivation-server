package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileSaveDto {
    private String originalName;
    private String storedName;
    private String fileType;
    private long fileSize;
    private String filePath;
    private String fileUrl;
}
