package com.uwv.spring_boot;

import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static ApplicationContext applicationContext;

	@Autowired
	MessageService messageService;

	public Application(MessageService messageService){
		this.messageService = messageService;
	}

	public static void main(String[] args){
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		for( String beanDefinitionName: applicationContext.getBeanDefinitionNames()){
			// System.out.println( beanDefinitionName);
			if( beanDefinitionName.endsWith("DataSource")){
				Object object = applicationContext.getBean( beanDefinitionName);
				System.out.println("Stop hier");
			}
		}
	}

	@Override
	public void run(String... args) throws Exception {

		messageService.save( new Message("Dit is de eerste mededeling"));
		messageService.save( new Message("Dit is de tweede mededeling"));
		messageService.save( new Message("Dit is de derde mededeling"));
		messageService.save( new Message("Dit is de vierde mededeling"));
	}
}
