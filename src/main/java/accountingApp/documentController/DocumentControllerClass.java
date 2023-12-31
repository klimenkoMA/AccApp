package accountingApp.documentController;

import accountingApp.documentEntity.DocumentClass;
import accountingApp.documentService.DocumentServiceClass;
import accountingApp.entity.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addNewDocument(@RequestParam String name,
                                 @RequestParam String content,
                                 Model model) {

        String nameWithoutSpaces = name.trim();
        if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
            DocumentClass document = new DocumentClass(name, content);
            documentServiceClass.addDocument(document);
            List<DocumentClass> documentClassList = documentServiceClass.findAllDocuments();
            model.addAttribute("documentClassList", documentClassList);
        }
        return "documents";
    }

    //    @PostMapping
    public String deleteSomeDocument(String doc) {
        return "page";

    }

    //    @PostMapping
    public String updateSomeDocument(String doc) {

        return "page";
    }

    //    @PostMapping
    public String findSomeDocument(String doc) {
        return "page";
    }

}
