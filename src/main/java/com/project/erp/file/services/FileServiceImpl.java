package com.project.erp.file.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.erp.file.repository.FileRepository;
import com.project.erp.files.models.FileModel;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileRepository fileRepository;

	@Override
	public FileModel uploadFile(FileModel fileModel) {
		// TODO Auto-generated method stub
		return this.fileRepository.save(fileModel);
	}

	@Override
	public FileModel downloadFile(Long fileId) {
		// TODO Auto-generated method stub
		return this.fileRepository.findByFileId(fileId);
	}

	@Override
	public FileModel downloadByUser(Long userId) {
		// TODO Auto-generated method stub
		return this.fileRepository.findByUserId(userId);
	}

}
