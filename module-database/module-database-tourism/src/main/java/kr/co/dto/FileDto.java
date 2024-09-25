package kr.co.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class FileDto {
    private org.springframework.core.io.Resource resource;
    private String contentType;

    public FileDto(Resource resource, String contentType) {
        this.resource = resource;
        this.contentType = contentType;
    }
}
