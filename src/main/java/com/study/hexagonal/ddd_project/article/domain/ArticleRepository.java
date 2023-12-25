package com.study.hexagonal.ddd_project.article.domain;

import com.study.hexagonal.ddd_project.article.infra.ArticleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {}
