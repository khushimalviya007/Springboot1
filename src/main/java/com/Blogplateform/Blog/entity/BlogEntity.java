package com.Blogplateform.Blog.entity;

import jakarta.persistence.*;

@Entity
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) private long id;
    private String name;
    private String email;
@OneToMany(mappedBy = "blog_entity")
private List<ArticleEntity> articlelist;
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
