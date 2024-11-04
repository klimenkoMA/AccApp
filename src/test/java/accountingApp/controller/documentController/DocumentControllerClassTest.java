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
import org.springframework.web.multipart.MultipartFile;

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
        byte[] documentContent = "content".getBytes();
        String documentDescription = "descr";

        when(this.documentService.findAllDocuments()).thenReturn(documentClassList);

        String viewName = documentController.addNewDocument(new DocumentClass(documentContent),documentDescription, model);

        Assertions.assertEquals("documents", viewName);

        verify(model).addAttribute("documentClassList", documentClassList);

        verify(documentService).addDocument(any(DocumentClass.class));

    }

    @Test
    void addDocumentFail() {

        String documentName = " ";
        byte[] documentContent = " ".getBytes();
        String documentDescription = " ";

        when(this.documentService.findAllDocuments()).thenReturn(documentClassList);

        String viewName = documentController.addNewDocument(
                new DocumentClass(documentContent), documentDescription, model);

        Assertions.assertEquals("documents", viewName);

        verify(model).addAttribute("documentClassList", documentClassList);

        verify(documentService, never()).addDocument(any(DocumentClass.class));

    }

    @Test
    void addDocumentFailWithException() {

        doThrow(new RuntimeException()).when(documentService).addDocument(any(DocumentClass.class));

        verify(documentService, never()).addDocument(any(DocumentClass.class));

    }

    @Test
    void deleteDocumentValid() {

        String documentName = "doc1";

        String viewName = documentController.deleteSomeDocument(documentName, model);

        Assertions.assertEquals("documents", viewName);

        verify(documentService).deleteDocument(any());
    }

    @Test
    void deleteDocumentFail() {

        String documentName = " ";

        String viewName = documentController.deleteSomeDocument(documentName, model);

        Assertions.assertEquals("documents", viewName);

        verify(documentService, never()).deleteDocument(any());
    }

    @Test
    void deleteDocumentFailWithException() {

        String documentName = "gfdddf";

        doThrow(new RuntimeException()).when(documentService).deleteDocument(any());

        verify(model, never()).addAttribute("documentClassList", documentClassList);

        verify(documentService, never()).deleteDocument(any());
    }

    @Test
    void findDocumentValid() {

        String documentId = "1";

        String viewName = documentController.findSomeDocument(documentId, model);

        Assertions.assertEquals("documents", viewName);

        verify(documentService).findDocumentById(documentId);

    }

    @Test
    void findDocumentFail() {

        String documentId = "";

        String viewName = documentController.findSomeDocument(documentId, model);

        Assertions.assertEquals("documents", viewName);

        verify(documentService, never()).findDocumentById(documentId);

    }

    @Test
    void findDocumentFailWithException() {

        String documentId = "1212";

       doThrow(new RuntimeException()).when(documentService).findDocumentById(documentId);

        verify(documentService, never()).findDocumentById(documentId);

    }

}