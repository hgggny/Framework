package com.kh.mvc.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// Model 객체에 loginMember라는 키 값을 객체가 추가되면 해당 객체를
// Session Scope 까지 확장하는 어노테이션이다. 
@SessionAttributes("loginMember")
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
	
//	@PostMapping("/login")
//	public String login(HttpSession session, Model model,
//			@RequestParam String id, @RequestParam String password) {
//		
//		log.info("{} {}", id, password);
//		
//		Member loginMember = service.login(id, password);
//		
//		if(loginMember != null) {
//			session.setAttribute("loginMember", loginMember);
//			
//			// redirect 방식으로 여기서 리턴 한 경로로 브라우저에서 다시 요청하도록 반환한다. 
//			return "redirect:/"; 
//		} else {
//			model.addAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다. ");
//			model.addAttribute("location", "/");
//			
//			// forwarding 방식으로 리턴 한 view 이름이 ViewResolver에 의해 
//			// /WEB-INF/views/common/msg.jsp로 요청을 넘긴다. 
//			return "common/msg";
//		}
//	}
	
	
	
	/*
	 * 2. @SessionAttributes과 ModelAndView 객체 사용
	 * 		1) @SessionAttributes("키 값")
	 * 			- Model 객체에서 "키 값"에 해당하는 Attribute를 Session Scope까지 범위를 확장시키는 어노테이션이다. 
	 * 			- 기존 방법으로 Session을 정리할 수 없고 SessionStatus 객체를 통해서 Session Scope까지 범위가 확장된 데이터를 정리해야 한다. 
	 * 		2) ModelAndView
	 * 			- 컨트롤러에서 뷰로 전달할 데이터와 포워딩하려는 뷰에 대한 정보를 담는 객체이다. 
	 *
	 */
	// Http 세션에 넣지 않고 model 객체에 넣고 있다 !
	@PostMapping("/login")
	public ModelAndView login(ModelAndView model,
				@RequestParam("id") String userId, @RequestParam("password") String userPwd) {
		
		log.info("{}, {}", userId, userPwd);
		
		Member loginMember = service.login(userId, userPwd);
		
		if(loginMember != null) {
			model.addObject("loginMember", loginMember);
			model.setViewName("redirect:/");
		} else {
			model.addObject("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			model.addObject("location", "/");
			
			model.setViewName("common/msg");
		}
		
		return model;
	}
	
	
	
	
	
	/*
	 * 로그아웃 처리
	 * 
	 * 1. HttpSession 객체 사용
	 */
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		
//		// 세션 삭제
//		session.invalidate();
//		
//		return "redirect:/";
//	}
	
	/*
	 * 2. SessionStatus 객체 사용
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		
		status.setComplete(); // 세션 스코프로 확장된 객체들을 지워준다. 
		
		return "redirect:/";
	}
	
	@GetMapping("/member/enroll")
	public String enroll() {
		
		log.info("회원 가입 페이지 요청");
		
		// WEB-INF/views까지는 prefix 
		return "member/enroll";
	}
	
	// 객체로 받기 위해 @ModelAttribute 사용
	@PostMapping("/member/enroll")
	public ModelAndView enroll(ModelAndView model, @ModelAttribute Member member) {
		log.info(member.toString());
		
		int result = 0;
		
		result = service.save(member);
		
		if(result > 0) {
			model.addObject("msg", "회원가입이 정상적으로 완료되었습니다. ");
			model.addObject("location", "/");
		} else {
			model.addObject("msg", "회원가입을 실패하였습니다.  ");
			model.addObject("location", "/member/enroll");
		}
	
		model.setViewName("common/msg");
		return model;
	}
	
	/*
	 * @ResponseBody
	 * 		- 일반적인 컨트롤러 메소드의 반환형이 String일 경우 뷰의 이름을 반환한다. 
	 * 		- @ResponseBody 붙은 String 반환은 해당 요청을 보낸 클라이언트에 전달할 데이터를 의미한다. 
	 * 	
	 */
//	@GetMapping("/jsonTest")
//	@ResponseBody
//	public String jsonTest() {
//		
////		return "{name : 'park', age : 10}"; // json 배열
//		return "park"; // 문자열
//	}
	
	/*
	 * jackson 라이브러리
	 * 		- 자바 객체를 JSON 형태의 데이터로 변환하는 라이브러리이다. 
	 * 		- 스프링에서 jackson 라이브러리를 추가하고 @ResponseBody를 사용하면 리턴된는 객체를 자동으로 JSON 문자열로 변경해서 응답한다. 
	 * 
	 * @RestController
	 * 		- 해당 어노테이션이 붙은 컨트롤러의 모든 메소드에서 데이터를 반환하는 경우 사용한다. 
	 * 		- @Controller과 @ResponseBody를 합쳐놓은 역할을 수행한다. 
	 */
//	@GetMapping("/jsonTest")
//	@ResponseBody
//	public Object jsonTest() {
//		
//		return new Member(); // 반환 타입을 Object 로 하고 했을 때, 에러 ! → jackson 라이브버리 추가 후 오류 해결	
//	}
	
	@GetMapping("/jsonTest")
	@ResponseBody
	public Object jsonTest() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("test1", null);
		map.put("test2", "Hi");
		map.put("test3", 10);
		map.put("test4", false);

		return map; 
	}
	
	@PostMapping("/member/idCheck")
