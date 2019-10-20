package com.hkumru.repository;

import com.hkumru.entity.Article;
import com.hkumru.entity.ArticleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> getAllArticles() {
        String sql = "SELECT articleId, title, category FROM articles";
        RowMapper<Article> rowMapper = new ArticleRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Article getArticleById(int articleId) {
        String sql = "SELECT articleId, title, category FROM articles WHERE articleId = ?";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, articleId);
    }

    @Override
    public void addArticle(Article article) {
        String sql = "INSERT INTO articles (articleId, title, category) values (?, ?, ?)";
        jdbcTemplate.update(sql, article.getArticleId(), article.getTitle(), article.getCategory());
        sql = "SELECT articleId FROM articles WHERE title = ? and category=?";
        int articleId = jdbcTemplate.queryForObject(sql, Integer.class, article.getTitle(), article.getCategory());
        article.setArticleId(articleId);
    }

    @Override
    public void updateArticle(Article article) {
        String sql = "UPDATE articles SET title=?, category=? WHERE articleId=?";
        jdbcTemplate.update(sql, article.getTitle(), article.getCategory(), article.getArticleId());
    }

    @Override
    public void deleteArticle(int articleId) {
        String sql = "DELETE FROM articles WHERE articleId=?";
        jdbcTemplate.update(sql, articleId);
    }

    @Override
    public boolean articleExists(String title, String category) {
        String sql = "SELECT count(*) FROM articles WHERE title=? and category=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, title, category);
        return count != 0;
    }
}
