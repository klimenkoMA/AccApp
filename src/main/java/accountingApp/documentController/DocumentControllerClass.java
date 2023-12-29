package accountingApp.documentController;

        import accountingApp.documentService.DocumentServiceClass;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс для взаимодействия со слоем VIEW, непосредственно HTML-страницами.
 * Содержит в себе методы, которые на вход принимают вводимые пользователем
 * данные либо нажатие на элементы дизайна страницы(кнопки, ссылки). В методе
 * эти данные обрабатываються и пересылаются дальше по цепочке в класс Сервиса.
 * Полученный оттуда ответ возвращается пользователю в виде изменений на HTML-странице
 */

public class DocumentControllerClass {

    @Autowired
    DocumentServiceClass documentServiceClass;

    @GetMapping
    public String getDocument(String doc){
        return "page";
    }

    public String addNewDocument(String doc) {
        return "page";
    }

    public String deleteSomeDocument(String doc) {
        return "page";

    }

    public String updateSomeDocument(String doc) {

        return "page";
    }

    public String findSomeDocument(String doc) {
        return "page";
    }

}
