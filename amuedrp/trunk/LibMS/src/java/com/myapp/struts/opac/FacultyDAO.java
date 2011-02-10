/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import com.myapp.struts.MyConnection;
import java.sql.*;

/**
 *
 * @author Dushyant
 */
public class FacultyDAO {
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

public String getFacultyByLibraryID (String library_id) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer lib_ids = new StringBuffer();
String sql = "select faculty_id,faculty_name from faculty where library_id='"+library_id+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.

rs.beforeFirst();
lib_ids.append("<faculty_ids>");
while(rs.next())
{
   lib_ids.append("<faculty_id>"+rs.getString("faculty_id")+"</faculty_id>");
   lib_ids.append("<faculty_name>"+rs.getString("faculty_name")+"</faculty_name>");
 }
lib_ids.append("</faculty_ids>");

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
return lib_ids.toString();
}

public String getDeptByFacultyID (String library_id,String faculty_id) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer dept_ids = new StringBuffer();
String sql = "select a.dept_id,a.dept_name from department a inner join faculty b on  a.faculty_id=b.faculty_id and a.library_id=b.library_id where b.library_id='"+library_id+"' and b.faculty_id='"+faculty_id+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.

rs.beforeFirst();
dept_ids.append("<dept_ids>");
while(rs.next())
{
   dept_ids.append("<dept_id>"+rs.getString("dept_id")+"</dept_id>");
   dept_ids.append("<dept_name>"+rs.getString("dept_name")+"</dept_name>");
 }
dept_ids.append("</dept_ids>");

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
return dept_ids.toString();
}

public String getCourseByDeptID (String library_id,String faculty_id,String dept_id) {
Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;
StringBuffer course_ids = new StringBuffer();
String sql = "select a.course_id,a.course_name from courses a inner join department b on a.dept_id=b.dept_id and a.faculty_id=b.faculty_id and a.library_id=b.library_id where b.library_id='"+library_id+"' and b.faculty_id='"+faculty_id+"' and b.dept_id='"+dept_id+"'";
try {
conn = getConnection();//
psmt = conn.prepareStatement(sql);
rs = psmt.executeQuery();
//construct the xml string.

rs.beforeFirst();
course_ids.append("<course_ids>");
while(rs.next())
{
   course_ids.append("<course_id>"+rs.getString("course_id")+"</course_id>");
   course_ids.append("<course_name>"+rs.getString("course_name")+"</course_name>");

}
course_ids.append("</course_ids>");

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
return course_ids.toString();
}
}
