package org.smvdu.payroll.beans;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.beans.db.UserDB;

/**
 *
 * @author Algox
 */
public class UserInfo implements Serializable{    
    private int userId = 0;
    private int currentMonth;
    private int currentYear;
    private String[] months = {"","JAN","FEB","MARCH","APRIL","MAY","JUNE","JULY","AUG","SEPT","OCT","NOW","DEC"};
    private boolean admin=true;
    private String userName = "System";
    private String password;
    private String pass3;
    private String currentDate;
    private String currentMonthName;
    private int userOrgCode;

    private boolean isAuthority;

    public boolean isIsAuthority() {
        if(groupCode==2)
        {
            return true;
        }
        else
            return false;
        
    }

    public void setIsAuthority(boolean isAuthority) {
        this.isAuthority = isAuthority;
    }



    private int groupCode;
    private String groupName;

    public int getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    

    private UserGroup userGroup;

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
    

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    


    public int getUserOrgCode() {
        return userOrgCode;
    }
    public void setUserOrgCode(int userOrgCode) {
        this.userOrgCode = userOrgCode;
        orgName = new OrgProfileDB().getProfileName(userOrgCode);
    }
    public int getCurrentYear() {
        return currentYear;
    }
    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }
    public int getCurrentMonth() {
        return currentMonth;
    }
    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }
    public String getCurrentMonthName() {
        return currentMonthName;
    }
    public void setCurrentMonthName(String currentMonth) {
        this.currentMonthName = currentMonth;
    }
    public String getCurrentDate() {
        return currentDate;
    }
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
    public String getPass3() {
        return pass3;
    }
    public void setPass3(String pass3) {
        this.pass3 = pass3;
    }
    private String pass1;
    public String getPass1() {
        return pass1;
    }
    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }
    private String orgName;
    private ArrayList<UserInfo> allUser;
    public ArrayList<UserInfo> getAllUser() {
        return new UserDB().loadAll();
    }
    private String timeStamp;
    private String pass2;

    public String getPass2() {
        return pass2;
    }
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void save()  {
        if(!password.equals(pass2))
        {
            message = "<html><font color='red'>Password do not match </font>";
            return;
        }
 else
        {
            message = "";
        }
        
        new UserDB().save(this);
        message = "User Created Successfully";
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public void editPass(){
        
        if(!pass3.equals(password))
        {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Current Password is Wrong", "Wrong password");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            return;
        }


        if(!pass1.equals(pass2))
        {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords Doesnot Matches", "Passwords Doesnot Matches");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            return;
        }
        boolean b = new UserDB().editPass(password, userId);
        if(b)
        {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwords Changed", "Passwords Updated");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
        }
        else
        {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Passwords Not Changed", "Passwords Not Updated");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            message = "<html><font color='red'>Password Change failed ! </font></html>";
        }
    }
    public void setPass2(String pass2) {
        
        this.pass2 = pass2;
    }
    public String getTimeStamp() {

        return CommonDB.getTimeStamp();
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    private String error = "";
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public String logout()   {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession)ectx.getSession(false);
        session.invalidate();
        return "Logout.jsf";
    }
    public void set() {
            String[] dd = currentDate.split("-");
            currentMonthName = months[Integer.parseInt(dd[1])] +","+dd[0];
            currentMonth = Integer.parseInt(dd[1]);
            currentYear = Integer.parseInt(dd[0]);
    }
    public String validate()  {
        int x = new UserDB().validate(userName, password);
        if(x>0)
        {
            currentDate = new CommonDB().getDate();
            String[] dd = currentDate.split("-");
            currentMonthName = months[Integer.parseInt(dd[1])] +","+dd[0];
            currentMonth = Integer.parseInt(dd[1]);
            currentYear = Integer.parseInt(dd[0]);
            return "MainPage.jsf";
        }
 else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password", ""));
            return "Login.jsf";
        }

        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "Main.jsf");
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

   
}
