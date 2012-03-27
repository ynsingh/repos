
public enum LogoutStatus {

    E,
    L;

    public String value() {
        return name();
    }
    
    public static LogoutStatus fromValue(String v) {
        return valueOf(v);
    }

}