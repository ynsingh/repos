package in.ac.dei.edrp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author Dayal Sharan Sukhdhami
 */
import in.ac.dei.edrp.client.CM_BranchSpecializationInfoGetter;
import in.ac.dei.edrp.client.CM_UniversityInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectD;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;


//import java.util.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CM_connectDImpl extends RemoteServiceServlet implements CM_connectD {
    Connection con;
    SqlMapClient client = SqlMapManager.getSqlMapClient();

    // Log4JInitServlet logObj = new Log4JInitServlet();
    @SuppressWarnings("unchecked")
    public String methodAddUniversity(String universityCode,
        Date sessionStartDate, Date sessionEndDate, String universityName,
        String universityAddress, String universityCity,
        String universityState, String universityPincode,
        String universityPhoneNumber, String universityOtherPhoneNumber,
        String universityFaxNumber, String universityInsertTime,
        String universityCreatorID) throws Exception {
        List l1;

        try {
            l1 = client.queryForList("selectMaxUniversityCode", null);

            CM_UniversityInfoGetter[] universityMaxCode1 = (CM_UniversityInfoGetter[]) l1.toArray(new CM_UniversityInfoGetter[l1.size()]);

            int maxCode = Integer.parseInt(universityMaxCode1[0].getUniversityMaxCode());

            int code = maxCode + 1;
            String universityID = null;

            if ((code / 10000) == 0) {
                universityID = ("" + code);

                if ((code / 1000) == 0) {
                    universityID = ("0" + code);

                    if ((code / 100) == 0) {
                        universityID = ("00" + code);

                        if ((code / 10) == 0) {
                            universityID = ("000" + code);
                        }
                    }
                }
            }

            CM_UniversityInfoGetter insert = new CM_UniversityInfoGetter();
            insert.setUniversityCode(universityID);
            insert.setSessionStartDate(sessionStartDate);
            insert.setSessionEndDate(sessionEndDate);
            insert.setUniversityName(universityName);
            insert.setUniversityAddress(universityAddress);
            insert.setUniversityCity(universityCity);
            insert.setUniversityState(universityState);
            insert.setUniversityPincode(universityPincode);
            insert.setUniversityPhoneNumber(universityPhoneNumber);
            insert.setUniversityOtherPhoneNumber(universityOtherPhoneNumber);
            insert.setUniversityFaxNumber(universityFaxNumber);
            insert.setUniversityInsertTime(universityInsertTime);
            insert.setUniversityCreatorID(universityCreatorID);
            client.insert("insertNewUniversity", insert);

            /*
             * Queries for dumping data into control tables
             */

            //               String userid= "E"+universityID+"0000000000001";
            client.insert("dumpComponentDescription", universityID);
            client.insert("dumpProgramBranches", universityID);
            client.insert("dumpCosCode", universityID);
            client.insert("dumpProgramSpecialization", universityID);
            client.insert("dumpSeatReservationCategory", universityID);
            client.insert("dumpSystemValues", universityID);

            client.insert("dumpUniversityEntityType", universityID);
            client.insert("dumpUniversityProgramMode", universityID);
            client.insert("dumpUniversityProgramType", universityID);
            client.insert("dumpWeightageDescription", universityID);

            return universityID;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception(ex);
        }
    }

    public void methodAddUniversityAdmin(boolean flagd, String universityName,
        String userGroupName, String userName, String password,
        String regTimeStamp, boolean activated) {
        List<?> l1;

        //    	List<?> l2;
        try {
            l1 = client.queryForList("selectUniversityID", universityName);

            CM_UniversityInfoGetter[] UniversityID = (CM_UniversityInfoGetter[]) l1.toArray(new CM_UniversityInfoGetter[l1.size()]);

            String univerID = UniversityID[0].getUniversityCode();
            CM_userInfoGetter insert = new CM_userInfoGetter();

            int codeValue = 1;

            //        	if(flagd == true){
            insert.setUser_id("E" + univerID + "000000000000" + codeValue);

            //        	}
            //        	if(flagd ==false){
            //	            	
            //	            	
            //	            	l2=client.queryForList("selectMaxUserID", univerID);
            //	           	 System.out.println("here"+l2.size());
            //	           	 CM_userInfoGetter[] maxUserID = (CM_userInfoGetter[]) l2.toArray(new CM_userInfoGetter[l2.size()]);
            //	             
            //	           	 String maxStrID = maxUserID[0].getUser_id();
            //	            	 
            //	        	 int maxID = Integer.parseInt(maxStrID);
            //	        	 System.out.println(maxID);
            //	        	 
            //	        	 int code = maxID + 1;
            //	        	 String userIdEnd = null;
            //	        	 if(code/1000000 == 0){
            //	        		 userIdEnd = (""+code);
            //	
            //		        	 if(code/100000 == 0){
            //		        		 userIdEnd = ("0"+code);
            //		        	 
            //			        		if(code/10000 == 0){
            //			        			userIdEnd = ("00"+code);
            //			        	                	 
            //			       	      		 if(code/1000 == 0){
            //			       	      		    userIdEnd = ("000"+code);
            //			        	                    	 
            //			      	           		  if(code/100 == 0){
            //			      	           			userIdEnd = ("0000"+code);
            //			        	                               	 
            //			        	          		   if(code/10 == 0){
            //			        	          			 userIdEnd = ("00000"+code);
            //			  	                           } 		
            //			      	                  } 
            //			         	         }
            //			            	}
            //		        	  }
            //	        	 }	 
            //		        	 
            //	        	insert.setUser_id("E"+univerID+"0000"+userIdEnd);
            //	        	System.out.println("E"+univerID+"0000"+userIdEnd);
            //
            //        	}
            insert.setUser_group_name(userGroupName);
            insert.setUser_name(userName);
            insert.setpassword(password);
            insert.setRegistered_timestamp(regTimeStamp);
            insert.setActivated(activated);

            client.insert("insertNewUser_info", insert);

            insert.setId(univerID);
            /*dumping defalut employee record*/
            client.insert("dumpEntityEmployee", insert);
        } catch (Exception ex) {
            System.out.println(ex);

            try {
                throw new Exception(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public CM_UniversityInfoGetter[] methodUniversityList()
        throws Exception {
        try {
            //	        createconnection();
            List li = null;

            Object value = null;
            li = client.queryForList("selectUniversityName", value);

            return (CM_UniversityInfoGetter[]) li.toArray(new CM_UniversityInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method for generating list of Universities
    *
    * @throws Exception
    */
    @SuppressWarnings("unchecked")
    public CM_UniversityInfoGetter[] methodManageUniversityList(
        String criteria, String value) throws Exception {
        try {
            //            createconnection();
            List li = null;

            if (criteria == null) {
                li = client.queryForList("selectAllUniversityDetails", null);
            }
            else if (criteria.equalsIgnoreCase("University Name")) {
                li = client.queryForList("selectAllUniversityDetailsWithUniversityName",
                        value);
            }
            else if (criteria.equalsIgnoreCase("University ID")) {
                li = client.queryForList("selectAllUniversityDetailsWithUniversityID",
                        value);
            }
            else if (criteria.equalsIgnoreCase("University City")) {
                li = client.queryForList("selectAllUniversityDetailsWithUniversityCity",
                        value);
            }
            else if (criteria.equalsIgnoreCase("University State")) {
                li = client.queryForList("selectAllUniversityDetailsWithUniversityState",
                        value);
            }

            return (CM_UniversityInfoGetter[]) li.toArray(new CM_UniversityInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method for generating list of Programs
    *
    * @throws Exception
    */
    public CM_BranchSpecializationInfoGetter[] methodProgramList()
        throws Exception {
        try {
            //	        createconnection();
            List<?> l1 = null;

            @SuppressWarnings("unused")
			Object value1 = null;
            l1 = client.queryForList("selectProgramName", null);

            return (CM_BranchSpecializationInfoGetter[]) l1.toArray(new CM_BranchSpecializationInfoGetter[l1.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method for generating list of Branches under Programs
    *
    * @throws Exception
    */
    public CM_BranchSpecializationInfoGetter[] methodBranchList(
        String programName) {
        List<?> l2 = null;

        try {
            //	        createconnection();        
            @SuppressWarnings("unused")
            Object value1 = null;
            l2 = client.queryForList("selectBranchName", programName);
        } catch (SQLException e1) {
            System.out.println("SQl error" + e1);
        } catch (Exception e) {
            System.out.println("error" + e);
        }

        return (CM_BranchSpecializationInfoGetter[]) l2.toArray(new CM_BranchSpecializationInfoGetter[l2.size()]);
    }

    /**
    * Method for generating list of Specializations under Programs and Branches
    *
    * @throws Exception
    */
    @SuppressWarnings("unchecked")
    public CM_BranchSpecializationInfoGetter[] methodSpecializationList(
        String programName, String branchName) {
        List l2 = null;

        try {
            //	        createconnection();        
            CM_BranchSpecializationInfoGetter programWithSpecialization = new CM_BranchSpecializationInfoGetter();
            CM_BranchSpecializationInfoGetter cmb = new CM_BranchSpecializationInfoGetter();

            if ((programName != null) && branchName.equals("Select")) {
                programWithSpecialization.setProgram_name(programName);
                l2 = client.queryForList("selectSpecializationWithProgram",
                        programWithSpecialization);
            }
            else if ((programName != null) && !branchName.equals("Select")) {
                cmb.setProgram_name(programName);
                cmb.setBranch_name(branchName);

                l2 = client.queryForList("selectSpecializationWithProgramBranch",
                        cmb);
            }

            //				l2 = client.queryForList("selectSpecializationName", programWithSpecialization);
        } catch (SQLException e1) {
            System.out.println("SQl error" + e1);
        } catch (Exception e) {
            System.out.println("error" + e);
        }

        return (CM_BranchSpecializationInfoGetter[]) l2.toArray(new CM_BranchSpecializationInfoGetter[l2.size()]);
    }

    //	/**
    //     * Method for generating list of Specialization under only Programs.
    //     *
    //     * @throws Exception
    //     */	
    //	public CM_BranchSpecializationInfoGetter[] methodSpecializationWithProgramList(String programName)
    //	{	
    //		System.out.println("Heyy Specialization!! Ur program is: " + programName);
    //		List l2 = null;
    //	    try {
    //	        createconnection();
    //	        	
    //	        	Object value1 = null;
    //
    //				l2 = client.queryForList("selectSpecializationWithProgram", programName);
    //	        	 
    //	     	        System.out.println("size+"+l2.size()+programName);
    //		       
    //	    } 
    //	    catch (SQLException e1) {
    //	    	System.out.println("SQl error" + e1);
    //	    }
    //	    catch (Exception e) {
    //	    	System.out.println("error" + e);
    //	    }
    //	    
    //	    return (CM_BranchSpecializationInfoGetter[]) l2.toArray(new CM_BranchSpecializationInfoGetter[l2.size()]);
    //	}

    /**
    * Method for generating list of Programs with its Branches and Specializations.
    *
    * @throws Exception
    */
    public CM_BranchSpecializationInfoGetter[] methodBranchSpecializationList(
        String programName, String branchName, String specializationName) {
        List<?> li = null;
        List<?> l1 = null;

        try {
            //	        createconnection();        
            CM_BranchSpecializationInfoGetter programWithBranchWithSpecialization =
                new CM_BranchSpecializationInfoGetter();

            if (programName != null) {
                if (branchName.equals("Select") &&
                        specializationName.equals("Select")) {
                    li = client.queryForList("selectBranchTest", programName);

                    String value = li.toArray(new CM_BranchSpecializationInfoGetter[li.size()])[0].getBranch();

                    programWithBranchWithSpecialization.setProgram_name(programName);

                    if (value.equalsIgnoreCase("1")) {
                        //	    					select c.program_name, b.branch_name, a.specialization_name from entity_program_master c,entity_program_specialization_detail a,entity_program_branch_detail b where a.program_id = (select program_id from entity_program_master where program_name = 'B.E.') AND a.branch=b.branch AND c.program_id = a.program_id;
                        l1 = client.queryForList("selectAllWithProgramName",
                                programWithBranchWithSpecialization);
                    }

                    if (value.equalsIgnoreCase("0")) {
                        l1 = client.queryForList("selectAllWithProgramSpecializationWithoutBranch",
                                programWithBranchWithSpecialization);
                    }
                } else if (branchName.equals("Select") &&
                        !specializationName.equals("Select")) {
                    //	   						select a.specialization_name,b.program_name from entity_program_specialization_detail a,entity_program_master b where specialization_name = 'Computer Networks' AND b.program_id = a.program_id;
                    programWithBranchWithSpecialization.setProgram_name(programName);
                    programWithBranchWithSpecialization.setSpecialization_name(specializationName);

                    l1 = client.queryForList("selectAllWithProgramNameSpecialization",
                            programWithBranchWithSpecialization);
                } else if (!branchName.equals("Select") &&
                        specializationName.equals("Select")) {
                    //	    								select a.program_name, b.branch_name, c.specialization_name from entity_program_master a, entity_program_branch_detail b, entity_program_specialization_detail c where a.program_name = 'B.E.' AND b.branch_name = 'Computer Science';      		
                    programWithBranchWithSpecialization.setProgram_name(programName);
                    programWithBranchWithSpecialization.setBranch_name(branchName);

                    l1 = client.queryForList("selectAllWithProgramNameBranchName",
                            programWithBranchWithSpecialization);
                } else if (!branchName.equals("Select") &&
                        !specializationName.equals("Select")) {
                    //	    					select a.program_name, b.branch_name, c.specialization_name from entity_program_master a, entity_program_branch_detail b, entity_program_specialization_detail c where a.program_name = 'B.E.' AND b.branch_name = 'Computer Science' AND c.specialization_name = 'Computer Networks';
                    programWithBranchWithSpecialization.setProgram_name(programName);
                    programWithBranchWithSpecialization.setBranch_name(branchName);
                    programWithBranchWithSpecialization.setSpecialization_name(specializationName);

                    l1 = client.queryForList("selectAllWithProgramNameBranchNameSpecialization",
                            programWithBranchWithSpecialization);
                }
            }

            //				l1 = client.queryForList("selectAllThree", programWithBranchWithSpecialization);
        } catch (SQLException e1) {
            System.out.println("SQl error" + e1);
        } catch (Exception e) {
            System.out.println("error" + e);
        }

        return (CM_BranchSpecializationInfoGetter[]) l1.toArray(new CM_BranchSpecializationInfoGetter[l1.size()]);
    }

    /**
    * Method for generating list of Universities
    *
    * @throws Exception
    */
    @SuppressWarnings("unchecked")
    public CM_BranchSpecializationInfoGetter[] methodManageProgramList(
        String selectedProgramOfferedColumn) throws Exception {
        try {
            //            createconnection();
            List li = null;
            //            if(criteria==null)
            //           li = client.queryForList("selectAllProgramDetails", null);
            //            
            //            else
            //            	if(criteria.equalsIgnoreCase("Program"))
            li = client.queryForList("selectProgramOfferingEntity1",
                    selectedProgramOfferedColumn);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodEntityPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectEntityName", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodMentorPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectMentorName", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodLocationPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectLocationName", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method for adding data in program offered by.
    */
    public CM_BranchSpecializationInfoGetter[] methodAssignProgramsToEntity(
        String programName, String branchName, String specializationName,
        String collegeCenter, String location, String seats, String mentor)
        throws Exception {
        List<?> l1;
        List<?> l2;
        List<?> l3;
        List<?> l4;
        List<?> l5;

        try {
            l1 = client.queryForList("selectProgramID", programName);

            CM_BranchSpecializationInfoGetter[] progID = (CM_BranchSpecializationInfoGetter[]) l1.toArray(new CM_BranchSpecializationInfoGetter[l1.size()]);
            String program_id = progID[0].getProgram_id();

            String branch = "";

            try {
                if (branchName.equals("NULL")) {
                    branch = "000";
                } else {
                    l2 = client.queryForList("selectBranch", branchName);

                    CM_BranchSpecializationInfoGetter[] brnch = (CM_BranchSpecializationInfoGetter[]) l2.toArray(new CM_BranchSpecializationInfoGetter[l2.size()]);
                    branch = brnch[0].getBranch();
                    System.out.println(" connect IMPL:  " + branch);
                }

                //            	else{
                //            			branch = null;
                //            	}
            } catch (Exception e) {
            }

            String specialization = "";

            try {
                if (specializationName.equals("null")) {
                    specialization = "null";
                } else {
                    l3 = client.queryForList("selectSpecialization",
                            specializationName);

                    CM_BranchSpecializationInfoGetter[] speclztion = (CM_BranchSpecializationInfoGetter[]) l3.toArray(new CM_BranchSpecializationInfoGetter[l3.size()]);
                    specialization = speclztion[0].getSpecialization();
                }
            } catch (Exception e) {
            }

            l4 = client.queryForList("selectEntityID", collegeCenter);

            CM_BranchSpecializationInfoGetter[] offeringEntity = (CM_BranchSpecializationInfoGetter[]) l4.toArray(new CM_BranchSpecializationInfoGetter[l4.size()]);
            String offered_by = offeringEntity[0].getEntity_id();

            l5 = client.queryForList("selectLocationID", location);

            CM_BranchSpecializationInfoGetter[] loction = (CM_BranchSpecializationInfoGetter[]) l5.toArray(new CM_BranchSpecializationInfoGetter[l5.size()]);
            String location_id = loction[0].getLocation_id();

            CM_BranchSpecializationInfoGetter insert = new CM_BranchSpecializationInfoGetter();
            insert.setProgram_id(program_id);
            insert.setBranch(branch);
            insert.setSpecialization(specialization);
            insert.setOffered_by(offered_by);
            insert.setMentor(mentor);
            insert.setSeats(seats);
            insert.setLocation_id(location_id);

            client.insert("insertNewProgramOfferedBy", insert);
        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception(ex);
        }

        return null;
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodProgramOfferedByPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectProgramOfferedByName", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodProgramOfferedByProgramList(
        String programOfferedBy, String value) throws Exception {
        try {
            List<?> li = null;

            CM_BranchSpecializationInfoGetter POB = new CM_BranchSpecializationInfoGetter();
            POB.setOffered_by(programOfferedBy);
            POB.setProgram_name(value);

            try {
                li = client.queryForList("selectProgramOfferedBy", POB);
            } catch (Exception e) {
                throw new Exception(e);
            }

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method for Editing data in program offered by.
    */
    public void methodEditProgramOfferedBy(
        CM_BranchSpecializationInfoGetter editProgramOfferedBy)
        throws Exception {
        try {
            client.update("editProgramOfferingEntity", editProgramOfferedBy);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void methodDeleteProgramOfferedBy(
        CM_BranchSpecializationInfoGetter deleteProgramOfferedBy)
        throws Exception {
        try {
            client.delete("deleteProgramOfferingEntity", deleteProgramOfferedBy);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodWeightageDescriptionPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectWeightageDescription", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodWeightageDescriptionFromSpecialWeightagePopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectWeightageDescriptionFromSpecialWeightage",
                    null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodGroupsPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectGrouping", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void methodAddSpecialWeightage(String weightageDescription,
        String group, String weightagePercentage, String university_id,
        String code) throws Exception {
        try {
            CM_BranchSpecializationInfoGetter systemValue = new CM_BranchSpecializationInfoGetter();
            systemValue.setUniversity_id(university_id);
            systemValue.setSystem_code(code);

            List<?> li;
            li = client.queryForList("sysValue", systemValue);

            CM_BranchSpecializationInfoGetter[] sysinfo = li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);

            int value = Integer.parseInt(sysinfo[0].getSystem_value()) + 1;

            //    		System.out.println("Value: "+ value);
            String value1 = null;

            if ((value / 100) == 0) {
                value1 = ("" + value);

                if ((value / 10) == 0) {
                    value1 = ("0" + value);
                }
            }

            systemValue.setSystem_value(value1);
            client.update("updateSysValue", systemValue);

            CM_BranchSpecializationInfoGetter specialWeightageValues = new CM_BranchSpecializationInfoGetter();

            specialWeightageValues.setUniversity_id(university_id);
            specialWeightageValues.setWeightageID(value1);
            specialWeightageValues.setWeightageDescription(weightageDescription);
            specialWeightageValues.setGrouping(group);
            specialWeightageValues.setWeightagePercentage(weightagePercentage);

            client.insert("insertSpecialWeightage", specialWeightageValues);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void methodUpdateSpecialWeightage(String grouping,
        String weightagePercentage, String weightageDescription)
        throws Exception {
        try {
            CM_BranchSpecializationInfoGetter updateValues = new CM_BranchSpecializationInfoGetter();
            updateValues.setGrouping(grouping);
            updateValues.setWeightagePercentage(weightagePercentage);
            updateValues.setWeightageDescription(weightageDescription);

            client.update("updateSpecialWeightage", updateValues);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void methodDeleteSpecialWeightage(String weightageDescription)
        throws Exception {
        try {
            client.delete("deleteSpecialWeightage", weightageDescription);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Method to populate details of chosen subject
     */
    public String methodShowdetailsofsubjects(String coursename) {
        String Value = "";

        // String typeOfSemester = "";
        try {
            //            createconnection();

            /**
             * Include instituteID also here
             */

            // This query retreives Semester Type
            String query2 = "select semester,subjectID,subjectName,credits,lectures,tutorials,practicals,sinceSession from subjects where subjectID = ? ";
            PreparedStatement pstatement2 = con.prepareStatement(query2);
            pstatement2.setString(1, coursename);

            //            pstatement2.setString(2, selectedSession);
            //            pstatement2.setString(3, select2);
            //            pstatement2.setString(4, selectedSession);
            ResultSet resultset2 = pstatement2.executeQuery();

            if (resultset2.wasNull()) {
            } else {
                while (resultset2.next()) {
                    // Concatenating values of ResultSet into single string
                    Value = Value + resultset2.getString(1) + '|' +
                        resultset2.getString(2) + '|' +
                        resultset2.getString(3) + '|' +
                        resultset2.getString(4) + '|' +
                        resultset2.getString(5) + '|' +
                        resultset2.getString(6) + '|' +
                        resultset2.getString(7) + '|' +
                        resultset2.getString(8) + '|';
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Value;
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodSpecialWeightagePopulate(
        String weightageDescription) throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectSpecialWeightage",
                    weightageDescription);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void methodAddProgramFirstDegree(String programOfferedBy,
        String program, String descriptionText, String firstDegreeCodeNumber)
        throws Exception {
        List<?> l1;
        List<?> l2;
        @SuppressWarnings("unused")
        List<?> l3;

        try {
            l1 = client.queryForList("selectEntityID", programOfferedBy);

            CM_BranchSpecializationInfoGetter[] enID = (CM_BranchSpecializationInfoGetter[]) l1.toArray(new CM_BranchSpecializationInfoGetter[l1.size()]);

            l2 = client.queryForList("selectProgramID", program);

            CM_BranchSpecializationInfoGetter[] prID = (CM_BranchSpecializationInfoGetter[]) l2.toArray(new CM_BranchSpecializationInfoGetter[l2.size()]);

            CM_BranchSpecializationInfoGetter insert = new CM_BranchSpecializationInfoGetter();
            insert.setEntity_id(enID[0].getEntity_id());
            insert.setProgram_id(prID[0].getProgram_id());
            insert.setFirst_degree_description(descriptionText);
            insert.setFirst_degree_code(firstDegreeCodeNumber);

            client.insert("insertNewProgramFirstDegree", insert);
        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception(ex);
        }
    }

    /**
    * Method to Populate Entity of Program First Degree
    */
    public CM_BranchSpecializationInfoGetter[] methodEntityFromProgramFirstDegreePopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectEntityFromProgramFirstDegree", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method to Populate Program of Program First Degree
    */
    public CM_BranchSpecializationInfoGetter[] methodProgramFromProgramFirstDegreePopulate(
        String programOfferedBy) throws Exception {
        try {
            List<?> li;

            li = client.queryForList("selectProgramFromProgramFirstDegree",
                    programOfferedBy);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
    * Method to Populate Program First Degree
    */
    public CM_BranchSpecializationInfoGetter[] methodProgramFirstDegreePopulate(
        String programOfferedBy, String programDescription)
        throws Exception {
        try {
            List<?> li;

            CM_BranchSpecializationInfoGetter entityProgram = new CM_BranchSpecializationInfoGetter();
            entityProgram.setEntity_name(programOfferedBy);
            entityProgram.setProgram_name(programDescription);
            li = client.queryForList("selectProgramFirstDegree", entityProgram);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void methodDeleteProgramFirstDegree(
        CM_BranchSpecializationInfoGetter deleteProgramFirstDegree)
        throws Exception {
        try {
            client.delete("deleteProgramFirstDegree", deleteProgramFirstDegree);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void methodInsertProgramPaperCode(String uniid, String program,
        String programOfferedBy, String groupText, String paperCode)
        throws Exception {
        List<?> l1;
        List<?> l2;
        List<?> l3;

        try {
            l1 = client.queryForList("selectEntityID", programOfferedBy);

            CM_BranchSpecializationInfoGetter[] enID = (CM_BranchSpecializationInfoGetter[]) l1.toArray(new CM_BranchSpecializationInfoGetter[l1.size()]);

            l2 = client.queryForList("selectProgramID", program);

            CM_BranchSpecializationInfoGetter[] prID = (CM_BranchSpecializationInfoGetter[]) l2.toArray(new CM_BranchSpecializationInfoGetter[l2.size()]);

            CM_BranchSpecializationInfoGetter insert = new CM_BranchSpecializationInfoGetter();
            insert.setUniversity_id(uniid);
            insert.setEntity_id(enID[0].getEntity_id());
            insert.setProgram_id(prID[0].getProgram_id());
            insert.setGrouping(groupText);
            insert.setPaper_code(paperCode);

            l3 = client.queryForList("CheckDuplicatePaperCode", insert);

            CM_BranchSpecializationInfoGetter[] duplicate = (CM_BranchSpecializationInfoGetter[]) l3.toArray(new CM_BranchSpecializationInfoGetter[l3.size()]);

            if (duplicate.length > 0) {
                throw new Exception("Entry already exists");
            } else {
                client.insert("insertNewProgramPaperCode", insert);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
    * Method to Populate Program Paper Code
    */
    public CM_BranchSpecializationInfoGetter[] methodProgramPaperCodePopulate()
        throws Exception {
        try {
            List<?> li;

            li = client.queryForList("selectProgramPaperCode", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void methodDeleteFromProgramPaperCode(
        CM_BranchSpecializationInfoGetter deleteProgramPaperCode)
        throws Exception {
        try {
            client.delete("deleteFromProgramPaperCode", deleteProgramPaperCode);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public CM_BranchSpecializationInfoGetter[] methodPapersPopulate()
        throws Exception {
        try {
            List<?> li = null;

            li = client.queryForList("selectPaperCodes", null);

            return (CM_BranchSpecializationInfoGetter[]) li.toArray(new CM_BranchSpecializationInfoGetter[li.size()]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public CM_UniversityInfoGetter[] methodGetUniversityDetail(String universityId) {
        try {
            List li = null;
            li = client.queryForList("selectUniversityDetail", universityId);
            return (CM_UniversityInfoGetter[]) li.toArray(new CM_UniversityInfoGetter[li.size()]);
        } catch (Exception e) {
            System.out.println("exception in connectDImpl "+e.getStackTrace());
        }
		return null;
    }
}
