package com.telmore.dao;

import com.telmore.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Page<Article> findByTitle(String name, Pageable pageable);
 
    @Query("{\"bool\": {\"must\": [{\"match\": {\"article.name\": \"?0\"}}]}}")
    Page<Article> findByTitleUsingCustomQuery(String name, Pageable pageable);

}
