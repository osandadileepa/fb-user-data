package com.osanda.roihunter.fbuserdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "PHOTO", catalog = "fb_user")
public class Photo extends BaseModel {

	private static final long serialVersionUID = 690055354128195603L;

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "URL", nullable = false)
	private String url;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "FILE_SIZE")
	private Double fileSize;
	
	

}
