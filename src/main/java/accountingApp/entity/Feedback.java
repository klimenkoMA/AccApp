package accountingApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс-объект, описывающий фидбек от пользователя
 */
@Document(collection = "feedbacks")
public class Feedback {

    @Id
    private ObjectId id;
    @Field
    @Indexed
    private String name;
    @Field
    @Indexed
    private String email;
    @Field
    @Indexed
    private String message;
    @Field
    private Long idCount;
    @Field
    private Map<ObjectId, Long> idMap = new HashMap<>();

    public Feedback() {
    }

    public Feedback(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Feedback(ObjectId id, String name, String email, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Long getIdCount() {
        return idCount;
    }

    public void setIdCount(Long idCount) {
        this.idCount = idCount;
    }

    public Map<ObjectId, Long> getIdMap() {
        return idMap;
    }

    public void setIdMap(Map<ObjectId, Long> idMap) {
        this.idMap = idMap;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
