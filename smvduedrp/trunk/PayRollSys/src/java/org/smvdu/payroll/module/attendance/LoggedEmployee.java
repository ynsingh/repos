/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.module.attendance;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.StringUtils;
import org.smvdu.payroll.api.email.Mail;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.user.ActiveProfile;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class LoggedEmployee implements Serializable {

    private String loginId;
    private String orgName;
    private int currentMonth;
    private int currentYear;
    private int currentDay;

    public int getCurrentDay() {
        return currentDay;
    }


    private String passOne;
    private String passTwo;

    public String getPassOne() {
        return passOne;
    }

    public void setPassOne(String passOne) {
        this.passOne = passOne;
    }

    public String getPassTwo() {
        return passTwo;
    }

    public void setPassTwo(String passTwo) {
        this.passTwo = passTwo;
    }
   
    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }
    private String[] months = {"", "JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOW", "DEC"};
    private boolean admin = true;
    private String currentDate;
    private String currentMonthName;
    private int userOrgCode;
    private String password;
    private String date;
    private Employee profile;
    private Employee empsuptdata;
    private String city;
    private String state;
    private String address;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
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

    public void setCurrentMonthName(String currentMonthName) {
        this.currentMonthName = currentMonthName;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void set() {
        String[] dd = currentDate.split("-");
        currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
        currentMonth = Integer.parseInt(dd[1]);
        currentYear = Integer.parseInt(dd[0]);
        currentDay = Integer.parseInt(dd[2]);
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getUserOrgCode() {
        return userOrgCode;
    }

    public void setUserOrgCode(int userOrgCode) {
        this.userOrgCode = userOrgCode;
        orgName = new OrgProfileDB().getProfileName(userOrgCode);
    }

   /* public String invalidate() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        new EmployeeLoginController().invalidate(profile.getCode(), new CommonDB().getDate());
        return "../Login.jsf";
    }*/

   /* public String validate() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        String s = new EmployeeLoginController().validate(loginId, password,userOrgCode);
        if (s != null) {

            currentDate = new CommonDB().getDate();
            String[] dd = currentDate.split("-");
            currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
            currentMonth = Integer.parseInt(dd[1]);
            currentYear = Integer.parseInt(dd[0]);
            currentDay = Integer.parseInt(dd[2]);
            profile = new EmployeeDB().loadProfile(s,userOrgCode);
            ActiveProfile ap = new ActiveProfile();
            ap.setOrgId(userOrgCode);
            ap.setProfile(profile);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ActiveProfile", ap);
            UserHistory uh = new UserHistory();
            uh.setUserId(profile.getEmpId());
            uh.setAction(true);
            uh.save();
            System.out.println(profile.getName() + ", " + profile.getDesigName() + "," + profile.getDeptName() + currentMonthName);
            return "case1";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Login Id or Password", ""));
            return null;
        }

    }*/
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public void changePassword()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        UserInfo userBean =(UserInfo)fc.getExternalContext().getSessionMap().get("UserBean");
        FacesMessage msg = new FacesMessage();
        if(!passOne.equals(passTwo))
        {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("Password Are Not Matching.....Please Try Again.");
            fc.addMessage("", msg);
            return;
             
        }
        boolean b = new EmployeeLoginController().changePassword(userBean.getUserName(), passOne);
        if(b)
        {
            password = passOne;
            new Mail().sendMailMessage("New Password",userBean.getUserName(),password);
            //System.out.print(passOne+":3"+userBean.getUserName()+":"+password);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Password Changed and email has been sent successfully",""));
        }
        else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Passwords Not Changed", "Passwords Not Updated");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            message = "<html><font color='red'>Password Change failed ! </font></html>";
        }
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logout() {
        try{
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
       /*UserHistory uh = new UserHistory();
         LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
         uh.setUserId(le.getProfile().getEmpId());
	 uh.setAction(false);
         uh.save();
         return "EmployeeLogin.jsf";*/
        new EmployeeLoginController().invalidate(profile.getCode(), new CommonDB().getDate());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext(); 
        extContext.redirect(extContext.getRequestContextPath()+"../Login.jsf");
        
        
        }
         catch(Exception e)
        {
            
            e.printStackTrace();
            //return null;
        }
    }
     public void switchAccount() {
        try{
           FacesContext facesContext = FacesContext.getCurrentInstance();
           ExternalContext extContext = facesContext.getExternalContext(); 
           extContext.invalidateSession();
           extContext.redirect(extContext.getRequestContextPath()+"../WelcomePage.jsf");
         
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
        }
    }

    public Employee getProfile() {
       ActiveProfile ac = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");
       profile = ac.getProfile();
       currentDate = new CommonDB().getDate();
       String[] dd = currentDate.split("-");
       currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
       currentMonth = Integer.parseInt(dd[1]);
       currentYear = Integer.parseInt(dd[0]);
       currentDay = Integer.parseInt(dd[2]);
       return profile;
    }

    public void setProfile(Employee profile) {
        this.profile = profile;
    }
    public Employee getEmpSuptData() {
        empsuptdata=new EmployeeDB().loadEmpsupportProfile(profile.getCode());
        return empsuptdata;
    }

    public void setEmpSuptData(Employee empsuptdata) {
        this.empsuptdata = empsuptdata;
                
    }
    public String getCity() {
        if(profile.getAddress()!=null){
           city=StringUtils.substringBeforeLast(profile.getAddress(), ","); 
           city=city.substring(city.lastIndexOf(',') + 1);
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
                
    }
    public String getState() {
        if(profile.getAddress()!=null){
        //state = profile.getAddress().substring(profile.getAddress().lastIndexOf(',') + 1);
         state = profile.getAddress().substring(profile.getAddress().lastIndexOf(',') + 1);
        //System.out.println("value of last string==state=="+state);
       }
        return state;
    }

    public void setState(String state) {
        this.state = state;
                
    }
    public String getAddress(){
        if(profile.getAddress()!=null){
        address=StringUtils.substringBeforeLast(profile.getAddress(), ",");
        address =StringUtils.substringBeforeLast(address, ",");
        //System.out.println("value of last string==address=="+address);
       }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
                
    }
    public void updateEmpCommInfo()
    {
        try{
            FacesContext fc = FacesContext.getCurrentInstance();
            UserInfo userBean =(UserInfo)fc.getExternalContext().getSessionMap().get("UserBean");
            FacesMessage msg = new FacesMessage();
            address=this.address.trim()+","+this.city.trim()+","+this.state.trim();
            System.out.println("city:"+city+"state:"+state);
            Exception ex=new EmployeeLoginController().updateCommInformation(profile.getCode(),profile.getPhone(),address);
            if(ex == null) {
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                msg.setSummary("Communication Information updated successfully.");
                fc.addMessage("", msg);
                
            }
            else {
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                msg.setSummary("Communication Information not updated.....Please Try Again.");
                fc.addMessage("", msg);
                
            }
        
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
        }
    }  
    String payband;
    public String getPayBand(){
       try{
            PreparedStatement ps;
            ResultSet rs;
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select grd_name, grd_max, grd_min, grd_gp from salary_grade_master where grd_code='"+profile.getGrade()+"' ");
            rs = ps.executeQuery();
            rs.next();
            payband=rs.getString(1)+"("+rs.getInt(2)+"-"+rs.getInt(3)+") - "+rs.getString(4);
            System.out.println("value of payscalein getpayband=="+ rs.getString(1)+"("+rs.getInt(2)+"-"+rs.getInt(3)+") - "+rs.getString(4));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
       
        return payband;
    }
    
    String saldeptname;
    public String getSalDeptName(){
       // System.out.println("saldeptname====="+this.empsuptdata.getSaldept());
       saldeptname=getDeptName(getEmpSuptData().getSaldept());
        
        return saldeptname;
    }

    public void setSalDeptName(String saldeptname) {
        this.saldeptname = saldeptname;
                
    }
    
    String joineddesig;
    public String getJoinDesigName(){
       
       joineddesig=getDesigName(getEmpSuptData().getJoindesig());
       return joineddesig;
    }
    
    public void setJoinDesigName(String joineddesig) {
        this.joineddesig = joineddesig;
            
    }
    
    String joinedept;
    public String getJoinDeptName(){
            joinedept=getDeptName(getEmpSuptData().getJoindept());
            return joinedept;
    } 
    
    public void setgetJoinDeptName(String joinedept) {
        this.joinedept = joinedept;
            
    }
    //get depatment name through department code
    public String getDeptName(int deptcode) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select dept_name from department_master where dept_code=?"); 
            ps.setInt(1, deptcode);
            rs = ps.executeQuery();
            rs.next();
            String deptname = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return deptname;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //get Designation code through designation code
    public String getDesigName(int desigcode) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select desig_name from designation_master where desig_code=?"); 
            ps.setInt(1, desigcode);
            rs = ps.executeQuery();
            rs.next();
            String designame = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return designame;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
