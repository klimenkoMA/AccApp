package accountingApp.documentRepository;

import accountingApp.documentEntity.DocumentClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DocumentRepositoryClass extends MongoRepository <DocumentClass, String> {

    @Query ("{'name' : {$regex: ?0, $options: 'i' }}")
    DocumentClass findByName (String name);

}
