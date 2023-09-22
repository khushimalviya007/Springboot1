package com.Blogplateform.Blog.repository;

import com.Blogplateform.Blog.entity.ArticleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticle extends JpaRepository<ArticleEntity,Long> {
}
