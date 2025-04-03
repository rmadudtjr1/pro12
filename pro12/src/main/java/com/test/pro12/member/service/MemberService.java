package com.test.pro12.member.service;

import java.util.List;

import com.test.pro12.member.dto.MemberDTO;

public interface MemberService {

	List<MemberDTO> listMembers();
	MemberDTO memberDetail(String id);
	int login(String id, String pwd);
	int addMember(String id, String pwd, String name, String email);
	int memberDel(String id);
	int memberMod(String id, String pwd, String name, String email);

}
