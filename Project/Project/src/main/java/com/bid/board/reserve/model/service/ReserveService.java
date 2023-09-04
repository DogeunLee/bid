package com.bid.board.reserve.model.service;

import java.util.List;

import com.bid.board.member.model.vo.Member;

public interface ReserveService {


	int insertNewMember(int projectNo, List<String> memberId);


}
