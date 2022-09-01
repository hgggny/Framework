package com.kh.mvc.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.mvc.member.model.vo.Member;


// alt + shift + s -> override
/*
 * 인터셉터
 * 		- 컨트롤러에 들어오는 요청과 응답을 가로채는 역할을 한다. 
 * 		- 인터셉터를 구현하기 위해서는 HandlerInterceptorAdapter 클래스를 상속해야 한다. 
 * 	
 * 필터와의 차이점
 * 		- 필터는 서블릿 실행 전에 실행된다. (web.xml에 설정)
 * 		- 인터셉터는 디스패처 서블릿 수행 후 컨트롤러에 요청을 넘기기 전에 실행된다. (servlet-context.xml 에 설정)
 * 
 * 필터/인터셉터/AOP 차이점 - 면접 !
 */


public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러가 실행되기 전에 필요한 작업을 할 수 있는 메소드
		// 반환값이 false일 경우 컨트롤러를 실행하지 않는다. 
		System.out.println("preHandle() - 호출");
		
		Member loginMember = (Member) request.getSession().getAttribute("loginMember");
		
		if(loginMember == null) {
			request.setAttribute("msg", "로그인 후 이용이 가능합니다. ");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
			return false;
			
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러가 실행된 후에 필요한 작업을 할 수 있는 메소드
		System.out.println("postHandle() - 호출");

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 컨트롤러의 처리가 끝나고 화면 처리까지 모두 끝나면 실행되는 메소드
		System.out.println("afterCompletion() - 호출");
		
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 비동기 요청시 postHandle(), afterCompletion()를 수행하지 않고 afterConcurrentHandlingStarted() 메소드가 호출된다. 
		System.out.println("afterConcurrentHandlingStarted() - 호출");

		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
