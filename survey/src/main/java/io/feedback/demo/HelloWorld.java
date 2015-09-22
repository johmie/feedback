package io.feedback.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.feedback.survey.Project;

@Component
public class HelloWorld {
    private Project p1;
    private Project p2;
    
    @PostConstruct
    public void construct() {
        System.out.println("Construct");
    }
    
    @PreDestroy
    public void destruct() {
        System.out.println("Destruct");
    }

    @Autowired
    public HelloWorld(Project p1, Project p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void sayHello() {
        System.out.println("Hello World");
        System.out.println(this.p1.toString() + " " + this.p2.toString());
    }
}
