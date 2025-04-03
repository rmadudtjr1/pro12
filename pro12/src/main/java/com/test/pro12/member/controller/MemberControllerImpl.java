package com.test.pro12.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.pro12.member.service.MemberService;
import com.test.pro12.member.dto.MemberDTO;
/**
 * 	문제1.
 * 	마이바티스를 이용하여 데이터를 가져올 수 있도록 수정
 * 
 * 	문제2.
 *  Member 패키지의 URL 을 이용하여 tiles 로 화면을 각각 구성해 
 *  보세요. 기존 JSP 파일을 사용해도 됩니다. 
 *  tiles의 member.xml에서
 *  definitions 의 name 과 viewName 과 일치 해야 한다.
 */
@Controller
@RequestMapping("/member")
public class MemberControllerImpl extends MultiActionController 
	implements MemberController {
	
	@Autowired
	public MemberService memberService;
	
	@Override
	@RequestMapping("/listMembers.do")
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<MemberDTO> membersList = memberService.listMembers();
		String viewName = 
				(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	@RequestMapping(value="/addMember.do", 
							method=RequestMethod.POST)
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		int result = memberService.addMember(id, pwd, name, email);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.write("<script>");
			out.write("alert('회원 가입에 성공했습니다.');");
			out.write("location.href='/pro12/member/loginForm.do';");
			out.write("</script>");
		} else {
			out.write("<script>");
			out.write("alert('회원 가입에 실패했습니다.');");
			out.write("location.href='/pro12/member/memberForm.do';");
			out.write("</script>");	
		}
		return null;
	}

	@Override
	@RequestMapping("/memberForm.do")
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = 
				(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping("/memberDetail.do")
	public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = 
				(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		MemberDTO member = memberService.memberDetail(id);
		mav.addObject("member", member);
		return mav;
	}

	@Override
	@RequestMapping("/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = 
				(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		int result = memberService.login(id, pwd);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			session.setAttribute("isLogon", true);
			session.setAttribute("id", id);
			out.write("<script>");
			out.write("alert('로그인에 성공했습니다.');");
			out.write("location.href='/pro12/member/listMembers.do';");
			out.write("</script>");
		} else {
			out.write("<script>");
			out.write("alert('로그인에 실패했습니다.');");
			out.write("location.href='/pro12/member/loginForm.do';");
			out.write("</script>");	
		}
		return null;
	}

	@Override
	@RequestMapping("/memberDel.do")
	public ModelAndView memberDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		int result = memberService.memberDel(id);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.write("<script>");
			out.write("alert('"+id+"회원님의 정보가 삭제되었습니다.');");
			out.write("location.href='/pro12/member/listMembers.do';");
			out.write("</script>");
		} else {
			out.write("<script>");
			out.write("alert('"+id+"회원님의 정보 삭제에 실패했습니다.');");
			out.write("location.href='/pro12/member/listMembers.do';");
			out.write("</script>");	
		}
		return null;
	}

	@Override
	@RequestMapping("/memberMod.do")
	public ModelAndView memberMod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		int result = memberService.memberMod(id,pwd,name,email);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.write("<script>");
			out.write("alert('"+id+"회원님의 정보가 수정되었습니다.');");
			out.write("location.href='/pro12/member/listMembers.do';");
			out.write("</script>");
		} else {
			out.write("<script>");
			out.write("alert('"+id+"회원님의 정보 수정에 실패했습니다.');");
			out.write("location.href='/pro12/member/listMembers.do';");
			out.write("</script>");	
		}
		return null;
	}

}




