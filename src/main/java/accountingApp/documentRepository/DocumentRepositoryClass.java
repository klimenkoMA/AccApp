//package accountingApp.documentRepository;
//
//import accountingApp.documentEntity.DocumentClass;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
///**
// * Стандартный класс-интерфейс для связи с БД
// */
//
//public interface DocumentRepositoryClass extends MongoRepository <DocumentClass, String> {
//
//    @Query ("{'name' : {$regex: ?0, $options: 'i' }}")
//    DocumentClass findByName (String name);
//
//    DocumentClass findAll(String name);
//
//}
