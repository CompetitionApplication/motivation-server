package kr.co.common.file;

import kr.co.dto.common.file.response.FileResponseDto;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.uploadPath}")
    private String uploadPath;

    @Value("${file.url}")
    private String fileUrl;

    final FileMapper fileMapper;

    final CommonMapper commonMapper;

    @Override
    public List<FileResponseDto> uploadImage(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{

        //응답부
        List<FileResponseDto> fileResponseDtoList = new ArrayList<>();

        //허용 파일
        List<String> allowFileType = Arrays.asList("gif", "png", "jpeg", "bmp", "pdf", "html");

        //파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map
        Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();

        //files.entrySet()의 요소
        Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();

        MultipartFile mFile;

        //파일이 업로드 될 경로를 지정
        String filePath = uploadPath;

        //파일명이 중복되었을 경우, 사용할 스트링 객체
        String saveFileName = "";
        String savaFilePath = "";

        //그룹 테이블 선 저장
        String fileGroupId = commonMapper.selectUUID();
        fileMapper.insertFileGroup(fileGroupId);

        //읽어 올 요소가 있으면 true, 없으면 false를 반환
        while (itr.hasNext()) {

            Map.Entry<String, MultipartFile> entry = itr.next();

            //entry에 값을 가져온다.
            mFile = entry.getValue();

            //실제 파일명
            String fileName = mFile.getOriginalFilename();

            //키로 저장될 파일ID(문자숫자 포함 랜덤 문자열 8자리)
            String fileId = RandomStringUtils.randomAlphanumeric(10);

            //확장자
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

            boolean allowFileCheck = allowFileType.contains(fileExt.toLowerCase());

            if(!allowFileCheck) {
                throw new Exception();
            }

            //저장될 경로와 파일명
            String saveFilePath = filePath + File.separator + fileName;

            //filePath에 해당되는 파일의 File 객체를 생성
            File fileFolder = new File(filePath);

            if (!fileFolder.exists()) {
                //부모 폴더까지 포함하여 경로에 폴더 생성
                if (fileFolder.mkdirs()) {
                    log.info("[file.mkdirs] : Success");
                } else {
                    log.error("[file.mkdirs] : Fail");
                }
            }

            //중복여부
            File saveFile = new File(saveFilePath);

            //saveFile이 File이면 true, 아니면 false
            //파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성
            if (saveFile.isFile()) {
                boolean _exist = true;

                int index = 0;

                // 동일한 파일명이 존재하지 않을때까지 반복
                while (_exist) {
                    index++;

                    saveFileName = fileName.substring(0,fileName.lastIndexOf(".")) + "(" + index + ")." + fileExt;

                    String dictFile = filePath + File.separator + saveFileName;

                    _exist = new File(dictFile).isFile();

                    if (!_exist) {
                        savaFilePath = dictFile;
                    }
                }

                //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리
                mFile.transferTo(new File(savaFilePath));

                //DB저장
                kr.co.entity.File file = new kr.co.entity.File();
                file.setFile_id(fileId);
                file.setFile_group_id(fileGroupId);
                file.setFile_url(fileUrl+File.separator+fileId);
                file.setFile_name(saveFileName);
                file.setFile_path(filePath);
                fileMapper.insertFile(file);

                //응답부 추가
                FileResponseDto fileResponseDto = new FileResponseDto();
                fileResponseDto.setFileId(fileId);
                fileResponseDto.setGroupFileId(fileGroupId);
                fileResponseDto.setFileName(saveFileName);
                fileResponseDto.setFilePath(filePath);
                fileResponseDto.setFileUrl(fileUrl+File.separator+fileId);
                fileResponseDtoList.add(fileResponseDto);

            } else {
                //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리
                mFile.transferTo(saveFile);

                //DB저장
                kr.co.entity.File file = new kr.co.entity.File();
                file.setFile_id(fileId);
                file.setFile_group_id(fileGroupId);
                file.setFile_url(fileUrl+File.separator+fileId);
                file.setFile_name(fileName);
                file.setFile_path(filePath);
                fileMapper.insertFile(file);

                //응답부 추가
                FileResponseDto fileResponseDto = new FileResponseDto();
                fileResponseDto.setFileId(fileId);
                fileResponseDto.setGroupFileId(fileGroupId);
                fileResponseDto.setFileName(fileName);
                fileResponseDto.setFilePath(filePath);
                fileResponseDto.setFileUrl(fileUrl+File.separator+fileId);
                fileResponseDtoList.add(fileResponseDto);
            }
        }
        return fileResponseDtoList;
    }

    public ResponseEntity<byte[]> showImage(String fileId) throws Exception, IOException {

        kr.co.entity.File fileInfo =  fileMapper.selectFile(fileId);

        File file = new File(fileInfo.getFile_path()+File.separator+fileInfo.getFile_name());

        if(!file.isFile()){
            throw new Exception();
        }

        ResponseEntity<byte[]> result = null;

        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", Files.probeContentType(file.toPath()));
        result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        return result;
    }



}
