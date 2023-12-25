package com.study.hexagonal.ddd_project.domain;

import javax.persistence.EntityManager;
import com.study.hexagonal.ddd_project.article.domain.Article;
import com.study.hexagonal.ddd_project.article.domain.ArticleContent;
import com.study.hexagonal.ddd_project.article.domain.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    void 조회() {

        // given
        Article article = new Article("title", new ArticleContent("content", "contentType"));
        Article savedArticle = articleRepository.save(article);
        entityManager.clear();

        // when
        Article findArticle = articleRepository.findById(savedArticle.getId()).get();

        // then
        System.out.println(findArticle.getArticleContent().getContent());

        //        Article article = new Article("title");
        //        ArticleContent articleContent = new ArticleContent("content", "contentType", article);
        //        article.addArticleContent(articleContent);
        //
        //        Article savedArticle = articleRepository.save(article);
        //        entityManager.clear();
        //
        //        Article findArticle = articleRepository.findById(savedArticle.getId()).get();
        //
        //        System.out.println(findArticle.getArticleContent().getContent());
    }
}
