package com.hkumru.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "articles")
@Entity
@Data
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "articleId")
    private int articleId;

    private String title;

    private String category;

}
