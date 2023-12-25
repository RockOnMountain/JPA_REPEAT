package com.study.hexagonal.ddd_project.article.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL, optional = false)
    private ArticleContent articleContent;


    public Article(String title, ArticleContent articleContent) {
        this.title = title;
        this.articleContent = articleContent;
        articleContent.addArticle(this);
    }


    public Article(String title) {
        this.title = title;
    }


    public void addArticleContent(ArticleContent articleContent) {
        this.articleContent = articleContent;
    }
}
