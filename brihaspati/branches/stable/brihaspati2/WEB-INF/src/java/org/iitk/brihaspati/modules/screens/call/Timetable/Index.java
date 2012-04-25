package org.iitk.brihaspati.modules.screens.call.Timetable;
import java.io.*;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;

import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.*;

import java.util.List;
import org.apache.turbine.util.RunData;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.InfraManagementUtil;
import org.iitk.brihaspati.om.Department;
import org.iitk.brihaspati.om.DepartmentPeer;
import org.iitk.brihaspati.om.Room;
import org.iitk.brihaspati.om.RoomPeer;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.Timetable.*;

public class Index extends SecureScreen_Institute_Admin
{
	String xmlFile=new String();
	public void doBuildTemplate(RunData data, Context context)
	{
		String mode=data.getParameters().getString("mode","");
		String count=data.getParameters().getString("count","");
		context.put("count",count);
		context.put("mode",mode);
		context.put("utils", new InfraManagementUtil());
		System.out.println("qwertyu" + count);
		
		try 
		{
			if(count.equals("1"))
			{
				initialize(data, context);
			}
			else if(count.equals("2"))
			{
				String department = data.getParameters().getString("department", "");
				data.getSession().setAttribute("department", department);
				
				System.out.println("Department is " + department);
				log.info("Department is " + department);
				Utils.getUploadedFiles(data, department);
				
				StringTokenizer st = new StringTokenizer(department, "~");
					data.getSession().setAttribute("department_display", st.nextToken());
			}
			else if(count.equals("3"))
			{
				User user = data.getUser();
				String username = user.getName();
				String path = "/reports/" + username + "/" ;
				String filesPath=data.getServletContext().getRealPath(path);
				File directory = new File(filesPath);
				File[] dir = directory.listFiles();	
		
				if(mode.equals("get")) {
					/*Vector dirs = new Vector();	
					for(int index = 0 ; index < dir.length; ++index)
					{
						File uploadDir = dir[index].getAbsoluteFile();
						if(uploadDir.isDirectory()) {
							String dirName = uploadDir.toString();
							StringTokenizer st = new StringTokenizer(dirName, "-");
							while(st.hasMoreElements())
								dirName = st.nextToken();
							dirs.add(dirName);
						}
					}
					System.out.println("Entering the page to manage departments.");
					data.getRequest().setAttribute("dirList", dirs);*/
				}
				else if(mode.equals("update")) {
					System.out.println("Updating department list.");
					String[] departments = data.getParameters().getStrings("department");
					int len = departments.length;
		
					/** DELETE **/
					for(int index = 0 ; index < dir.length; ++index)
					{
						File uploadDir = dir[index].getAbsoluteFile();
						if(uploadDir.isDirectory()) {
							String dirName = uploadDir.getName();
							System.out.println(dirName);
							//	StringTokenizer st = new StringTokenizer(dirName, "-");
							//	dirName = st.nextToken();
							boolean flag = false;
							for(int i = 0 ; i < len; ++i)
								if(dirName.equals(departments[i])) {
									flag = true;
									break;
								}
							if(!flag) {
								boolean success = Utils.deleteDirectory(uploadDir);
								if(!success)
									System.out.println(uploadDir + " Not Deleted");
								else 
									System.out.println(uploadDir + " Deleted");
							}
						}
					}
					/** CREATE **/
					directory = new File(filesPath);
					dir = directory.listFiles();	
					for(int i = 0; i < len; ++i)
					{
						System.out.println(departments[i]);
						boolean flag = false;
						for(int index = 0 ; index < dir.length; ++index)
						{
							File uploadDir = dir[index].getAbsoluteFile();
							String dirName = uploadDir.getName();
							if(departments[i].equals(dirName)) {
								flag = true;
								break;
							}	
						}
						if(!flag) {
							String filePath = filesPath + "/" + departments[i];
							Utils.checkDirectoryPath(filePath);
						}
					}
					data.getSession().setAttribute("department", "");
				
				}
			}
			else if(count.equals("4"))
			{
				FileItem fileItem = data.getParameters().getFileItem("file");
				String fileName = fixFileName( fileItem.getName() );
				
				StringTokenizer st=new StringTokenizer(fileName,".");
				String file = st.nextToken();
				String fileExtension = st.nextToken();
				if(!fileExtension.equals("xls")) {
					context.put("msg", "Only xls files are supported currently.");
					return;
				}
				
		
				User user = data.getUser();
			    String username = user.getName();
				Date date = new Date();
				String department = (String)data.getSession().getAttribute("department");
				
				String newDir = date.getDate() + "-" + date.getMonth() + "-" + new String("" + (date.getYear() + 1900))
				+ " " + date.getHours() + ":" + date.getMinutes()+ ":" + date.getSeconds()	;
			
				String path = "/reports/" + username + "/" + department + "/" + newDir;
				String dirPath = data.getServletContext().getRealPath(path) + "/";
				Utils.checkDirectoryPath(dirPath);
				
				String filePath = dirPath + file;
				System.out.println("Filepath for uploaded xls file is " + filePath);
		
				String xlsFile = filePath + "." + fileExtension;
				fileItem.write(new File(xlsFile));
				System.out.println(xlsFile + " file has been uploaded.");
		
				/* Generate xml file of the uploaded xls file at same place */
				
				try {
					System.out.println("Generating xml file for " + fileName);
					
					xmlFile = filePath + ".xml";
					System.out.println("xmlfile is : " + xmlFile);
					
					generateXml(xlsFile);
					System.out.println("Generated XML successfully.");
				} catch(Throwable t) {
					t.printStackTrace();
					System.out.println("SEVERE: Error in generating xml file");
					context.put("msg", "Error in uploaded file. Please review it.");
					return;
				}
			}
			initialize(data, context);
		}
		catch(Exception e)
		{
			return;
		}
	}
	private void initialize(RunData data, Context context) throws Exception
	{
		Utils.getDepartments(data,context);
		String department = (String)data.getSession().getAttribute("department");
		if(department.equals("")) 
			return;
		
		Utils.getUploadedFiles(data, department);
		
	}
	public void generateXml(String xlsFile) throws IOException, BiffException {
		String encoding = "UTF8";
		boolean formatInfo = false;

		String file=xlsFile;
		Workbook workbook = Workbook.getWorkbook(new File(file));
		
		FileWriter bw=new FileWriter(new File(xmlFile));
		bw.write("<?xml version=\"1.0\" ?>\n");
		bw.write("<workbook>\n");
		for (int sheet = 0; sheet < workbook.getNumberOfSheets(); sheet++)
		{
			Sheet s = workbook.getSheet(sheet);
			bw.write("  <sheet>\n");
			bw.write("    <name>["+s.getName()+"]</name>\n");
			Cell[] row = null;
			for (int i = 0 ; i < s.getRows() ; i++)
			{
				bw.write("    <row number=\"" + i + "\">\n");
				row = s.getRow(i);

				for (int j = 0 ; j < row.length; j++)
				{
					if (row[j].getType() != CellType.EMPTY)
					{
						bw.write("      <col number=\"" + j + "\">");
						bw.write(row[j].getContents());
						bw.write("</col>\n");
					}
				}
				bw.write("    </row>\n");
			}
			bw.write("  </sheet>\n");
		}
		bw.write("</workbook>");
		bw.close();
		
	}
	
	
	/*
	 * Fix file name if uploaded from a windows client
	 */
	public static String fixFileName(String longFile)
	{
		int loc = longFile.lastIndexOf("\\");
		return longFile.substring( (loc+1), longFile.length() );
	}
	
}
