package accountingApp.documentController;

import accountingApp.documentService.DocumentServiceClass;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentControllerClass {

    @Autowired
    DocumentServiceClass documentServiceClass;

    public String addNewDocument(String doc){
        return "page";
    }
}
