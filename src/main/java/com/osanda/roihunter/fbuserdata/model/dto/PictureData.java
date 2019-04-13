package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PictureData implements Serializable {

	private static final long serialVersionUID = -1460135344102068515L;

	private String link;

	private String name;

	private String id;

	private AlbumDto album;

	private List<ImageDto> webp_images;

}
