/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.MyConnection;
import java.sql.*;
import javax.servlet.http.*;
/**
 *
 * @author Dushyant
 */
public class EmailDAO {
Connection connection = null;
    public Connection getConnection() {

try{
        
        connection=MyConnection.getMyConnection();
        return connection;
        }
        catch(Exception e)
        {

            System.out.println(e);
            return null;
         }


    }


public String getEmailByName (String searchText) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer email_ids = new StringBuffer();
String sql = "select emai_id from staff_detail where emai_id='"+searchText+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.

 
if(rs.next())
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
catch(SQLException se) {
se.printStackTrace();
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

public String getStaffID(String searchText,String library_id) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;

StringBuffer email_ids = new StringBuffer();
String sql = "select staff_id  from staff_detail where staff_id='"+searchText+"' and library_id='"+ library_id + "'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.


if(rs.next())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>This Staff ID not found</email_id>");
email_ids.append("</email_ids>");
}


rs.close();psmt.close();







}
catch(SQLException se) {
se.printStackTrace();
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
public String getLoginID(String searchText) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer email_ids = new StringBuffer();
String sql = "select user_id  from login where user_id='"+searchText+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.


if(rs.next())
{
email_ids.append("<email_ids>");
email_ids.append("<email_id></email_id>");
email_ids.append("</email_ids>");

}
else
{
email_ids.append("<email_ids>");
email_ids.append("<email_id>This Login ID not found</email_id>");
email_ids.append("</email_ids>");
}


rs.close();psmt.close();







}
catch(SQLException se) {
se.printStackTrace();
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
public String getPassword(String searchText,String searchText2) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer email_ids = new StringBuffer();
String sql = "select *  from login where user_id='"+searchText+"' and password='"+searchText2+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.


if(rs.next())
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


rs.close();psmt.close();







}
catch(SQLException se) {
se.printStackTrace();
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
public String getInstitute_Email_ID(String searchText) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer email_ids = new StringBuffer();
String sql = "select *  from admin_registration where admin_email='"+searchText+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.


if(rs.next())
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


rs.close();psmt.close();







}
catch(SQLException se) {
se.printStackTrace();
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

public String getStaffEmailByName (String staff_id,String searchText) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer email_ids = new StringBuffer();
String sql = "select emai_id from staff_detail where staff_id!='"+staff_id+"' and emai_id='"+searchText+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.


if(rs.next())
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
catch(SQLException se) {
se.printStackTrace();
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
}
