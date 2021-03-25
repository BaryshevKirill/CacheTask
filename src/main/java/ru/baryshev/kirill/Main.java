package ru.baryshev.kirill;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cache cache = context.getBean("cache", Cache.class);
        cache.put(1L, "Long 1");
        cache.put(2L, "Long 2");
        cache.put(3L, "Long 3");
    }
}
