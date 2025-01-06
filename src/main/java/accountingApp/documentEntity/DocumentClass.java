package accountingApp.documentEntity;

import accountingApp.entity.MultipartFileAdapter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс-объект, описывающий документ, который будет храниться в БД
 */

@Document(collection = "documents")

public class DocumentClass extends MultipartFileAdapter {

    @Id
    private ObjectId id;

    @Field
    @Indexed
    private String name;

    @Field
    @Indexed
    private byte[] content;

    @Field
    @Indexed
    private String description;

    @Field
    @Indexed
    private String contentType;
    @Field
    private Long idCount;
    @Field
    private Map<ObjectId, Long> idMap = new HashMap<>();

    public DocumentClass(String name, byte[] content, String description, String contentType) {
        this.name = name;
        this.content = content;
        this.description = description;
        this.contentType = contentType;
    }

    public DocumentClass(ObjectId id, String name, byte[] content, String description, String contentType) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.description = description;
        this.contentType = contentType;
    }

    public DocumentClass(byte[] content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    public DocumentClass() {
    }

    @PostPersist
    public void postPersist(){
        idMap.put(id, idCount);
    }

    public Map<ObjectId, Long> getIdMap() {
        return idMap;
    }

    public void setIdMap(Map<ObjectId, Long> idMap) {
        this.idMap = idMap;
    }

    public Long getIdCount() {
        return idCount;
    }

    public void setIdCount(Long idCount) {
        this.idCount = idCount;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
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
        return name;
    }
}

