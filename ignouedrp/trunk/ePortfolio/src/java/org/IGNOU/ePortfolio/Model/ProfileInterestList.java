
package org.IGNOU.ePortfolio.Model;

/**
 *
 * @author IGNOU Team
 */
public class ProfileInterestList {
    private long interestId;
     private String userId;
     private String acadInterest;
     private String persInterest;
     private String techInterest;
     private String reserInterst;
     private String myHobbies;

    public ProfileInterestList() {
    }

	
    public ProfileInterestList(long interestId) {
        this.interestId = interestId;
    }
    public ProfileInterestList(long interestId, String userId, String acadInterest, String persInterest, String techInterest, String reserInterst, String myHobbies) {
       this.interestId = interestId;
       this.userId = userId;
       this.acadInterest = acadInterest;
       this.persInterest = persInterest;
       this.techInterest = techInterest;
       this.reserInterst = reserInterst;
       this.myHobbies = myHobbies;
    }
   
    public long getInterestId() {
        return this.interestId;
    }
    
    public void setInterestId(long interestId) {
        this.interestId = interestId;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAcadInterest() {
        return this.acadInterest;
    }
    
    public void setAcadInterest(String acadInterest) {
        this.acadInterest = acadInterest;
    }
    public String getPersInterest() {
        return this.persInterest;
    }
    
    public void setPersInterest(String persInterest) {
        this.persInterest = persInterest;
    }
    public String getTechInterest() {
        return this.techInterest;
    }
    
    public void setTechInterest(String techInterest) {
        this.techInterest = techInterest;
    }
    public String getReserInterst() {
        return this.reserInterst;
    }
    
    public void setReserInterst(String reserInterst) {
        this.reserInterst = reserInterst;
    }
    public String getMyHobbies() {
        return this.myHobbies;
    }
    
    public void setMyHobbies(String myHobbies) {
        this.myHobbies = myHobbies;
    }

}
