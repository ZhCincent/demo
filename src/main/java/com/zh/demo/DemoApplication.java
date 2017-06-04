package com.zh.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication  {

	@RequestMapping("/test")
	public String test(){
		return "hello spring-boot";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Override
//	public void customize(ConfigurableEmbeddedServletContainer container) {
//		// 修改端口号  实现  EmbeddedServletContainerCustomizer
//		container.setPort(8080);
//	}
}
