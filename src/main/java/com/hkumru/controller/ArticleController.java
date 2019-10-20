package com.hkumru.controller;

import com.hkumru.entity.Article;
import com.hkumru.service.ArticleServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
public class ArticleController {

    private ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {
        Article articleById = articleService.getArticleById(id);
        return new ResponseEntity<Article>(articleById, HttpStatus.OK);
    }

    @GetMapping("/article")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> allArticles = articleService.getAllArticles();
        return new ResponseEntity<>(allArticles, HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {

        boolean result = articleService.addArticle(article);

        if (!result){
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
