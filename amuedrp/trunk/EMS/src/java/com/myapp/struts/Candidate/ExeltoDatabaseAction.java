/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.Candidate;


//import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
//import com.myapp.struts.cataloguingDAO.DAO;
import com.myapp.struts.Voter.VoterRegistrationDAO;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.StaffDetailId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.myapp.struts.utility.Email;

public class ExeltoDatabaseAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    public String institute_id;
    private LoginDAO logindao = new LoginDAO();
     private Login login =new Login();
  private StaffDetail staffd =new StaffDetail();
      private StaffDetailId staffid =new StaffDetailId();
      private final ExecutorService executor=Executors.newFixedThreadPool(1);
   // Email mail;
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
session.removeAttribute("clog");
      institute_id=(String)session.getAttribute("institute_id");

  String election_name="";       VoterRegistration obj = new  VoterRegistration();
        DAO dataaccess = new DAO();


        
       

      
        int row_no = 0;
     
        String table_name = uploadForm.getCombo_table_name();
        int size = DAO.columnname2(table_name).size();
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
         
            map_table = (String[]) DAO.selectedcombo1(map_table, (size), uploadForm);
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
                    CandidateRegistrationId voterid=new CandidateRegistrationId();
                    CandidateRegistration genericobj = new  CandidateRegistration();


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

                                               if(cellvalue.isEmpty()){
                                               request.setAttribute("msg1", "EnrollMent No Cannot Blank , Import Terminates");
                                              log.add("EnrollMent No Cannot Blank for record No"+row_no);session.setAttribute("clog",log);
                                              continue begin;

                                               }

                                               VoterRegistration v=(VoterRegistration)VoterRegistrationDAO.searchVoterRegistration(institute_id, cellvalue.trim());
                                               if(v==null){
                                               request.setAttribute("msg1", "Sorry Candidate EnrollMent is not in a Voter List, cannot impprt");
                                              log.add("Sorry Candidate EnrollMent is not in a Voter List, cannot impprt for record No"+row_no);session.setAttribute("clog",log);
                                              continue begin;


                                               }
                                              // List<CandidateRegistration> o=( List<CandidateRegistration>)CandidateRegistrationDAO.getCandidateDetails(institute_id, cellvalue.trim());
//                                               if(o!=null && o.isEmpty()==false){
  //                                           request.setAttribute("msg1", "EnrollMent No Already Found");
    //                                          log.add("EnrollMent No Already Found for record No"+row_no);session.setAttribute("clog",log);
      //                                        continue begin;
