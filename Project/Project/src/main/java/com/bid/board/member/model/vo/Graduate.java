package com.bid.board.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Graduate {
	private int codeNo;
	private String memberGrad;
	private int memberNo;
	private String gradName;
	private String gradValue;
	private String gradHsDate;
	private String gradHeDate;
}
