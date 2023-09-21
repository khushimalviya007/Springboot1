package com.Blogplateform.Blog.entity;

import jakarta.persistence.*;

@Entity
public class ArticleEntity {

    public ArticleEntity() {
        System.out.println("****************");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) private long id;
    private String articleName;
    private String description;
@ManyToOne
private BlogEntity blogentity;
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
