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
	@Column(name = "PHOTO_ID", nullable = false)
	private Long photoId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "LINK", nullable = false)
	private String link;

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Album album;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Image> images;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Reaction> reactions;
}
