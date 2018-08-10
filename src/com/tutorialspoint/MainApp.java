package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AbstractApplicationContext abstractContext = new ClassPathXmlApplicationContext("Beans.xml");
		
		HelloWorld(context);
		SingletonHello(context);
		PrototypeHello(context);
		InitAndDestroy(abstractContext);
	}
	
	public static void HelloWorld(ApplicationContext context){
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();		
	}
	
	public static void SingletonHello(ApplicationContext context){
		HelloWorld objA = (HelloWorld) context.getBean("helloSingleton");
		objA.setMessage("I'm object A!");
		objA.getMessage();
		
		HelloWorld objB = (HelloWorld) context.getBean("helloSingleton");
		objB.getMessage();
	}
	
	public static void PrototypeHello(ApplicationContext context){
		HelloWorld objA = (HelloWorld) context.getBean("helloProto");
		objA.setMessage("I'm object A!");
		objA.getMessage();
		
		HelloWorld objB = (HelloWorld) context.getBean("helloProto");
		objB.getMessage();
	}
	
	public static void InitAndDestroy(AbstractApplicationContext context){
		// Abstract context is needed b/c registerShutdownHook() needs to be called to make sure
		// shutdown happens properly
		
		HelloWorld bean = (HelloWorld) context.getBean("helloInit");
		bean.getMessage();
		context.registerShutdownHook(); // Tells the bean to shut down
	}
	
	
	
	
}