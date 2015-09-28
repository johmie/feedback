package io.feedback.survey.entity;

import java.util.Set;

import io.feedback.core.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Page extends AbstractBaseEntity {

    private String name;
    private String title;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "page")
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
}