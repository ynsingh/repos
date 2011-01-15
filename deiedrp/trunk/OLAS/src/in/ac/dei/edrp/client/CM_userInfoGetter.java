package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_userInfoGetter implements IsSerializable {
    private String instituteID;
    private String uID;
    private int role;
    private String id;
    private String user_id;
    private String user_group_name;
    private String user_name;
    private String password;
    private String registered_timestamp;
    private String modified_timestamp;
    private String last_login;
    private Boolean activated;
    private String page;
    private String authority;
    private String menu_item_name;
    private String menu_item_id;

    public String getUID() {
        return uID;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUID(String uid) {
        uID = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_group_name() {
        return user_group_name;
    }

    public void setUser_group_name(String user_group_name) {
        this.user_group_name = user_group_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRegistered_timestamp() {
        return registered_timestamp;
    }

    public void setRegistered_timestamp(String registered_timestamp) {
        this.registered_timestamp = registered_timestamp;
    }

    public String getModified_timestamp() {
        return modified_timestamp;
    }

    public void setModified_timestamp(String modified_timestamp) {
        this.modified_timestamp = modified_timestamp;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public void setInstituteID(String instituteID) {
        this.instituteID = instituteID;
    }

    public String getInstituteID() {
        return instituteID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuID() {
        return uID;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getpassword() {
        return password;
    }

    public void setrole(int role) {
        this.role = role;
    }

    public int getrole() {
        return role;
    }

	public String getMenu_item_name() {
		return menu_item_name;
	}

	public void setMenu_item_name(String menu_item_name) {
		this.menu_item_name = menu_item_name;
	}

	public String getMenu_item_id() {
		return menu_item_id;
	}

	public void setMenu_item_id(String menu_item_id) {
		this.menu_item_id = menu_item_id;
	}
    
    
}
