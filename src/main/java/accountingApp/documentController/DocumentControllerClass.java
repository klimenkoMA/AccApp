package accountingApp.documentController;

import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentService.DocumentServiceClass;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс для взаимодействия со слоем VIEW, непосредственно HTML-страницами.
 * Содержит в себе методы, которые на вход принимают вводимые пользователем
 * данные либо нажатие на элементы дизайна страницы(кнопки, ссылки). В методе
 * эти данные обрабатываються и пересылаются дальше по цепочке в класс Сервиса.
 * Полученный оттуда ответ возвращается пользователю в виде изменений на HTML-странице
 */

@Controller
public class DocumentControllerClass {

    @Autowired
    DocumentServiceClass documentServiceClass;

    @GetMapping("/documents")
    public String getDocument(Model model) {
        List<DocumentClass> documentClassList = documentServiceClass.findAllDocuments();
        model.addAttribute("documentClassList", documentClassList);
        return "documents";
    }

    @PostMapping("/adddocument")
    public String addNewDocument(@RequestParam("content") MultipartFile content,
                                 @RequestParam String description,
                                 Model model) {

        if (
                content == null
                || description == null
        ) {
            System.out.println("*** DocumentControllerClass.addNewDocument():" +
                    "  Attribute has a null value! ***");
            return getDocument(model);
        }

        String descriptionWithoutSpaces = description.trim();

        try {
            if (
                    !content.isEmpty()
                    && !descriptionWithoutSpaces.equals("") && !descriptionWithoutSpaces.equals(" ")) {
                DocumentClass document = new DocumentClass(content.getOriginalFilename(), content.getBytes()
                        , description, content.getContentType());
                documentServiceClass.addDocument(document);
                return getDocument(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            System.out.println("*** DocumentControllerClass.addNewDocument():" +
                    "  WRONG DB VALUES*** " + e.getMessage());
            return getDocument(model);
        }
    }

    @PostMapping("/deletedocument")
    public String deleteSomeDocument(@RequestParam String name,
                                     Model model) {

        if (name == null) {
            System.out.println("*** DocumentControllerClass.deleteSomeDocument():" +
                    "  Attribute has a null value! ***");
            return getDocument(model);
        }

        String nameWithoutSpaces = name.trim();

        try {
            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {

                DocumentClass document = documentServiceClass.findDocumentById(name);
                documentServiceClass.deleteDocument(document);
                return getDocument(model);
            }
            throw new Exception("It`s not an ID!");
        } catch (Exception e) {
            System.out.println("*** DocumentControllerClass.deleteSomeDocument(): " +
                    e.getMessage());
            try {
                if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {

                    DocumentClass document = documentServiceClass.findDocumentByName(name);
                    documentServiceClass.deleteDocument(document);

                    return getDocument(model);
                }
                throw new Exception("Attribute is empty!");
            } catch (Exception e1) {
                System.out.println("*** DocumentControllerClass.deleteSomeDocument():" +
                        "  WRONG DB VALUES*** " + e.getMessage());
                return getDocument(model);
            }
        }
    }

    @PostMapping("/updatedocument")
    public String updateSomeDocument(@RequestParam String id,
                                     @RequestParam("content") MultipartFile content,
                                     @RequestParam String description,
                                     Model model) {
        if (id == null
                || content == null
                || description == null
        ) {
            System.out.println("*** DocumentControllerClass.updateSomeDocument():" +
                    "  Attribute has a null value! ***");
            return getDocument(model);
        }


        String idWithoutSpaces = id.trim();
        String descriptionWithoutSpaces = description.trim();

        try {
            if (!idWithoutSpaces.equals("") && !idWithoutSpaces.equals(" ")
                    && !content.isEmpty()
                    && !descriptionWithoutSpaces.equals("") && !descriptionWithoutSpaces.equals(" ")) {
                DocumentClass documentFromBD = documentServiceClass.findDocumentById(idWithoutSpaces);
                if (documentFromBD != null) {

                    DocumentClass documentToBD = new DocumentClass();
                    documentToBD.setId(new ObjectId(idWithoutSpaces));
                    documentToBD.setName(documentFromBD.getOriginalFilename());
                    documentToBD.setContent(content.getBytes());
                    documentToBD.setDescription(descriptionWithoutSpaces);
                    documentToBD.setContentType(documentFromBD.getContentType());
                    documentServiceClass.addDocument(documentToBD);
                    return getDocument(model);
                }
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            System.out.println("*** DocumentControllerClass.updateSomeDocument():" +
                    "  WRONG DB VALUES*** " + e.getMessage());
            return getDocument(model);
        }
    }

    @PostMapping("/finddocument")
    public String findSomeDocument(@RequestParam String name,
                                   Model model) {

        if (name == null) {
            System.out.println("*** DocumentControllerClass.findSomeDocument():" +
                    "  Attribute has a null value! ***");
            return getDocument(model);
        }

        String nameWithoutSpaces = name.trim();

        try {
            if (!nameWithoutSpaces.isEmpty()) {
                DocumentClass document = documentServiceClass.findDocumentById(name);
                List<DocumentClass> documentClassList = new ArrayList<>();
                documentClassList.add(document);
                model.addAttribute("documentClassList", documentClassList);
                return "documents";
            }
            throw new Exception("It`s not an ID!");
        } catch (Exception e) {
            System.out.println("*** DocumentControllerClass.findSomeDocument(): " +
                    e.getMessage());
            try {
                if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
                    DocumentClass document = documentServiceClass.findDocumentByName(name);
                    List<DocumentClass> documentClassList = new ArrayList<>();
                    documentClassList.add(document);
                    model.addAttribute("documentClassList", documentClassList);
                    return "documents";
                }
                throw new Exception("Attribute is empty!");
            } catch (Exception e1) {
                System.out.println("*** DocumentControllerClass.findSomeDocument():" +
                        "  WRONG DB VALUES*** " + e.getMessage());
                return getDocument(model);
            }
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable String id) {
        DocumentClass document = documentServiceClass.findDocumentById(id);
        if (document != null) {

            HttpHeaders headers = new HttpHeaders();
            String docType = document.getContentType();
            assert docType != null;
            headers.setContentType(MediaType.parseMediaType(docType));
//            headers.setContentDisposition(ContentDisposition.attachment().filename(document.getName() + ".pdf").build());
            headers.setContentDisposition(ContentDisposition.attachment().filename(document.getName()).build());
            headers.setContentLength(document.getContent().length);

            return new ResponseEntity<>(document.getContent(), headers, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
