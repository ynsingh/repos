package in.ac.dei.edrp.api;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.RandPasswordUtil;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
/**
 * This class provide the API for accessing remote data from DEI server
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */
public class DEIRemoteAccessAPI {
	private static String hdir=System.getProperty("user.home");
    private static String path=hdir+"/remote_auth/dei-remote-access.properties";
	
	/**
 	 * Method to check user exist in the system or not
 	 * If user exist it return false and if user does not exit it return true
 	 * @param  email  this is user email/ login name
 	 * @param  srcid this is application resource institute identification code
 	 * @return      String "false" for user exist and "true" for user not exist
 	 */
	public static String checkUserExist(String email, String srcid){
		String vale="false";
		try{
            String randompswd = RandPasswordUtil.randmPass();
            String kline=ReadNWriteInTxt.readLin(path,srcid);
            String skey=StringUtils.substringBetween(kline,";",";");
            String serverUrl=StringUtils.substringAfterLast(kline,";");
            System.out.println("server url "+serverUrl);
            serverUrl = serverUrl.concat("/studentMasterAPI/chkUserExist.htm");
            System.out.println("server url "+serverUrl);
            String hashcode=EncrptDecrpt.keyedHash(srcid,randompswd,skey);
            String params="email="+email+"&srcid="+srcid+"&rand="+randompswd+"&hash="+hashcode+"&aname=getUsrEst";
            vale=connectAndGetData(serverUrl,params,email,"User_Exist");
        }
        catch(Exception ex){
            System.out.println("The problem in httpurl connection RemoteAccessApi util (checkUserExist) "+ex);
        }
		return vale;
	}	
	
	/**
	 *  Method To make connection and return data from Remote server
     */

  private static String connectAndGetData(String serverUrl, String param, String emailId, String folderName){
	  String reslt=null;
	  StringBuffer response=null;	  
	  BufferedInputStream urlin = null;
	  BufferedOutputStream fout = null;
	  String returnStatement="";
	  try{
		  HttpURLConnection urlConnection = getServletConnection(serverUrl);
		  //first url connection for validation
		  urlConnection.connect();
		  OutputStream os = urlConnection.getOutputStream();
		  os.write(param.getBytes("UTF-8"));
		  os.close();
		  InputStream is = urlConnection.getInputStream();

		  //second url connection to copy content to file
		  HttpURLConnection urlConnection2 = getServletConnection(serverUrl);
		  urlConnection2.connect();
		  OutputStream os2 = urlConnection2.getOutputStream();
		  os2.write(param.getBytes("UTF-8"));
		  os2.close();
		  InputStream is2 = urlConnection2.getInputStream();
		  			  			 
		// receive result from servlet								
		if(urlConnection.getHeaderField("ErrorMessage")!=null){
			returnStatement = "sorry! data fetch fail. Please contact DEI Admin with this log detail \n"+urlConnection.getHeaderField("ErrorMessage");
		}
		else{									
		  BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		  String line;
		  response = new StringBuffer();
		  while((line = rd.readLine()) != null) {				 
			  response.append(line);
			  response.append('\n');
		  }
		  rd.close();
		  reslt=response.toString();
		  if(reslt.contains("?xml")){
			  File f = new File(hdir+"/remote_auth/DEI_data/"+folderName);
			  if(!f.exists()){
				  f.mkdirs();
			  }
			  int bufSize = 8 * 1024;
			  urlin = new BufferedInputStream(is2,bufSize);
			  fout = new BufferedOutputStream(new FileOutputStream(f+"/"+emailId+".xml"), bufSize);
			  returnStatement="Required xml file generated successfully";
			  copyPipe(urlin, fout, bufSize);
		  }
		  else{
			  returnStatement=reslt;
		  }	  
	  }//end else error message 
	  }
	  catch (MalformedURLException ex) {
		  ex.printStackTrace();
		  returnStatement = "Malformed url exception...Please check the URL";
		  return returnStatement;
	  } catch (ProtocolException ex) {
		  ex.printStackTrace();
		  returnStatement = "Exception related with the protocol...Please check the URL";
		  return returnStatement;
	  } catch (IOException ex) {
		  ex.printStackTrace();
		  returnStatement = "Some problem in establishing connection with DEI server....Please try after some time\n If problem persist then contact" +
		  		"DEI Admin with proper log";
		  return returnStatement;				
	  }
	  catch (SecurityException sx) {
		  sx.printStackTrace();
		  returnStatement = "Some security exception...Please contact your admin";
		  return returnStatement;
	  }
	  catch(Exception ex){
		  ex.printStackTrace();
		  returnStatement = "Sorry some Exception in the process...Contact DEI admin with the proper log";
		  return returnStatement;
	  }
	  finally {
		  if (urlin != null) {
			  try {
				  urlin.close();
			  }
			  catch (IOException cioex) {
			  }
		  }
		  if (fout != null) {
			  try {
				  fout.close();
			  }
			  catch (IOException cioex) {
			  }
		  }
	  }
	  return returnStatement;
  }
  
