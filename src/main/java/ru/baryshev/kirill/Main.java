package ru.baryshev.kirill;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        ApplicationContext context = new AnnotationConfigApplicationContext("ru.baryshev.kirill");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyCache impl = context.getBean(MyCache.class);
    }
}
