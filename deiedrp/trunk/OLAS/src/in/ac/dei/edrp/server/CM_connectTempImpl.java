package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_CourseInfoGetter;
import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_instituteInfoGetter;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.w3c.dom.NodeList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("serial")
public class CM_connectTempImpl extends RemoteServiceServlet
    implements CM_connectTemp {
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();
 
      public String[][] getStateData(){
    	  String[][] data=null;
    	  String path= this.getServletContext().getRealPath("stateCity.xml");
    	  File fXmlFile = new File(path);
          if(fXmlFile.exists()){
          	javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
      		javax.xml.parsers.DocumentBuilder dBuilder;
  			try {  				
  				dBuilder = dbFactory.newDocumentBuilder();  				
  				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
  				doc.getDocumentElement().normalize();  				
  	    		org.w3c.dom.NodeList stateList = doc.getElementsByTagName("state");  	    		
  	    			data=new String[stateList.getLength()][2];
  	  	    		System.out.println("state-----------------------"+stateList.getLength());
  	  	    		for(int i=0;i<stateList.getLength();i++){
  	  	    			org.w3c.dom.Node nNode = stateList.item(i);
  	  	    			org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
  	  	    			data[i][0]="st";
  	  	    			data[i][1]=element.getAttribute("name");  	  	    			
  	  	    			
  	  	    		}
  			} catch (Exception e) {  			
  				logObj.logger.error("Error inside getCityData:in CM_connectTempImpl file ::"+e.getMessage());
  			}
          }
          else{        	  
        	  data[0][0]="FileNotFound";
        	  return data; 
          }       
  		return data;
      }
      
      public String[][]getCityData(String state){
    	  String[][] data=null;
     	 String path= this.getServletContext().getRealPath("stateCity.xml"); 
     	  File fXmlFile = new File(path);
           if(fXmlFile.exists()){
           	javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
       		javax.xml.parsers.DocumentBuilder dBuilder;
   			try {  				
   				dBuilder = dbFactory.newDocumentBuilder();  				
   				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
   				doc.getDocumentElement().normalize();  				
   	    		org.w3c.dom.NodeList stateList = doc.getElementsByTagName("state");   	    		   	    			
   	  	    		System.out.println("Total states -----------------------"+stateList.getLength());
   	  	    		for(int i=0;i<stateList.getLength();i++){
   	  	    			org.w3c.dom.Node nNode = stateList.item(i);
   	  	    			org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
   	  	    			if(state.equals(element.getAttribute("name"))){   	  	    				
   	  	    				NodeList cityNodeList = element.getElementsByTagName("city");
   	  	    				System.out.println("Total cities -----------------------"+cityNodeList.getLength());
   	  	    				data=new String[cityNodeList.getLength()][2];
   	  	    				for(int j=0;j<cityNodeList.getLength();j++){
   	  	    				org.w3c.dom.Node childNode = cityNodeList.item(j);
   	   	  	    			org.w3c.dom.Element childElement=(org.w3c.dom.Element) childNode;
   	   	  	    				data[j][0]=childElement.getAttribute("code");
   	   	  	    				data[j][1]=childElement.getTextContent();    	   	  	    			   	   	  	    				
   	  	    				}
   	  	    			}   	  	    			   	  	    			   	  	    			
   	  	    			
   	  	    		}
   			} catch (Exception e) {   			
   				logObj.logger.error("Error inside getCityData:in CM_connectTempImpl file ::"+e.getMessage());
   			}
           }
           else{        	  
         	  data[0][0]="FileNotFound";
         	  return data; 
           }          
   		return data;
    	  
      }
    public String Entity_Course(String univ, String courseID, String course,
        boolean status) {
        @SuppressWarnings("unused")
        Object li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourseCode(courseID);
            CMDG.setcourse_name(course);
            CMDG.setStatus(status);

            li = client.insert("insertCourse", CMDG);
        } catch (SQLException sqle) {
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] getCourseCode(String univ, boolean status) {
        List li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setStatus(status);

            li = client.queryForList("Getcoursecode", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] getCourseName(String univ, String courseID) {
        List li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourseCode(courseID);
            CMDG.setStatus(true);

            li = client.queryForList("courseName", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    public String deleteCourse(String univ, String courseID) {
        @SuppressWarnings("unused")
        Object li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourseCode(courseID);
            CMDG.setStatus(false);

            li = client.delete("deleteCourse", CMDG);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    public String CourseType(String univ, String coursetypeId,
        String course_type_name) {
        @SuppressWarnings("unused")
        Object li;

        //Begin The Try-Catch Loop		
        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourse_Type(coursetypeId);
            CMDG.setcourse_type_name(course_type_name);
            CMDG.setStatus(true);
            li = client.insert("insertCourseType", CMDG);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] getCourseType(String univ) {
        List li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setStatus(true);

            li = client.queryForList("GetcourseType", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] getCourseTypeName(String univ,
        String coursetypeID) {
        List li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourse_Type(coursetypeID);
            CMDG.setStatus(true);

            li = client.queryForList("courseTypeName", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    public String deleteCourseType(String univ, String coursetypeID) {
        @SuppressWarnings("unused")
        Object li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourse_Type(coursetypeID);
            CMDG.setStatus(false);

            li = client.delete("deleteCourseType", CMDG);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] CourseTypeName(String univ) {
        List li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setStatus(true);

            li = client.queryForList("GetcourseTypeName", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getProgramme() {
        List li;

        try {
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            li = client.queryForList("Getprogram_name", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getProgrammeOff(String entity, int counter)
        throws Exception {
        List li;

        try {
            logObj.logger.info("in prog off");
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            if (counter == 0) {
                CMDG.setEntity_name(entity + "%");

                li = client.queryForList("GetprogramOff", CMDG);

                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
            } else if (counter == 1) {
                CMDG.setEntity_name(entity);
     

                li = client.queryForList("GetprogramOffered", CMDG);

                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
            }else if (counter == 2){
            	
            	CMDG.setEntity_id(entity.substring(1,9));
            	
            	li = client.queryForList("GetprogramOfferedonManage", CMDG);

                return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
            }
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] CourseName(String univ) {
        List li;

        try {
            logObj.logger.info("in coursename");

            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setStatus(true);

            li = client.queryForList("GetNameofCourse", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        //Return the Value of s in Function	
        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] getCourseCode(String univ, String courseName) {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in course code");

            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourse_name(courseName);

            li = client.queryForList("GetcourseName", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        //Return the Value of s in Function	
        return null;
    }

    @SuppressWarnings("unchecked")
    public boolean checkCourseCode(String univ, String CourseId) {
        boolean flag = false;
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in course code");

            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setStatus(true);

            li = client.queryForList("checkcourse", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_CourseInfoGetter) li.get(i);

                if (CMDG.getcourseCode().equals(CourseId)) {
                    flag = true;
                }
            }

            return flag;
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return flag;
    }

    @SuppressWarnings("unchecked")
    public boolean checkCourseCodeType(String univ, String CourseTypeId) {
        boolean flag = false;
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in coursecode type");

            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setStatus(true);

            li = client.queryForList("checkcourseType", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_CourseInfoGetter) li.get(i);

                if (CMDG.getCourse_Type().equals(CourseTypeId)) {
                    flag = true;
                }
            }

            return flag;
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            System.out.println(ex);
        }

        return flag;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getProgramId(String progName)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in get programid");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_name(progName);

            li = client.queryForList("Getprogram_id", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getProgramOffId() throws Exception {
        List li;

        try {
            logObj.logger.info("in prog offrd id");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            li = client.queryForList("Getprogram_offid", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    public String programCourse(String[] prog) {
        @SuppressWarnings("unused")
        Object li;

        try {
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setUniversity_code("DEI");
            CMDG.setProgram_id(prog[0]);
            CMDG.setEntity_program_TermID(prog[1]);
            CMDG.setCourse_code(prog[2]);
            CMDG.setFlag(prog[3]);
            CMDG.setAvailable(prog[4]);
            CMDG.setCourse_type(prog[6]);
            CMDG.setCredits(prog[7]);
            CMDG.setLectures(prog[8]);
            CMDG.setTutorials(prog[9]);
            CMDG.setPracticals(prog[10]);

            CMDG.setUnits(prog[11]);

            CMDG.setStatus(true);
            li = client.insert("insertProg", CMDG);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_CourseInfoGetter[] TypeID(String univ, String coursetypeName) {
        List li;

        try {
            CM_CourseInfoGetter CMDG = new CM_CourseInfoGetter();

            CMDG.setuniversity_code(univ);
            CMDG.setcourse_type_name(coursetypeName);
            CMDG.setStatus(true);

            li = client.queryForList("GetTypeId", CMDG);

            return (CM_CourseInfoGetter[]) li.toArray(new CM_CourseInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] programDetails(String[] progdetails) {
        List li;

        //Begin The Try-Catch Loop		
        try {
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_id(progdetails[0]);
            CMDG.setEntity_program_TermID(progdetails[1]);
            CMDG.setStatus(true);

            li = client.queryForList("selectProgDetails", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    public String editProg(String[] editProg) {
        try {
            String Update = "";

            @SuppressWarnings("unused")
            Object li;

            //Begin The Try-Catch Loop
            try {
                CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

                CMDG.setProgram_id(editProg[0]);
                CMDG.setCourse_code(editProg[1]);
                CMDG.setCredits(editProg[2]);
                CMDG.setLectures(editProg[3]);
                CMDG.setTutorials(editProg[4]);
                CMDG.setPracticals(editProg[5]);
                CMDG.setUnits(editProg[6]);
                CMDG.setAvailable(editProg[7]);

                li = client.update("editProg", CMDG);
            } catch (SQLException sqle) {
                logObj.logger.error(sqle);
            } catch (Exception ex) {
                logObj.logger.error(ex);
            }

            return Update;
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    public String disableProg(String courseCode) {
        String Update = "";

        @SuppressWarnings("unused")
        Object li;

        try {
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setCourse_code(courseCode);
            CMDG.setStatus(false);

            li = client.update("deleteProgAgeLimit", CMDG);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return Update;
    }

    @SuppressWarnings("unchecked")
    public CM_instituteInfoGetter[] methodgetDesignation() {
        List li;

        //Begin The Try-Catch Loop		
        try {
            CM_instituteInfoGetter CSIG = new CM_instituteInfoGetter();

            li = client.queryForList("getDesignation", CSIG);

            return (CM_instituteInfoGetter[]) li.toArray(new CM_instituteInfoGetter[li.size()]);
        } catch (SQLException sqle) {
            logObj.logger.error(sqle);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_entityInfoGetter[] Entity_Name(String univID, String Type)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in EntityName");

            CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
            CSIG.setUniversity_id(univID.substring(1, 5));
            CSIG.setEntity_type(Type);

            li = client.queryForList("getEntityName", CSIG);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    public boolean checkProgrammeCourse(String univ, String ProgrmId,
        String CourseId, String sem) {
        boolean flag = false;
        List li;

        //Begin The Try-Catch Loop		
        try {
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setUniversity_code(univ);
            CMDG.setProgram_id(ProgrmId);
            CMDG.setEntity_program_TermID(sem);
            CMDG.setStatus(true);

            li = client.queryForList("checkProgram", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_ProgramInfoGetter) li.get(i);

                if (CMDG.getCourse_code().equals(CourseId)) {
                    flag = true;
                }
            }

            return flag;
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return flag;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_entityInfoGetter[] Entity_Description(String univID)
        throws Exception {
        try {
            List li;
            int count = 0;
            logObj.logger.info("in EntityDesc");

            CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
            CSIG.setUniversity_id(univID.substring(1, 5));

            li = client.queryForList("getEntityDesc", CSIG);

            count = li.size();

            CM_entityInfoGetter[] CMDG = new CM_entityInfoGetter[count];

            for (int i = 0; i < count; i++) {
                CMDG[i] = (CM_entityInfoGetter) li.get(i);
            }

            return CMDG;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_ProgramInfoGetter[] papercodeGroupCount(String progID)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in papercodeGroupCount");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_id(progID);

            li = client.queryForList("GetGrouping", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_ProgramInfoGetter[] DistinctPaperGroupingCount(String progId,
        String group) throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in DistinctPaperGroupingCount");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_id(progId);
            CMDG.setGrouping(group);

            li = client.queryForList("getPaperCode", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public String[][] getProgramComponents(String programID, String entityId) throws Exception {
        List li;
        int count = 0;

        String[][] coa = null;

        try {
            logObj.logger.info("in getProgramComponents");
            CM_ProgramInfoGetter CMDG2 = new CM_ProgramInfoGetter();
            CMDG2.setProgram_id(programID);
            CMDG2.setEntity_id(entityId);

            /**
             * query updated
             */
            li = client.queryForList("GetPCcount", CMDG2);
            count = li.size();

            CM_ProgramInfoGetter CMDG3 = new CM_ProgramInfoGetter();
            CMDG3.setProgram_id(programID);

            /**
             * query updated
             */
            List liPC = client.queryForList("GetProgComponents", CMDG3);

            coa = new String[count][7];

            for (int i = 0; i < liPC.size(); i++) {
                CMDG3 = (CM_ProgramInfoGetter) liPC.get(i);

                String component_id = CMDG3.getComponent_id();

                CMDG2.setComponent_id(component_id);

                /**
                 * query unchanged
                 */
                li = client.queryForList("GetComponent_name", CMDG2);

                CMDG2 = (CM_ProgramInfoGetter) li.get(0);

                coa[i][0] = CMDG2.getComponent();

                coa[i][1] = CMDG3.getType();

                coa[i][2] = CMDG3.getSequence();

                coa[i][3] = CMDG3.getBoard_flag();

                coa[i][4] = CMDG3.getUGorPG();

                coa[i][5] = component_id;

                coa[i][6] = CMDG3.getWeightage_flag();
            }

            return coa;
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_ProgramInfoGetter[] FirstDegreeCode(String progID, String Type)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in firstdegree");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_id(progID);
            CMDG.setUGorPG(Type);

            li = client.queryForList("getFirstDegreeCode", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    public String PersonalInfo(String[] student, String creatorId)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in PersonalInfo");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setFirst_name(student[0]);
            CMDG.setMiddle_name(student[1]);
            CMDG.setLast_name(student[2]);
            CMDG.setGender(student[3]);
            CMDG.setCategory(student[4]);
            CMDG.setDate_of_birth(student[5]);
            CMDG.setFather_Fname(student[6]);
            CMDG.setFather_Mname(student[7]);
            CMDG.setFather_Lname(student[8]);
            CMDG.setMother_Fname(student[9]);
            CMDG.setMother_Mname(student[10]);
            CMDG.setMother_Lname(student[11]);
            CMDG.setInsert_time(date);
            CMDG.setUser_id(student[14]);
            CMDG.setCreator_id(creatorId);
            CMDG.setUniversity_code(creatorId.substring(1, 5));

            /**
             * query updated
             */
            li = client.insert("insertPersonalInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter getEnrolledStudentPersonalInfo(
        String enrollNumber) throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getEnrolledStudentPersonalInfo");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();
            CMDG.setEnrollment_number(enrollNumber);

            /**
             * query updated
             */
            li = client.queryForList("getEnrolledStudentPersonalInfo", CMDG);

            if (li.size() == 0) {
                CMDG = null;
            } else {
                CMDG = (CM_StudentInfoGetter) li.get(0);
            }

            return CMDG;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    public String addressInfo(String[] address, String creatorId)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in address info");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            CM_entityInfoGetter CMDG = new CM_entityInfoGetter();

            CMDG.setEntity_id(address[0]);
            CMDG.setAddress_line1(address[1]);
            CMDG.setAddress_line2(address[5]);
            CMDG.setCity(address[2]);
            CMDG.setState(address[3]);
            CMDG.setPincode(address[4]);
            CMDG.setCreator_id(creatorId);
            CMDG.setInsert_time(date);

            /**
             * query updated
             */
            li = client.insert("insertaddressInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public String paperCodeInfo(String ProgId, String RegNo, String FormNo,
        String PaperCode, String entity_id, String creatorId, String grouping)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;
        List li3;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in papercodeInfo");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();
            CM_entityInfoGetter CMEG = new CM_entityInfoGetter();

            CMDG.setProgramId(ProgId);
            CMDG.setForm_number(FormNo);
            CMDG.setRegistrationNumber(RegNo);
            CMDG.setPapercode(PaperCode);

            CMEG.setEntity_id(entity_id);
            CMEG.setUniversity_id(entity_id.substring(0, 4));

            /**
             * query updated
             */
            li3 = client.queryForList("Getsessiondate", CMEG);

            CMEG = (CM_entityInfoGetter) li3.get(0);

            String Start_date = CMEG.getStartdate();
            String end_date = CMEG.getEnddate();

            CMDG.setStartdate(Start_date);
            CMDG.setEnddate(end_date);
            CMDG.setInsert_time(date);
            CMDG.setCreator_id(creatorId);
            CMDG.setGrouping(grouping);

            /**
             * query updated
             */
            li = client.insert("insertPaperCodeInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public String StudentcallListInfo(String entity_id, String ProgId,
        String branchID, String specializationId, String RegNo, String[] data,
        String creatorId) throws Exception {
        List li3;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in studentcalllist");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_entityInfoGetter CMEG = new CM_entityInfoGetter();

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            for (int j = 1; j < 5; j++) {
                if ((Float.parseFloat(data[j]) > 0) == false) {
                    data[j] = null;
                }
            }

            CMDG.setEntity_id(entity_id);
            CMDG.setProgramId(ProgId);
            CMDG.setBranch(branchID);
            CMDG.setRegistrationNumber(RegNo);
            CMDG.setComponentID(data[0]);
            CMDG.setMarksPercentage(data[1]);
            CMDG.setMarksObtained(data[2]);
            CMDG.setTotalMarks(data[3]);
            CMDG.setScore(data[4]);
            CMDG.setBoard_id(data[5]);
            CMEG.setEntity_id(entity_id);
            CMEG.setUniversity_id(entity_id.substring(0, 4));

            /**
             * query updated
             */
            li3 = client.queryForList("Getsessiondate", CMEG);

            CMEG = (CM_entityInfoGetter) li3.get(0);

            String Start_date = CMEG.getStartdate();
            String end_date = CMEG.getEnddate();

            CMDG.setStartdate(Start_date);
            CMDG.setEnddate(end_date);
            CMDG.setInsert_time(date);
            CMDG.setCreator_id(creatorId);
            CMDG.setSpecialization_id(specializationId);

            /**
             * query updated
             */
            client.insert("insertstudentCallListInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method unchanged
     */
    @SuppressWarnings({"unchecked"
    })
    public String studentSpWt(String progID, String RegNo, String WtId,
        String entity_id, String creatorid_modifierid)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;
        @SuppressWarnings("unused")
        Object li2;
        List li3;
        List li4;
        CM_entityInfoGetter CMEG = new CM_entityInfoGetter();

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in studentspwt");

            String insert_time = null;
            String modification_time = null;
            String creator = null;
            String modifier = null;

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CM_StudentInfoGetter CMSG = new CM_StudentInfoGetter();

            CMDG.setUniversity_code(creatorid_modifierid.substring(1, 5));
            CMDG.setProgramId(progID);
            CMDG.setRegistrationNumber(RegNo);
            CMDG.setWeightageID(WtId);

            CMEG.setEntity_id(entity_id);
            CMEG.setUniversity_id(entity_id.substring(0, 4));

            /**
             * query updated
             */
            li3 = client.queryForList("Getsessiondate", CMEG);

            CMEG = (CM_entityInfoGetter) li3.get(0);

            String Start_date = CMEG.getStartdate();
            String end_date = CMEG.getEnddate();

            CMDG.setStartdate(Start_date);
            CMDG.setEnddate(end_date);

            CMSG.setRegistrationNumber(RegNo);
            CMSG.setWeightageID(WtId);

            /**
             * query unchanged
             */
            li4 = client.queryForList("GetInsertCreator", CMSG);

            if (li4.size() > 0) {
                CM_StudentInfoGetter CMSG1 = new CM_StudentInfoGetter();
                CMSG1 = (CM_StudentInfoGetter) li4.get(0);

                insert_time = CMSG1.getInsert_time();
                creator = CMSG1.getCreator_id();
                modification_time = date;
                modifier = creatorid_modifierid;

                CMSG.setRegistrationNumber(RegNo);
                CMSG.setWeightageID(WtId);

                /**
                 * query unchanged
                 */
                li2 = client.delete("deleteStudentSpWt", CMSG);
            } else {
                insert_time = date;
                creator = creatorid_modifierid;
                modification_time = null;
                modifier = null;
            }

            CMDG.setInsert_time(insert_time);
            CMDG.setCreator_id(creator);
            CMDG.setModification_time(modification_time);
            CMDG.setModifier_id(modifier);

            /**
             * query unchanged
             */
            li = client.insert("insertstudentSpWtInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getBranch(String progId, String entity_id)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getBranch");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            String univ_id = progId.substring(0, 4);

            CMDG.setProgram_id(progId);
            CMDG.setUniversity_id(univ_id);

            li = client.queryForList("GetBranch", CMDG);            
            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getBranchID(String progId, String branchName)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getBranchID");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_id(progId);
            CMDG.setBranch_name(branchName);

            li = client.queryForList("GetBranchID", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter[] getStudentPersonalInfo(String RegNo)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getStudentPersonalInfo");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);

            /**
             * query unchanged
             */
            li = client.queryForList("GetStudentPersonalInfo", CMDG);

            return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public CM_entityInfoGetter[] getStudentAddressInfo(String StudId)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getStudentAddressInfo");

            CM_entityInfoGetter CMDG = new CM_entityInfoGetter();

            CMDG.setUser_id(StudId);

            /**
             * query unchanged
             */
            li = client.queryForList("GetStudentAddressInfo", CMDG);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    @SuppressWarnings({"unchecked"
    })
    public CM_entityInfoGetter[] getEnrolledStudentAddressInfo(String studId)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getStudentAddressInfo");

            CM_entityInfoGetter CMDG = new CM_entityInfoGetter();

            CMDG.setUser_id(studId);

            /**
             * query updated
             */
            li = client.queryForList("GetEnrolledStudentAddressInfo", CMDG);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter[] getStudentPaperInfo(String RegNo,
        String ProgId) throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getStudentPaperInfo");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);

            /**
             * query unchanged
             */
            li = client.queryForList("GetStudentPaperInfo", CMDG);

            return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_StudentInfoGetter[] getStudentSpWt(String RegNo, String ProgId)
        throws Exception {
        List li;

        try {
            logObj.logger.info("getStudentSpWt");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);
            CMDG.setProgramId(ProgId);

            /**
             * query unchanged
             */
            li = client.queryForList("GetStudentSpWt", CMDG);

            return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_StudentInfoGetter[] getStudentCallListInfo(String RegNo)
        throws Exception {
        List li;

        try {
            logObj.logger.info("getStudentCallListInfo");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);

            /**
             * query unchanged
             */
            li = client.queryForList("GetStudentCallListInfo", CMDG);

            CM_StudentInfoGetter[] cs = new CM_StudentInfoGetter[li.size()];

            for (int i = 0; i < li.size(); i++) {
                cs[i] = new CM_StudentInfoGetter();
                cs[i] = (CM_StudentInfoGetter) li.get(i);

                String marksPercentage = cs[i].getMarksPercentage();
                String marksObtained = cs[i].getMarksObtained();
                String totalMarks = cs[i].getTotalMarks();
                String score = cs[i].getScore();

                if (marksPercentage == null) {
                    marksPercentage = "0";
                }

                if (marksObtained == null) {
                    marksObtained = "0";
                }

                if (totalMarks == null) {
                    totalMarks = "0";
                }

                if (score == null) {
                    score = "0";
                }

                cs[i].setMarksPercentage(marksPercentage);
                cs[i].setMarksObtained(marksObtained);
                cs[i].setTotalMarks(totalMarks);
                cs[i].setScore(score);
            }

            return cs;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method unchanged
     */
    public String UpdatePersonalInfo(String[] student, String modifierId)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("update personal info");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CMDG.setFirst_name(student[0]);
            CMDG.setMiddle_name(student[1]);
            CMDG.setLast_name(student[2]);
            CMDG.setGender(student[3]);
            CMDG.setCategory(student[4]);
            CMDG.setDate_of_birth(student[5]);
            CMDG.setFather_Fname(student[6]);
            CMDG.setFather_Mname(student[7]);
            CMDG.setFather_Lname(student[8]);
            CMDG.setMother_Fname(student[9]);
            CMDG.setMother_Mname(student[10]);
            CMDG.setMother_Lname(student[11]);
            CMDG.setForm_number(student[12]);
            CMDG.setRegistrationNumber(student[13]);
            CMDG.setModification_time(date);
            CMDG.setModifier_id(modifierId);

            /**
             * query unchanged
             */
            li = client.update("updatePersonalInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public String UpdateaddressInfo(String[] address, String modifierId)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;
        List si;

        try {
            logObj.logger.info("in update address info");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            CM_entityInfoGetter CMDG = new CM_entityInfoGetter();

            CM_StudentInfoGetter cs = new CM_StudentInfoGetter();
            cs.setRegistrationNumber(address[0]);

            /**
             * query unchanged
             */
            si = client.queryForList("GetStuId1", cs);
            cs = (CM_StudentInfoGetter) si.get(0);

            String entity_id = cs.getUser_id();
            CMDG.setEntity_id(entity_id);
            CMDG.setAddress_line1(address[1]);
            CMDG.setAddress_line2(address[5]);
            CMDG.setCity(address[2]);
            CMDG.setState(address[3]);
            if(!(address[4].trim().equalsIgnoreCase(""))){
            CMDG.setPincode(address[4].trim());
            }
            CMDG.setModification_time(date);
            CMDG.setModifier_id(modifierId);

            /**
             * query unchanged
             */
            li = client.update("updateaddressInfo", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        //Return the Value of s in Function	
        return null;
    }

    /**
     * method unchanged
     */
    public String UpdatepaperCode(String RegNo, String OldPaperCode,
        String NewPaperCode, String modifierid, String PaperGroup)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in update paper info");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CMDG.setRegistrationNumber(RegNo);
            CMDG.setPapercode(OldPaperCode);
            CMDG.setNewpapercode(NewPaperCode);
            CMDG.setModification_time(date);
            CMDG.setModifier_id(modifierid);
            CMDG.setGrouping(PaperGroup);

            li = client.update("updatePaperCode", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method unchanged
     */
    public String deleteStudentSpWt(String RegNo)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in delete student spwt");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);

            /**
             * query unchanged
             */
            li = client.delete("deleteStudentSpWt", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_entityInfoGetter[] getStudentValue(String UnivId)
        throws Exception {
        List li;
        @SuppressWarnings("unused")
        Object newli;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getstudentval");

            CM_entityInfoGetter CMDG = new CM_entityInfoGetter();

            CMDG.setUniversity_id(UnivId.substring(1, 5));
            CMDG.setCode("STUDID");

            /**
             * query updated
             */
            li = client.queryForList("getStudentValue", CMDG);

            CMDG = (CM_entityInfoGetter) li.get(0);

            int value = Integer.parseInt(CMDG.getValue()) + 1;

            String value1 = null;

            value1 = String.format("%07d", value);

            //            if ((value / 1000000000) == 0) {
            //                value1 = ("" + value);
            //
            //                if ((value / 100000000) == 0) {
            //                    value1 = ("0" + value);
            //
            //                    if ((value / 10000000) == 0) {
            //                        value1 = ("00" + value);
            //
            //                        if ((value / 1000000) == 0) {
            //                            value1 = ("000" + value);
            //
            //                            if ((value / 100000) == 0) {
            //                                value1 = ("0000" + value);
            //
            //                                if ((value / 10000) == 0) {
            //                                    value1 = ("00000" + value);
            //
            //                                    if ((value / 1000) == 0) {
            //                                        value1 = ("000000" + value);
            //
            //                                        if ((value / 100) == 0) {
            //                                            value1 = ("0000000" + value);
            //
            //                                            if ((value / 10) == 0) {
            //                                                value1 = ("00000000" + value);
            //                                            }
            //                                        }
            //                                    }
            //                                }
            //                            }
            //                        }
            //                    }
            //                }
            //            }
            CM_entityInfoGetter CMDG1 = new CM_entityInfoGetter();

            CMDG1.setValue(value1);
            CMDG1.setCode("STUDID");
            CMDG1.setUniversity_id(UnivId.substring(1, 5));

            /**
             * query updated
             */
            newli = client.update("updateStudentValue", CMDG1);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter checkDuplicacy(String[] checkData)
        throws Exception {
        List li;
        List li1;

        try {
            logObj.logger.info("in check duplicacy");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            /**
             * query unchanged
             */
            li = client.queryForList("CheckStudentPersonal", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_StudentInfoGetter) li.get(i);

                if ((CMDG.getFirst_name().compareTo(checkData[1]) == 0) &&
                        (CMDG.getLast_name().compareTo(checkData[2]) == 0) &&
                        (CMDG.getDate_of_birth().compareTo(checkData[3]) == 0) &&
                        (CMDG.getFather_Fname().compareTo(checkData[4]) == 0)) {
                    String Stu_id = CMDG.getUser_id();
                    CMDG.setUser_id(Stu_id);
                    li1 = client.queryForList("GetRegfromStuId", CMDG);
                    CMDG = (CM_StudentInfoGetter) li1.get(0);
                    CMDG.setFlag(true);
                } else {
                    CMDG.setRegistrationNumber(null);
                    CMDG.setFlag(false);
                }
            }

            return CMDG;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    public String StudentFD(String ProgId, String RegNo, String FormNo,
        String FirstDeg, String creatorId) throws Exception {
        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("in StudentFD");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setProgramId(ProgId);
            CMDG.setForm_number(FormNo);
            CMDG.setRegistrationNumber(RegNo);
            CMDG.setFirstDegreeCode(FirstDeg);
            CMDG.setInsert_time(date);
            CMDG.setCreator_id(creatorId);

            /**
             * query updated
             */
            li = client.insert("insertStudentFD", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter[] getStudentFD(String RegNo, String progID,
        String ug_pg) throws Exception {
        List li;

        try {
            logObj.logger.info("in get studentfd");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            if (RegNo != null) {
                CMDG.setRegistrationNumber(RegNo);
                CMDG.setProgramId(progID);
                CMDG.setUGorPG(ug_pg);

                /**
                 * query updated
                 */
                li = client.queryForList("GetStudentFDInfo", CMDG);

                return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
            }
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    public String UpdateStudentFD(String RegNo, String FirstDegree,
        String type, String modifierid) throws Exception {
        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("in updatestudentfd");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);
            CMDG.setFirstDegreeCode(FirstDegree);
            CMDG.setUGorPG(type);
            CMDG.setModification_time(date);
            CMDG.setModifier_id(modifierid);

            li = client.update("updateStudentFD", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public String studentReg(String StuId, String RegNo, String FormNo,
        String Stu_Cos, String entity_id, String creatorId)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;
        List li3;

        try {
            logObj.logger.info("in studentreg");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            if (StuId != null) {
                CMDG.setRegistrationNumber(RegNo);
                CMDG.setUser_id(StuId);
                CMDG.setForm_number(FormNo);
                CMDG.setCos_value(Stu_Cos);

                CM_entityInfoGetter CMEG = new CM_entityInfoGetter();

                CMEG.setEntity_id(entity_id);
                CMEG.setUniversity_id(entity_id.substring(0, 4));

                /**
                 * query updated
                 */
                li3 = client.queryForList("Getsessiondate", CMEG);

                CMEG = (CM_entityInfoGetter) li3.get(0);

                String Start_date = CMEG.getStartdate();
                String end_date = CMEG.getEnddate();

                CMDG.setStartdate(Start_date);
                CMDG.setEnddate(end_date);
                CMDG.setInsert_time(date);
                CMDG.setCreator_id(creatorId);

                /**
                 * query updated
                 */
                li = client.insert("insertStuReg", CMDG);
            }
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter checkProgIdRegNo(String ProgId,
        String branchId, String RegNo, String ExistingRegNo,
        String specializationId) throws Exception {
        List li;
        int c = 0;
        int d = 0;

        try {
            logObj.logger.info("in checkProgIdRegNo");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(ExistingRegNo);

            /**
             * query unchanged
             */
            li = client.queryForList("GetStuId1", CMDG);

            CMDG = (CM_StudentInfoGetter) li.get(0);

            String userid = CMDG.getUser_id();

            CMDG.setUser_id(userid);

            /**
             * query unchanged
             */
            li = client.queryForList("GetRegfromStuId", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_StudentInfoGetter) li.get(i);

                String reg = CMDG.getRegistrationNumber();

                CMDG.setRegistrationNumber(reg);

                /**
                 * query updated
                 */
                List li1 = client.queryForList("checkProgIdRegNo", CMDG);

                CM_StudentInfoGetter CMDG1 = (CM_StudentInfoGetter) li1.get(0);

                if ((CMDG1.getSpecialization_id() != null) ||
                        (specializationId != null)) {
                    if ((CMDG1.getBranch().equals(branchId) == false)) {
                        if (CMDG1.getProgramId().equals(ProgId) == false) {
                            d = 0;
                        } else if (CMDG1.getProgramId().equals(ProgId) &&
                                (CMDG1.getBranch().equals(branchId))) {
                            c = c + 1;
                        }
                    }
                } else {
                    if (CMDG1.getProgramId().equals(ProgId) == false) {
                        d = 0;
                    } else {
                        d++;
                    }
                }
            }

            CM_StudentInfoGetter CMDGfinal = new CM_StudentInfoGetter();

            if ((c + d) == 0) {
                CMDGfinal.setUser_id(userid);
                CMDGfinal.setFlag(true);
            } else {
                CMDGfinal.setFlag(false);
            }

            return CMDGfinal;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    public boolean[] checkRegNo(String RegNo, String FormNo)
        throws Exception {
        boolean[] flag = new boolean[2];

        flag[0] = false;
        flag[1] = false;

        List li;

        try {
            logObj.logger.info("in checkRegNo");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            li = client.queryForList("checkRegNo", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_StudentInfoGetter) li.get(i);

                if (CMDG.getRegistrationNumber().equals(RegNo)) {
                    flag[0] = true;
                }

                /*  if(CMDG.getForm_number().equals(FormNo)){


                              flag[1]=true;
                      }*/
            }

            return flag;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public int checkReg(String RegNo) throws Exception {
        List li;

        try {
            logObj.logger.info("in checkReg");

            int reg = 0;
            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            li = client.queryForList("RegNoValid", CMDG);

            for (int i = 0; i < li.size(); i++) {
                CMDG = (CM_StudentInfoGetter) li.get(i);

                if (CMDG.getRegistrationNumber().equals(RegNo)) {
                    reg++;
                }
            }

            return reg;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcos_value(String progId,
        String Entity_Id, String catCode)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getcos_value");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
            CMDG.setProgram_id(progId);
            CMDG.setEntity_id(Entity_Id);
            CMDG.setCos_value(catCode);

            /**
             * query updated
             */
            li = client.queryForList("GetCOS_value", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter[] Category(String userId) {
        List li;

        try {
            logObj.logger.info("in Category");

            //userId null aa rahi hai yahan se aage
            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setUniversity_code(userId.substring(1, 5));

            /**
             * query updated
             */
            li = client.queryForList("Category", CMDG);

            return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            System.out.println(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter[] getEnrolledStuCategory(String category_code) {
        List li;

        try {
            logObj.logger.info("in getEnrolledStuCategory");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();
            CMDG.setCategory_code(category_code);

            /**
             * query updated
             */
            li = client.queryForList("getEnrolledStuCategory", CMDG);

            return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            System.out.println(ex);
        }

        return null;
    }

    /**
     * method unchanged
     */
    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getComponentId(String ComponentName)
        throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getComponentId");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setComponent(ComponentName);

            /**
             * query unchanged
             */
            li = client.queryForList("GetComponent_id", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public CM_StudentInfoGetter[] getBoard() throws Exception {
        List li;

        try {
            logObj.logger.info("in getBoard");

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            li = client.queryForList("BoardNames", CMDG);

            return (CM_StudentInfoGetter[]) li.toArray(new CM_StudentInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    //Update by Devendra June 8
    @SuppressWarnings("unchecked")
    public int programEligibility(String[] details) throws Exception {
        @SuppressWarnings("unused")
        Object li;
        List si;
        int check = 0;
        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in programEligibility");
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
            si = client.queryForList("checkProgAgeElig", CMDG);
            for (int i = 0; i < si.size(); i++) {
                CMDG = (CM_ProgramInfoGetter) si.get(i);
                if ((CMDG.getEntity_id().equalsIgnoreCase(details[0])) &&
                        (CMDG.getProgram_id().equalsIgnoreCase(details[1])) &&                      
                        (CMDG.getCategory().equalsIgnoreCase(details[2]) )) {
                    check++;
                }
            }
            if (check == 0) {
                CMDG.setEntity_id(details[0]);
                CMDG.setProgram_id(details[1]);                
                CMDG.setCategory(details[2]);
                CMDG.setAge_limit(details[3]);
                CMDG.setCreator_id(details[4]);               
                li = client.insert("progEligibilityInfo", CMDG);
            }
            return check;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }
    
    
    //UPDATE BY DEVENDRA JUNE 7
    @SuppressWarnings("unchecked")
	public List<CM_ProgramInfoGetter> getprogAgeElig(String entity_id){
    	List<CM_ProgramInfoGetter> ageList=null;
            try {
                CM_ProgramInfoGetter CMPG = new CM_ProgramInfoGetter();
                CMPG.setEntity_id(entity_id);
                ageList = client.queryForList("getProgAgeElig", CMPG);                    
            } catch (Exception ex) {
                logObj.logger.error("Error During getting age limit for mange : "+ex);                
            }
            return ageList;
        }
    
    
    //Update by Devendra June 7
    public String editProgAgeLimit(CM_ProgramInfoGetter input){     
    	String msg="";
        try {
        	System.out.println("inside Edit Age Limit impl");
            client.update("editProgAgeLimit", input);
            msg="success";
        } catch (Exception ex) {
        	 msg="error";
            logObj.logger.error("Exception during update Age Limit : "+ex);
        }
        return msg;
    }
  //Update by Devendra June 8
    public String deleteProgAge(List<CM_ProgramInfoGetter> inpultList) {
    	String msg="";
        try {    
        	int total=inpultList.size();
        	int count=0;
        	for(int i=0;i<inpultList.size();i++){
        		CM_ProgramInfoGetter been=new CM_ProgramInfoGetter();
        		been.setEntity_id(inpultList.get(i).getEntity_id());
        		been.setProgram_id(inpultList.get(i).getProgram_id());
        		been.setCategory(inpultList.get(i).getCategory());
        		client.delete("deleteProgAgeLimit", been);
        		count++;
        	}            
            msg="success-"+total+"-"+count+"-"+(total-count);
        } catch (Exception ex) {        	
            logObj.logger.error("Exception During Delete Age Limit "+ex);
        }
        return msg;
    }

    @SuppressWarnings("unchecked")
    public int componentEligibility(String[] compdetails)
        throws Exception {
        @SuppressWarnings("unused")
        Object li;
        List si;
        List pi;
        int check = 0;
        String component_id;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in componentEligibility");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setComponent(compdetails[3]);

            pi = client.queryForList("GetComponent_id", CMDG);

            CMDG = (CM_ProgramInfoGetter) pi.get(0);
            component_id = CMDG.getComponent_id();

            si = client.queryForList("checkProgCompElig", CMDG);

            for (int i = 0; i < si.size(); i++) {
                CMDG = (CM_ProgramInfoGetter) si.get(i);

                if ((CMDG.getEntity_id().equalsIgnoreCase(compdetails[0])) &&
                        (CMDG.getProgram_id().equalsIgnoreCase(compdetails[1])) &&
                        (CMDG.getComponent_id().equalsIgnoreCase(component_id)) &&
                        (CMDG.getCategory().equalsIgnoreCase(compdetails[4]))){
                    check++;
                }
            }

            if (check == 0) {
                CMDG.setEntity_id(compdetails[0]);
                CMDG.setProgram_id(compdetails[1]);
                CMDG.setComponent_id(component_id);
                CMDG.setCategory(compdetails[4]);
                CMDG.setCut_off_percentage(Float.parseFloat(compdetails[5]));
                CMDG.setInsert_time(date);
                CMDG.setCreator_id(compdetails[7]);

                li = client.insert("CompAgeEligibility", CMDG);
            }

            return check;
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getprogCompElig(String entity_id)
        throws Exception {
        List li;
        String program = null;
        String category = null;
        Float cut_off;
        String progId = null;
        String component;
        CM_ProgramInfoGetter[] CMPGF = null;

        try {
            CM_ProgramInfoGetter CMPG = new CM_ProgramInfoGetter();

            CMPG.setEntity_id(entity_id);
            li = client.queryForList("getProgCompElig", CMPG);
            CMPGF = new CM_ProgramInfoGetter[li.size()];

            for (int i = 0; i < li.size(); i++) {
                CMPG = (CM_ProgramInfoGetter) li.get(i);
                progId = (CMPG.getProgram_id());
                category = (CMPG.getCategory());
                cut_off = (CMPG.getCut_off_percentage());

                String ComponentID = CMPG.getComponent_id();

                CM_ProgramInfoGetter CMPGG = new CM_ProgramInfoGetter();
                CMPGG.setComponent_id(ComponentID);

                List pi1 = client.queryForList("GetComponent_name", CMPGG);
                CMPGG = (CM_ProgramInfoGetter) pi1.get(0);

                component = CMPGG.getComponent();

                CM_ProgramInfoGetter CMPG1 = new CM_ProgramInfoGetter();

                CMPG1.setProgram_id(progId);

                List pi = client.queryForList("GetprogramName", CMPG1);
                CMPG1 = (CM_ProgramInfoGetter) pi.get(0);

                program = CMPG1.getProgram_name();

                CMPGF[i] = new CM_ProgramInfoGetter();
                CMPGF[i].setProgram_name(program);

                CMPGF[i].setCategory(category);

                CMPGF[i].setComponent_id(component);

                CMPGF[i].setCut_off_percentage(cut_off);
            }

            return CMPGF;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String editProgCompEligibility(String[] ProgAgeDetails,
        String modifierID) throws Exception {
        String branchId;
        @SuppressWarnings("unused")
        Object li;
        String componentId = null;

        //Begin The Try-Catch Loop
        try {
            logObj.logger.info("in editProgCompEligibility");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CM_ProgramInfoGetter CMPG1 = new CM_ProgramInfoGetter();

            CMPG1.setProgram_name(ProgAgeDetails[1]);

            List pi = client.queryForList("Getprogram_id", CMPG1);
            CMPG1 = (CM_ProgramInfoGetter) pi.get(0);

            String programid = CMPG1.getProgram_id();

            CM_ProgramInfoGetter CMPG2 = new CM_ProgramInfoGetter();

            CMPG2.setBranch_name(ProgAgeDetails[2]);
            CMPG2.setUniversity_id(programid.substring(0, 4));

            CM_ProgramInfoGetter CMPGG = new CM_ProgramInfoGetter();
            CMPGG.setComponent(ProgAgeDetails[4]);

            List pi1 = client.queryForList("GetComponent_id", CMPGG);
            CMPGG = (CM_ProgramInfoGetter) pi1.get(0);

            componentId = CMPGG.getComponent_id();

            CMDG.setEntity_id(ProgAgeDetails[0]);
            CMDG.setProgram_id(programid);
            CMDG.setComponent_id(componentId);
            CMDG.setCategory(ProgAgeDetails[3]);
            CMDG.setCut_off_percentage(Float.parseFloat(ProgAgeDetails[5]));
            CMDG.setModifier_id(modifierID);
            CMDG.setUniversity_id(programid.substring(0, 4));
            li = client.update("editProgCompElig", CMDG);
        } catch (Exception ex) {
            logObj.logger.error("exception" + ex);
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    @Override
    public String deleteProgCompElig(String[] delProgComp)
        throws Exception {
        String branchId;
        @SuppressWarnings("unused")
        Object li;
        String componentId = null;

        //Begin The Try-Catch Loop
        try {
            logObj.logger.info("deleteProgCompElig");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CM_ProgramInfoGetter CMPG1 = new CM_ProgramInfoGetter();

            CMPG1.setProgram_name(delProgComp[1]);

            List pi = client.queryForList("Getprogram_id", CMPG1);
            CMPG1 = (CM_ProgramInfoGetter) pi.get(0);

            String programid = CMPG1.getProgram_id();

            CM_ProgramInfoGetter CMPGG = new CM_ProgramInfoGetter();
            CMPGG.setComponent(delProgComp[4]);

            List pi1 = client.queryForList("GetComponent_id", CMPGG);
            CMPGG = (CM_ProgramInfoGetter) pi1.get(0);

            componentId = CMPGG.getComponent_id();

            CMDG.setEntity_id(delProgComp[0]);
            CMDG.setProgram_id(programid);
            CMDG.setComponent_id(componentId);
            CMDG.setCategory(delProgComp[3]);
            CMDG.setCut_off_percentage(Float.parseFloat(delProgComp[5]));
            CMDG.setUniversity_id(programid.substring(0, 4));

            li = client.delete("deleteProgCompElig", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter getStudentProgBranch(String RegNo)
        throws Exception {
        String program = null;
        //String branchID = null;
        String flag = null;
        String entityID = null;
        String programID = null;
        String entity_name = null;
        //String specialization_id = null;
        //String branchname = null;

        CM_ProgramInfoGetter CMDGB = new CM_ProgramInfoGetter();

        try {
            logObj.logger.info("getStudentProgBranch");
            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);

            /**
             * query updated
             */
            List li1 = client.queryForList("checkProgIdRegNo", CMDG);
            if (li1.size() > 0) {
                flag = "true";

                CM_StudentInfoGetter CMDG1 = (CM_StudentInfoGetter) li1.get(0);

                programID = CMDG1.getProgramId();
               // branchID = CMDG1.getBranch();
                entityID = CMDG1.getEntity_id();
               // specialization_id = CMDG1.getSpecialization_id();

                CM_entityInfoGetter CMEI = new CM_entityInfoGetter();

                CMEI.setEntity_id(entityID);

                /**
                 * query unchanged
                 */
                List liEntity = client.queryForList("getEntityNamefromID", CMEI);
                CMEI = (CM_entityInfoGetter) liEntity.get(0);

                entity_name = CMEI.getEntity_name();

                CM_ProgramInfoGetter CMDGProg = new CM_ProgramInfoGetter();

                //CM_ProgramInfoGetter CMDGBranch = new CM_ProgramInfoGetter();

                if (programID != null) {
                    CMDGProg.setProgram_id(programID);

                    /**
                     * query unchanged
                     */
                    List liProg = client.queryForList("GetprogramName", CMDGProg);

                    CMDGProg = (CM_ProgramInfoGetter) liProg.get(0);

                    program = CMDGProg.getProgram_name();                    
                }

//                if (branchID.equals("")) {
//                    CMDGB.setBranch(null);
//                    CMDGB.setBranch_name(null);
//                } else {
//                    CMDGB.setBranch(branchID);
                    CMDGB.setUniversity_id(programID.substring(0, 4));

                   // List liB = client.queryForList("Getbranch_name", CMDGB);
                    //CMDGBranch = (CM_ProgramInfoGetter) liB.get(0);

//                    branchname = CMDGBranch.getBranch_name();
//                }

//                /**
//                 * added code
//                 */
//                if (specialization_id.equals("")) {
//                    CMDGB.setSpecialization_id(null);
//                    CMDGB.setSpecialization_name(null);
//                } else {
//                    CMDGB.setSpecialization_id(specialization_id);
//                    CMDGB.setUniversity_id(programID.substring(0, 4));
//
                    //List<String> liB = client.queryForList("getUniversityName",CMDGB);
//                    CMDGB = (CM_ProgramInfoGetter) liB.get(0);
                    
//                }
            } else {
                flag = "false";
            }

            //CMDGB.setBranch_code(branchID);
            CMDGB.setProgram_id(programID);
            CMDGB.setProgram_name(program);
            CMDGB.setEntity_name(entity_name);
            CMDGB.setEntity_id(entityID);
            //CMDGB.setSpecialization_id(specialization_id);
           // CMDGB.setBranch_name(branchname);
            CMDGB.setFlag(flag);

            //            CMDGB.setBranch_name(CMDGB.getBranch_name());
            //            CMDGB.setSpecialization_name(CMDGB.getSpecialization_name());
            System.out.println("before return in getStudentProgBranch");
            return CMDGB;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public String deleteStudentFD(String RegNo, String program_name) {
        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("in deleteStudentFD");

            CM_ProgramInfoGetter CMDGProg = new CM_ProgramInfoGetter();

            CMDGProg.setProgram_name(program_name);

            List liProg = client.queryForList("Getprogram_id", CMDGProg);

            CMDGProg = (CM_ProgramInfoGetter) liProg.get(0);

            String progId = CMDGProg.getProgram_id();

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);
            CMDG.setProgramId(progId);

            li = client.delete("deleteStudentFD", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            System.out.println(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public boolean CheckComponents(String EntityID, String ProgramID) throws Exception {
        boolean flag = true;
        int count = 0;

        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("CheckComponents");
            System.out.println("inside CheckComponents on server "+EntityID+" : "+ProgramID);
            CM_ProgramInfoGetter CMP = new CM_ProgramInfoGetter();

            /**
             * query updated
             */
            List liPrg = client.queryForList("checkComponents", CMP);

            for (int i = 0; i < liPrg.size(); i++) {
                CMP = (CM_ProgramInfoGetter) liPrg.get(i);

                if (CMP.getProgram_id().equals(ProgramID) &&
                        CMP.getEntity_id().equals(EntityID)) {
                    count++;
                }
            }

            if (count == 0) {
                flag = false;
            } else {
                flag = true;
            }

            return flag;
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @Override
    public String UpdatecallListInfo(String RegNo, String[] data,
        String modifierid) throws Exception {
        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("in UpdatecallListInfo");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            for (int j = 1; j < 5; j++) {
                if ((Float.parseFloat(data[j]) > 0) == false) {
                    data[j] = null;
                }
            }

            CMDG.setRegistrationNumber(RegNo);
            CMDG.setComponentID(data[0]);
            CMDG.setMarksPercentage(data[1]);
            CMDG.setMarksObtained(data[2]);
            CMDG.setTotalMarks(data[3]);
            CMDG.setScore(data[4]);
            CMDG.setBoard_id(data[5]);

            CMDG.setModification_time(date);
            CMDG.setModifier_id(modifierid);

            li = client.update("updateStuCallList", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method unchanged
     */
    public String UpdateStudentReg(String RegNo, String Stu_Cos,
        String modifierId) throws Exception {
        @SuppressWarnings("unused")
        Object li;

        try {
            logObj.logger.info("in UpdateStudentReg");

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            CM_StudentInfoGetter CMDG = new CM_StudentInfoGetter();

            CMDG.setRegistrationNumber(RegNo);
            CMDG.setCos_value(Stu_Cos);
            CMDG.setModification_time(date);
            CMDG.setModifier_id(modifierId);

            /**
             * query unchanged
             */
            li = client.update("updateStuReg", CMDG);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public String[] getProgRegNumber(String univID, String entity_id,
        String ProgId)
        throws Exception {
        List li;
        @SuppressWarnings("unused")
        Object newli;
        String value1 = null;
        String year = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(2, 4);

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in GetProgRegValue");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setUniversity_id(univID.substring(1, 5));
            CMDG.setEntity_id(entity_id);
            CMDG.setProgram_id(ProgId);
            //CMDG.setBranch_code(branchID);
            //CMDG.setSpecialization_id(specializationId);

            /**
             * query updated
             */
            li = client.queryForList("GetProgRegValue", CMDG);

            if (li.size() == 0) {
                setIntialRegNumber(univID, entity_id, ProgId);
            }
            
            li = client.queryForList("GetProgRegValueforupdate", CMDG);

//            /**
//             *
//             */
//            li = client.queryForList("GetProgRegValue", CMDG);
//
//            if (li.size() == 0) {
//                value1 = null;
//            } else {
                CMDG = (CM_ProgramInfoGetter) li.get(0);

                int value = Integer.parseInt(CMDG.getValue()) + 1;

                if ((value / 10000) == 0) {
                    value1 = ("" + value);

                    if ((value / 1000) == 0) {
                        value1 = ("0" + value);

                        if ((value / 100) == 0) {
                            value1 = ("00" + value);

                            if ((value / 10) == 0) {
                                value1 = ("000" + value);
                            }
                        }
                    }
                }
                
//                value1 = String.format("%4d", value);

         CM_ProgramInfoGetter CMDG1 = new  CM_ProgramInfoGetter();
                		                       
         CMDG1.setValue(value1);
         CMDG1.setUniversity_id(univID.substring(1, 5));
         CMDG1.setEntity_id(entity_id);
         CMDG1.setProgram_id(ProgId);
//         CMDG1.setBranch_code(branchID);
//         CMDG1.setSpecialization_id(specializationId);
         
         newli = client.update("updateProgRegValue", CMDG1);
//            }

            String[] result = { value1, year };

            return (result);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    public String updateProgRegNumber(String univID, String entity_id,
        String ProgId, String branchID, String value, String specializationId)
        throws Exception {
        try {
            logObj.logger.info("in updateProgRegValue");

            CM_ProgramInfoGetter CMDG1 = new CM_ProgramInfoGetter();

            CMDG1.setValue(value);
            CMDG1.setUniversity_id(univID.substring(1, 5));
            CMDG1.setEntity_id(entity_id);
            CMDG1.setProgram_id(ProgId);
            CMDG1.setBranch_code(branchID);
            CMDG1.setSpecialization_id(specializationId);

            /**
             * query updated
             */
            client.update("updateProgRegValue", CMDG1);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    public void setIntialRegNumber(String univID, String entity_id,
        String ProgId)
        throws Exception {
        try {
            logObj.logger.info("in setIntialRegNumber");

            String initialValue = "0000";
            CM_ProgramInfoGetter CMDG1 = new CM_ProgramInfoGetter();
            CMDG1.setValue(initialValue);
            CMDG1.setUniversity_id(univID.substring(1, 5));
            CMDG1.setEntity_id(entity_id);
            CMDG1.setProgram_id(ProgId);
//            CMDG1.setBranch_code(branchID);
//            CMDG1.setSpecialization_id(specializationId);

            /**
             * query updated
             */
            client.insert("insertIntialProgRegValue", CMDG1);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_entityInfoGetter[] defaultEntityName(String univID,
        String entity_type) throws Exception {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in defaultEntityName");

            CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
            CSIG.setUniversity_id(univID.substring(1, 5));
            CSIG.setEntity_type(entity_type);

            li = client.queryForList("getDefaultEntityName", CSIG);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    /**
     * method updated
     */
    @SuppressWarnings({"unchecked"
    })
    public CM_entityInfoGetter[] checkForDefaultView()
        throws Exception {
        List li;
        String univId = "0001";
        String code = "DEFENT";

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in checkForDefaultView");

            CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
            CSIG.setUniversity_id(univId);
            CSIG.setCode(code);

            /**
             * query updated
             */
            li = client.queryForList("checkForDefaultEntity", CSIG);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings({"unchecked"
    })
    public CM_entityInfoGetter[] defaultEntityType(String univID)
        throws Exception {
        List li;

        try {
            logObj.logger.info("in defaultEntityType");

            CM_entityInfoGetter CSIG = new CM_entityInfoGetter();
            CSIG.setUniversity_id(univID.substring(1, 5));
            li = client.queryForList("getDefaultEntityDesc", CSIG);

            return (CM_entityInfoGetter[]) li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getSpecializationDetails(String entity_id,
        String progID, String branchId) {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getBranch");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            String univ_id = progID.substring(0, 4);

            CMDG.setProgram_id(progID);
            CMDG.setEntity_id(entity_id);
            CMDG.setUniversity_id(univ_id);
            CMDG.setBranch_id(branchId);

            li = client.queryForList("Getspecialization", CMDG);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] checkFirstSemSpecialization(String progID,
        String branchId) {
        List li;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in checkFirstSemSpecialization");

            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

            CMDG.setProgram_id(progID);
            CMDG.setBranch_code(branchId);

            /**
             * query updated
             */
            li = client.queryForList("checkFirstSemSpecialization", CMDG);

            return (li.size() == 0) ? null
                                    : (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getSpecialization(String[] specializationId,
        String progID, String branchId) {
        List pi;

        //Begin The Try-Catch Loop		
        try {
            logObj.logger.info("in getSpecialization");

            String univ_id = progID.substring(0, 4);

            CM_ProgramInfoGetter[] CMPG = new CM_ProgramInfoGetter[specializationId.length];

            for (int i = 0; i < specializationId.length; i++) {
                CMPG[i] = new CM_ProgramInfoGetter();
                CMPG[i].setUniversity_id(univ_id);
                CMPG[i].setSpecialization_id(specializationId[i]);

                /**
                 * query updated
                 */
                pi = client.queryForList("getSpecialization", CMPG[i]);

                CMPG[i] = (CM_ProgramInfoGetter) pi.get(0);
            }

            return CMPG;
        } catch (Exception ex) {
            logObj.logger.error(ex);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_entityInfoGetter[] getOfferedByDetails(String programId,
        String universityId) {
        CM_entityInfoGetter getter = new CM_entityInfoGetter();

        getter.setProgram_id(programId);
        getter.setUniversity_id(universityId);

        List list;

        try {
            /**
             * query unchanged
             */
            list = client.queryForList("getOfferingEntity", getter);

            return (CM_entityInfoGetter[]) list.toArray(new CM_entityInfoGetter[list.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

	@SuppressWarnings("unchecked")
	public String setApplicantRecord(CM_StudentInfoGetter studentInfoGetter,String[] spwt,String[][] hello,
			String[][] paperCodes, String firstDeg) {
		
		
		
		studentInfoGetter.setUniversity_code(studentInfoGetter.getProgramId().substring(0,4));
		
		
		
		CM_entityInfoGetter newli = new CM_entityInfoGetter();
				
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
        .substring(0, 19);
		
		List li;
		List<CM_ProgramInfoGetter> cosList;				
		
		List li3;
		
		CM_StudentInfoGetter studentVerify = new CM_StudentInfoGetter();
		
		newli.setCode("STUDID");
		newli.setUniversity_id(studentInfoGetter.getProgramId().substring(0,4));
		
		CM_entityInfoGetter CMDG = new CM_entityInfoGetter();

        
        CMDG.setAddress_line1(studentInfoGetter.getAddressLine1());
        CMDG.setAddress_line2(studentInfoGetter.getAddressLine2());
        CMDG.setCity(studentInfoGetter.getCity());
        CMDG.setState(studentInfoGetter.getState());
        if(studentInfoGetter.getPinCode().equalsIgnoreCase("")){
        	
        	CMDG.setPincode(null);
        	
        }else{
        	
        	CMDG.setPincode(studentInfoGetter.getPinCode());
        	
        }
        
        CMDG.setCreator_id(studentInfoGetter.getCreator_id());
        CMDG.setInsert_time(date);
        
        studentInfoGetter.setInsert_time(date);
              

        
        try {
        	client.startTransaction();
        	
        	
        	studentVerify = (CM_StudentInfoGetter) client.queryForObject("getStudentIdforDuplicateapplicant",studentInfoGetter);
			if(studentVerify==null){
				
				
				/*
	        	 * gets the value for student id
	        	 */
				li = client.queryForList("getStudentValue", newli);
				newli = (CM_entityInfoGetter) li.get(0);

		        int value = Integer.parseInt(newli.getValue()) + 1;

		        String value1 = null;

		        value1 = String.format("%07d", value);

		       
		        CM_entityInfoGetter CMDG1 = new CM_entityInfoGetter();

		        CMDG1.setValue(value1);
		        CMDG1.setCode("STUDID");
		        CMDG1.setUniversity_id(studentInfoGetter.getProgramId().substring(0,4));
		        
		        /*
		         * update the student id constant
		         */
		        client.update("updateStudentValue", CMDG1);
		        
		        /*
		         * student id for the student
		         */
		        studentInfoGetter.setUser_id("S"+studentInfoGetter.getEntity_id()+value1);
		        
		        /*
		         * insert into ENTITY_STUDENT
		         */		       
		        client.insert("insertPersonalInfo",studentInfoGetter);
		        
		        CMDG.setEntity_id(studentInfoGetter.getUser_id());
		        CMDG.setUniversity_id(studentInfoGetter.getProgramId().substring(0,4));
		        		        
				/*
				 * insert into admission_addresses_master
				 */
		        client.insert("insertaddressInfo",CMDG);      
				
				
			}else{
				
				studentInfoGetter.setUser_id(studentVerify.getStudent_id());
				
			}
			
	        
	        
	        /**
             * query updated to get the session dates from university_master
             */
            li3 = client.queryForList("Getsessiondate", CMDG);
            CMDG = (CM_entityInfoGetter) li3.get(0);

            studentInfoGetter.setStartdate(CMDG.getStartdate());
            studentInfoGetter.setEnddate(CMDG.getEnddate()); 
	        
	        /*
	         * for loop for special weight age
	         */
	        for(int i=0;i<spwt.length;i++){
	        		        	
	        	
	        	/*
	        	 * for deleting all records for the registration
	        	 * number & inserting staff ward flag
	        	 */
	        	if(spwt[i]=="SW"){
	        		
	        		client.delete("deleteStudentSpWt", studentInfoGetter);
	        		
	        	}
	        	
	        	studentInfoGetter.setWeightageID(spwt[i]);	        	           
	           
	            if(spwt[i]!=null){
	            	
	            	/*
		        	 * insert into STUDENT_SPECIAL_WEIGHTAGE
		        	 */
		        	client.insert("insertstudentSpWtInfo",studentInfoGetter);
	            	
	            }
	            
	        	
	        	
	        }
	        System.out.println("inside CM_connect_tempImpl before insert into call list hello size is "+hello.length);
	       if(hello!=null){ 	        
	        for(int i=0;i<hello.length;i++){	        	
	        		studentInfoGetter.setComponentID(hello[i][0]);
		        	studentInfoGetter.setMarksPercentage(hello[i][1]);
		        	studentInfoGetter.setMarksObtained(hello[i][2]);
		        	studentInfoGetter.setTotalMarks(hello[i][3]);
		        	studentInfoGetter.setScore(hello[i][4]);		        	
		        	studentInfoGetter.setBoard_id(hello[i][5]);		        				        			        	
		        		            
		            /*
		             * insert into STUDENT_CALL_LIST
		             */
		        	
		            client.insert("insertstudentCallListInfo", studentInfoGetter);	        		        	        	        	
	        }	  
	       }
	        if(paperCodes!=null){
		        for(int i=0;i<paperCodes.length;i++){	        	
		        	studentInfoGetter.setPapercode(paperCodes[i][0]);
		        	studentInfoGetter.setGrouping(paperCodes[i][1]);		        	
		        	/*
		        	 * insert into STUDENT_PAPER
		        	 */
		        	client.insert("insertPaperCodeInfo", studentInfoGetter);	        	
		        	
		        }
	        }	      
	        CM_ProgramInfoGetter CMDG11 = new CM_ProgramInfoGetter();
	        String stu_COS = null;

	        CMDG11.setProgram_id(studentInfoGetter.getProgramId());
	        //CMDG11.setBranch(studentInfoGetter.getBranch());
	        CMDG11.setEntity_id(studentInfoGetter.getEntity_id());
	        System.out.println("inside impl subject code and category is "+studentInfoGetter.getCategory()+" : "+studentInfoGetter.getSubject_code()+" : "+studentInfoGetter.getGender());
	       
	        CMDG11.setCos_value(studentInfoGetter.getCategory()+"%"+studentInfoGetter.getSubject_code());
	       
	        //CMDG11.setSpecialization_id(studentInfoGetter.getSpecialization_id());
	        

            /**
             * to generate a cos for the student
             */
            cosList = client.queryForList("GetCOS_value", CMDG11);
            
//            CMDG11 = (CM_ProgramInfoGetter) li.get(0);
	       
            int temp = 0;
            if(cosList.size()==0){
            	
            	stu_COS = "XXXX";
            	temp = 1;
            }
           else if (cosList.size()==1) {
        	   System.out.println("befotr cos value generation "+cosList.get(0).getCos_value()+" : "+cosList.get(0).getCos_value().charAt(2)+" : "+studentInfoGetter.getGender().charAt(0));
        	   if(cosList.get(0).getCos_value().charAt(2) == studentInfoGetter.getGender().charAt(0)){
        		  stu_COS = cosList.get(0).getCos_value();
             	  temp = 1; 
        	   }
        	   else{
             	  stu_COS = studentInfoGetter.getCategory()+"X"+studentInfoGetter.getSubject_code();
             	  temp = 1;              	 
               }          	 
           }else{
           for(CM_ProgramInfoGetter info : cosList){            		
              if (info.getCos_value().charAt(2) == studentInfoGetter.getGender().charAt(0)) {
            	  stu_COS = info.getCos_value();
            	  temp = 1; 
            	  break;
              }
              
            }
            }
            if(temp == 0)
            {
            	stu_COS = "XXXX";
            }          
            studentInfoGetter.setCos_value(stu_COS);	       
            /*
             * insert into STUDENT_REGISTRATION
             */            
            client.insert("insertStuReg", studentInfoGetter);
            
            studentInfoGetter.setFirstDegreeCode(firstDeg);
            
            /*
             * insert into STUDENT_FIRST_DEGREE
             */
            client.insert("insertStudentFD", studentInfoGetter);
            
            client.commitTransaction();
            
            client.endTransaction();
            
            
	        return "success";
	        
	        
		} catch (Exception e1) {
			
			try {
				client.endTransaction();
			} catch (Exception e) {
				
				System.out.println("Exception"+e);
				
				e.printStackTrace();
				
			}
			
			e1.printStackTrace();
			System.out.println("exception"+e1);
			logObj.logger.error("Error in submission"+e1);
		}

		
		
		return "failure";
	}

	public Boolean checkStudentValidation(CM_StudentInfoGetter studentInfoGetter) {
		
		CM_StudentInfoGetter  studentVerify = new CM_StudentInfoGetter();		
		try{
			
			studentVerify = (CM_StudentInfoGetter) client.queryForObject("getDuplicateFormNumber",studentInfoGetter);
													
			if( studentVerify == null){
				
				studentVerify = (CM_StudentInfoGetter) client.queryForObject("checkStudentValidate",studentInfoGetter);
				
				if(studentVerify == null){
					
					return false;
					
				}			
				
			}			
			
		}catch (Exception e) {
			System.out.println("exception in checkStudentValidation"+e);
		}
		
		
		return true;
	}

	public String getRegistrationNumber(String programId,String facultyRegno) {
		
		CM_StudentInfoGetter infoGetter = new CM_StudentInfoGetter();
		CM_StudentInfoGetter list = new CM_StudentInfoGetter();	
		System.out.println("inside getRegistrationNumber "+facultyRegno+" : "+programId);
		infoGetter.setForm_number(facultyRegno);
		infoGetter.setProgramId(programId);
		
		try {
			
			list = (CM_StudentInfoGetter) client.queryForObject("getRegistrationNumber",infoGetter);
			System.out.println("list size is "+list);
			return list.getRegistrationNumber();
			
		} catch (Exception e) {
			
			System.out.println("Exception"+e);
			logObj.logger.error("Erron in getRegistrationNumber"+e);

			
		}
		
		return null;
	}

	public Boolean deleteStudentRecord(String regNo) {
		
		boolean flag = true;
		
		try {
			
			client.startTransaction();
			
			/*
			 * delete from entity_student
			 */
			client.delete("deletefromEntityStudent",regNo);
			/*
			 * delete from student_registration
			 */
			client.delete("deletefromStudentRegistration",regNo);
			/*
			 * delete from student_call_list
			 */
			client.delete("deletefromStudentCallList",regNo);
			/*
			 * delete from student_special_weightage
			 */
			client.delete("deletefromStudentSpecialWeightage",regNo);
			/*
			 * delete from student_paper
			 */
			client.delete("deletefromStudentPaper",regNo);
			/*
			 * delete from student_first_degree
			 */
			client.delete("deletefromStudentFirstDegree",regNo);
			/*
			 * delete from student_test_number
			 */
			client.delete("deletefromStudentTestNumber",regNo);
			
			client.commitTransaction();
			
			client.endTransaction();
			
			flag=false;
			
		} catch (Exception e) {
			
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				
				logObj.logger.error("Error in deleting records"+e);
				e1.printStackTrace();
			}
			
			System.out.println("error"+e);
			
		}
		
		
		return flag;
	}

	//Add by Devendra for getting Subject Code
	@Override
	public CM_ProgramInfoGetter[] getSubject(String progId) throws Exception {
		 List li;

	        //Begin The Try-Catch Loop		
	        try {
	            logObj.logger.info("in getBranch");

	            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();

	            String univ_id = progId.substring(0, 4);

	            CMDG.setProgram_id(progId);
	            CMDG.setUniversity_id(univ_id);
	            li = client.queryForList("GetSubject", CMDG);

	            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
	        } catch (Exception ex) {
	            logObj.logger.error(ex);
	            throw new Exception(ex);
	        }
	}

	//Add by Devendra
	@SuppressWarnings("unchecked")
	@Override
	public CM_ProgramInfoGetter[] getComponentList(String programId,String entityId) throws Exception {
		 List li;
	        try {
	            CM_ProgramInfoGetter CMDG = new CM_ProgramInfoGetter();
	            System.out.println("inside impl "+programId+" : "+entityId);
	            CMDG.setProgram_id(programId);
	            CMDG.setEntity_id(entityId);
	            li = client.queryForList("getComponentList", CMDG);
	            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
	            
	        } catch (Exception ex) {
	            logObj.logger.error("Error in CM_connectTempImpl inside getComponentList method:: "+ex);
	            throw new Exception(ex);
	        }
	}

	//Add by Devendra May 10
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportInfoGetter> getOmrTestId(String date) throws Exception {
		List<ReportInfoGetter>li;
		try {
			ReportInfoGetter CMDG = new ReportInfoGetter();
           if(date==null){
        	   li = client.queryForList("getOmrTestId1", CMDG);
           }
           else{
        	   System.out.println("date is "+date);
        	   CMDG.setStart_date(date);
        	   li = client.queryForList("getOmrTestId", CMDG);
           }          
           return li;
            
        } catch (Exception ex) {
            logObj.logger.error("Error in CM_connectTempImpl inside getComponentList method:: "+ex);
            throw new Exception(ex);
        }
	}	
}
