
package com.ycrash.springboot.buggy.app;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.Module;

@SpringBootApplication
@ComponentScan(basePackages = { 
		"com.ycrash.springboot.buggy.app",
		"com.ycrash.springboot.buggy.app.config", 
		"com.ycrash.springboot.buggy.app.controller",
		"com.ycrash.springboot.buggy.app.service.blockedapp",
		"com.ycrash.springboot.buggy.app.service.cpuspike",
		"com.ycrash.springboot.buggy.app.service.deadlock",
		"com.ycrash.springboot.buggy.app.service.memoryleak",
		"com.ycrash.springboot.buggy.app.service.metaspaceleak",
		"com.ycrash.springboot.buggy.app.service.oomcrash",
		"com.ycrash.springboot.buggy.app.service.stackoverflow",
		"com.ycrash.springboot.buggy.app.service.threadleak",
		"com.ycrash.springboot.buggy.app.service.webclient",
		"com.ycrash.springboot.buggy.app.service.resttemplate",
		"com.ycrash.springboot.buggy.app.network"
		
 })
public class SpringBootApp implements CommandLineRunner{

	@Override
	public void run (String...arg0) throws Exception{
		if(arg0.length > 0 && arg0[0].equals("exitcode")) {
			throw new ExitException();
		}
	}
	
	public static void main(String args[]) throws Exception{
		new SpringApplication(SpringBootApp.class).run(args);
	}
	
	static class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}
	
	@Bean
	public WebMvcConfigurer webConfigurer() {
		return new WebMvcConfigurer() {
		};
	}

	@Bean
	public Module jsonNullableModule() {
		return new JsonNullableModule();
	}
}
