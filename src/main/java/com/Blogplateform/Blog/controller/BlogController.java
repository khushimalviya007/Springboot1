package com.Blogplateform.Blog.controller;

import com.Blogplateform.Blog.BlogApplication;
import com.Blogplateform.Blog.entity.ArticleEntity;
import com.Blogplateform.Blog.entity.BlogEntity;
import com.Blogplateform.Blog.repository.IArticle;
import com.Blogplateform.Blog.repository.IBlog;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogger")
public class BlogController {
    @Autowired
    IBlog blog;
    @Autowired
    IArticle article;

    //Blogger Save
    @PostMapping("/save")
    public BlogEntity save(@RequestBody BlogEntity blog1) {
        try {
            return blog.save(blog1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return blog1;
    }

    //Find by id
    @GetMapping("/{id}")
    public BlogEntity getById(@PathVariable Long id) {
        try {
            Optional<BlogEntity> op = blog.findById(id);
            return op.get();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

//Find all Blogger

    @GetMapping("/")
    public List<BlogEntity> get() {
        try {
            return blog.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Delete blogger
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        try {
            blog.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    //update blogger data
    @PutMapping("/{id}")
    public BlogEntity updateEntity(@PathVariable Long id, @RequestBody BlogEntity blogEntity) {

        BlogEntity update = blog.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        update.setName(blogEntity.getName());
        update.setEmail(blogEntity.getEmail());
        return blog.save(update);
    }


    //Save article with paticular blogger id
    @PostMapping("/{id}/savearticle")
    public ResponseEntity<String> save(@PathVariable Long id, @RequestBody ArticleEntity article1) {
        Optional<BlogEntity> blogEntity = blog.findById(id);
        if (blogEntity.isPresent()) {
            BlogEntity b = blogEntity.get();
            article1.setBlogentity(b);
            article.save(article1);
            return ResponseEntity.ok("article saved for this blogger Id " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Find all article
    @GetMapping("//")
    public List<ArticleEntity> get1() {
        try {
            return article.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //article delete by id
    @DeleteMapping("/article{id}")
    public boolean deleteByIdArticle(@PathVariable Long id) {
        try {
            article.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }



    //blogger save then show that blogger
    @GetMapping("/save/{id}")
    public BlogEntity getById1(@PathVariable Long id) {
        Optional<BlogEntity> op = blog.findById(id);
        return op.get();
    }


    //save then give id for foreign key and then save article
    @PostMapping("/save/{id}/savearticle")
    public ArticleEntity save1(@RequestBody ArticleEntity article1) {
        System.out.println(article1.getArticleName());
        System.out.println(article1.getId());
        System.out.println(article1.getDescription());
        return article.save(article1);
    }


    // showing articles by id of bolgger

        @GetMapping("/{id}/articles")
        public List<ArticleEntity> getList(@PathVariable Long id) {
        try {
            Optional<BlogEntity> bl = blog.findById(id);
            if(bl.isPresent()){
            BlogEntity blog = bl.get();
            return blog.getArticlelist();
            }else{
                System.out.println("no such data found");
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
            return null;
        }

    @Autowired
    ArticleEntity article11;
//correction needed
    @DeleteMapping("/{blogId}/deletearticle/{articleId}")
    public String deleteArticle(@PathVariable Long blogId, @PathVariable Long articleId) {
        Optional<BlogEntity> blogger = blog.findById(blogId);
        if (blogger.isPresent()) {
            BlogEntity blogg = blogger.get();
            if (blogg.getId(blogId) == articleId) {
                article.deleteById(articleId);
                return "Article deleted successfully ";
            } else {
                return "article not found";
            }
        }
        return "blogger not found";
    }
}