package com.osanda.roihunter.fbuserdata.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "PHOTO_ID", nullable = false)
	private Long photoId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "LINK", nullable = false)
	private String link;

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

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Album album;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Reaction> reactions;

}
