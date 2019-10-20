package com.hkumru.repository;

import com.hkumru.entity.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
    boolean articleExists(String title, String category);

}
