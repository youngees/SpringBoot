package com.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootAplication 
 * 				ㄴ @EnableAutoConfiguration : 스프링 부트 개발에 필요한 필수적인 몇가지의 설정처리
 * 				ㄴ @ComponentScan : 빈 등록 및 스캔을 위해 수동으로 선언하는. 해당 어노테이션에 의해 자동으로 컴포넌트 클래스를 검색하고 IoC컨테이너에 빈으로 등록된다.
 * 				ㄴ @Configuration : 해당 어노테이션이 선언된 클래스는 자바기반의 설정파일로 인식.
*/

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
