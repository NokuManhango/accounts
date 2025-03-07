package com.tus.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDto {
	
	/*
	 * public ResponseDto(String statusCde, String statusMsg) { this.statusCode =
	 * statusCode; this.statusMsg = statusMsg; }
	 */
	private String statusCode;
	
	private String statusMsg;

}
