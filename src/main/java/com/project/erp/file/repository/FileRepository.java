package com.project.erp.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.erp.files.models.FileModel;

public interface FileRepository extends JpaRepository<FileModel, Long> {

	FileModel findByFileId(Long fileId);
    
	// Not Required Still to show case that how to write our native queries
	@Query(value = "select * from files where user_id =?1", nativeQuery = true) 
	FileModel findByUserId(Long userId);

}
