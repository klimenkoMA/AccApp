package accountingApp.entity;

public enum DeviceCategory {

    Монитор,
    Принтер,
    Колонки,
    Проектор,
    Ноутбук,
    Стационарный_компьютер,
    ;

    public String getCategory() {
        return name();
    }

}
