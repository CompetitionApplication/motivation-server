package kr.co.dto.common.file.response;

import lombok.Data;

@Data
public class FileResponseDto {

    private String groupFileId;

    private String fileId;

    private String fileName;

    private String filePath;

    private String fileUrl;
}
