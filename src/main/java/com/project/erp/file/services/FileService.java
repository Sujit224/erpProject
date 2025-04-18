package com.project.erp.file.services;

import com.project.erp.files.models.FileModel;

public interface FileService {
	
	public FileModel uploadFile(FileModel fileModel);
	
	public FileModel downloadFile(Long fileId);

	public FileModel downloadByUser(Long userId);

}
