package kr.co.repository;

import kr.co.entity.File;
import kr.co.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,String> {
}
