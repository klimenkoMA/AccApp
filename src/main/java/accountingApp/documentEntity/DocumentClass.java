package accountingApp.documentEntity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/**
 * Класс-объект, описывающий документ, который будет храниться в БД
 */

@Document(collection = "documents")
public class DocumentClass {

    @Id
    private ObjectId id;

    @Field
    @Indexed
    private String name;

    public DocumentClass(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public DocumentClass() {
    }

    public DocumentClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DocumentClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

