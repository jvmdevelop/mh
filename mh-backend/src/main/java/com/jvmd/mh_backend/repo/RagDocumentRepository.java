package com.jvmd.mh_backend.repo;

import com.jvmd.mh_backend.model.RagDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RagDocumentRepository
        extends ElasticsearchRepository<RagDocument, String> {
}
