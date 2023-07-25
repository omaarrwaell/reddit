package com.example.reddit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;
@Entity
@NoArgsConstructor
@Data
public class Comment extends Auditable{
    @Id
    @GeneratedValue
    private  long id ;
    private String body;

    @ManyToOne
    private Link link;


    public Comment(String body, Link link) {
        this.body = body;
        this.link = link;
    }
}
