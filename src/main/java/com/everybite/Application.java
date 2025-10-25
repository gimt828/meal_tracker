package com.everybite; // 최상위 패키지

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Spring Boot 실행 및 자동 설정 관현 핵심 클래스를 import함.

@SpringBootApplication // 자동 설정 + 컴포넌트 스캔
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 프로젝트 실행 트리거
		// 스프링 부트 애플리케이션을 실행시킴.
		// 내부적으로 Spring 컨테이너를 생성하고 Bean 등록, 웹 서버(Tomcat) 시작 등 전체 초기화.
    }
}

// Main Class.
