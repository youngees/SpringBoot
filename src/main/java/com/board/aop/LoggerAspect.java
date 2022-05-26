package com.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * @Component : 스프링 컨테이너에 빈으로 등록하기 위한 어노테이션 @Bean은 개발자가 제어할 수 없는 외부 라이브러리를 빈으로 등록할때 사용한다면
 * 			@Component는 개발자가 직접 정의한 클래스를 빈으로 등록할때 사용한다. 
 * 
 * @Aspect : AOP기능을 하는 클래스의 클래스 레벨에 지정하는 어노테이션
 * 
 * @Around : 어드바이스의 종류 중 한가지. 다섯가지 중 어라운드는 메서드의 호출 자체를 제어할 수 있기 때문에 어드바이스 중 가장 강력한 기능이라고 볼 수 있다.
 * 		ㄴ@Before : Target 메서드 호출 이전에 적용할 어드바이스 정의
 * 		ㄴ@AfterReturning : Target 메서드가 성공적으로 실행되고, 결괏값을 반환한 뒤에 적용
 * 		ㄴ@AfterThrowing : Target 메서드에서 예외 발생 이후에 적용
 * 		ㄴ@After : Target 메서드에서 예외 발생에 관계없이 적용
*/

@Component
@Aspect
public class LoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.board..controller.*Controller.*(..)) or execution(* com.board..service.*Impl.*(..)) or execution(* com.board..mapper.*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();

		if (name.contains("Controller") == true) {
			type = "Controller ===> ";

		} else if (name.contains("Service") == true) {
			type = "ServiceImpl ===> ";

		} else if (name.contains("Mapper") == true) {
			type = "Mapper ===> ";
		}

		logger.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}

}