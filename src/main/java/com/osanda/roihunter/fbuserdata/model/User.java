package com.osanda.roihunter.fbuserdata.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.osanda.roihunter.fbuserdata.model.enums.Gender;

import lombok.Getter;
import lombok.Setter;

/***
 * basic details about user
 * 
 * @author Osanda Wedamulla
 *
 */

@Setter
@Getter
@Entity
@Table(name = "USER", catalog = "fb_user")
public class User extends BaseModel {

	private static final long serialVersionUID = -2650748825652470008L;

	@Id
	@Column(name = "FB_ID", nullable = false)
	private Long fbid;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "PROFILE_PIC_URL")
	private String profilePicUrl;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Photo> photos;

}
