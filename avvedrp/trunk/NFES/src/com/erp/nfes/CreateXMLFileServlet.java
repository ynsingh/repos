package com.erp.nfes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateXMLFileServlet extends HttpServlet
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
StringBuffer str = new StringBuffer(10000);
  Connection conn;

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    String action = req.getParameter("action");
    if ((action == null) || (action == "")){action = "XML";}
    String id = req.getParameter("id");
    this.str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    if (action.equals("WEB_PAGE")) this.str.append("<?xml-stylesheet type=\"text/xsl\" href=\"./xml/style-profile.xml\" ?>\n");
    this.str.append("<StaffProfile>\n<id>" + id + "</id>\n");
    try {
      ConnectDB conObj = new ConnectDB();
      this.conn = conObj.getMysqlConnection();
      String tableName = "";
      String tableFields = "";
      int tableId = 1;
      int recordId = 1;
      int i = 0;
      String data = "";
      Statement theStatement = this.conn.createStatement();
      Statement theStatement1 = this.conn.createStatement();
      ResultSet theResult = theStatement.executeQuery("select username,user_full_name from users where id=" + id);
      ResultSet theResult1 = null;
      theResult.first();
      this.str.append("<username>" + theResult.getString("username") + "</username>\n");
      this.str.append("<name>" + theResult.getString("user_full_name") + "</name>\n");
      theResult = theStatement.executeQuery("select table_name,table_fields from staff_profile_xml where active_yesno=1");
      while (theResult.next()) {
        tableName = theResult.getString("table_name");

        tableFields = theResult.getString("table_fields");
        theResult1 = theStatement1.executeQuery("select " + tableFields + " from " + tableName + " where idf=" + id);
        String[] x = tableFields.split(",");
        recordId = 1;
        while (theResult1.next()) {
          this.str.append("<" + tableName + ">\n<id>" + recordId + "</id>\n"); recordId++;
          for (i = 0; i < x.length; i++) {
            data = theResult1.getString(x[i]);
            if ((data != null) && (!data.isEmpty())) {
              this.str.append("<" + x[i] + ">" + data + "</" + x[i] + ">\n");
              if ((action.equals("WEB_PAGE")) && (tableName.equals("staff_profile_report_v0_values")) && (x[i].equals("upload_photo")))
                this.str.append("<GetPhoto>./GetImageServlet?filename=" + id + "/photo/" + data + "</GetPhoto>\n");
            }
          }
          this.str.append("</" + tableName + ">\n");
        }
      }

      theResult.close(); theResult1.close(); theStatement.close(); theStatement1.close();
      this.conn.close();
      this.str.append("</StaffProfile>\n");
      res.setContentType("application/xml; charset=UTF-8");
      res.getWriter().println(this.str);
     /* if (action.equals("XML")) {
        Writer output = null;
        File file = new File(getServletContext().getRealPath("/") + "XMLProfile/StaffProfile" + id + ".xml");
        output = new BufferedWriter(new FileWriter(file));
        output.write(this.str.toString());
        output.close();
      }*/
      this.str.delete(0, this.str.length());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}