/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.beans.db.UserDB;

/**
 *
 * @author Algox
 */
public class UserInfo {
    
    private int userId = 0;

    private boolean admin=true;
    private String userName = "System";
    private String password;
    private String orgName;

    public String getOrgName() {
        orgName = new OrgProfileDB().getProfile();
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
        new OrgProfileDB().update(orgName);

    }
    


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

    public void save()
    {
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


    public void editPass()
    {
        if(!password.equals(pass2))
        {
            message = "<html><font color='red'>Password do not match. </font></html>";
            return;
        }
        boolean b = new UserDB().editPass(password, userId);
        if(b)
        {
            message = "<html><font color='green'>Password Changed </font></html>";
        }
        else
        {
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


    

    public String validate()
    {
        int x = new UserDB().validate(userName, password);
        if(x>0)
        {
            return "success";
        }
        return "fail";
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     */
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

}
