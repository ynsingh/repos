package com.myapp.struts.hbm;
// Generated 28 Jan, 2012 4:37:00 PM by Hibernate Tools 3.2.1.GA



/**
 * SetVoter generated by hbm2java
 */
public class SetVoter  implements java.io.Serializable {


     private SetVoterId id;
     private String password;

    public SetVoter() {
    }

	
    public SetVoter(SetVoterId id) {
        this.id = id;
    }
    public SetVoter(SetVoterId id, String password) {
       this.id = id;
       this.password = password;
    }
   
    public SetVoterId getId() {
        return this.id;
    }
    
    public void setId(SetVoterId id) {
        this.id = id;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


