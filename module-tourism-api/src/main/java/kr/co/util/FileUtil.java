package kr.co.util;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.FileSaveDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    public static List<FileSaveDto> uploadFile(List<MultipartFile> images, String uploadDir) {
        try {
            List<FileSaveDto> fileSaveDtos = new ArrayList<>();
            StringBuilder filenames = new StringBuilder();
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    // 파일명을 유니크하게 생성하여 저장
                    String originalFilename = image.getOriginalFilename();
                    String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
                    String fileType = image.getContentType();
                    long fileSize = image.getSize();
                    Path filePath = Paths.get(uploadDir + uniqueFilename);

                    // 디렉토리가 존재하지 않으면 생성
                    File dir = new File(uploadDir);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    // 파일 저장
                    Files.write(filePath, image.getBytes());
                    filenames.append(uniqueFilename).append(", ");

                    // 파일 정보 리턴
                    fileSaveDtos.add(
                            FileSaveDto.builder()
                            .originalName(originalFilename)
                            .storedName(uniqueFilename)
                            .fileType(fileType)
                            .fileSize(fileSize)
                            .filePath(filePath.toString())
                            .build());
                }
            }
            return fileSaveDtos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CommonException(CommonErrorCode.FILE_UPLOAD_FAIL.getCode(), CommonErrorCode.FILE_UPLOAD_FAIL.getMessage());
    }
}
