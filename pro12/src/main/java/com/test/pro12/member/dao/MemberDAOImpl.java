package com.test.pro12.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.pro12.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	SqlSession session;
	

	@Override
	public List<MemberDTO> listMembers() {
		// TODO Auto-generated method stub
		List<MemberDTO> membersList 
			= session.selectList
			("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public MemberDTO memberDetail(String id) {
		// TODO Auto-generated method stub
		MemberDTO member = 
			session.selectOne("mapper.member.selectMemberById", id);
		return member;
	}

	@Override
	public int login(String id, String pwd) {
		// TODO Auto-generated method stub
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		int result = session.selectOne
				("mapper.member.loginCheck", dto);
		return result;
	}

	@Override
	public int addMember(String id, String pwd, String name, String email) {
		// TODO Auto-generated method stub
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setEmail(email);
		int result = session.insert
				("mapper.member.insertMember", dto);
		return result;
	}

	@Override
	public int memberDel(String id) {
		// TODO Auto-generated method stub
		int result = session.delete
				("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public int memberMod(String id, String pwd, String name, String email) {
		// TODO Auto-generated method stub
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setEmail(email);
		int result = session.update
				("mapper.member.updateMember", dto);
		
		return result;
	}
	
}










