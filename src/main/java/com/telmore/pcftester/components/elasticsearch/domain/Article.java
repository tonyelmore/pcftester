package com.telmore.pcftester.components.elasticsearch.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "pntliapffkjy")
@Data
public class Article {
 
    @Id
    private String id;
     
    private String title;
     
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;
     
}