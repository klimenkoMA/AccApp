package accountingApp.entity;

public enum Profession {

    TEACHER,
    PROGRAMMER,
    IT_MANAGER,
    DIRECTOR,
    ACCOUNTANT,
    CHIEF_OF_SECURITY,
    HR,
    ;

    public String getProfession() {
        return name();
    }
}
