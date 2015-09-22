package io.feedback.survey;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Page {
    private int id;
    private String name;
    private String title;
    private List<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Page [id=" + id + ", name=" + name + ", title=" + title
                + ", questions=" + questions + "]";
    }
}
