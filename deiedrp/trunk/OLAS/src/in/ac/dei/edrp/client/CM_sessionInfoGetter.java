package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_sessionInfoGetter implements IsSerializable {
    private String instituteID;
    private String oldSession;
    private String newSession;
    private String currentSemester;
    private String currentStatus;

    public String getInstituteID() {
        return instituteID;
    }

    public void setInstituteID(String instituteID) {
        this.instituteID = instituteID;
    }

    public String getOldSession() {
        return oldSession;
    }

    public void setOldSession(String oldSession) {
        this.oldSession = oldSession;
    }

    public String getNewSession() {
        return newSession;
    }

    public void setNewSession(String newSession) {
        this.newSession = newSession;
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
