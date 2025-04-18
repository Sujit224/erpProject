package com.project.erp.file.controllers;

import java.io.IOException;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.erp.file.services.FileService;
import com.project.erp.files.models.FileModel;
import com.project.erp.setup.models.APIReturnModel;
import com.project.erp.user.models.UserModel;
import com.project.erp.user.service.UserService;
import com.project.erp.util.FileUtiles;


@RestController
@RequestMapping("/file-resource")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserService userService;
	
	private APIReturnModel apiReturnModel;
	
	private Vector<FileModel> fileVec;
	
	
	@PostMapping("/")
	ResponseEntity<?> upload (@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException{
		apiReturnModel = new APIReturnModel();
		fileVec = new Vector<>();
		UserModel user = userService.getUserByUserId(userId);
		 FileModel fileModel = new FileModel();
		 if(user != null) {
			
				fileModel.setFileName(file.getOriginalFilename());
				fileModel.setFileType(file.getContentType());
				fileModel.setFileData(FileUtiles.compressFile(file.getBytes()));
				fileModel.setUser(user);
		 }
	
	    try {
			FileModel responceFile = fileService.uploadFile(fileModel);
			fileVec.add(responceFile);
//			apiReturnModel.setData(fileVec);
			apiReturnModel.setMessage(" File Uploaded Successfully");
			apiReturnModel.setStatus("Success");
			apiReturnModel.setCount(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiReturnModel.setStatus("Fail");
			apiReturnModel.setCount(0);
		}
	    
	    return ResponseEntity.ok(apiReturnModel);
	    
	}
	
	@GetMapping("/{fileId}")
	ResponseEntity<?> download(@PathVariable("fileId") Long fileId){
		apiReturnModel = new APIReturnModel();
		fileVec = new Vector<>();
		byte [] file = new byte[100000000];
		String fileType = "image/jpeg";
		try {
			FileModel fileModel = this.fileService.downloadFile(fileId);
			System.out.println("fileName: "+fileModel.getFileName());
			file = FileUtiles.decompressFile(fileModel.getFileData());

//			System.out.println("fileData: "+fileModel.getFileData());
//			System.out.println("fileType: "+fileModel.getFileType());
//			fileType = fileModel.getFileType();
		//	String filePath = fileModel.get
//			FileModel fileModel2 = new FileModel();
//			fileModel2.setFileData(FileUtils.decompressFile(fileModel.getFileData()));
//			fileModel2.setFileId(fileModel.getFileId());
//			fileModel2.setFileName(fileModel.getFileName());
//			fileModel2.setFileType(fileModel.getFileType());
//			fileVec.add(fileModel2);
//			apiReturnModel.setData(fileVec);
//			apiReturnModel.setCount(fileVec.size());
//			apiReturnModel.setMessage("file Downloaded Successfully");
//			apiReturnModel.setStatus("Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			apiReturnModel.setMessage("Something went Wrong !!");
//			apiReturnModel.setStatus("fail");
		}
	    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileType)).body(file);
		
	}
	
	@GetMapping("/user/{userId}")
	ResponseEntity<?> downloadByUser(@PathVariable("userId") Long userId){
		apiReturnModel = new APIReturnModel();
		fileVec = new Vector<>();
		byte [] file = new byte[100000000];
		String fileType = "image/jpeg";
		try {
			FileModel fileModel = this.fileService.downloadByUser(userId);
			System.out.println("fileName: "+fileModel.getFileName());
			file = FileUtiles.decompressFile(fileModel.getFileData());
			
			System.out.println("fileData: "+fileModel.getFileData());
			System.out.println("fileType: "+fileModel.getFileType());
			fileType = fileModel.getFileType();
		//	String filePath = fileModel.get
//			FileModel fileModel2 = new FileModel();
//			fileModel2.setFileData(FileUtils.decompressFile(fileModel.getFileData()));
//			fileModel2.setFileId(fileModel.getFileId());
//			fileModel2.setFileName(fileModel.getFileName());
//			fileModel2.setFileType(fileModel.getFileType());
//			fileVec.add(fileModel2);
//			apiReturnModel.setData(fileVec);
//			apiReturnModel.setCount(fileVec.size());
//			apiReturnModel.setMessage("file Downloaded Successfully");
//			apiReturnModel.setStatus("Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			apiReturnModel.setMessage("Something went Wrong !!");
//			apiReturnModel.setStatus("fail");
		}
	    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileType)).body(file);
		
	}


}