  private static HttpURLConnection getServletConnection(String serverUrl)throws MalformedURLException, IOException {
		URL urlServlet = new URL(serverUrl);
		HttpURLConnection con = (HttpURLConnection)urlServlet.openConnection();		 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//		  con.connect();		  		  		
		
		return con;
	}

  /**
   * Reads data from the input and writes it to the output, until the end of the input
   * stream.
   * 
   * @param in
   * @param out
   * @param bufSizeHint
   * @throws IOException
   */
  public static void copyPipe(InputStream in, OutputStream out, int bufSizeHint)
          throws IOException {
      int read = -1;
      byte[] buf = new byte[bufSizeHint];
      while ((read = in.read(buf)) >= 0) {	    	  
          out.write(buf, 0, read);
          out.write('\n');
      }
      out.flush();
  }
  
  private static String onSendData(StudentMasterBeanAPI info, String serverUrl, String param) {
		String result = "false";
		try {
			// send data to the servlet				
			HttpURLConnection con = getServletConnection(serverUrl);
			con.setRequestProperty("Content-Type","application/x-java-serialized-object");
			OutputStream outstream = con.getOutputStream();
//			outstream.write(param.getBytes("UTF-8"));
			ObjectOutputStream oos = new ObjectOutputStream(outstream);
			oos.writeObject(info);
			oos.writeObject(param);
			oos.flush();
			oos.close();
			// receive result from servlet								
			if(con.getHeaderField("ErrorMessage")!=null){
				System.out.println("sorry! update fail. Please contact DEI Admin with this log detail \n"+con.getHeaderField("ErrorMessage"));
			}
			else{
				InputStream instr = con.getInputStream();
				ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
				result = (String) inputFromServlet.readObject();
				inputFromServlet.close();
				instr.close();
			}			
		}  catch (MalformedURLException ex) {
			  ex.printStackTrace();
			  result = "Malformed url exception...Please check the URL";
			  return result;
		  } catch (ProtocolException ex) {
			  ex.printStackTrace();
			  result = "Exception related with the protocol...Please check the URL";
			  return result;
		  } catch (IOException ex) {
			  ex.printStackTrace();
			  result = "Exception in object read/write...Please check the beanApi are available";
			  return result;				
		  }
		  catch (SecurityException sx) {
			  sx.printStackTrace();
			  result = "Some security exception...Please contact your admin";
			  return result;
		  }
		  catch(Exception ex){
			  ex.printStackTrace();
			  result = "Sorry some Exception in the process...Contact DEI admin with the proper log \n"+ex.getMessage();
			  return result;
		  }	  
		return result;
	}

	/**
	   * Save URL contents to a file.
	   */
	  public static boolean copy(URL from, File to) {
	      BufferedInputStream urlin = null;
	      BufferedOutputStream fout = null;	     
	      try {
	          int bufSize = 8 * 1024;
	          urlin = new BufferedInputStream(
	                  from.openConnection().getInputStream(),
	                  bufSize);
	          fout = new BufferedOutputStream(new FileOutputStream(to), bufSize);
	          copyPipe(urlin, fout, bufSize);
	      }
	      catch (IOException ioex) {
	          return false;
	      }
	      catch (SecurityException sx) {
	          return false;
	      }
	      finally {
	          if (urlin != null) {
	              try {
	                  urlin.close();
	              }
	              catch (IOException cioex) {
	              }
	          }
	          if (fout != null) {
	              try {
	                  fout.close();
	              }
	              catch (IOException cioex) {
	              }
	          }
	      }
	      return true;
	  }
	  	  
