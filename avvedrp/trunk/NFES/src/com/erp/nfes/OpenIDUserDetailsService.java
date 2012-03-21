package com.erp.nfes;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OpenIDUserDetailsService implements UserDetailsService {
   
    
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException, DataAccessException {
    	Connection connect = null;
		ConnectDB conObj=new ConnectDB();	
		try{	
    		connect = conObj.getMysqlConnection();
        if (identifier != null ) {
        	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();		
			Statement st_authorities = connect.createStatement();			
			ResultSet rs_authorities = st_authorities.executeQuery("SELECT role_name FROM roles WHERE active_yesno=1");
			while(rs_authorities.next()) {
				GrantedAuthority lst_roles = new GrantedAuthorityImpl(rs_authorities.getString("role_name"));
				authorities.add(lst_roles);
			}
			//System.out.println("==================identifier"+identifier);
			ResultSet rs_users = st_authorities.executeQuery("SELECT  users.username,password FROM users INNER JOIN user_openID_map ON user_openID_map.user_id=users.id where enabled=1 AND user_openID_map.openid='"+ identifier +"'");
			String username = "";
            String password = "";     
           Integer findUser=0;
			while(rs_users.next()) {
				findUser=1;
				username=rs_users.getString("username");			
				password=rs_users.getString("password");
			}  
			if(findUser==0){
				throw new UsernameNotFoundException("Invalid OpenID");				
			}
				
			boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;            
            //System.out.println("username:"+username);
            //System.out.println("password:"+password);            
            return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        } else {        	
        	throw new UsernameNotFoundException("Invalid OpenID");        	
        }
    } catch (SQLException e) {			
			e.printStackTrace();
		}
    finally{
    	try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
		return null;
    }
    	    	
}