//
//
  //                                             }


                                            AdminRegistration x1=AdminRegistrationDAO.searchVoterRegistration(institute_id, cellvalue.trim().toString());
                                            StaffDetail staff=StaffDetailDAO.searchStaffId(cellvalue.trim().toString(), institute_id);
                                            if(x1!=null || staff!=null){
                                              request.setAttribute("msg1", "EnrollMent No Already Assigned to CEO or EO  , Import Terminates");
                                              log.add("EnrollMent No Already Assigned to Some One Else for record No"+row_no);session.setAttribute("log",log);
                                              continue begin;


                                            }








                                               voterid.setEnrollment(cellvalue.trim());

                                            if(voterid.getEnrollment().isEmpty() || voterid==null){
                                              request.setAttribute("msg1", "EnrollMent No Cannot Blank , Import Terminates");
                                              log.add("EnrollMent No Cannot Blank for record No"+row_no);session.setAttribute("clog",log);
                                              continue begin;



                                            }



                                        }

                                              if (map_table[column_index].equals("p_marks")) {

                                               
                                               genericobj.setPMarks(cellvalue.trim());

                                            



                                        }
                                           if (map_table[column_index].equals("p_attendence")) {

                                                 genericobj.setPAttendence(cellvalue.trim());


                                        }

                                            if (map_table[column_index].equals("backlog")) {

                                              genericobj.setBacklog(cellvalue.trim());



                                        }
                                            if (map_table[column_index].equals("criminal")) {

                                             
                                        genericobj.setCriminal(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("indisc")) {

                                           genericobj.setIndisc(cellvalue.trim());


                                        }
                                          if (map_table[column_index].equals("rejected_reason")) {

                                               
 genericobj.setRejectedReason(cellvalue.trim());


                                        }
 if (map_table[column_index].equals("status1")) {

                                               genericobj.setStatus(cellvalue.trim());
                                               if(genericobj.getStatus().isEmpty()){
                                              request.setAttribute("msg1", "Status Cannot Blank , Import Terminates");
                                              log.add("Status Cannot  Cannot Blank for record No"+row_no);session.setAttribute("clog",log);
                                              continue begin;



                                            }



                                        }
                                          if (map_table[column_index].equals("request_date")) {
                                             // System.out.println(cellvalue.trim()+"////////////////");

                                            genericobj.setRequestDate(cellvalue.trim());
                                               if(genericobj.getRequestDate().isEmpty()){
                                              request.setAttribute("msg1", "Request Date cannot be blank cannot import");
                                              log.add("Request Date cannot be blank cannot import for record No"+row_no);session.setAttribute("clog",log);
                                              continue begin;



                                            }




                                        }

                                         if (map_table[column_index].equals("accepted_date")) {

                                               genericobj.setAcceptedDate(cellvalue.trim());

                                               if(genericobj.getAcceptedDate().isEmpty()){
                                              request.setAttribute("msg1", "Accepted Date cannot be blank cannot import");
                                              log.add("Accepted Date cannot be blank cannot import for record No"+row_no);session.setAttribute("clog",log);
                                              continue begin;



                                            }


                                        }




                                            if (map_table[column_index].equals("election_id")) {
                                   

                                                 election_name=cellvalue.trim();
                                                  if( election_name.isEmpty()){
                                              request.setAttribute("msg1", "Election id Cannot be Blank ,cannot import");
                                              log.add( "Election id Cannot be Blank ,cannot import At Record No ="+row_no);session.setAttribute("clog",log);
                                             continue begin;



                                            }


                                                   Election obj1=null;
                                                if(election_name!=null){

                                                  ElectionDAO dao= new ElectionDAO();
                                                   obj1=dao.searchElection(election_name, institute_id);
                                                }
                                                if(obj1!=null)
                                            voterid.setElectionId(obj1.getId().getElectionId());
// System.out.println("Election Id======"+obj1.getId().getElectionId());
                                              if( obj1==null){
                                              request.setAttribute("msg1", "Election id not Found Cannot Import");
                                              log.add( "Election id not Found Cannot Import At Record No ="+row_no);session.setAttribute("clog",log);
                                             continue begin;



                                            }



                                        }

                                             if (map_table[column_index].equals("position")) {
                                                String position_name=cellvalue.trim();
                                                if(position_name.isEmpty()){
                                              request.setAttribute("msg1", "Position Name Cannot Blank , Import Terminates");
                                              log.add( "PositionName Cannot Blank At Record No ="+row_no);session.setAttribute("clog",log);
                                             continue begin;



                                            }


                                                Position1 pos=null;
                                               //  Election obj1=null;

                                                if(position_name!=null){

                                                  PositionDAO dao= new PositionDAO();
                                                   System.out.println("Position Id"+position_name);
                                                   pos=dao.getPositionByName(position_name, voterid.getElectionId(), institute_id);
                                             //  System.out.println("Pos Id"+pos.getId().getPositionId());
                                                }
                                                 
                                                if(pos!=null)
                                            voterid.setPosition(String.valueOf(pos.getId().getPositionId()));
                                              if(pos==null){
                                              request.setAttribute("msg1", "PositionName not found in the given election");
                                              log.add( "PositionName not found in the given election At Record No ="+row_no);session.setAttribute("clog",log);
                                             continue begin;



                                            }



                                        }
                                            if (map_table[column_index].equals("proposed_by")) {

                                                if(cellvalue.isEmpty()){
                                               request.setAttribute("msg1", "Proposed By Field Cannot Blank , Import Terminates");
                                               log.add("Proposed By Field Cannot Blank at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;



                                            }

                                                Login vo=logindao.getUserId(cellvalue.trim());
                                                 if(vo!=null)
                                                 {
                                                     if(vo.getRole().equalsIgnoreCase("voter"))
                                                     genericobj.setProposedBy(cellvalue.trim());
                                                     else{
                                                      request.setAttribute("msg1", "Proposed By Field cannot found as a voter , Import Terminates");
                                               log.add("Proposed By Field cannot found as a voter at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;
                                                     }
                                                 }
                                               if(genericobj.getProposedBy()==null || genericobj.getProposedBy().isEmpty()){
                                               request.setAttribute("msg1", "Proposed By Field cannot found as a voter, Import Terminates");
                                               log.add("Proposed By Field cannot found as a voter at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;



                                            }


                                        }

                                                if (map_table[column_index].equals("seconded_by")) {

                                                    if(cellvalue.isEmpty()){
                                               request.setAttribute("msg1", "Seconded by Cannot Blank , Import Terminates");
                                               log.add("Seconded by Cannot Blank at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;



                                            }

                                                Login vo=logindao.getUserId(cellvalue.trim());
                                                 if(vo!=null)
                                                 {
                                                     if(vo.getRole().equalsIgnoreCase("voter"))
                                                         genericobj.setSecondedBy(cellvalue.trim());
                                                     else{
                                                      request.setAttribute("msg1", "Seconded by not found Import Terminates");
                                               log.add("Seconded by not found  at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;
                                                     }

                                                 }
                                               if(genericobj.getSecondedBy()==null || genericobj.getSecondedBy().isEmpty()){
                                              request.setAttribute("msg1", "Seconded by not found Import Terminates");
                                               log.add("Seconded by not found  at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;



                                            }

                                                if(genericobj.getProposedBy().equalsIgnoreCase(genericobj.getSecondedBy())){
                                                request.setAttribute("msg1", "Proposed By & Seconded By Person Should not be same");
                                               log.add("Proposed By & Seconded By Person Should not be same CANNOT Import at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;


                    }



                                        }

                                     if (map_table[column_index].equals("position_accepted")) {




                                          if(cellvalue.isEmpty()){
                                               request.setAttribute("msg1", "Position Accepted Cannot Blank , Import Terminates");
                                               log.add("Position Accepted Cannot Blank at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;



                                            }
                                                if(cellvalue.trim().equalsIgnoreCase("Yes") || cellvalue.trim().equalsIgnoreCase("No")){
                                                 genericobj.setPositionAccepted(cellvalue.trim());

                                                }else{
                                                      request.setAttribute("msg1", "Invalid date in Position Accepted Cannot Import");
                                               log.add("Invalid date in Position Accepted Cannot Import at Record No="+row_no);session.setAttribute("clog",log);
                                               continue begin;
                                                }

                                                
                                                     }
                                               
                                        


 

                                

                                         }else{
                                         break;
                                         }
                                    
                                    

                                
                            }
                          //  System.out.println("column index::::::::::::." + column_index);
                            
                          //  System.out.println("This is the values in the table :::1::::::" + map_table[cell.getColumnIndex()] + "::::::::::::::::::::");
                            
                        }
                   
                        //log.add("insert funcion is going to call"+row_no);

                        //////////////
                        //check for duplicate record and terminate it found
                        genericobj.setId(voterid);

 







                   /*   VoterRegistration x=VoterRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());
                      AdminRegistration x1=AdminRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());
                      StaffDetail staff=StaffDetailDAO.searchStaffId(voterid.getEnrollment(), institute_id);
                      if(x!=null && x.getEmail().equalsIgnoreCase(genericobj.getEmail())){
                     //  request.setAttribute("msg1", "Voter with Enrollment No"+x.getId().getEnrollment()+" Already Exist , Import Terminates");
                       log.add("Voter with Enrollment No"+x.getId().getEnrollment()+" Already Exist cannot Import at record no="+row_no);
                       session.setAttribute("log",log);//   return mapping.findForward(SUCCESS);
                      continue begin;
                      }


                      if(x1==null && staff==null){

*/
                        if(genericobj.getPositionAccepted().equalsIgnoreCase("No")){
                        genericobj.setStatus("Rejected");

                        }

                    

                   boolean result=DAO.insertgenericc(genericobj);
                   if(result==false){
                       // request.setAttribute("msg1", "Import data has some problem encourted in row no="+row_no+", Previous data imported Successfully");
                      log.add("Import data has some problem encourted in row no="+row_no+", Previous data imported Successfully");
                     //  return mapping.findForward(SUCCESS);
                    session.setAttribute("clog",log);   continue begin;
                   }

                   //add in final table
                 //  System.out.println("Accept is working");
             CandidateRegistrationDAO voterdao=new CandidateRegistrationDAO();

             // System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+eid);
             Election e = ElectionDAO.searchElection(election_name, institute_id);
//System.out.println(e+"gdfgdfgdfgfd");
          List vr=voterdao.getCandidateDetailsByStatus1(institute_id,e.getId().getElectionId(),voterid.getEnrollment());
         // List ve=voterdao.getEmail(institute_id,genericobj.getId().getEnrollment());

CandidateRegistration ob=new CandidateRegistration();
VoterRegistration ab=new VoterRegistration();
          if(!vr.isEmpty())

          {    ob=(CandidateRegistration)vr.get(0);}

ab=VoterRegistrationDAO.searchVoterRegistration(institute_id, voterid.getEnrollment());



Candidate1 c1 = new Candidate1();
Candidate1Id c1Id = new Candidate1Id();
c1Id.setElectionId(voterid.getElectionId());
c1Id.setInstituteId(voterid.getInstituteId());
c1Id.setPositionId(Integer.valueOf(voterid.getPosition()));
c1.setId(c1Id);
c1.setCandidateName(ab.getVoterName());
c1.setEnrollment(voterid.getEnrollment());
PositionDAO positiondao=new PositionDAO();
Position1 pos = positiondao.searchPosition(Integer.parseInt(voterid.getPosition()));
if(genericobj.getPositionAccepted().equalsIgnoreCase("yes"))
     genericobj.setStatus("REGISTERED");
else
     genericobj.setStatus("Rejected");





CandidateRegistrationDAO.updateCandidature(genericobj,c1);
String path = servlet.getServletContext().getRealPath("/");
request.setAttribute("msg", "Registration Accepted Successfully");
Email mail=new Email(ab.getEmail(),admin_password,"Registration Accepted Successfully from EMS","Dear. "+ c1.getCandidateName() +"\n"+ (ab.getPAddress()!=null?ab.getPAddress():(ab.getCAddress()!=null?ab.getCAddress():"Address"))+" \n Your request of candidature for the post of "+ pos.getPositionName() +" has been accepted.\nWith Regards\nElection Manager\n"+session.getAttribute("institute_name"));

mail.send();
         //executor.submit(new Runnable() {

           //     public void run() {
             //       obj.send();
             //   }
          //  });
            
             //return mapping.findForward(SUCCESS);


          

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
                        request.setAttribute("msg", "Import Process in completed");
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
