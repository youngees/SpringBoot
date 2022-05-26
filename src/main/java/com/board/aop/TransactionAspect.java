package com.board.aop;

import java.util.Collections;
import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {

	@Autowired
	private PlatformTransactionManager transactionManager;

	private static final String EXPRESSION = "execution(* com.board..service.*Impl.*(..))";

	@Bean
	public TransactionInterceptor transactionAdvice() {

		//rollbackRules : 트랜잭션에서 롤백을 수행하는 규칙이다. 
		List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));

		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setRollbackRules(rollbackRules);
		//setName : 트랜잭션의 이름을 설정
		transactionAttribute.setName("*");

		MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
		attributeSource.setTransactionAttribute(transactionAttribute);

		return new TransactionInterceptor(transactionManager, attributeSource);
	}

	@Bean
	public Advisor transactionAdvisor() {

		//AOP의 포인트컷을 설정한다. 
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		//expression : 포인트컷
		pointcut.setExpression(EXPRESSION);

		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}

}