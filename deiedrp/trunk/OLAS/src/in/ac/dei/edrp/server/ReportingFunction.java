package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

// Referenced classes of package in.ac.dei.edrp.server:
//            GreetingServiceImpl

public class ReportingFunction
{

    SqlMapClient client;
    Log4JInitServlet logObj;

    public ReportingFunction()
    {
        client = SqlMapManager.getSqlMapClient();
        logObj = new Log4JInitServlet();
    }

    public String getEntity_description(String university_id, String entity_type)
    {
        String result = null;
        try
        {
            CM_entityInfoGetter entitydesc = new CM_entityInfoGetter();
            entitydesc.setUniversity_id(university_id.substring(1, 5));
            entitydesc.setEntity_type(entity_type);
            List li = client.queryForList("getentityDescription", entitydesc);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                CM_entityInfoGetter bid = (CM_entityInfoGetter)iterator.next();
                result = bid.getEntity_description();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting Entity Descrition: ")).append(e.getMessage()).toString());
        }
        return result;
    }

    public String getEntity_Name(String entity_id, String university_id)
    {
        String result = null;
        try
        {
            CM_entityInfoGetter entity = new CM_entityInfoGetter();
            entity.setEntity_id(entity_id);
            entity.setUniversity_id(university_id.substring(1, 5));
            List li = client.queryForList("getReportEntityName", entity);
            //System.out.println((new StringBuilder("sgghega ")).append(li.size()).toString());
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter entityid = (ReportInfoGetter)iterator.next();
                result = entityid.getEntity_name();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting Entity Name: ")).append(e.getMessage()).toString());
        }
        return result;
    }

    public String getProgram_Name(String entity_id, String program_id)
    {
        String result = null;
        try
        {
            ReportInfoGetter program = new ReportInfoGetter();
            program.setOffered_by(entity_id);
            program.setProgram_id(program_id);
            List li = client.queryForList("getProgramName", program);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter programid = (ReportInfoGetter)iterator.next();
                result = programid.getProgram_name();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting Program Name: ")).append(e.getMessage()).toString());
        }
        return result;
    }

    public String getBranch_Name(String university_id, String branch_id)
    {
        String result = null;
        try
        {
            ReportInfoGetter branchId = new ReportInfoGetter();
            branchId.setUniversity_id(university_id.substring(1, 5));
            branchId.setBranch_code(branch_id);
            List li = client.queryForList("getBranchName", branchId);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter bid = (ReportInfoGetter)iterator.next();
                result = bid.getBranch_name();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting Branch Name: ")).append(e.getMessage()).toString());
        }
        return result;
    }

    public String[] getCategoryCos(ReportInfoGetter export)
    {
        String category1[] = (String[])null;
        try
        {
            List li = client.queryForList("getCosCount", export);
            //System.out.println((new StringBuilder("cos=")).append(li.size()).toString());
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter coscount = (ReportInfoGetter)iterator.next();
                category1 = new String[coscount.getCount()];
            }

            int catrslen = 0;
            List li1 = client.queryForList("getCosValue", export);
            Iterator iterator1 = li1.iterator();
            while(iterator1.hasNext())
            {
                ReportInfoGetter cosvalue = (ReportInfoGetter)iterator1.next();
                category1[catrslen] = cosvalue.getCos_value();
                catrslen++;
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting COS Value: ")).append(e.getMessage()).toString());
            //System.out.println(e.getMessage());
        }
        return category1;
    }

    public void updateCalledStudentTest(String name, ReportInfoGetter entityInfo, String flag, String udate[])
    {
        try
        {
            ReportInfoGetter called = entityInfo;
            called.setRegistration_number(name);
            called.setCalled(flag);
            called.setStart_date(udate[0]);
            called.setEnd_date(udate[1]);
            client.update("updatecalledFlag", called);
        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while updating CALLED flag in student_test_number: ")).append(e.getMessage()).toString());
        }
    }

