package accountingApp.entity;

public enum Profession {

    Преподаватель,
    Программист,
    РуководительIT,
    Директор,
    Бухгалтер,
    ИнженерТБ,
    Кадровик,
    ;

    public String getProfession() {
        return name();
    }
}
