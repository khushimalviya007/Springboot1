package com.Blogplateform.Blog.repository;

import com.Blogplateform.Blog.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticle extends JpaRepository<ArticleEntity,Long> {
}
