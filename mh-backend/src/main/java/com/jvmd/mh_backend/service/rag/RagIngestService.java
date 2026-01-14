package com.jvmd.mh_backend.service.rag;

import com.jvmd.mh_backend.model.RagDocument;
import com.jvmd.mh_backend.repo.RagDocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RagIngestService {

    private final RagDocumentRepository repository;

    public void saveChunk(String docId, String module, String title, String text) {
        repository.save(new RagDocument(
            UUID.randomUUID().toString(),
            text,
            title,
            module,
            docId
        ));
    }
}
