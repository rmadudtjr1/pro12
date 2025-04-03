package com.test.pro12.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.pro12.member.dao.MemberDAO;
import com.test.pro12.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	public MemberDAO memberDAO;
	
	@Override
	public List<MemberDTO> listMembers() {
		// TODO Auto-generated method stub
		return memberDAO.listMembers();
	}

	@Override
	public MemberDTO memberDetail(String id) {
		// TODO Auto-generated method stub
		return memberDAO.memberDetail(id);
	}

	@Override
	public int login(String id, String pwd) {
		// TODO Auto-generated method stub
		return memberDAO.login(id, pwd);
	}

	@Override
	public int addMember(String id, String pwd, String name, String email) {
		// TODO Auto-generated method stub
		return memberDAO.addMember(id, pwd, name, email);
	}

	@Override
	public int memberDel(String id) {
		// TODO Auto-generated method stub
		return memberDAO.memberDel(id);
	}

	@Override
	public int memberMod(String id, String pwd, String name, String email) {
		// TODO Auto-generated method stub
		return memberDAO.memberMod(id, pwd, name, email);
	}
}







