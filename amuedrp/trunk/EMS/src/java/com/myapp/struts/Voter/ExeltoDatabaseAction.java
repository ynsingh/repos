/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.Voter;


//import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
//import com.myapp.struts.cataloguingDAO.DAO;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.DAO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.Iterator;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.hbm.VoterRegistrationId;
import  com.myapp.struts.utility.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.LoginDAO;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.StaffDetailDAO;
import com.myapp.struts.hbm.StaffDetailId;
import java.util.ArrayList;
import java.util.List;


public class ExeltoDatabaseAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    public String institute_id;
    private LoginDAO logindao = new LoginDAO();
     private Login login =new Login();
  private StaffDetail staffd =new StaffDetail();
      private StaffDetailId staffid =new StaffDetailId();
      private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email mail;
    private String admin_password;
    private String admin_password1;
    String userid;
    List log=new ArrayList();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{

        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
    
        // Object genericobj=new Object();
   
  HttpSession session = request.getSession();
session.removeAttribute("log");
      institute_id=(String)session.getAttribute("institute_id");

         VoterRegistration obj = new  VoterRegistration();
        DAO dataaccess = new DAO();


        
       

      
        int row_no = 0;
     
        String table_name = uploadForm.getCombo_table_name();
        int size = DAO.columnname(table_name).size();
        int sheet_column_size=0;
          if (session.getAttribute("no_columns") != null)
          {
                                sheet_column_size = (Integer) session.getAttribute("no_columns");
          }


        System.out.println("I Am here");
        InputStream inputStream = null;
     

       
        
            FormFile myFile = uploadForm.getExcelFile();


           // HSSFWorkbook workbook = new HSSFWorkbook(myFile.getInputStream());
            inputStream = (myFile.getInputStream());
       //     System.out.println("++++++++++++++++my file  :::::::" + myFile.getFileName().toString());

       
        POIFSFileSystem fileSystem = null;

               
            fileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            String cellvalue = "";
          

            String map_table[] = new String[Math.max(sheet_column_size ,size)];
         
            map_table = (String[]) DAO.selectedcombo(map_table, (size), uploadForm);
            for (int j = 0; j < size-1; j++) {
                System.out.println("selected combo box is" + j + ":::::: " + map_table[j]);
            }
            

            if (uploadForm.getButton().equals("Back")) {
            
                return mapping.findForward("Back");
            }
            if (uploadForm.getButton().equals("Import Data")) {


               
                rows.next();
                  begin:
                while (rows.hasNext()) {
                  
                     System.out.println("this is row no::::::::: " + row_no);
                    VoterRegistrationId voterid=new VoterRegistrationId();
                    VoterRegistration genericobj = new  VoterRegistration();


                    Row row = rows.next();
                    row_no = row.getRowNum();


                    // once get a row its time to iterate through cells.
                    Iterator<Cell> cells = row.cellIterator();



                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);

                            switch (cell.getCellType())
                            {
                                case HSSFCell.CELL_TYPE_BOOLEAN:
                                {

                                    // cell type numeric.
                                    if (cell.getBooleanCellValue() == true) {
                                        cellvalue = "True";
                                    }   {
                                        cellvalue = "false";
                                    }


                                


                                }

                                case HSSFCell.CELL_TYPE_STRING: {

                                    // cell type string.
                                    RichTextString richTextString = cell.getRichStringCellValue();
                                    cellvalue = cell.getRichStringCellValue().getString();
                                    System.out.println("String value: " + richTextString.getString());

                                    break;
                                }


                                default:
                                {

                                    // types other than String and Numeric.
                                    cellvalue = "Unable to read";
                                //    System.out.println("Type not supported.");

                                    break;
                                }

                            }

                            int column_index = cell.getColumnIndex();
                           
                            if (table_name.equals("bibliographic_details"))
                            {
                            //    System.out.println("column index::::::::::::." + column_index);
                               
                                
                                 
                                 
                                         

                                         if(size>column_index){
                                             voterid.setInstituteId(institute_id);

                                           if (map_table[column_index].equals("enrollment")) {
                                            voterid.setEnrollment(cellvalue.trim());


                                            VoterRegistration x=VoterRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());
                                            AdminRegistration x1=AdminRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());
                                            StaffDetail staff=StaffDetailDAO.searchStaffId(voterid.getEnrollment(), institute_id);
                                            if(x1!=null || staff!=null || x!=null){
                                              request.setAttribute("msg1", "EnrollMent No Already Assigned to Some One Else , Import Terminates");
                                              log.add("EnrollMent No Already Assigned to Some One Else for record No"+row_no);session.setAttribute("log",log);
                                              continue begin;


                                            }






                                            if(voterid.getEnrollment().isEmpty()){
                                              request.setAttribute("msg1", "EnrollMent No Cannot Blank , Import Terminates");
                                              log.add("EnrollMent No Cannot Blank for record No"+row_no);session.setAttribute("log",log);
                                              continue begin;



                                            }



                                        }
                                            if (map_table[column_index].equals("voter_name")) {
                                            genericobj.setVoterName(cellvalue.trim());
                                              if(genericobj.getVoterName().isEmpty()){
                                              request.setAttribute("msg1", "VoterName Cannot Blank , Import Terminates");
                                              log.add( "VoterName Cannot Blank At Record No ="+row_no);session.setAttribute("log",log);
                                             continue begin;



                                            }



                                        }
                                            if (map_table[column_index].equals("birthdate")) {
                                            genericobj.setBirthdate(cellvalue.trim());

                     //                          if(genericobj.getBirthdate().isEmpty()){
                       //                        request.setAttribute("msg1", "BirthDate Cannot Blank , Import Terminates");
                         //                      log.add("BirthDate Cannot Blank at Record No="+row_no);session.setAttribute("log",log);
                           //                    continue begin;



                             //               }


                                        }

                                              if (map_table[column_index].equals("f_name")) {
                                            genericobj.setFName(cellvalue.trim());

//                                                  if(genericobj.getFName().isEmpty()){
//                                              request.setAttribute("msg2", "Father Name Cannot Blank , Import Terminates");
//                                            return mapping.findForward(SUCCESS);
//
//
//
//                                            }


                                        }

                                              if (map_table[column_index].equals("m_name")) {
                                            genericobj.setMName(cellvalue.trim());

//                                                  if(genericobj.getMName().isEmpty()){
//                                              request.setAttribute("msg2", "Mother Name Cannot Blank , Import Terminates");
//                                            return mapping.findForward(SUCCESS);
//
//
//
//                                            }


                                        }

                                             if (map_table[column_index].equals("department")) {
                                            genericobj.setDepartment(cellvalue.trim());


//                                              if(genericobj.getDepartment().isEmpty()){
//                                              request.setAttribute("msg2", "Department Cannot Blank , Import Terminates");
//                                            return mapping.findForward(SUCCESS);
//
//
//
//                                            }


                                        }   if (map_table[column_index].equals("course")) {
                                            genericobj.setCourse(cellvalue.trim());
//                                                  if(genericobj.getCourse().isEmpty()){
//                                              request.setAttribute("msg2", "Course Cannot Blank , Import Terminates");
//                                            return mapping.findForward(SUCCESS);
//
//
//
//                                            }



                                        }   if (map_table[column_index].equals("year")) {
                                            genericobj.setYear(cellvalue.trim());

                                        }   if (map_table[column_index].equals("course_duration")) {
                                            genericobj.setCourseDuration(cellvalue.trim());


                                        }   if (map_table[column_index].equals("current_session")) {
                                            genericobj.setCurrentSession(cellvalue.trim());

                                        }   if (map_table[column_index].equals("joining_date")) {
                                            genericobj.setJoiningDate(cellvalue.trim());

                                        }   if (map_table[column_index].equals("gender")) {
                                            genericobj.setGender(cellvalue.trim());

                                                  if(genericobj.getGender().isEmpty()){
                                            request.setAttribute("msg1", "Gender Cannot Blank , Import Terminates");
                                            log.add("Gender Cannot Blank at Record No="+row_no);
                                            session.setAttribute("log",log);
                                            //return mapping.findForward(SUCCESS);
                                             continue begin;



                                            }


                                        }   if (map_table[column_index].equals("mobile_number")) {
                                            genericobj.setMobileNumber(cellvalue.trim());

//                                          if(genericobj.getMobileNumber().isEmpty()){
//                                              request.setAttribute("msg1", "Mobile No Cannot Blank , Import Terminates");
//                                            return mapping.findForward(SUCCESS);
//
//
//
//                                            }



                                        }   if (map_table[column_index].equals("c_address")) {
                                            genericobj.setCAddress(cellvalue.trim());

                                        }   if (map_table[column_index].equals("city")) {
                                            genericobj.setCity(cellvalue.trim());

                                        }   if (map_table[column_index].equals("state")) {
                                            genericobj.setState(cellvalue.trim());

                                        }   if (map_table[column_index].equals("country")) {
                                            genericobj.setCountry(cellvalue.trim());
//                                                if(genericobj.getCountry().isEmpty()){
//                                              request.setAttribute("msg1", "Country Cannot Blank , Import Terminates");
//                                            return mapping.findForward(SUCCESS);
//
//
//
//                                            }

                                        }   if (map_table[column_index].equals("zip_code")) {
                                            genericobj.setZipCode(cellvalue.trim());

                                        }   if (map_table[column_index].equals("p_address")) {
                                            genericobj.setPAddress(cellvalue.trim());

                                        }   if (map_table[column_index].equals("city1")) {
                                            genericobj.setCity1(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("state1")) {
                                            genericobj.setState1(cellvalue.trim());

                                        }
                                         if (map_table[column_index].equals("country1")) {
                                            genericobj.setCountry1(cellvalue.trim());

                                        }   if (map_table[column_index].equals("zip_code1")) {
                                            genericobj.setZipCode1(cellvalue.trim());

                                        }   if (map_table[column_index].equals("email")) {

                                             if(cellvalue.trim().isEmpty()){
                                              request.setAttribute("msg1", "EmailID Cannot Blank , Import Terminates");
                                              log.add("EmailID Cannot Blank at Record No="+row_no);session.setAttribute("log",log);
                                           // return mapping.findForward(SUCCESS);

                                             continue begin;

                                            }


                                            //extract mail_id
                                            String mail=cellvalue.trim();
                                            String pmail[]=mail.split(",");
                                           // StringBuffer alternatemail=new StringBuffer();
                                            System.out.println(cellvalue.trim()+"..........."+pmail.length+".......");

                                            if(pmail.length>1)
                                            {
                                            //for(int i=1;i<pmail.length;i++)
                                                //alternatemail.append(pmail[i].trim());
                                          //  System.out.println(mail.substring(mail.indexOf(",")+1,mail.length())+"......////"+pmail[0].toString());
                                             genericobj.setEmail(pmail[0].toString());
                                         //   genericobj.setAlternateMail(mail.substring(mail.indexOf(",")+1,mail.length()).toString());

                                            }else 
                                            {genericobj.setEmail(cellvalue.trim());}


                                           
                                                if(genericobj.getEmail().isEmpty()){
                                              request.setAttribute("msg1", "EmailID Cannot Blank , Import Terminates");
                                              log.add("EmailID Cannot Blank at Record No="+row_no);session.setAttribute("log",log);
                                           // return mapping.findForward(SUCCESS);

                                             continue begin;



                                            }

                                        }   
                                        if (map_table[column_index].equals("alternate_mail")) {

                                             genericobj.setAlternateMail(cellvalue.trim());

                                        }



                                

                                         }else{
                                         break;
                                         }
                                    
                                    

                                
                            }
                          //  System.out.println("column index::::::::::::." + column_index);
                            
                          //  System.out.println("This is the values in the table :::1::::::" + map_table[cell.getColumnIndex()] + "::::::::::::::::::::");
                            
                        }
                       genericobj.setStatus("REGISTERED");
                        //log.add("insert funcion is going to call"+row_no);

                        //////////////
                        //check for duplicate record and terminate it found
                        genericobj.setId(voterid);

                      VoterRegistration x=VoterRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());
                      AdminRegistration x1=AdminRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());
                      StaffDetail staff=StaffDetailDAO.searchStaffId(voterid.getEnrollment(), institute_id);

                      if(x!=null && x.getEmail().equalsIgnoreCase(genericobj.getEmail())){
                     //  request.setAttribute("msg1", "Voter with Enrollment No"+x.getId().getEnrollment()+" Already Exist , Import Terminates");
                       log.add("Voter with given Email ID "+x.getId().getEnrollment()+" Already Exist cannot Import at record no="+row_no);
                       session.setAttribute("log",log);//   return mapping.findForward(SUCCESS);
                      continue begin;
                      }


                      if(x1==null && staff==null){




                   boolean result=DAO.insertgeneric(genericobj);
                   if(result==false){
                       // request.setAttribute("msg1", "Import data has some problem encourted in row no="+row_no+", Previous data imported Successfully");
                      log.add("Import data has some problem encourted in row no="+row_no+", Previous data imported Successfully");
                     //  return mapping.findForward(SUCCESS);
                    session.setAttribute("log",log);   continue begin;
                   }

                   //Insert Login Details Info in Login table

                      admin_password= RandomPassword.getRandomString(10);
                
                admin_password1=PasswordEncruptionUtility.password_encrupt(admin_password);

//System.out.println(voterid.getEnrollment()+"....................");
//serach Record Inserted
  x=VoterRegistrationDAO.searchVoterRegistration(institute_id, genericobj.getId().getEnrollment());
                      if(x==null){
                      // request.setAttribute("msg1", "Voter with Enrollment No"+x.getId().getEnrollment()+" Cannot be Inserted , Import Terminates");
                        log.add("Voter with Enrollment No"+x.getId().getEnrollment()+" Cannot be Inserted  AT ROW NO"+row_no);

                             return mapping.findForward(SUCCESS);

                      }
                      else{
      login=new Login();
   
login.setUserId(genericobj.getEmail().trim());
userid=genericobj.getEmail().trim();
login.setPassword(admin_password1);
login.setRole("voter");
login.setUserName(x.getVoterName());

staffid.setInstituteId(institute_id);
staffid.setStaffId(genericobj.getId().getEnrollment());
staffd.setId(staffid);

login.setStaffDetail(staffd);
logindao.insert(login, userid);
System.out.println(login.getUserId());

 



           mail=new Email(x.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear "+x.getVoterName()+"\n You are Registered as a Voter with given User Id="+userid +" , Password for Election Management System (EMS) Login ="+admin_password+"\n The URL of the EMS server is https://202.141.40.218:8443/EMS \nFor Voting you will receive separate one time password.\n\n\nWith Regards\nElection Officer\n"+session.getAttribute("institute_name"));

                    mail.send();
			log.add( "\nMail has been send successfully to= "+userid);
			session.setAttribute("log",log);
                      }
                      }
   //if Voter list has record of CEO
                      else if(x1!=null){

                              boolean result=DAO.insertgeneric(genericobj);
                   if(result==false){
                    //    request.setAttribute("msg1", "Import data has some problem encourted in row no="+row_no+", Previous data imported Successfully");
                    log.add( "Import data has some problem encourted in row no="+row_no);
                          // return mapping.findForward(SUCCESS);
                      continue begin;

                   }

    Login temp=logindao.getStaffDetails1("admin."+institute_id, institute_id);
   // System.out.println("IMport Bug..............."+voterid.getEnrollment()+institute_id+" "+"admin."+institute_id+temp);
    if(temp!=null)
    {
    if(temp.getRole().equalsIgnoreCase("insti-admin"))
        temp.setRole("insti-admin,voter");
 logindao.update(temp);

    }
  

  

           mail=new Email(x1.getAdminEmail(),"","Voter Registration Accepted Successfully from EMS","Dear "+x1.getAdminFname()+" "+x1.getAdminLname()+"\n You are Registered as a Voter in EMS.\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));

                    mail.send();
           


 

 }
                      //if voter list has record of Any Election Manager
else if(staff!=null){

        boolean result=DAO.insertgeneric(genericobj);
                   if(result==false){
                       // request.setAttribute("msg1", "Import data has some problem encourted in row no="+row_no+", Previous data imported Successfully");
                       log.add( "Import data has some problem encourted in row no="+row_no);
                       //  return mapping.findForward(SUCCESS);
  continue begin;
                   }

               
  Login temp=logindao.getStaffDetails1(voterid.getEnrollment(), institute_id);
    if(temp!=null)
    {
    if(temp.getRole().equalsIgnoreCase("Election Manager"))
        temp.setRole("Election Manager,voter");


    }
   logindao.update(temp);

 

         mail=new Email(staff.getEmailId(),"","Voter Registration Accepted Successfully from EMS","Dear "+staff.getFirstName()+" "+staff.getLastName()+"\n You are Registered as a Voter in EMS.\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));


                   mail.send();

           
 
}

                    }

            
                   

                }
               

        }
   
          catch (org.apache.poi.poifs.filesystem.OfficeXmlFileException e) {
            request.setAttribute("msg1", e.getMessage());
            return mapping.findForward("Back");
        }  catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg1", "Error In Importing due to "+e.getMessage());
            log.add( "Import data has some problem encourted "+e.getMessage());
            return mapping.findForward("Back");
        }

        
                        System.out.println("++++++++++++++++++++++++++insert funcion is  called");
                        request.setAttribute("msg", "Import Process Completed");
                        return mapping.findForward(SUCCESS);


    }
       public boolean Datatype(String t){
    char d=t.charAt(0);


    switch(d){
        case 48:
            case 49:
                case 50:
                    case 51:
                        case 52:
                            case 53:
                              case 54:
                                case 55:
                                    case 56:
                                        case 57:
                                            return true;

        default:
            return false;
    }





    }


}
class MyExcep extends Exception{
String Mess;
MyExcep(String field ){
this.Mess=field;

}
}
