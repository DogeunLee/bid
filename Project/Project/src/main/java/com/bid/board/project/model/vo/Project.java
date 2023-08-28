package com.bid.board.project.model.vo;

import com.bid.board.corperation.model.vo.Corperation;

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
	private String projectDetail;
	private String projectSt;
	private int projectPrice;
	private String projectSDate;
	private String projectEDate;
	private String projectAddr;
	
	private Corperation corp;
	
}
