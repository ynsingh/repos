package in.ac.dei.edrp.cms.domain.studentregistration;


/**
 * Bean for
 * @author Manpreet
 * @date 10-12-2010
 * @version 1.0
 */
public class LoginInfoGetter {
    private String studentId;
    private String registrationNumber;
    private String rollNumber;
    private String password;
    private String userName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
