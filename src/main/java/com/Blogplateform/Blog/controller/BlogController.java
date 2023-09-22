package com.Blogplateform.Blog.controller;

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

            System.out.println("No Blogger found");
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


    //Delete blogger with their articles
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        try {
            blog.deleteById(id);
            return "Blogger deleted successfully";
        } catch (Exception e) {
            System.out.println(e);
            return "Blogger not found";
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


    //blogger save then show that blogger
    @GetMapping("/save/{id}")
    public BlogEntity getById1(@PathVariable Long id) {
        Optional<BlogEntity> op = blog.findById(id);
        return op.get();
    }


    //save then give id for foreign key and then save article
    @PostMapping("/save/{id}/savearticle")
    public ResponseEntity<String> save1(@PathVariable Long id, @RequestBody ArticleEntity article1) {
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


    // showing articles by id of bolgger
    @GetMapping("/{id}/articles")
    public List<ArticleEntity> getList(@PathVariable Long id) {
        try {
            Optional<BlogEntity> bl = blog.findById(id);
            if (bl.isPresent()) {
                BlogEntity blog = bl.get();
                return blog.getArticlelist();
            } else {
                System.out.println("no such data found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //update article with blogger id
    @PutMapping("/{blogId}/{articleId}/art")
    public ArticleEntity updateArticle(@PathVariable Long blogId, @PathVariable Long articleId, @RequestBody ArticleEntity art) {
        Optional<BlogEntity> optionalBlog = blog.findById(blogId);
        if (!optionalBlog.isPresent()) {
            System.out.println("blogger not found");
        }
        Optional<ArticleEntity> optionalArticle = article.findById(articleId);
        if (!optionalArticle.isPresent()) {
            System.out.println("Article not found");
        }
        Optional<BlogEntity> bl = blog.findById(blogId);
        if (bl.isPresent()) {
            BlogEntity blog = bl.get();
            List<ArticleEntity> li = blog.getArticlelist();
            Optional<ArticleEntity> optionalArticleEntity = li.stream().filter(list -> articleId == list.getId()).findFirst();
            if (optionalArticleEntity.isPresent()) {
                ArticleEntity update = article.findById(articleId).orElseThrow(() -> new EntityNotFoundException("blog not found"));
                update.setArticleName(art.getArticleName());
                update.setDescription(art.getDescription());
                return article.save(update);
            } else {
                System.out.println("id not found");
            }
        }
        return null;
    }

    //delete paticular article
    @DeleteMapping("/{blogId}/delete/{articleId}")
    public String deleteArticle(@PathVariable Long blogId, @PathVariable Long articleId) {

        Optional<BlogEntity> optionalBlog = blog.findById(blogId);
        if (!optionalBlog.isPresent()) {
            return "blogger not found";
        }
        Optional<ArticleEntity> optionalArticle = article.findById(articleId);
        if (!optionalArticle.isPresent()) {
            return "article not found";
        }
        BlogEntity blogg = optionalBlog.get();
        ArticleEntity articlee = optionalArticle.get();
        if (!blogg.getArticlelist().contains(articlee)) {
            return "Article not found";
        }
        blogg.getArticlelist().remove(articlee);
        blog.save(blogg);
        article.delete(articlee);
        return "delete article";
    }
}