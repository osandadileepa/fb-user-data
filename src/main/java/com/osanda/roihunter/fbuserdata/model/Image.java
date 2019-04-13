package com.osanda.roihunter.fbuserdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "IMAGE", catalog = "fb_user")
public class Image extends BaseModel {

	private static final long serialVersionUID = -4073697566061896138L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "WIDTH")
	private Integer width;

	@Column(name = "HEIGHT")
	private Integer height;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FULL_FILE_PATH")
	private String fullFilePath;

	@Column(name = "RELETIVE_FILE_PATH")
	private String reletiveFilePath;

	@Column(name = "FILE_SIZE")
	private Long fileSize;

}
