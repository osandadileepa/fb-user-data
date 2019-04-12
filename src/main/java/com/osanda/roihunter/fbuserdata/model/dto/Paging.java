package com.osanda.roihunter.fbuserdata.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Paging implements Serializable {
	
	private static final long serialVersionUID = 7228647326300035381L;
	
	private Cursor cursors;

}
