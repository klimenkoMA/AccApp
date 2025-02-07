package accountingApp.entity;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

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
    @JoinColumn
    private Devices device;
    @Column
    private DeviceCategory category;
    @Column
    private String health;
    @Column
    private int durability;
    private String repairedPart;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column
    private List<String> repairedParts;
    @ElementCollection(targetClass = Important.class)
    @CollectionTable(name = "repair_important", joinColumns = @JoinColumn(name = "repair_id"))
    @Enumerated(EnumType.STRING)
    private List<Important> importants;

    public Repair() {
    }

    public Repair(String firstDay, Devices device) {
        this.firstDay = firstDay;
        this.device = device;
        lastRepairDay = firstDay;
        isImportant = false;
        repairCount = 1;
        health = "green";
        durability = 0;
        importants = new ArrayList<>();
        importants.add(Important.Нет);
        repairedParts = new ArrayList<>();
        repairedPart = "";
        category = device.getCategory();
    }

    public Repair(Long id
            , String lastRepairDay
            , boolean isImportant
            , Devices device
            , String repairedPart) {
        this.id = id;
        this.lastRepairDay = lastRepairDay;
        this.isImportant = isImportant;
        this.device = device;
        this.repairedPart = repairedPart;

        if (isImportant) {
            importants.add(Important.Да);
        } else {
            importants.add(Important.Нет);
        }
        durability = getDurability();

        if (durability <= 30) {
            setHealth("green");
        } else if (durability <= 75) {
            setHealth("yellow");
        } else {
            setHealth("red");
        }
        repairCount++;
        repairedParts.add(repairedPart);
        category = device.getCategory();
    }

    public Repair(Long id
            , String firstDay
            , String lastRepairDay
            , int repairCount
            , boolean isImportant
            , Devices device
            , String health
            , int durability
            , String repairedPart
            , List<String> repairedParts
            , List<Important> importants) {
        this.id = id;
        this.firstDay = firstDay;
        this.lastRepairDay = lastRepairDay;
        this.repairCount = repairCount;
        this.isImportant = isImportant;
        this.device = device;
        this.health = health;
        this.durability = durability;
        this.repairedPart = repairedPart;
        this.repairedParts = repairedParts;
        this.importants = importants;

        if (isImportant) {
            importants.add(Important.Да);
        } else {
            importants.add(Important.Нет);
        }
        durability = getDurability();

        if (durability <= 30) {
            setHealth("green");
        } else if (durability <= 75) {
            setHealth("yellow");
        } else {
            setHealth("red");
        }
        repairCount++;
        repairedParts.add(repairedPart);
        category = device.getCategory();
        device.setRepair(this);
    }

    public int getDurability() {

        int firstYear;
        int currentYear = LocalDate.now().getYear();

        String[] firstYearArray = firstDay.split("-");
        firstYear = Integer.parseInt(firstYearArray[2]);

        if (importants.size() == 0) {
            setDurability((currentYear - firstYear) * 5);
            return durability;
        }

        int countYes = 0;

        for (Important imp : importants
        ) {
            if (imp.equals(Important.Да)) {
                countYes += 10;
            } else {
                countYes += 5;
            }
        }

        setDurability(countYes + (currentYear - firstYear) * 5);

        return durability;
    }

    public DeviceCategory getCategory() {
        return category;
    }

    public String getRepairedPart() {
        return repairedPart;
    }

    public void setRepairedPart(String repairedPart) {
        this.repairedPart = repairedPart;
    }

    public List<String> getRepairedParts() {
        return repairedParts;
    }

    public void setRepairedParts(List<String> repairedParts) {
        this.repairedParts = repairedParts;
    }

    public void setDurability(int durability) {
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

        if (importants.size() == 0) {
            setRepairCount(0);
            return repairCount;
        }
        setRepairCount(importants.size());
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

    @Override
    public String toString() {
        return durability + " %";
    }
}
