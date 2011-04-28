package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA



/**
 * Login generated by hbm2java
 */
public class Login  implements java.io.Serializable {


     private LoginId id;
     private StaffDetail staffDetail;
     private String sublibraryId;
     private String loginId;
     private String userName;
     private String question;
     private String ans;
     private String role;
     private String password;

    public Login() {
    }

	
    public Login(LoginId id, StaffDetail staffDetail, String loginId) {
        this.id = id;
        this.staffDetail = staffDetail;
        this.loginId = loginId;
    }
    public Login(LoginId id, StaffDetail staffDetail, String sublibraryId, String loginId, String userName, String question, String ans, String role, String password) {
       this.id = id;
       this.staffDetail = staffDetail;
       this.sublibraryId = sublibraryId;
       this.loginId = loginId;
       this.userName = userName;
       this.question = question;
       this.ans = ans;
       this.role = role;
       this.password = password;
    }
   
    public LoginId getId() {
        return this.id;
    }
    
    public void setId(LoginId id) {
        this.id = id;
    }
    public StaffDetail getStaffDetail() {
        return this.staffDetail;
    }
    
    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }
    public String getSublibraryId() {
        return this.sublibraryId;
    }
    
    public void setSublibraryId(String sublibraryId) {
        this.sublibraryId = sublibraryId;
    }
    public String getLoginId() {
        return this.loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAns() {
        return this.ans;
    }
    
    public void setAns(String ans) {
        this.ans = ans;
    }
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


