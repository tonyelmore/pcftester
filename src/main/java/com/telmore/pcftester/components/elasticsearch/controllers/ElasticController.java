package com.telmore.pcftester.components.elasticsearch.controllers;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.telmore.pcftester.components.elasticsearch.config.ElasticVars;
import com.telmore.pcftester.components.elasticsearch.dao.ArticleRepository;
import com.telmore.pcftester.components.elasticsearch.domain.Article;
import com.telmore.pcftester.components.elasticsearch.domain.Author;

@RestController
@RequestMapping("/elastic/")
@Conditional(ElasticConditional.class)
public class ElasticController {

    @Autowired
    public ArticleRepository articleService;

    // @Autowired
    // private final ElasticVars elasticVars = null;

    @GetMapping("test")
    public String test() {
        return "ElasticSearch test not yet implemented";
    }

    @RequestMapping("realtest")
    public String elastic() throws IOException {
//        String indexname = elasticVars.getIndex();
// the index is on the annotation of the Article class
// Which is really not convenient since any new instance of elasticsearch will have a different index

        System.out.println("In the elastic method in the controller");
        Article article = new Article();
        article.setTitle("TestArticlename");
        article.setAuthors(Arrays.asList(new Author(1, "John Smith", 30), new Author(2, "John Doe", 40)));
        System.out.println("About to save article");
        articleService.save(article);

        // Saw some code like this... should it be used instead?
        // IndexRequest request = new IndexRequest("spring-data", "elasticsearch", randomID())
        //     .source(singletonMap("feature", "high-level-rest-client"))
        //     .setRefreshPolicy(IMMEDIATE);

        // IndexResponse response = highLevelClient.index(request);

        // Should really do a "get" and return the retrieved article
        return "Article saved in elasticsearch: " + article.getId();
    }

}
