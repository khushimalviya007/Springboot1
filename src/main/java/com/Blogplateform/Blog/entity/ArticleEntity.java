package com.Blogplateform.Blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component
public class ArticleEntity {

    public ArticleEntity() {
        System.out.println("****************");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String articleName;


    private String description;
    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    private BlogEntity blogentity;
    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", articleName='" + articleName + '\'' +
                ", description='" + description + '\'' +
                ", blogentity=" + blogentity +
                '}';
    }


    public void setBlogentity(BlogEntity blogentity) {
        this.blogentity = blogentity;
    }
    public ArticleEntity(String articleName, String description) {
        this.articleName = articleName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
