package accountingApp.documentService;


import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentRepository.DocumentRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс для реализации основной логики методов CRUD.
 * Прослойка между репозиторием и контроллером.
 */

@Service
public class DocumentServiceClass {

    @Autowired
    DocumentRepositoryClass documentRepositoryClass;

    public List<DocumentClass> findAllDocuments() {
        return documentRepositoryClass.findAll();
    }

    public void addDocument(DocumentClass doc) {
        documentRepositoryClass.save(doc);
    }

    public void deleteDocument(DocumentClass doc) {

    }

    public void findDocument(DocumentClass doc) {

    }

    public void updateDocument(DocumentClass doc) {

    }

    public void findDocumentByName(String name) {

    }


}
