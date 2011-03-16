package com.myapp.struts.admin;



public class LoginDoc
{

        protected String user_id;
        protected String user_name;
        protected String password;
	protected String library_id;
        protected String staff_id;
        protected String question;
        protected String ans;
        private String role;

     
     public String getuser_id() { return user_id;}
     public void setuser_id(String user_id) {this.user_id = user_id;}

     public String getuser_name() { return user_name;}
     public void setuser_name(String user_name) {this.user_name = user_name;}


     public String getpassword() { return password;}
     public void setpassword(String password) {this.password = password;}


     public String getlibrary_id() { return library_id;}
     public void setlibrary_id(String library_id) {this.library_id = library_id;}

        public String getStaff_id() { return staff_id;}
     public void setStaff_id(String staff_id) {this.staff_id = staff_id;}

   public String getquestion() { return question;}
     public void setquestion(String question) {this.question = question;}

   public String getans() { return ans;}
     public void setans(String ans) {this.ans = ans;}

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

			} //End of Class