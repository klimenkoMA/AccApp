package accountingApp.documentRepository;

import accountingApp.documentEntity.DocumentClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Стандартный класс-интерфейс для связи с БД
 */
@Repository


public interface DocumentRepositoryClass extends MongoRepository<DocumentClass, String> {


    @Query("{'name' : {$regex: ?0, $options: 'i' }}")
    DocumentClass findByName(String name);

    @Override
    List<DocumentClass> findAll();


}
