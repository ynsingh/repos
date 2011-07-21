/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.AdminDAO.LoginDAO;
import java.util.List;
/**
 *
 * @author Dushyant
 */
public class EmailDAO {

public String getEmailByName (String searchText) {
List rs = null;
StringBuffer email_ids = new StringBuffer();

try {
     



LoginDAO login = new LoginDAO();
rs = login.getUser(searchText);


//construct the xml string.

 
if(!rs.isEmpty())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>This Email Already Exists</email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");
}










}
catch(Exception se) {
    email_ids.append("<email_ids>");
email_ids.append("<email_id>Database not Connected! Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
return email_ids.toString();
}
finally {
try {

}
catch(Exception e) {
return email_ids.toString();
}
}
return email_ids.toString();
}

public String getLoginID(String searchText) {
List rs = null;
StringBuffer email_ids = new StringBuffer();
LoginDAO login = new LoginDAO();
try {
   


rs=login.getUser(searchText);
//construct the xml string.


if(!rs.isEmpty())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>This User ID not found</email_id>");
email_ids.append("</email_ids>");
}

}
catch(RuntimeException se) {
email_ids.append("<email_ids>");
email_ids.append("<email_id>Database Not Connected Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
}
finally {
try {

}
catch(Exception e) {
email_ids.append("<email_ids>");
email_ids.append("<email_id>Database Not Connected Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
}
}
return email_ids.toString();
}
public String getPassword(String searchText,String searchText2) {
List rs = null;
StringBuffer email_ids = new StringBuffer();

try {
   

LoginDAO login = new LoginDAO();
rs = login.getLoginDetails(searchText, searchText2);
//construct the xml string.


if(!rs.isEmpty())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>Password Incorrect</email_id>");
email_ids.append("</email_ids>");
}

}
catch(RuntimeException se) {
email_ids.append("<email_ids>");
email_ids.append("<email_id>Database Not Connected Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
}
finally {
try {

}
catch(Exception e) {
e.printStackTrace();
}
}
return email_ids.toString();
}

/*
public String getEmailByName (String searchText) {
List rs = null;
StringBuffer email_ids = new StringBuffer();
//String sql = "select *  from login where user_id='"+searchText+"' and password='"+searchText+"'";
try {
LoginDAO login = new LoginDAO();
rs = login.getUser(searchText);


//construct the xml string.


if(!rs.isEmpty())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>This Email Already Exists</email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");
}










}
catch(Exception se) {
    email_ids.append("<email_ids>");
email_ids.append("<email_id>Database not Connected! Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
return email_ids.toString();
}
finally {
try {

}
catch(Exception e) {
return email_ids.toString();
}
}
return email_ids.toString();
}

/*public String getLoginID(String searchText) {
List rs = null;
StringBuffer email_ids = new StringBuffer();
LoginDAO login = new LoginDAO();
try {
rs=login.getUser(searchText);
//construct the xml string.


if(!rs.isEmpty())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>This User ID not found</email_id>");
email_ids.append("</email_ids>");
}

}
catch(RuntimeException se) {
email_ids.append("<email_ids>");
email_ids.append("<email_id>Database Not Connected Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
}
finally {
try {

}
catch(Exception e) {
email_ids.append("<email_ids>");
email_ids.append("<email_id>Database Not Connected Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
}
}
return email_ids.toString();
}
/*public String getPassword(String searchText,String searchText2) {
List rs = null;
StringBuffer email_ids = new StringBuffer();
String sql = "select *  from login where user_id='"+searchText+"' and password='"+searchText2+"'";
try {
LoginDAO login = new LoginDAO();
rs = login.getLoginDetails(searchText, searchText2);
//construct the xml string.


if(!rs.isEmpty())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>Password Incorrect</email_id>");
email_ids.append("</email_ids>");
}

}
catch(RuntimeException se) {
email_ids.append("<email_ids>");
email_ids.append("<email_id>Database Not Connected Please contact Web Admin</email_id>");
email_ids.append("</email_ids>");
}
finally {
try {

}
catch(Exception e) {
e.printStackTrace();
}
}
return email_ids.toString();
}



*/

}
