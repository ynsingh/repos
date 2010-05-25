package in.ac.dei.edrp.pms.upload;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.util.ArrayList;
import java.sql.*;
/**
 * Class DownloadFileAction
 * This class is responsible for listing the uploaded files in a JSP page.
 * It has fillList() method and other getter and setter of length,no of pages for view and offset length.
 */

public class DownloadFileAction {

		/**
		 * It will contain the list of files with other details. And it will contain only
		 * UploadFileForm type object in it.
		 */
		ArrayList<UploadFileForm> list = new ArrayList<UploadFileForm>();
		
		
		/**
		 * For creating the object of DownloadFileAction
		 */
		
		
		/**
		 * This parametric constructor sets the offset and length both and then call the fillList()
		 * method for getting the list of files.
		 * @param offset It shows the position of record,from where the next record will be shown.
		 * @param length  no of records in a page
		 */
		public DownloadFileAction(){
			fillList();
		}
		
		public DownloadFileAction(String owner){
			fillList(owner);
		}

		
	
		/**
		 * This method is responsible for returns the list of all files from database.
		 * @return list which contains the objects of UploadFileForm class with the details of all files.
		 */
		public ArrayList<UploadFileForm> fillList(){
			Connection con=null;
			try{
				
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select file_name,owner,project_name,file_description,size from file");
				/*ResultSetMetaData rsmd=rs.getMetaData();
				int col=rsmd.getColumnCount();	
				for(int i=1;i<=col;i++)
				{
				heading.add(rsmd.getColumnLabel(i));
				}*/
				//rs.next();
				while(rs.next())
				{
					//System.out.println("FILLIST IN DOWNLOADFILEACTION "+rs.getString(5));
					list.add(new UploadFileForm(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

				}

			}
			catch(Exception e)
			{
				System.out.println("Exception is coming IN download file action"+e);
			}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return list;
		}
		
		public ArrayList<UploadFileForm> fillList(String owner){
			Connection con=null;
			try{
				con=MyDataSource.getConnection();//This method Established the connection from the database MySql
				PreparedStatement st=con.prepareStatement("select file_name,project_name,file_description,size from file where owner=? ");
				st.setString(1, owner);
				ResultSet rs=st.executeQuery();
				/*ResultSetMetaData rsmd=rs.getMetaData();
				int col=rsmd.getColumnCount();	
				for(int i=1;i<=col;i++)
				{
				heading.add(rsmd.getColumnLabel(i));
				}*/
				//rs.next();
				while(rs.next())
				{
					//System.out.println("FILLIST IN DOWNLOADFILEACTION by owner"+rs.getString(1));
					list.add(new UploadFileForm(rs.getString(1),owner,rs.getString(2),rs.getString(3),rs.getString(4)));

				}

			}
			catch(Exception e)
			{
				System.out.println("Exception is coming"+e);
			}
			finally
			{
				MyDataSource.freeConnection(con);
			}
			return list;
		}
		
		
		
		/**
		 * It gets the list of all records of files
		 * @return list
		 */
		public ArrayList<UploadFileForm> getList() {
			return list;
		}
		
		/**
		 * It sets the list of all records of files
		 */
		public void setList(ArrayList<UploadFileForm> list) {
			this.list = list;
		}
	}

