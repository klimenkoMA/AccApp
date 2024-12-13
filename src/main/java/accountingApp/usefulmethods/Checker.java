package accountingApp.usefulmethods;

public class Checker {


    public boolean checkAttribute(String attr){
        boolean isValid;

        isValid = attr == null
                || attr.equals("")
                || attr.isEmpty()
                || attr.equals(" ");

        return isValid;
    }
}
