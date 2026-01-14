package com.jvmd.mh_backend.repo;

import com.jvmd.mh_backend.model.RagDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RagDocumentRepository
        extends ElasticsearchRepository<RagDocument, String> {
}
