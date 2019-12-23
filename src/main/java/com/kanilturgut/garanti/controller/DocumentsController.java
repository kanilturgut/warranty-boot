package com.kanilturgut.garanti.controller;

import com.kanilturgut.garanti.exception.ResourceNotFoundException;
import com.kanilturgut.garanti.model.Document;
import com.kanilturgut.garanti.service.business.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DocumentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentsController.class);

    private final DocumentService documentService;

    public DocumentsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getDocuments() {
        LOGGER.info("getDocuments called.");
        List<Document> documents = documentService.getDocuments();
        LOGGER.info("getDocuments completed.");
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/documents")
    public ResponseEntity<Document> createDocument(@Valid @RequestBody DocumentRequest documentRequest) {

        Document document = documentService.createDocument(documentRequest);
        return ResponseEntity.accepted().body(document);
    }

    @DeleteMapping("/documents/{documentId}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long documentId) {
        try {
            documentService.deleteDocumentById(documentId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            LOGGER.error(String.format("ResourceNotFoundException occurred during deleteDocument for %d documentId", documentId), e);
            return ResponseEntity.notFound().build();
        }
    }
}
