package com.myapp.struts.admin;

public class Item implements java.io.Serializable{
	private String dateTime;
	private String username;
	private String library_id;
	private String sublibrary_id;
	private String userid;
        private String url;
        private String  role;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSublibrary_id() {
        return sublibrary_id;
    }

    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "User Info : [Username=" + username + ", date=" + dateTime + ", URL="
				+ url + ", Role" + role + ", LoginId=" + userid + ", Library="+library_id+"]";
	}
}


