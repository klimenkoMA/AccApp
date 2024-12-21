package accountingApp.entity;

public enum DeviceCategory {

    Монитор,
    Принтер,
    Колонки,
    Проектор,
    Ноутбук,
    Компьютер,
    ;

    public String getCategory() {
        return name();
    }

}
