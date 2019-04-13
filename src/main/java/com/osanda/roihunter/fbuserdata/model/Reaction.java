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
@Table(name = "REACTION", catalog = "fb_user")
public class Reaction extends BaseModel {
	
	private static final long serialVersionUID = -2940491291285426884L;
	
	@Id
	@Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;

}
