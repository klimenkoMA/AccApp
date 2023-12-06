package accountingApp.documentRepository;

import accountingApp.documentEntity.DocumentClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepositoryClass extends MongoRepository <DocumentClass, String> {


}
