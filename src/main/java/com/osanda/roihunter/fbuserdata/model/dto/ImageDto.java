package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ImageDto implements Serializable {

	private static final long serialVersionUID = -3684997321502885908L;

	private Integer height;

	private Integer width;

	private String source;

}
