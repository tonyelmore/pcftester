package com.telmore.pcftester.components.elasticsearch.dao;

import com.telmore.pcftester.components.elasticsearch.controllers.ElasticConditional;
import com.telmore.pcftester.components.elasticsearch.domain.Article;

import org.springframework.context.annotation.Conditional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Conditional(ElasticConditional.class)
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Page<Article> findByTitle(String name, Pageable pageable);
 
    @Query("{\"bool\": {\"must\": [{\"match\": {\"article.name\": \"?0\"}}]}}")
    Page<Article> findByTitleUsingCustomQuery(String name, Pageable pageable);

}
