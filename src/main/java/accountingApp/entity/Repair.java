package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "repair")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String firstDay;
    @Column
    private String lastRepairDay;
    @Column
    private int repairCount;
    @Column
    private boolean isImportant;
    @OneToOne
    @Column
    private Devices device;
    @Column
    private String health;
    @Column
    private double durability;

    @ElementCollection(targetClass = Important.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "repair_important", joinColumns = @JoinColumn(name = "repair_id"))
    @Enumerated(EnumType.STRING)
    private List<Important> importants;

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    public String getLastRepairDay() {
        return lastRepairDay;
    }

    public void setLastRepairDay(String lastRepairDay) {
        this.lastRepairDay = lastRepairDay;
    }

    public int getRepairCount() {
        return repairCount;
    }

    public void setRepairCount(int repairCount) {
        this.repairCount = repairCount;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public List<Important> getImportants() {
        return importants;
    }

    public void setImportants(List<Important> importants) {
        this.importants = importants;
    }
}
