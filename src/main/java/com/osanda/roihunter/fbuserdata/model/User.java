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
import javax.validation.constraints.Email;

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

	@Column(name = "NAME")
	private String name;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Email
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "PROFILE_PIC_URL")
	private String profilePicUrl = "https://graph.facebook.com/v3.2/" + this.fbid + "/picture";

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Photo> photos;

}
