package accountingApp.documentService;


import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentRepository.DocumentRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentServiceClass {

    /*
    Класс для реализации основной логики методов CRUD.
    Прослойка между репозиторием и контроллером.
     */


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
