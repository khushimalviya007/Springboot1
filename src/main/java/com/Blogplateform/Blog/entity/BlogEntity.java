package com.Blogplateform.Blog.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;
    private String email;
    @OneToMany(mappedBy = "blogentity" ,cascade =CascadeType.ALL

            ,fetch= FetchType.EAGER)
    private List<ArticleEntity> articlelist;


    public List<ArticleEntity> getArticlelist() {
        return articlelist;
    }

    public void setArticlelist(List<ArticleEntity> articlelist) {
        this.articlelist = articlelist;
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
