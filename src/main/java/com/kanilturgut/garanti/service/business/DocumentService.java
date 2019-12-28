package com.kanilturgut.garanti.service.business;

import com.kanilturgut.garanti.controller.document.DocumentRequest;
import com.kanilturgut.garanti.exception.ResourceNotFoundException;
import com.kanilturgut.garanti.model.Document;
import com.kanilturgut.garanti.respository.DocumentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getDocuments() {
        return documentRepository.findAll(Sort.by(Sort.Order.asc("expiredAt")));
    }

    public Document createDocument(DocumentRequest documentRequest) {
        Document document = documentRequest.toDocument();
        return documentRepository.save(document);
    }

    public void deleteDocumentById(Long documentId) throws ResourceNotFoundException {
        documentRepository.findById(documentId)
                .map(document -> {
                    documentRepository.delete(document);
                    return document;
                }).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Document not found with id %d", documentId))
        );
    }
}
