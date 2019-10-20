package com.hkumru.service;

import com.hkumru.entity.Article;
import com.hkumru.repository.ArticleDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDaoImpl articleDao;

    public ArticleServiceImpl(ArticleDaoImpl articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Override
    public Article getArticleById(int articleId) {
        return articleDao.getArticleById(articleId);
    }

    @Override
    public synchronized boolean addArticle(Article article) {
        if (articleDao.articleExists(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            articleDao.addArticle(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public void deleteArticle(int articleId) {
        articleDao.deleteArticle(articleId);
    }

}
