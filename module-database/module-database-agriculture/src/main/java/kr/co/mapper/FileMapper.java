package kr.co.mapper;

import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import kr.co.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFileGroup(@Param("fileGroupId") String fileGroupId);
    void insertFile(File file);

    File selectFile(@Param("fileId") String fileId);
}