//	@ResponseBody을 사용하지 않고 ResponseEntity를 사용하는 방법 (jackson 추가하고 사용하기)
	public ResponseEntity<Map<String, Boolean>> idCheck(@RequestParam String userId) {
	      log.info("{}", userId);
		
		
		
		Map<String, Boolean> map = new HashMap<>();
		
		map.put("duplicate", service.isDuplicateID(userId));
		
		/*
		 * ResponseEntity
		 * 		- 사용자의 요청에 대한 응답(상태 코드, 헤더, Body)을 개발자가 직접 설정하여 반환할 수 있게 해준다.  
		 * 
		 * 		- ResponseEntity 반환이면 그 내용을 HTTP 응답에 삽입하는 것이 명확하므로 @ResponseBody 설정 필요가 없고,
		 * 		  스테이터스 코드도 오브젝트로 설정하므로 @ResponseCode 설정할 필요없다. 
		 * 
		 * 		- statusCode : 스테이터스 코드
		 * 		- headers : HTTP 응답 / 웹서버가 웹브라우저에 응답하는 메세지가 들어있다 / 개별적 헤더를 설정 가능
		 * 		- body : body에 삽입할 정보를 유지하는 오브젝트 / 데이터 값이 들어있다. 
		 */
//		return ResponseEntity.ok()
//							 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//							 .body(map);
		return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/member/myPage")
	public String myPage() {
		log.info("회원 정보 수정 페이지 요청");
		
		return "member/myPage";	
	
	}
	
	@PostMapping("/member/update")
	public ModelAndView update(
			ModelAndView model,
			@SessionAttribute("loginMember") Member loginMember,
			@ModelAttribute Member member) {
		
		log.info(member.toString());
		log.info(loginMember.toString());
		
		int result = 0;
		
		member.setNo(loginMember.getNo()); // member에 No 값이 없기 때문에 loginMember에서 가져온다. 
		
		result = service.save(member);
		
		if(result > 0) {
			// 수정 사항이 DB에는 바로 반영되지만 화면에는 반영이 안돼서 loginMember.getId를 다시 조회하고 add
			model.addObject("loginMember", service.findMemberById(loginMember.getId()));
			model.addObject("msg", "회원 정보 수정을 완료했습니다.");
		} else {
			model.addObject("msg", "회원 정보 수정을 실패했습니다.");
		}
		model.addObject("location", "/member/myPage");
		model.setViewName("common/msg");
		
		return model;
	}

	@GetMapping("/member/delete")
	public ModelAndView delete(
			ModelAndView model,
			@SessionAttribute("loginMember") Member loginMember) {
		
		int result = 0;
		
		result = service.delete(loginMember.getNo());
		
		if(result > 0) {
			model.addObject("msg", "정상적으로 탈퇴되었습니다.");
			model.addObject("location", "/logout");
		} else {
			model.addObject("msg", "회원 탈퇴를 실패하였습니다.");
			model.addObject("location", "/member/myPage");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
	
	
	
	
}