	  /**
	 	 * Method to get student information from the dei server
	 	 * @param  email  this is student's emailId
	 	 * @param  srcid this is application resource institute identification code
	 	 * @return String student information in the xml form
	 	 */
		public static String getStudentInfo(String emailId,String universityCode, String srcId){
			String vale="false";
			try{
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/getStudentInformation.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="emailId="+emailId+"&univCode="+universityCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale=connectAndGetData(serverUrl,params,emailId,"Student_Information");
	        }
			catch(NullPointerException ex){
				vale = "Pleaes check :\n 1. All the three variables passed have the genuine value\n 2. Your property file have the genuine and proper value" +
						" for your source id";
			}
	        catch(Exception ex){
	        	vale="Some Exception in the getStudentInfo process...Please contact DEI admin with this log : \n"+ex.getMessage();
	            System.out.println("Some Exception in the getStudentInfo process...Please contact DEI admin with this log "+ex);
	        }
			return vale;
		}	
		
	  /**
	 	 * Method to get student information from the dei server
	 	 * @param  email  this is student's emailId
	 	 * @param  srcid this is application resource institute identification code
	 	 * @return String student information in the xml form
	 	 */
		public static String updateStudentInfo(StudentMasterBeanAPI aa, String srcId,String emailId, String univCode){
			String vale="false";
			try{
				aa.setUserId(srcId);
				aa.setUserEmailId(emailId);
				aa.setUniversityId(univCode);
				aa.setStatus("ACT");
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/updateStudentInformation.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="emailId="+emailId+"&univCode="+univCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale = onSendData(aa,serverUrl,params);
	        }
			catch(NullPointerException ex){
				vale = "Pleaes check :\n 1. All the four variables passed have the genuine value\n 2. Your property file have the genuine and proper value" +
						" for your source id";
			}
			  catch (SecurityException sx) {
				  sx.printStackTrace();
				  vale = "Some security exception...Please contact your admin";
				  return vale;
			  }
			  catch(Exception ex){
				  ex.printStackTrace();
				  vale = "Sorry some Exception in the process...Contact DEI admin with the proper log \n"+ex.getMessage();
				  return vale;
			  }	  
			return vale;
		}			
		
		/**
	 	 * Method to get contact information from the dei server
	 	 * @param  email  this is user's emailId
	 	 * @param  srcid this is application resource institute identification code
	 	 * @return String contact information in the xml form
	 	 */
		public static String getContactInfo(String emailId, String srcId, String univCode){
			String vale="false";
			try{
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/getContactInfo.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="emailId="+emailId+"&univCode="+univCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale=connectAndGetData(serverUrl,params,emailId,"Contact_Information");
	        }
			catch(NullPointerException ex){
				vale = "Pleaes check :\n 1. All the three variables passed have the genuine value\n 2. Your property file have the genuine and proper value" +
						" for your source id";
			}
	        catch(Exception ex){
	        	vale="Some Exception in the getContactInfo process...Please contact DEI admin with this log :"+ex.getMessage();
	            System.out.println("Some Exception in the getContactInfo process...Please contact DEI admin with this log "+ex);
	        }
			return vale;
		}	

		 /**
	 	 * Method to update contact information in the dei server
	 	 * @param studentMasteBean object containing changes values
	 	 * @param  email  this is user's emailId whom record need to be updated
	 	 * @param  srcid this is application resource institute identification code
	 	 * @return String relevant success/failure message
	 	 */
		public static String updateContactInfo(StudentMasterBeanAPI aa, String srcId,String emailId, String univCode){
			String vale="false";
			try{
				aa.setUserId(srcId);
				aa.setUserEmailId(emailId);
				aa.setUniversityId(univCode);
				String addressKey = aa.getAddressKey()==null?"":aa.getAddressKey();
				if(addressKey.length()==0){
					return "It is mandatory to pass the addresskey, the valid values are cor/per";					
				}
				else if(!(addressKey.equalsIgnoreCase("cor") || addressKey.equalsIgnoreCase("per"))){
					return "The value you pass for addresskey is not valid , the valid values are cor/per";
				}
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/updateContactInformation.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="emailId="+emailId+"&univCode="+univCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale = onSendData(aa,serverUrl,params);
	        }
			  catch (SecurityException sx) {
				  sx.printStackTrace();
				  vale = "Some security exception...Please contact your admin";
				  return vale;
			  }
			  catch(Exception ex){
				  ex.printStackTrace();
				  vale = "Sorry some Exception in the process...Contact DEI admin with the proper log \n"+ex.getMessage();
				  return vale;
			  }	  
			return vale;
		}	
		
