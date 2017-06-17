package com.zh.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.Banner;


@SpringBootApplication
@RestController
@ServletComponentScan
public class DemoApplication implements EmbeddedServletContainerCustomizer {

	@RequestMapping("/test")
	public String test(){
		return "hello spring-boot";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//设置banner不打开
//		SpringApplication app=new SpringApplication(DemoApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);
				
	}
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// 修改端口号  实现  EmbeddedServletContainerCustomizer
		container.setPort(8080);
		container.addErrorPages(
				new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"),
				new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html"));
	}
}
