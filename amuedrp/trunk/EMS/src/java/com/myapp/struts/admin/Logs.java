package com.myapp.struts.admin;
public class Logs

{
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    private int voted;

    public int getVoted() {
        return voted;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

  

   

      public Logs(String a,int b)
         {
             gender=a;
             voted=b;
         }
   


}