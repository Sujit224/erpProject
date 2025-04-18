package com.project.erp.files.models;

import com.project.erp.user.models.UserModel;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
public class FileModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;

	private String fileName;

	private String fileType;

	@Lob
	@Column(columnDefinition = "BLOB")
	private byte[] fileData;

	@JsonIncludeProperties({ "userId", "userName" })
	@OneToOne
	@JoinColumn(name = "userId")
	private UserModel user;

}
