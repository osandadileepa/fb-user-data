package com.osanda.roihunter.fbuserdata.model.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseData implements Serializable {

	private static final long serialVersionUID = -4929402879917622533L;

	private String message;

	private Integer status_code;

	private String data;

	private LocalDateTime time_stamp;

}
