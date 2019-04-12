package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class FacebookResponse implements Serializable {

	private static final long serialVersionUID = 4401125092143397029L;

	private String name;

	private String id;

	private String gender;

	private String email;
	
	private String first_name;

	private String last_name;

	private Photos photos;

}
