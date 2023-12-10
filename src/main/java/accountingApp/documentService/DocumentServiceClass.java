package accountingApp.documentService;


import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentRepository.DocumentRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentServiceClass {

    @Autowired
    DocumentRepositoryClass documentRepositoryClass;

    public void addDocument(DocumentClass doc){


    }

    public void deleteDocument(DocumentClass doc){

    }
}