package kr.co.common.file;

import kr.co.dto.common.file.response.FileResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<FileResponseDto> uploadImage(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    ResponseEntity<byte[]> showImage(String fileId) throws Exception, IOException;

    ResponseEntity<byte[]> getPrivacyClause() throws Exception;
}
