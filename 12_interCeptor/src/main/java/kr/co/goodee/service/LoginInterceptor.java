package kr.co.goodee.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//Controler 가기전에 잡는 녀석
	//true가 반환되지 않으면 못지나간다.(원하는 컨트롤러 요청으로 못지간다.)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Controller 접근 전");
		//세션 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginId") == null) {
			System.out.println("로그인 안한 놈");
			//세션에 loginId가 없으면 ==> /로 보낸다.
			response.sendRedirect("/goodee/");
		}
		//있으면 통과
		return true;
	}

	//컨트롤러를 지나서 View로 가기 직전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Controller ~ View 전송 직전");
		String userId = (String) request.getSession().getAttribute("loginId");
		String content = "<div>안녕하세요."+userId+"님 <a href='./logout'>logout</a></div>";
		modelAndView.addObject("loginBox", content);
	}

	//컨트롤러 요청이 처리되고 난 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
