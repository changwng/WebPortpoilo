package Intake2020.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CsFAQDto {
	
	private int no;
	private String subject;
	private int count;
	private String writer;
	private LocalDateTime reg_date;
	private String user_ip;
	private String content;

}
