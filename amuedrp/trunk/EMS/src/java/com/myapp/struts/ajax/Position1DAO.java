/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.ajax;
import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.MyConnection;
import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import java.sql.Connection;
import java.util.List;
/**
 *
 * @author Dushyant
 */
public class Position1DAO {

public String getPositionByName (String searchText) {
List rs = null;
StringBuffer email_ids = new StringBuffer();
//String sql = "select *  from login where user_id='"+searchText+"' and password='"+searchText+"'";
try {
     Connection    con=MyConnection.getMyConnection();
            if(con==null)
            {
             email_ids.append("<email_ids>");
             email_ids.append("<email_id>Database not Connected! Please contact Web Admin</email_id>");
             email_ids.append("</email_ids>");
             return email_ids.toString();
            }



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

public String getPositionID(String searchText,String searchText1) {
List rs = null,rs1=null;
StringBuffer email_ids = new StringBuffer();
PositionDAO login = new PositionDAO();
try {
    Connection    con=MyConnection.getMyConnection();
            if(con==null)
            {
             email_ids.append("<position>");
             email_ids.append("<error>Database not Connected! Please contact Web Admin</error>");
             email_ids.append("</position>");
             return email_ids.toString();
            }


rs=login.getPosition(searchText,searchText1);
//construct the xml string.


if(!rs.isEmpty())
{
    Position1 position1 = new Position1();
   email_ids.append("<position>");
    while(rs.iterator().hasNext())
    {
    position1 = (Position1)rs.iterator().next();
    email_ids.append("<positionname>"+ position1.getPositionName() +"</positionname>");
    email_ids.append("<choice>"+ position1.getNumberOfChoice() + "</choice>");
    
    rs1 = login.getCandidate(position1.getId().getPositionId(),position1.getId().getElectionId(),position1.getId().getInstituteId());
    email_ids.append("<candidate>");
    while(rs1.iterator().hasNext())
    {
        Candidate1 candidate1 = new Candidate1();
        candidate1 = (Candidate1)rs1.iterator().next();
        email_ids.append("<candidates>"+ candidate1.getCandidateName() +"</candidates>");
    }
    email_ids.append("</candidate>");
    }
   email_ids.append("</position>");
}
else
{
email_ids.append("<position>");
email_ids.append("<error>This User ID not found</error>");
email_ids.append("</position>");
}

}
catch(RuntimeException se) {
email_ids.append("<position>");
email_ids.append("<error>Database Not Connected Please contact Web Admin</error>");
email_ids.append("</position>");
}
finally {
try {

}
catch(Exception e) {
email_ids.append("<position>");
email_ids.append("<error>Database Not Connected Please contact Web Admin</error>");
email_ids.append("</position>");
}
}
return email_ids.toString();
}
public String getPassword(String searchText,String searchText2) {
List rs = null;
StringBuffer email_ids = new StringBuffer();

try {
    Connection    con=MyConnection.getMyConnection();
            if(con==null)
            {
             email_ids.append("<email_ids>");
             email_ids.append("<email_id>Database not Connected! Please contact Web Admin</email_id>");
             email_ids.append("</email_ids>");
             return email_ids.toString();
            }


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
