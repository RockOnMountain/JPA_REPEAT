package com.study.hexagonal.ddd_project.article.infra;

import com.study.hexagonal.ddd_project.article.domain.Article;

public interface ArticleCustomRepository {

    Article findByIdWithContent(long articleId);
    Article findOnlyById(long articleId);
}
