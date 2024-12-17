package accountingApp.entity;

public enum DeviceCategory {

    MONITOR,
    PRINTER,
    SPEAKERS,
    PROJECTOR,
    NOTEBOOK,
    DESKTOP,
    ;

    public String getCategory() {
        return name();
    }

}
