package kr.co.mapper.common;

import kr.co.entity.File;
import kr.co.entity.FileGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {
    void insertFileGroup(String fileGroupId);
    void insertFile(File file);

    File selectFile(String fileId);
    FileGroup selectFileGroup(String fileGroupId);
    void deleteFileGroup(String fileGroupId);
    void updateFile(String fileId, String fileGroupId);
}
