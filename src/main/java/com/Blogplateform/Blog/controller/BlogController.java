package com.Blogplateform.Blog.controller;
import com.Blogplateform.Blog.BlogApplication;
import com.Blogplateform.Blog.entity.ArticleEntity;
import com.Blogplateform.Blog.entity.BlogEntity;
import com.Blogplateform.Blog.repository.IArticle;
import com.Blogplateform.Blog.repository.IBlog;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogger")
public class BlogController
{
    @Autowired
    IBlog blog;
    @Autowired
    IArticle article;
    @GetMapping("/findallarticle")
    public List<ArticleEntity> get1()
    {
        return article.findAll();
    }
    @DeleteMapping("/article{id}")
    public boolean deleteByIdArticle(@PathVariable Long id)
    {
        try{
            article.deleteById(id);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    @PostMapping("/savearticle")
    public ArticleEntity save(@RequestBody ArticleEntity article1)
    {
        return article.save(article1);
    }
   @GetMapping("/find")
   public List<BlogEntity> get() {
        return blog.findAll();
    }

    @PostMapping("/save")
    public BlogEntity save(@RequestBody BlogEntity blog1)
    {
        return blog.save(blog1);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id)
    {
        try{
       blog.deleteById(id);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
      }
    }
@PutMapping("/{id}")
public BlogEntity updateEntity(@PathVariable Long id,@RequestBody BlogEntity blogEntity)
{

    BlogEntity update = blog.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    update.setName(blogEntity.getName());
    update.setEmail(blogEntity.getEmail());
    return blog.save(update);
}
    @GetMapping("/{id}")
    public BlogEntity getById(@PathVariable Long id) {
        Optional<BlogEntity> op = blog.findById(id);
        return op.get();
    }
    @GetMapping("/save/{id}")
    public BlogEntity getById1(@PathVariable Long id) {
        Optional<BlogEntity> op = blog.findById(id);
        return op.get();
    }
    @PostMapping("/save/{id}/savearticle")
    public ArticleEntity save1(@RequestBody ArticleEntity article1)
    {

        return article.save(article1);
    }
}

