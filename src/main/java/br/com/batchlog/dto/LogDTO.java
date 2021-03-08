package br.com.batchlog.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogDTO {
	
	private LocalDateTime data;
	private String ip;
	private String request;
	private Integer status;
	private String userAgent;

}
