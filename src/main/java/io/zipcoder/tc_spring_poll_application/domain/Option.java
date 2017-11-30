package io.zipcoder.tc_spring_poll_application.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Option {

    @Id
    @GeneratedValue
    @Column(name="OPTION_ID")
    Long id;

    @Column(name="OPTION_VALUE")
    String value;

    public void setId(Long passedId){
        id=passedId;
    }

    public void setValue(String passedValue){
        value=passedValue;
    }

    public Long getId(){
        return id;
    }

    public String getValue(){
        return value;
    }


}
