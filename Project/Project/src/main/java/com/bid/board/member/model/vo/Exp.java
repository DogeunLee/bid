package com.bid.board.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exp {
	
	private int expNo;
	private int memberNo;
	private String expName;
	private String expDept;
	private String expSDate;
	private String expEDate;

}
