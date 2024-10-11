package accountingApp.documentController;

import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentService.DocumentServiceClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;


class DocumentControllerClassTest {

    @InjectMocks
    private DocumentControllerClass documentController;

    @Mock
    DocumentServiceClass documentService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDocumentShouldReturnDocumentList() {
        DocumentClass doc1 = new DocumentClass();
        DocumentClass doc2 = new DocumentClass();
        DocumentClass doc3 = new DocumentClass();

        List<DocumentClass> documentClassList = Arrays.asList(doc1, doc2, doc3);

        Mockito.when(this.documentService.findAllDocuments()).thenReturn(documentClassList);

        String viewName = documentController.getDocument(model);

        Assertions.assertEquals("documents", viewName);

        verify(model).addAttribute("documentClassList", documentClassList);

        verify(documentService).findAllDocuments();

    }

    @Test
    void addNewDocument() {
    }

    @Test
    void deleteSomeDocument() {
    }

    @Test
    void updateSomeDocument() {
    }

    @Test
    void findSomeDocument() {
    }
}