package com.bid.board.project.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	private int projectNo;
	private int corpNo;
	private String projectValue;
	private String porjectDetail;
	private String projectSt;
	private int projectPrice;
	private String projectSDate;
	private String projectEDate;
	
}
