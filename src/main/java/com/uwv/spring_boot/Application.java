package com.uwv.spring_boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static ApplicationContext applicationContext;

	public static void main(String[] args){
		applicationContext = SpringApplication.run(Application.class, args);

		for( String beanDefinitionName: applicationContext.getBeanDefinitionNames()){
			// System.out.println( beanDefinitionName);
			if( beanDefinitionName.startsWith("controller")){
				Object object = applicationContext.getBean( beanDefinitionName);
				System.out.println("Tot hier");
			}
		}

	}

	@Override
	public void run(String... args) throws Exception {



	}
}
