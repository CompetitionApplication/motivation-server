package kr.co.controller.common;

import kr.co.dto.FileDto;
import kr.co.service.common.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/common/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileId}")
    public ResponseEntity<?> getFileUrl(@PathVariable(value = "fileId") String fileId) throws IOException {
        FileDto fileDto = fileService.getFileUrl(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileDto.getResource().getFilename() + "\"")
                .body(fileDto.getResource());

        //return ResponseEntity.ok(fileService.getFileUrl(fileId));
    }
}