    public String[] getUserDetail(String name, ReportInfoGetter entityInfo)
    {
        String userdetail[] = (String[])null;
        try
        {
            ReportInfoGetter exportuser = entityInfo;
            exportuser.setRegistration_number(name);
            List li = client.queryForList("getRegistrationStudentId", exportuser);
            
            Iterator iterator = li.iterator(); 
            while(iterator.hasNext())
            {
            	ReportInfoGetter sid = (ReportInfoGetter)iterator.next();
            	exportuser.setStudent_id(sid.getStudent_id());
                
            }

            List li1 = client.queryForList("getRegistrationDetails", exportuser);
            String fullname = "";
            String date = "";
            String cat = "";
            String gen = "";
            String city = "";           
            Iterator iterator1 = li1.iterator();
            while( iterator1.hasNext())
            {
                ReportInfoGetter user = (ReportInfoGetter)iterator1.next();
                String middle_name = user.getMiddle_name();
                String last_name = user.getLast_name();
                if(user.getMiddle_name() == null)
                {
                    middle_name = "";
                }
                if(user.getLast_name() == null)
                {
                    last_name = "";
                }
                 fullname = user.getFirst_name()+" "+middle_name+" "+last_name;//Update by devendra 2 june
                date = user.getDate_of_birth();
                cat = getCategorydescription(user.getCategory());
                gen = user.getGender();
                city = user.getCity();              
            }

            userdetail = (new String[] {
                fullname, date, cat, gen, city
            });
        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting User detail: ")).append(e.getMessage()).toString());
        }
        return userdetail;
    }

