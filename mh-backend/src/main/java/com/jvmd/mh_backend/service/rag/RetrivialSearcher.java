package com.jvmd.mh_backend.service.rag;

import com.jvmd.mh_backend.model.RagDocument;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RetrivialSearcher {
    private final ElasticsearchOperations operations;

    public List<String> retrieve(String query) {
        Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .multiMatch(m -> m
                                .query(query)
                                .fields("content^3", "title")
                                .fuzziness("AUTO")
                        )
                )
                .withMaxResults(10)
                .build();

        SearchHits<RagDocument> hits =
                operations.search(searchQuery, RagDocument.class);

        return hits.getSearchHits()
                .stream()
                .map(hit -> hit.getContent().getContent())
                .toList();
    }


}
