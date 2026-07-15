package com.example.CRUDbyFM;

import com.example.CRUDbyFM.App.Controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MyProjectFai {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(MyProjectFai.class, args);;
        System.out.println(ctx.isActive());
        System.out.println(ctx.isRunning());
        System.out.println(ctx.isClosed());
//        System.out.println(ctx.getDisplayName());
//        System.out.println(ctx.getBeanFactory());
//        System.out.println(ctx.getId());
//        System.out.println(ctx.getEnvironment());
//        System.out.println(ctx.getApplicationStartup());
//        System.out.println(ctx.getApplicationName());

    }
}
