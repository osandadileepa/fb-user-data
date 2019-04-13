package com.osanda.roihunter.fbuserdata.model.dto.response;

import java.io.Serializable;

import com.osanda.roihunter.fbuserdata.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = 4317890485974537800L;

	private Long fbid;

	private String name;

	private String firstName;

	private String lastName;

	private String email;

	private Gender gender;

	private String profilePicUrl;

}
