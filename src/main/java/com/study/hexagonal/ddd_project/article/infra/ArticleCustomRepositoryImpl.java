package com.study.hexagonal.ddd_project.article.infra;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.hexagonal.ddd_project.article.domain.Article;
import com.study.hexagonal.ddd_project.article.domain.QArticle;
import static com.study.hexagonal.ddd_project.article.domain.QArticle.article;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Article findByIdWithContent(long articleId) {
        return jpaQueryFactory.selectFrom(article).join(article.articleContent)
                .where(article.id.eq(articleId)).fetchOne();
    }


    @Override
    public Article findOnlyById(long articleId) {
        return jpaQueryFactory.selectFrom(QArticle.article).where(QArticle.article.id.eq(articleId)).fetchOne();
    }
}
