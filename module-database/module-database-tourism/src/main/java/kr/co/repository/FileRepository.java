package kr.co.repository;

import kr.co.entity.File;
import kr.co.entity.FileGroup;
import kr.co.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File,String> {
    List<File> findAllByFileGroup(FileGroup fileGroup);
}
