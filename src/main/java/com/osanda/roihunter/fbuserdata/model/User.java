package com.osanda.roihunter.fbuserdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-z0-9]*$")
    @Size(min = 1, max = 50)
    @Column(name = "USER_NAME", length = 50, unique = true, nullable = false)
    private String userName;

    @Email
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "GENDER", nullable = false)
    private String gender;
    
    @Column(name = "FB_ID")
    private String fbid;
    
    

}
