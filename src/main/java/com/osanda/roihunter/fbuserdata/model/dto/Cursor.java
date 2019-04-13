package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Cursor implements Serializable {

	private static final long serialVersionUID = 6751848581673201989L;

	private String before;

	private String after;

}
