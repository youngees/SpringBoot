package com.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggerInterceptor implements HandlerInterceptor{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//preHandle : 컨트롤러의 메서드에 매핑된 특정 URI를 호출했을때 컨트롤러에 접근하기 전에 실행되는 메서드.
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("==================== START ====================");
		logger.debug(" Request URI \t: " + request.getRequestURI());
        return true;
    }

	//postHandle : 컨트롤러를 경유한 다음, 화면으로 결과를 전달하기 전에 실행되는 메서드.
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("==================== END ====================");
    }

}
