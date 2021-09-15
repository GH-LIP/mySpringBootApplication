package com.springAOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOP {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");// 获取配置文件
        IAccountService accountService = (IAccountService)ac.getBean("accountService");// 获取bean对象
        accountService.saveAccount();
    }
}
