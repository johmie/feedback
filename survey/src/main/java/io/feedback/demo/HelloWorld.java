package io.feedback.demo;

import io.feedback.survey.Project;

public class HelloWorld {
    private Project p1;
    private Project p2;

    public HelloWorld(Project p1, Project p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void sayHello() {
        System.out.println("Hello World");
        System.out.println(this.p1.toString() + " " + this.p2.toString());
    }
}
