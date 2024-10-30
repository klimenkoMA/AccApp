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

    @Field
    @Indexed
    private String content;

    @Field
    @Indexed
    private String description;


    public DocumentClass(String name, String content, String description) {
        this.name = name;
        this.content = content;
        this.description = description;
    }

    public DocumentClass(ObjectId id, String name, String content, String description) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.description = description;
    }

    public DocumentClass() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

