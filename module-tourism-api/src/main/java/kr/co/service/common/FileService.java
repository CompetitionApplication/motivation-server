package kr.co.service.common;

import kr.co.dto.FileDto;
import kr.co.entity.File;
import kr.co.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;


    public FileDto getFileUrl(String fileId) throws IOException {
        File fileInfo = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));
        // 파일 경로 지정 (파일 경로를 저장하는 변수를 사용)
        String basePath = "/app/tour/files/";

        // 파일 이름이 한글일 경우에 대비해 URL 디코딩
        String decodedFileName = URLDecoder.decode(fileInfo.getStoredName(), StandardCharsets.UTF_8);
        Path filePath = Paths.get(basePath + decodedFileName);

        // 파일 경로에서 리소스 추출
        Resource resource = new UrlResource(filePath.toUri());

        // 파일이 존재하는지 및 읽기 가능한지 확인
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("파일을 찾을 수 없거나 읽을 수 없습니다.");
        }

        // 파일 확장자에 따라 Content-Type 설정
        String contentType = Files.probeContentType(filePath);  // MIME 타입을 자동 추출

        // MIME 타입이 없을 경우 기본값 설정
        if (contentType == null) {
            contentType = "application/octet-stream";  // 기본 MIME 타입
        }


        return new FileDto(resource, contentType);
    }
}
