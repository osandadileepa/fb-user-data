package com.osanda.roihunter.fbuserdata.model.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserPhotoDto implements Serializable {

	private static final long serialVersionUID = 8305107774132697544L;

	private String name;

	private String fbUrl;

	private String imageUrl;

	private String albumName;

	private LocalDateTime albumCreatedTime;

	private String imageName;

	private String imagePath;

}
