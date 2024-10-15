package accountingApp.controller.documentController;

import accountingApp.documentController.DocumentControllerClass;
import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentService.DocumentServiceClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class DocumentControllerClassTest {

    @InjectMocks
    private DocumentControllerClass documentController;

    @Mock
    DocumentServiceClass documentService;

    @Mock
    Model model;

    private final List<DocumentClass> documentClassList;

    {
        DocumentClass doc1 = new DocumentClass();
        DocumentClass doc2 = new DocumentClass();
        DocumentClass doc3 = new DocumentClass();

        documentClassList = Arrays.asList(doc1, doc2, doc3);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDocumentShouldReturnDocumentList() {


        when(this.documentService.findAllDocuments()).thenReturn(documentClassList);

        String viewName = documentController.getDocument(model);

        Assertions.assertEquals("documents", viewName);

        verify(model).addAttribute("documentClassList", documentClassList);

        verify(documentService).findAllDocuments();

    }

    @Test
    void addDocumentValid() {

        String documentName = "doc1";
        String documentContent = "content";

        when(this.documentService.findAllDocuments()).thenReturn(documentClassList);

        String viewName = documentController.addNewDocument(documentName, documentContent, model);

        Assertions.assertEquals("documents", viewName);

        verify(model).addAttribute("documentClassList", documentClassList);

        verify(documentService).addDocument(any(DocumentClass.class));

    }

    @Test
    void addDocumentFail() {

        String documentName = " ";
        String documentContent = " ";

        when(this.documentService.findAllDocuments()).thenReturn(documentClassList);

        String viewName = documentController.addNewDocument(documentName, documentContent, model);

        Assertions.assertEquals("documents", viewName);

        verify(model, never()).addAttribute("documentClassList", documentClassList);

        verify(documentService, never()).addDocument(any(DocumentClass.class));

    }

    @Test
    void addDocumentFailWithException() {

        doThrow(new RuntimeException()).when(documentService).addDocument(any(DocumentClass.class));

        verify(documentService, never()).addDocument(any(DocumentClass.class));

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