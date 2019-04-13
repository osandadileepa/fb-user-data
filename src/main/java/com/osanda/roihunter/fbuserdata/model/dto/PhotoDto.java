package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PhotoDto implements Serializable {

	private static final long serialVersionUID = 6930280912005454783L;

	private List<PictureData> data;

	private Paging paging;

}
