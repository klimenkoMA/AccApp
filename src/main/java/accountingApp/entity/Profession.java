package accountingApp.entity;

public enum Profession {

    Преподаватель,
    Программист,
    Руководитель_IT,
    Директор,
    Бухгалтер,
    Инженер_ТБ,
    Кадровик,
    Системный_Администратор,
    Инженер_ИБ,
    Сетевой_Администратор,
    ;

    public String getProfession() {
        return name();
    }
}
