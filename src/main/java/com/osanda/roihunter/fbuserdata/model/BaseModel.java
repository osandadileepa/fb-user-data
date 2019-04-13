package com.osanda.roihunter.fbuserdata.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/***
 * base model to use as the abstract layer of any entity
 * 
 * @author Osanda Wedamulla
 *
 */

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -1413982697301006018L;

	@CreatedBy
	@JsonIgnore
	@Column(name = "CREATED_BY", length = 50, updatable = false)
	private String createdBy;

	@JsonIgnore
	@CreatedDate
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate = LocalDateTime.now();

	@JsonIgnore
	@LastModifiedBy
	@Column(name = "LAST_MODIFIED_BY", length = 50)
	private String lastModifiedBy;

	@JsonIgnore
	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE")
	private LocalDateTime lastModifiedDate = LocalDateTime.now();;

	@Version
	@JsonIgnore
	@Column(name = "VERSION")
	private Long version;

}