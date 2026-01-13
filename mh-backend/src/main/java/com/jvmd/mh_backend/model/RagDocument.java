package com.jvmd.mh_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "rag_docs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RagDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "russian")
    private String content;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String module;

    @Field(type = FieldType.Keyword)
    private String docId;
}
