package com.study.hexagonal.ddd_project.article.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleContent {

    @Id
    private Long id;
    private String content;
    private String contentType;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Article article;


    public ArticleContent(String content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    public ArticleContent(String content, String contentType, Article article) {
        this.content = content;
        this.contentType = contentType;
        this.article = article;
    }


    public void addArticle(Article article) {
        this.article = article;
    }


    public String getContent() {
        return content;
    }


    public String getContentType() {
        return contentType;
    }
}