    public String[] getPersonalDetail(String name, ReportInfoGetter entityInfo)
    {
        String userdetail[] = (String[])null;
        //System.out.println("Coming here to get personal details");
        try
        {
            ReportInfoGetter exportuser = entityInfo;
            exportuser.setRegistration_number(name);            
            List li = client.queryForList("getRegistrationStudentId", exportuser);
            String formNumber = "";
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter sid = (ReportInfoGetter)iterator.next();
                exportuser.setStudent_id(sid.getStudent_id());
                if(sid.getFormNumber() != null)
                {
                    formNumber = sid.getFormNumber();
                }
            }

            String deiStaff = "N";
            boolean b = (new GreetingServiceImpl()).checkStaffWard(name, getSessionDate(exportuser.getUniversity_id()));
            if(b)
            {
                deiStaff = "Y";
            }           
            List li1 = client.queryForList("getPersonalDetails", exportuser);
            String fullname = "";
            String date = "";
            String cat = "";
            String gen = "";
            String city = "";
            String fatherName = "";
            Iterator iterator1 = li1.iterator();
            while(iterator1.hasNext())
            {
                ReportInfoGetter user = (ReportInfoGetter)iterator1.next();
                String middle_name = user.getMiddle_name();
                String last_name = user.getLast_name();
                String fatherFirstName = user.getFatherFirstName();
                String fatherMiddleName = user.getFatherMiddleName();
                String fatherLastName = user.getFatherLastName();
                if(user.getMiddle_name() == null)
                {
                    middle_name = "";
                }
                if(user.getLast_name() == null)
                {
                    last_name = "";
                }
                if(user.getFatherFirstName() == null)
                {
                    fatherFirstName = "";
                }
                if(user.getFatherMiddleName() == null)
                {
                    fatherMiddleName = "";
                }
                if(user.getFatherLastName() == null)
                {
                    fatherLastName = "";
                }
                fullname = user.getFirst_name()+middle_name+last_name;
                date = user.getDate_of_birth();
                cat = getCategorydescription(user.getCategory());
                gen = user.getGender();
                city = user.getCity();
                fatherName = fatherFirstName+fatherMiddleName+fatherLastName;
            }

            userdetail = (new String[] {
                formNumber, fullname, fatherName, date, cat, gen, city, deiStaff
            });
        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting User detail: ")).append(e.getMessage()).toString());
        }
        return userdetail;
    }

    public String getCategorydescription(String category)
    {
        String description = "";
        try
        {
            ReportInfoGetter desc = new ReportInfoGetter();
            desc.setCategory(category);
            List li = client.queryForList("getCategoryDescription", desc);
            Iterator iterator = li.iterator(); 
            while(iterator.hasNext())
            {
                ReportInfoGetter catdesc = (ReportInfoGetter)iterator.next();
                description = catdesc.getDescription();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting cetegory descrition for : ")).append(category).append(", ").append(e.getMessage()).toString());
        }
        return description;
    }

    public String[][] getFullyComputedmarks(String name, ReportInfoGetter entityInfo, String udate[])
    {
        String s[][] = (String[][])null;
        try
        {
            ReportInfoGetter getFullCompute = entityInfo;
            getFullCompute.setRegistration_number(name);
            getFullCompute.setFlag("S");
            getFullCompute.setStart_date(udate[0]);
            getFullCompute.setEnd_date(udate[1]);
            List li = client.queryForList("getExcelComponentCount", getFullCompute);
            int length = 0;
            Iterator iterator = li.iterator(); 
            while(iterator.hasNext())
            {
                ReportInfoGetter excelcomp = (ReportInfoGetter)iterator.next();
                length = excelcomp.getCount();
            }

            List li1 = client.queryForList("getExcelComponentMarksValue", getFullCompute);
            s = new String[length][4];
            int i = 0;
            Iterator iterator1 = li1.iterator();
            while( iterator1.hasNext())
            {
                ReportInfoGetter excelcomp = (ReportInfoGetter)iterator1.next();
                s[i][0] = getComponentDescription(excelcomp.getComponent_id(), name, entityInfo);
                s[i][1] = getBoardDescription(String.valueOf(excelcomp.getBoard_id()));
                if(String.valueOf(excelcomp.getMarks_percentage()).equalsIgnoreCase(null))
                {
                    s[i][2] = String.valueOf(0.0D);
                } else
                {
                    s[i][2] = String.valueOf(excelcomp.getMarks_percentage());
                }
                s[i][3] = String.valueOf(excelcomp.getComputed_Marks());
                i++;
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting fully computed marks for : ")).append(name).append(", ").append(e.getMessage()).toString());
        }
        return s;
    }

    public String[][] getFullyExcelComputedmarks(String name, ReportInfoGetter entityInfo, String udate[])
    {
        //System.out.println("Coming inside fully computed marks");
        String s[][] = (String[][])null;
        try
        {
            ReportInfoGetter getFullCompute = entityInfo;
            getFullCompute.setRegistration_number(name);
            getFullCompute.setFlag("S");
            getFullCompute.setStart_date(udate[0]);
            getFullCompute.setEnd_date(udate[1]);
            List li = client.queryForList("getExcelComponentCount", getFullCompute);
            int length = 0;
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter excelcomp = (ReportInfoGetter)iterator.next();
                length = excelcomp.getCount();
            }
            
            List li1 = client.queryForList("getFullExcelComponentMarksValue", getFullCompute);
            s = new String[length][9];
            int i = 0;
            Iterator iterator1 = li1.iterator(); 
            while(iterator1.hasNext())
            {
                ReportInfoGetter excelcomp = (ReportInfoGetter)iterator1.next();                
                s[i][0] = getComponentDescription(excelcomp.getComponent_id(), name, entityInfo);
                s[i][1] = getBoardDescription(String.valueOf(excelcomp.getBoard_id()));
                if(String.valueOf(excelcomp.getMarks_percentage()).equalsIgnoreCase(null))
                {
                    s[i][2] = String.valueOf(0.0D);
                    s[i][3] = String.valueOf(0.0D);
                    s[i][4] = String.valueOf(0.0D);
                } else
                {
                    s[i][2] = String.valueOf(excelcomp.getMarksObtained());
                    s[i][3] = String.valueOf(excelcomp.getTotal_marks());
                    s[i][4] = String.valueOf(excelcomp.getMarks_percentage());
                }
                s[i][5] = String.valueOf(excelcomp.getActual_computed_Marks());
                s[i][6] = String.valueOf(checkDEIStudent(name, excelcomp.getComponent_id(), udate));
                DecimalFormat df=new DecimalFormat("#.##");                
                s[i][7] = String.valueOf(df.format(excelcomp.getComputed_Marks() - excelcomp.getActual_computed_Marks()));
                s[i][8] = String.valueOf(excelcomp.getComputed_Marks());
                i++;
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting fully computed marks for : ")).append(name).append(", ").append(e.getMessage()).toString());
        }
        return s;
    }

    public String checkDEIStudent(String regnum, String componentId, String udate[])
    {       
        String deiStudent = "N";
        try
        {
            ReportInfoGetter check = new ReportInfoGetter();
            check.setRegistration_number(regnum);
            check.setStart_date(udate[0]);
            check.setEnd_date(udate[1]);
            List checkStaff = client.queryForList("getCheckStaff", check);          
            Iterator iterator = checkStaff.iterator(); 
            while(iterator.hasNext())
            {
                ReportInfoGetter getStaff = (ReportInfoGetter)iterator.next();
                if(getStaff.getWeightage_id().equalsIgnoreCase(componentId))
                {                   
                    deiStudent = "Y";
                    break;
                }
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while checking DEI Staff for ")).append(regnum).toString());
            logObj.logger.info((new StringBuilder("Exception is :")).append(e.getMessage()).toString());
        }
        return deiStudent;
    }

    public String getBoardDescription(String board_id)
    {
        String boardName = "None";
        try
        {
            ReportInfoGetter ceigboard = new ReportInfoGetter();
            ceigboard.setBoard_id(board_id);
            List board = client.queryForList("getBoardDescription", ceigboard);
            Iterator iterator = board.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter boarddescription = (ReportInfoGetter)iterator.next();
                boardName = boarddescription.getDescription();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting board description : ")).append(e.getMessage()).toString());
        }
        return boardName;
    }

    public String getComponentDescription(String component_id, String regnum, ReportInfoGetter entityInfo)
    {
        String description = null;
        try
        {
            ReportInfoGetter desc = entityInfo;
            desc.setComponent_id(component_id);
            List li;
			li = client.queryForList("getComponentDescription", desc);
            /*if(isUGorPGComponent(component_id))
            {
                desc.setRegistration_number(regnum);
                li = client.queryForList("getUGComponentDescription", desc);
            } else
            {
                li = client.queryForList("getComponentDescription", desc);
            }*/
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter descr = (ReportInfoGetter)iterator.next();
                description = descr.getComponent_description();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting Component Descrition for : ")).append(component_id).append(", registration").append(regnum).toString());
            logObj.logger.info((new StringBuilder("Exception is:  ")).append(e.getMessage()).toString());
        }
        return description;
    }

    public boolean isUGorPGComponent(String component_id)
    {
        boolean b = false;
        try
        {
            ReportInfoGetter ug = new ReportInfoGetter();
            ug.setComponent_id(component_id);
            List li = client.queryForList("getugorpg", ug);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter ugorpg = (ReportInfoGetter)iterator.next();
                if(ugorpg.getComponent_description().equalsIgnoreCase("UG") || ugorpg.getComponent_description().equalsIgnoreCase("PG"))
                {
                    b = true;
                }
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting Component Descrition for : ")).append(component_id).toString());
        }
        return b;
    }

    public boolean isInteger(String value)
    {
        boolean b = false;
        if(Integer.parseInt(value) >= 0 && Integer.parseInt(value) <= 9)
        {
            b = true;
        }
        return b;
    }

    //updated by devendra May 3rd
    public String isUpdated(String entity_id, String program_id, String udate[])
    {
        String comp = "N";       
        try
        {
            ReportInfoGetter update = new ReportInfoGetter();
            update.setProgram_id(program_id);
            update.setEntity_id(entity_id);           
            update.setStart_date(udate[0]);
            update.setEnd_date(udate[1]);        
            List li = client.queryForList("getResetFlag", update);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter updateflag = (ReportInfoGetter)iterator.next();               
                if(!updateflag.getFlag_status().equals(null))
                {
                    comp = updateflag.getFlag_status();               
                }
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting flag status for  : ")).append(program_id).append(", ").append(entity_id).append(", ").toString());
        }
        return comp;
    }

    public void updateControlReport(ReportInfoGetter entityInfo, String flag, String comp)
    {
        ReportInfoGetter updateControl = entityInfo;
        System.out.println("inside updateControlReport method update flag is "+flag);
        updateControl.setFlag(flag);
        try
        {
            if(!comp.equalsIgnoreCase("N"))
            {                
                updateControl.setFlag_status(flag);
                client.update("updateControlReport", updateControl);
            } else
            {               
                updateControl.setFlag_status(flag);
                updateControl.setUser_id("Deepak Pandey");
                client.insert("insertControlReport", updateControl);
            }
        }
        catch(Exception e)
        {
            logObj.logger.info("Error while inserting or updating flag for selected values  : ");            
        }
    }

    public String[] getSessionDate(String university_id)
    {
        String date[] = new String[2];
        try
        {
            ReportInfoGetter cdate = new ReportInfoGetter();
            cdate.setUniversity_id(university_id.substring(1, 5));
            List li = client.queryForList("getuniversitydate", cdate);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter udate = (ReportInfoGetter)iterator.next();
                date[0] = udate.getUniversity_start_date();
                date[1] = udate.getUniversity_end_date();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting sesion start date and end date for selected university : ")).append(university_id).toString());
        }
        return date;
    }

    public double getComponetEligibleMarks(String component_id, String registration_number, ReportInfoGetter entityInfo)
    {
        double marks = 0.0D;
        try
        {
            ReportInfoGetter comp_marks = entityInfo;
            comp_marks.setComponent_id(component_id);
            comp_marks.setCategory(getCategoryId(registration_number, entityInfo));
            List li = client.queryForList("getEligible", comp_marks);
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter el_marks = (ReportInfoGetter)iterator.next();
                marks = el_marks.getComponent_eligiblity();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting eligible for component marks for ")).append(registration_number).toString());
        }
        return marks;
    }

    public String getCategoryId(String registrationNumber, ReportInfoGetter entityInfo)
    {
        String s = null;
        try
        {
            ReportInfoGetter getcategory = entityInfo;
            getcategory.setRegistration_number(registrationNumber);
            List getLi = client.queryForList("getCategoryList", getcategory);
            Iterator iterator = getLi.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter getE = (ReportInfoGetter)iterator.next();
                s = getE.getCos_value().substring(0, 2);
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting category id: ")).append(registrationNumber).toString());
        }
        return s;
    }

    public int getTotalComponentLength(ReportInfoGetter meritStudent)
    {
        int length = 0;
        try
        {
            ReportInfoGetter ceigTotalComponent = meritStudent;
            ceigTotalComponent.setFlag("S");
            ceigTotalComponent.setWeightage_flag("Y");
            List totalList = client.queryForList("getTotalCompLength", ceigTotalComponent);
            Iterator iterator = totalList.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter total = (ReportInfoGetter)iterator.next();
                length = total.getCount();
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting category id: ")).append(e.getMessage()).toString());
        }
        return length;
    }

    public String[] getCategoryForProgram(ReportInfoGetter export)
    {
        String category1[] = (String[])null;
        try
        {
            List li = client.queryForList("getCosCountForProgram", export);           
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter coscount = (ReportInfoGetter)iterator.next();
                category1 = new String[coscount.getCount()];
            }

            int catrslen = 0;
            List li1 = client.queryForList("getCosValueForProgram", export);
            Iterator iterator1 = li1.iterator();
            while( iterator1.hasNext())
            {
                ReportInfoGetter cosvalue = (ReportInfoGetter)iterator1.next();
                category1[catrslen] = cosvalue.getCos_value();
                catrslen++;
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting COS Value: ")).append(e.getMessage()).toString());            
        }
        return category1;
    }

    /**
     * To get paper Codes
     * @param name
     * @param export
     * @return
     */
	public String[] getPaperCode(String name, ReportInfoGetter export) {
		// TODO Auto-generated method stub
		String paperCode[] = (String[])null;
        try
        {
        	export.setRegistration_number(name);
            List li = client.queryForList("getPaperCodes", export);
           
           
                paperCode = new String[li.size()];
        

            int catrslen = 0;
            List li1 = client.queryForList("getPaperCodes", export);
            Iterator iterator1 = li1.iterator();
            while(iterator1.hasNext())
            {
                ReportInfoGetter paper = (ReportInfoGetter)iterator1.next();
                paperCode[catrslen] = paper.getValue();
                catrslen++;
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting COS Value: ")).append(e.getMessage()).toString());           
        }
        return paperCode;
	}
	
	public String[] getCategoryForAllSubject(ReportInfoGetter export)
    {
        String category1[] = (String[])null;
        try
        {
            List li = client.queryForList("getCosCountForProgram", export);           
            Iterator iterator = li.iterator();
            while( iterator.hasNext())
            {
                ReportInfoGetter coscount = (ReportInfoGetter)iterator.next();
                category1 = new String[coscount.getCount()];
            }

            int catrslen = 0;
            List li1 = client.queryForList("getCosValueForAllSubject", export);
            Iterator iterator1 = li1.iterator();
            while( iterator1.hasNext())
            {
                ReportInfoGetter cosvalue = (ReportInfoGetter)iterator1.next();
                category1[catrslen] = cosvalue.getCos_value();
                catrslen++;
            }

        }
        catch(Exception e)
        {
            logObj.logger.info((new StringBuilder("Error while getting COS Value: ")).append(e.getMessage()).toString());            
        }
        return category1;
    }

}
