package accountingApp.entity;

import javax.persistence.*;

/**
 * Название заболевания
 */
@Entity
@Table(name = "WORK_AREA") //aeger_captions
public class WorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") //c_aeger
    private int id;
    @Column(name = "name") //caption
    private String name;

    public WorkArea() {
    }

    public WorkArea(String name) {
        this.name = name;
    }

    public WorkArea(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