		/**
	 	 * Method to get parent information from the dei server
	 	 * @param  email  this is user's emailId
	 	 * @param  srcid this is application resource institute identification code
	 	 * @return String contact information in the xml form
	 	 */
		public static String getParentInfo(String emailId, String srcId, String univCode){
			String vale="false";
			try{
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/getParentInfo.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="emailId="+emailId+"&univCode="+univCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale=connectAndGetData(serverUrl,params,emailId,"Parent_Information");
	        }
			catch(NullPointerException ex){
				vale = "Pleaes check :\n 1. All the three variables passed have the genuine value\n 2. Your property file have the genuine and proper value" +
						" for your source id";
			}
			catch (SecurityException sx) {
				  sx.printStackTrace();
				  vale = "Some security exception...Please contact your admin";
			  }
			  catch(Exception ex){
				  ex.printStackTrace();
				  vale = "Sorry some Exception in the process...Contact DEI admin with the proper log \n"+ex.getMessage();
			  }	
			return vale;
		}	
		
		/**
	 	 * Method to get academic information from the dei server
	 	 * @param  roll Number of the student
	 	 * @param  srcid this is application resource institute identification code
	 	 * @param univCode This is application resource institute code
	 	 * @return String academic information in the xml form
	 	 */
		public static String getAcademicInfo(String rollNumber, String srcId, String univCode){
			String vale="false";
			try{
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/getAcademicInfo.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="rollNo="+rollNumber+"&univCode="+univCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale=connectAndGetData(serverUrl,params,rollNumber,"Academic_Information");
	        }
			catch(NullPointerException ex){
				vale = "Pleaes check :\n 1. All the three variables passed have the genuine value\n 2. Your property file have the genuine and proper value" +
						" for your source id";
			}
			catch (SecurityException sx) {
				  sx.printStackTrace();
				  vale = "Some security exception...Please contact your admin";
			  }
			  catch(Exception ex){
				  ex.printStackTrace();
				  vale = "Sorry some Exception in the process...Contact DEI admin with the proper log \n"+ex.getMessage();
			  }	
			return vale;
		}	
		
		/**
	 	 * Method to get Registration information from the dei server
	 	 * @param  roll Number of the student
	 	 * @param  srcid this is application resource institute identification code
	 	 * @param univCode This is application resource institute code
	 	 * @return String Registration information in the xml form
	 	 */
		public static String getRegistrationInfo(String emailId, String srcId, String univCode){
			String vale="false";
			try{
	            String randomPassword = RandPasswordUtil.randmPass();
	            String kline=ReadNWriteInTxt.readLin(path,srcId);
	            String skey=StringUtils.substringBetween(kline,";",";");
	            String serverUrl=StringUtils.substringAfterLast(kline,";");
	            serverUrl = serverUrl.concat("studentMasterAPI/getRegistrationInfo.htm");
	            String hashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
	            String params="emailId="+emailId+"&univCode="+univCode+"&srcId="+srcId+"&randomPassword="+randomPassword+"&hashCode="+hashCode;
	            vale=connectAndGetData(serverUrl,params,emailId,"Registration_Information");
	        }
			catch(NullPointerException ex){
				vale = "Pleaes check :\n 1. All the three variables passed have the genuine value\n 2. Your property file have the genuine and proper value" +
						" for your source id";
			}
			catch (SecurityException sx) {
				  sx.printStackTrace();
				  vale = "Some security exception...Please contact your admin";
			  }
			  catch(Exception ex){
				  ex.printStackTrace();
				  vale = "Sorry some Exception in the process...Contact DEI admin with the proper log \n"+ex.getMessage();
			  }	
			return vale;
		}	

}

