package kr.co.repository;

import kr.co.entity.FileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileGroupRepository extends JpaRepository<FileGroup,String> {
}
