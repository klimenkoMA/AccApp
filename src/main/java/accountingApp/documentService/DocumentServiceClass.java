package accountingApp.documentService;


import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentRepository.DocumentRepositoryClass;
import org.bson.types.ObjectId;
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
        documentRepositoryClass.delete(doc);

    }

    public DocumentClass findDocumentById(String id) {

        List<DocumentClass> documentList = documentRepositoryClass.findAll();

        for (DocumentClass doc : documentList
        ) {
            if (doc.getId().toString().equals(id)) {
                return doc;
            }
        }

        return new DocumentClass(new ObjectId("111"),
                "Document not found", new byte[0]
                , "Document not found", "doc");
    }

    public DocumentClass findDocumentByName(String name) {

        return documentRepositoryClass.findByName(name);

    }


}
