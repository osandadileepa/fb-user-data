package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AlbumDto implements Serializable {

	private static final long serialVersionUID = 862156611007722930L;

	private String created_time;

	private String name;

	private String id;

}
