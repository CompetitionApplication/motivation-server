package kr.co.mapper.web;

import kr.co.entity.File;
import kr.co.entity.FileGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {
    void insertFileGroup(@Param("fileGroupId") String fileGroupId);
    void insertFile(File file);

    File selectFile(@Param("fileId") String fileId);
    FileGroup selectFileGroup(String fileGroupId);
}
