package com.bid.board.reserve.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
	private int reserveNo;
	private int projectNo;
	private String memberId;
}
