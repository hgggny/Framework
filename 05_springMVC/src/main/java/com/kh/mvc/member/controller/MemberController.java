package com.kh.mvc.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	// 컨트롤러가 처리할 요청을 정의한다. (URL, Method 등)
	// 원래는 GET이나 POST로 요청 처리해서 405 오류 발생 (
//	@RequestMapping(value = "/login", method= {RequestMethod.POST})
//	@GetMapping("/loin")
//	@PostMapping("/loin")
//	public String login() {
//		
//		return "home";
//	}
	
	// 요청 시 사용자의 파라미터를 전송받는 방법 
	// 1. HttpServletRequest 객체를 통해서 전송받기 (기존 Servlet 방식)
	// 		- 메소드의 매개변수로 HttpServletRequest를 작성하면 메소드 실행 시 스프링 컨테이너가 자동으로 객체를 인자로 주입해준다. 
//	@PostMapping("/login")
//	public String login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		
//		log.info("login() - 호출 : {} {}", id, password);
//		
//		return "home";
//	}
	
	// 2-1) @RequestParam 어노테이션을 통해서 전송받는 방법
	// 		- 스프링에서는 조금 더 간편하게 파라미터를 받아올 수 있는 방법을 제공한다.
	// 		- 내부적으로는 HttpServletRequest 객체를 이용해서 데이터를 전달한다. 
	// 		- 단, 매개변수의 이름과 파라미터의 name 속성의 값이 동일하면 @RequestParam 어노테이션을 생략할 수 있다. 
	// 		(생략 시 defaultValue 설정이 불가능하다. )
//	@PostMapping
//	@RequestMapping(value="/login", method = {RequestMethod.POST})
////	public String login(@RequestParam("id") String id, @RequestParam("password") String password) {
//	public String login(String id, String password) {
//		// @RequestParam의 네임속성("id")이랑 매개변수명(id) 같으면 어노테이션 생략 가능
//		log.info("login() - 호출 : {} {}", id, password);
//		
//		return "home";
//	}
	
	
	// 2-2) @RequestParam 어노테이션에 default 값 설정
	// - defaultValue 속성을 사용하면 파라미터 name 속성에 값이 없을 경우 기본값을 지정할 수 있다. 
//	@PostMapping("/login")
//	public String login(@RequestParam("id") String id, @RequestParam(value = "password", defaultValue = "1234") String password) {
//		
//		log.info("login() - 호출 : {} {}", id, password);
//		
//		return "home";
//	}

	// 2-3) @RequestParam 어노테이션에 실제 존재하지 않는 파라미터를 받으려고 할 때 
	// 		- 실제 존재하지 않는 파라미터를 받으려고 하면 에러가 발생한다. (required = true이기 때문이다. )
	//		- @RequestParam(required = false)로 설정하면 에러가 아닌 null 값을 넘겨준다. 
	// 		- 단, defaultValue를 설정하면 에러없이 defaultValue에 설정된 값으로 넘겨준다. 
//	@PostMapping("/login")
////	public String login(@RequestParam("id") String id, 
//	public String login(@RequestParam String id, // ("id") 매개변수과 같을 때 생략이 가능하다
//						@RequestParam("password") String password,
////						@RequestParam(value = "address", required = false) String address) {
//						@RequestParam(value = "address", defaultValue = "서울특별시") String address) {
//		
//		log.info("login() - 호출 : {} {} {}", new Object[] { id, password, address});
//		
//		return "home";
//	}
	
	
	// 3. @PathVariable 어노테이션을 통해서 전송받는 방법
	// 		- URL 패스상에 있는 특정 값을 가져오기 위해 사용하는 방법이다. 
	// 		- REST API를 사용할 때, 요청 URL 상에서 필요한 값을 가져오는 경우에 주로 사용한다. 
	// 		- 매핑 URL에 {} 안의 변수명과 매개변수명이 동일하다면 @PathVariable의 괄호는 생략이 가능하다. (어노테이션 생략은 안된다.)
//	@GetMapping("/member/{id}")
////	public String findMember(@PathVariable("id") String id) {
//	public String findMember(@PathVariable String id) {
//		
//		log.info("findMember() - 호출 : {}", id);
//		
//		return "home";
//	}
	
	// 4. @ModelAttribute 어노테이션을 통해서 전송받는 방법
	// 		- 요청 파라미터가 많은 경우 → 객체 타입으로 파라미터를 넘겨받는 방법이다. 
	// 		- 단, 기본 생성자와 Setter가 존재해야 한다.
//	@PostMapping("/login")
////	public String login(@ModelAttribute Member member) {
//	public String login(Member member) {
//		
//		System.out.println(member);
//		
//		return "home";
//	}
	
	
	@Autowired
	private MemberService service;
	
	/*
	 * 로그인 처리
	 * 
	 * 1. HttpSession과 Model 객체 사용
	 * 	- Model 객체는 컨트롤러에서 데이터를 뷰로 전달하고자 할 때 사용하는 객체이다. 
	 * 	- 전달하고자 하는 데이터를 맵 형식(key, value)으로 담을 수 있다. 
	 * 	- Model 객체의 Scope는 Request Scope 이다. 
	 * 	
	 */
	
	@PostMapping("/login")
	public String login(HttpSession session, Model model,
			@RequestParam String id, @RequestParam String password) {
		
		log.info("{} {}", id, password);
		
		Member loginMember = service.login(id, password);
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			
			// redirect 방식으로 여기서 리턴 한 경로로 브라우저에서 다시 요청하도록 반환한다. 
			return "redirect:/"; 
		} else {
			model.addAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다. ");
			model.addAttribute("location", "/");
			
			// forwarding 방식으로 리턴 한 view 이름이 ViewResolver에 의해 
			// /WEB-INF/views/common/msg.jsp로 요청을 넘긴다. 
			return "common/msg";
		}
		
	}
	
	/*
	 * 로그아웃 처리
	 * 
	 * 1. HttpSession 객체 사용
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		// 세션 삭제
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
