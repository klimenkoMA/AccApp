package accountingApp.documentService;


import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentRepository.DocumentRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс для реализации основной логики методов CRUD.
 * Прослойка между репозиторием и контроллером.
 */

@Service
public class DocumentServiceClass {

    @Autowired
    DocumentRepositoryClass documentRepositoryClass;

    public void addDocument(DocumentClass doc) {

    }

    public void deleteDocument(DocumentClass doc) {

    }

    public void findDocument(DocumentClass doc){

    }

    public void updateDocument(DocumentClass doc){

    }

    public void findDocumentByName(String name){

    }


}
