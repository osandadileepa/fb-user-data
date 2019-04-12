package com.osanda.roihunter.fbuserdata.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FULL_FILE_PATH")
	private String fullFilePath;

	@Column(name = "RELETIVE_FILE_PATH")
	private String reletiveFilePath;

	@Column(name = "FILE_SIZE")
	private Double fileSize;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Album album;

	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private List<Reaction> reactions;

}
