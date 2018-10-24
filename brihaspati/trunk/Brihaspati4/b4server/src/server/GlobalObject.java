package server;

public class GlobalObject {

    private static GlobalObject globalObject;
    private static boolean Certificate_Status = false;
    private static boolean Generic_status = true; // generic services running
    private static boolean Specific_status = false; //specific services not running
    private static boolean running_status;

    public static GlobalObject getGlobalObject() {
        if ( globalObject==null) {
            globalObject = new GlobalObject();
        }
        return globalObject;
    }

    public static void setRunStatus(boolean flag)
    {
        running_status = flag;

    }

    public boolean getRunStatus() {
        return running_status ;
    }

}



