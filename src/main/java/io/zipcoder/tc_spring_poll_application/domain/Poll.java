package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue
    @Column(name="POLL_ID")
    Long id;

    @Column(name="QUESTION")
    String question;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="POLL_ID")
    @OrderBy
    Set<Option> options;

    public Long getId() {
        return id;
    }

    public void setId(Long passedId) {
        id = passedId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String passedQuestion) {
        question = passedQuestion;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> passedOptions) {
        this.options = passedOptions;
    }
}
