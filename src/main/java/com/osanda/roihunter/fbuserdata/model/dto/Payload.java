package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Payload implements Serializable {

	private static final long serialVersionUID = 230032662139649017L;

	private String fbId;

	private String access_token;

}
