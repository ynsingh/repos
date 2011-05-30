package com.myapp.struts.admin;



public class StaffDoc {
        protected String staff_id;
        protected String first_name;
	protected String last_name;
	protected String email_id;
        protected String institute_id;
        protected String manager_id;
        private String user_id;
        private String status;
	

     public String getStaff_id() { return staff_id;}
     public void setStaff_id(String staff_id) {this.staff_id = staff_id;}

     public String getfirst_name() { return first_name;}
     public void setfirst_name(String first_name) {this.first_name = first_name;}


     public String getlast_name() { return last_name;}
     public void setlast_name(String last_name) {this.last_name = last_name;}


     public String getemail_id() { return email_id;}
     public void setemail_id(String email_id) {this.email_id = email_id;}



     public String getinstitute_id(){return institute_id;}
     public void setinstitute_id(String institute_id){this.institute_id=institute_id;}

     public String getmanager_id(){return manager_id;}
     public void setmanager_id(String manager_id){this.manager_id=manager_id;}

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

        
			} //End of Class