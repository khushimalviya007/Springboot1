package com.Blogplateform.Blog.repository;

import com.Blogplateform.Blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlog extends JpaRepository<BlogEntity,Long> {

}
