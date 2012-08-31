package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CMaddMarksInfoGetter;
import in.ac.dei.edrp.client.GridDataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_manageMarks;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("serial")
public class CM_manageMarksImpl extends RemoteServiceServlet
    implements CM_manageMarks {
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();
    Idgetter id = new Idgetter();

    /**
     * method updated by devendra May 3rd
     */
    public List<GridDataBean> methodProgramList(String university_id,
        String entity_type, String entity_id, String program_id) throws Exception {
        String[] s = null;
        Integer[] total_marks = null;

        List<GridDataBean> list = new ArrayList<GridDataBean>();

        try {
            String cm = null;
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
            addMarksObject.setProgram_id(program_id);
            addMarksObject.setEntity_id(entity_id);

            /**
             * query updated
             */
            List<?> flagStatus = client.queryForList("checkFlagStatus",
                    addMarksObject);
            getDataObject = flagStatus.toArray(new CMaddMarksInfoGetter[flagStatus.size()]);

            if (getDataObject[0].getFlag_status().equalsIgnoreCase("E")) {
                /**
                     * getting number of final merit components
                 * query updated
                 */
                List<?> noOfDescriptions = client.queryForList("countDescription",
                        addMarksObject);

                getDataObject = noOfDescriptions.toArray(new CMaddMarksInfoGetter[noOfDescriptions.size()]);
                s = new String[Integer.parseInt(getDataObject[0].getDescription())];

                total_marks = new Integer[Integer.parseInt(getDataObject[0].getDescription())];
                /**
                 * getting description and maximum marks for final merit components
                 * query updated
                 */
                List<?> getDescriptions = client.queryForList("getCompDescription",
                        addMarksObject);                
                getDataObject = getDescriptions.toArray(new CMaddMarksInfoGetter[getDescriptions.size()]);               
                for (int i = 0; i < getDataObject.length; i++) {
                    s[i] = getDataObject[i].getDescription();
                    total_marks[i] = getDataObject[i].getTotal_marks();                   
                }

                list.add(new GridDataBean("Registration", "Test Number",
                        "call Merit", s, total_marks, "TotalMarks"));

                /**
                 * query updated
                 */
                List<?> AllStudents = client.queryForList("getAllStudentCallMeritMarks",
                        addMarksObject);
                getDataObject = AllStudents.toArray(new CMaddMarksInfoGetter[AllStudents.size()]);
                for (int i = 0; i < getDataObject.length; i++) {
                    String name = getDataObject[i].getRegistration_number();

                    /**
                     * to be changed to i-batis
                     * method updated
                     */
                    if (id.validateRegistrationNumber(entity_id, program_id,
                                getDataObject[i].getRegistration_number())) {
                        String[] lens = new String[s.length];
                        int sum = 0;

                        cm = getDataObject[i].getSum_actual_computed_marks();

                        if (cm == null) {
                            cm = "0";
                        }
                        list.add(new GridDataBean(name,
                                getDataObject[i].getTest_number(), cm, lens,
                                String.valueOf((sum + Double.parseDouble(cm)))));
                        
                    }
                    
                }
            } else if (getDataObject[0].getFlag_status().equalsIgnoreCase("F")) {
                throw new Exception("Final list has already been generated");
            } else if (getDataObject[0].getFlag_status().equalsIgnoreCase("T")) {
                throw new Exception("List with test numbers not generated yet");
            } else {
                throw new Exception("Test numbers have not been generated yet");
            }
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        } // catch ends

        return list;
    }

    /**
     * for getting total edit marks
     * method unchanged
     */
    public String[][] addMarks(String regnumber, String program_id,
        String entity_id, int len) {
        String[][] call = new String[len][2];

        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
            addMarksObject.setProgram_id(program_id);
            addMarksObject.setRegistration_number(regnumber);
            addMarksObject.setEntity_id(entity_id);

            /**
             * query unchanged
             */
            List<?> marksAttandance = client.queryForList("getMarksAndAttandanceForEdit",
                    addMarksObject);
            getDataObject = marksAttandance.toArray(new CMaddMarksInfoGetter[marksAttandance.size()]);

            int j = 0;

            for (int i = 0; i < getDataObject.length; i++) {
                if (getDataObject[i].getMarks() == null) {
                    call[i][0] = "0";
                    call[i][1] = "A";
                } else {
                    call[i][0] = getDataObject[i].getMarks();
                    call[i][1] = getDataObject[i].getAttended();
                }

                j++;
            }

            if (j < len) {
                while (j < len) {
                    call[j][0] = "0";
                    call[j][1] = "A";
                    j++;
                }
            }
        } catch (Exception e) {
            logObj.logger.error("Error message in addMarks" + e.getMessage());
        }

        return call;
    }

    /**
     * Method for getting component id
     */
    //Updated by Devendra May 3rd
    public String getComponent(String description, String entity_id,String program_id) {
        String s = null;


        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();

            addMarksObject.setDescription(description);
            addMarksObject.setProgram_id(program_id);
            addMarksObject.setEntity_id(entity_id);

            /**
             * query updated
             */
            List<?> compId = client.queryForList("getCompId", addMarksObject);
            getDataObject = compId.toArray(new CMaddMarksInfoGetter[compId.size()]);

            s = getDataObject[0].getComponent_id();
        } catch (Exception e) {
            logObj.logger.error("Error mesage is " + e.getMessage());
        }

        return s;
    }

    /**
     * method updated by Devendra May 3rd
     */
    public void methodAddFinalMarks(String user_id, String entity_id,
        String prog_id, String reg_no, String testnumber,
        String callMerit, String[] evalComp, String[] markslist,String[] attList) {
        //		String message="Marks have already been added!!!";
        Idgetter id = new Idgetter();
        Float total = Float.parseFloat(callMerit);
        String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                                                                             .substring(0,
                19));

        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
            addMarksObject.setUniversity_id(prog_id.substring(0, 4));

            /**
             * query unchanged
             */
            List<?> sessiondates = client.queryForList("getSessionDates",
                    addMarksObject);
            getDataObject = sessiondates.toArray(new CMaddMarksInfoGetter[sessiondates.size()]);

            addMarksObject.setProgram_id(prog_id);
            addMarksObject.setEntity_id(entity_id);
            addMarksObject.setRegistration_number(reg_no);
            addMarksObject.setInsert_time(date);
            addMarksObject.setCreator_id(user_id);
            addMarksObject.setStart_date(getDataObject[0].getStart_date());
            addMarksObject.setEnd_date(getDataObject[0].getEnd_date());
            addMarksObject.setModification_time(null);
            addMarksObject.setModifier_id(null);
            
           for (int i = 0; i < markslist.length; i++) {
                /**
                 * query updated
                 */
                addMarksObject.setComponent_id(getComponent(evalComp[i],entity_id, prog_id));
                addMarksObject.setMarks(markslist[i]);
                addMarksObject.setAttended(attList[i]);

                /**
                 * query updated
                 */
                client.insert("insertStudentFinalMarks1", addMarksObject);
                total = total + Float.parseFloat(markslist[i]);
            }

            /**
             * to be changed to iBatis
             * method updated
             */
            String[] values = id.getTestCosForReg(entity_id, prog_id,reg_no);

            addMarksObject.setTest_number(testnumber);

            /**
             * to be checked for error as total_marks was going as 0
             */
            addMarksObject.setFinal_marks(total);
            addMarksObject.setCos_value(values[1]);

            /**
             * query updated
             */
            client.insert("insertStudentFinalMeritList1", addMarksObject);
        } catch (Exception e) {
            logObj.logger.error(
                "Coming Exception in methodAddFinalMarks on server " + e);
        }
    }

    /**
     * method updated by Devendra May 3rd
     */
    public List<GridDataBean> methodEditGridDataList(String university_id,
        String entity_id, String program_id,String crieteria_value, String search_value)
        throws Exception {
        List<GridDataBean> list = new ArrayList<GridDataBean>();
        String[] s = null;
        Integer[] max = null;

        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
            addMarksObject.setProgram_id(program_id);
            addMarksObject.setEntity_id(entity_id);
            /**
             * query updated
             */
            List<?> flagStatus = client.queryForList("checkFlagStatus",
                    addMarksObject);
            getDataObject = flagStatus.toArray(new CMaddMarksInfoGetter[flagStatus.size()]);

            if (getDataObject[0].getFlag_status().equalsIgnoreCase("E")) {
                /**
                     * query updated
                     */
                List<?> noOfDescriptions = client.queryForList("countDescription",
                        addMarksObject);
                getDataObject = noOfDescriptions.toArray(new CMaddMarksInfoGetter[noOfDescriptions.size()]);

                s = new String[Integer.parseInt(getDataObject[0].getDescription())];

                max = new Integer[Integer.parseInt(getDataObject[0].getDescription())];

                /**
                 * query updated
                 */
                List<?> getDescriptions = client.queryForList("getCompDescription",
                        addMarksObject);
                getDataObject = getDescriptions.toArray(new CMaddMarksInfoGetter[getDescriptions.size()]);

                for (int i = 0; i < getDataObject.length; i++) {
                    s[i] = getDataObject[i].getDescription();
                    max[i] = getDataObject[i].getTotal_marks();
                }

                list.add(new GridDataBean("Registration", "Test Number",
                        "call Merit", s, max, "Total Marks"));

                if (crieteria_value == null) {
                    crieteria_value = "no";
                }

                List<?> studentData = null;

                if (crieteria_value.equalsIgnoreCase("Registration Number")) {
                    addMarksObject.setRegistration_number(search_value + "%");

                    /**
                     * query updated
                     */
                    studentData = client.queryForList("finalMarksDetailsWithRegNo",
                            addMarksObject);
                } else if (crieteria_value.equalsIgnoreCase("Test Number")) {
                    addMarksObject.setTest_number(search_value + "%");

                    /**
                     * query updated
                     */
                    studentData = client.queryForList("finalMarksDetailsWithTestNo",
                            addMarksObject);
                } else {
                    addMarksObject.setRegistration_number("%");

                    /**
                     * query updated
                     */
                    studentData = client.queryForList("finalMarksDetailsWithRegNo",
                            addMarksObject);
                }

                getDataObject = studentData.toArray(new CMaddMarksInfoGetter[studentData.size()]);
                for (int i = 0; i < getDataObject.length; i++) {
                    /**
                     * method unchanged
                     */
                    String[][] sam = addMarks(getDataObject[i].getRegistration_number(),
                            program_id, entity_id, s.length);

                    list.add(new GridDataBean(
                            getDataObject[i].getRegistration_number(),
                            getDataObject[i].getTest_number(),
                            getDataObject[i].getSum_actual_computed_marks(),
                            sam, getDataObject[i].getMarks()));
                }
            } else if (getDataObject[0].getFlag_status().equalsIgnoreCase("F")) {
                throw new Exception("Final list has already been generated");
            } else {
                throw new Exception("Test numbers have not been generated yet");
            }
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }

        return list;
    }

    /**
         * method updated by Devendra May 3rd
         */
    public void methodEditFinalMarks(String user_id, String entity_id,
        String prog_id,String reg_no, String testnumber,
        String callMerit, String[] evalComp, String[] markslist,String[] attList) {
        Float total = Float.parseFloat(callMerit);

        String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                                                                             .substring(0,
                19));

        try {
            CMaddMarksInfoGetter editMarksObject = new CMaddMarksInfoGetter();

            editMarksObject.setProgram_id(prog_id);
            editMarksObject.setEntity_id(entity_id);
            editMarksObject.setRegistration_number(reg_no);
            editMarksObject.setModification_time(date);
            editMarksObject.setModifier_id(user_id);

            for (int i = 0; i < markslist.length; i++) {
                /**
                     * method updated by Devendra may 3rd
                     */
                editMarksObject.setComponent_id(getComponent(evalComp[i],entity_id, prog_id));
                editMarksObject.setMarks(markslist[i]);
                editMarksObject.setAttended(attList[i]);

                /**
                 * query updated
                 */
                client.update("updateStudentFinalMarks1", editMarksObject);
                total = total + Float.parseFloat(markslist[i]);
            }

            editMarksObject.setTest_number(testnumber);

            /**
             * to be checked for error as total_marks was going as 0
             */
            editMarksObject.setFinal_marks(total);

            /**
             * query updated
             */
            client.update("updateStudentFinalMeritList1", editMarksObject);
        } catch (Exception e) {
            logObj.logger.error(
                "Coming Exception in methodAddFinalMarks on server " + e);
        }
    }

    /**
     * method updated by devendra May 3rd
     */
    public String[] methodPopulateSuggestion(String criteria,
        String program_id,String entity_id) {
        String[] arr = null;

        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter editMarksObject = new CMaddMarksInfoGetter();

            editMarksObject.setProgram_id(program_id);
            editMarksObject.setEntity_id(entity_id);

            List<?> getList = null;

            if (criteria.equalsIgnoreCase("Registration Number")) {
                /**
                     * query updated
                     */
                getList = client.queryForList("distinctRegNo", editMarksObject);
            } else {
                /**
                     * query updated
                     */
                getList = client.queryForList("distinctTestNo", editMarksObject);
            }

            getDataObject = getList.toArray(new CMaddMarksInfoGetter[getList.size()]);

            arr = new String[getList.size()];

            for (int i = 0; i < getDataObject.length; i++) {
                arr[i] = new String();
                arr[i] = getDataObject[i].getRegistration_number();
            }
        } catch (Exception e) {
            logObj.logger.error(
                "Exception in methodPopulateSuggestion on server " + e);
        }

        return arr;
    }

    /**
     * method not in use
     */
    public CM_entityInfoGetter[] methodEntityListEdit(String user_id,
        String entity_type) throws Exception {
        try {
            CM_entityInfoGetter em = new CM_entityInfoGetter();

            em.setEntity_id(user_id.substring(1, 5) + "%");
            em.setEntity_type(entity_type);

            List<?> li = null;

            li = client.queryForList("entityListmarks", em);

            return li.toArray(new CM_entityInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    /**
     * method unchanged
     */
    public CM_progMasterInfoGetter[] methodProgListEdit(String entity_id)
        throws Exception {
        try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            pm.setProgram_id(entity_id);

            List<?> li = null;

            /**
             * query unchanged
             */
            li = client.queryForList("programListmarks", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    /**
     * method updated
     */
    public CM_progMasterInfoGetter[] methodBranchListEdit(String entity_id,
        String prog_id) throws Exception {
        try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            pm.setProgram_id(prog_id);
            pm.setProgram_code(entity_id);
            pm.setUniversity_id(entity_id.substring(0, 4));

            List<?> li = null;

            /**
             * query updated
             */
            li = client.queryForList("branchListmarks", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    /**
     * method updated
     */
    public CM_progMasterInfoGetter[] methodBranchListAdd(String entity_id,
        String prog_id) throws Exception {
        try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            pm.setProgram_id(prog_id);
            pm.setProgram_code(entity_id);
            pm.setUniversity_id(prog_id.substring(0, 4));

            List<?> li = null;

            /**
             * query updated
             */
            li = client.queryForList("branchListmarksforadd", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    /**
     * method updated
     */
    public CM_progMasterInfoGetter[] methodProgListAdd(String entity_id)
        throws Exception {
        try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            pm.setProgram_id(entity_id);

            List<?> li = null;

            /**
             * query unchanged
             */
            li = client.queryForList("programListmarksforadd", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
            throw new Exception(e);
        }
    }

    /**
     * method added
     */
    public CM_progMasterInfoGetter[] methodSpecializationListEdit(
        String entity_id, String program_id, String branch_id) {
        try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            pm.setEntity_id(entity_id);
            pm.setProgram_id(program_id);
            pm.setBranchcode(branch_id);
            pm.setUniversity_id(program_id.substring(0, 4));

            List<?> li = null;

            /**
             * query added
             */
            li = client.queryForList("getspecializations", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
        }

        return null;
    }
    
    
    
    
	public CM_progMasterInfoGetter[] methodGetPrograms(String userid) {
		
		try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            
            pm.setUniversity_id(userid.substring(1, 5)+"%");

            List<?> li = null;
            
            

            /**
             * query added
             */
            li = client.queryForList("getProgramsFromProgramMaster", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
        }

        return null;
	}

	public CM_progMasterInfoGetter[] methodBranchListAdd(String value) {
		
		try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            
            pm.setUniversity_id(value.substring(0,4));
            pm.setProgram_id(value);

            List<?> li = null;

            /**
             * query added
             */
            li = client.queryForList("getBranchDetails", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
        }
		return null;
	}

	public CM_progMasterInfoGetter[] methodSpecializationListEdit(String program_id,
			String branch_id) {
		
		try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            
            pm.setUniversity_id(program_id.substring(0,4));
            pm.setProgram_id(program_id);
            pm.setBranchcode(branch_id);

            List<?> li = null;

            /**
             * query added
             */
            li = client.queryForList("getSpecializationsDetails", pm);

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error(e);
        }
		return null;
	}

	//updated by devendra May 3rd
	@SuppressWarnings("unchecked")
	public CM_progMasterInfoGetter[] getProgramComponents(String userid,
			String value,String name, String facregnum) {
		
		try {		
			ReportingFunction function = new ReportingFunction();
			ReportInfoGetter infoGetter = new ReportInfoGetter();		
			infoGetter.setUniversity_id(userid.substring(1,5));
			infoGetter.setProgram_id(value);
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();            
            CM_progMasterInfoGetter getter1 = new CM_progMasterInfoGetter();            
            String registration_number;           
            pm.setUniversity_id(userid.substring(1,5));
            pm.setProgram_id(value);
            getter1 = (CM_progMasterInfoGetter) client.queryForObject("getEntityIdforCombination",pm);            
            infoGetter.setEntity_id(getter1.getEntity_id());
            
            /**
             * query updated
             */
            String[] udate = function.getSessionDate(userid);
            
            infoGetter.setStart_date(udate[0]);
            infoGetter.setEnd_date(udate[1]);                       
            List<CM_progMasterInfoGetter> li = new ArrayList<CM_progMasterInfoGetter>();            
            List<CM_progMasterInfoGetter> list2 = new ArrayList<CM_progMasterInfoGetter>();
            String[] userdetail;
            
            CM_progMasterInfoGetter[] masterInfoGetter = null ;
            
            /*
             * when no search criteria is defined
             */
            if((name.equalsIgnoreCase(""))&&(facregnum.equalsIgnoreCase(""))){
            	
            	li = client.queryForList("getRegistrations",pm);
                masterInfoGetter = new CM_progMasterInfoGetter[li.size()];
                int i=0;
                
                for(CM_progMasterInfoGetter getter:li){
                	
                	registration_number = getter.getRegistration_number();
                	
                	list2 = client.queryForList("getStudentsforRegistration",registration_number);
                	
                	userdetail = new String[] { list2.get(0).getFac_form_number(),
                			list2.get(0).getAplicant_name(), list2.get(0).getFather_name(),
                			list2.get(0).getDateofbirth(), list2.get(0).getCategory(),
                			list2.get(0).getGender()};
                	
                	 masterInfoGetter[i] = new CM_progMasterInfoGetter(registration_number,userdetail);
                	
                	i++;           	

                }
                
                return masterInfoGetter;
            	
            }else if((facregnum.length()>0)&&(name.length()>0)){
            	
            	
            	pm.setFac_form_number(facregnum);
            	pm.setStudent_name(name+"%");
            	
            	list2 = client.queryForList("getSearchByfacregnumbername",pm);
            	
            	return list2.toArray(new CM_progMasterInfoGetter[list2.size()]);
            	
            }else if(name.length()>0){
            	
            	
            	pm.setStudent_name(name+"%");
            	
            	list2 = client.queryForList("getSearchByName",pm);
            	
            	return list2.toArray(new CM_progMasterInfoGetter[list2.size()]);
            	
            }else if(facregnum.length()>0){
            	
            	
            	pm.setFac_form_number(facregnum);
            	
            	list2 = client.queryForList("getSearchByfacregnumber",pm);
            	
            	return list2.toArray(new CM_progMasterInfoGetter[list2.size()]);
            	
            }
                                                           
        } catch (Exception e) {
            logObj.logger.error(e);
        }
        
        return null;
	}

	//Updated by Devendra May 3rd
	@SuppressWarnings("unchecked")
	public List<List<CM_progMasterInfoGetter>> getApplicantsDetails(String value,
			String value2, String value3) {
		try {
            CM_progMasterInfoGetter pm = new CM_progMasterInfoGetter();
            pm.setProgram_id(value);
            List<CM_progMasterInfoGetter> li = new ArrayList<CM_progMasterInfoGetter>();            
            List <CM_progMasterInfoGetter> list = new ArrayList<CM_progMasterInfoGetter>();            
            List<CM_progMasterInfoGetter> list1= new ArrayList<CM_progMasterInfoGetter>();

            /**
             * query added
             */
            li = client.queryForList("getAllAplicants", pm);
            
            for(CM_progMasterInfoGetter obj:li){
            	
            	obj.getRegistration_number();
            	
            	
            	list = client.queryForList("getStudentMarksDetails",obj);
            	
            	
            	list1.addAll(list);
            	
            }

            List<List<CM_progMasterInfoGetter>> studentMarksDetails = new ArrayList<List<CM_progMasterInfoGetter>>();
            studentMarksDetails.add(li);
            
            return studentMarksDetails;
        } catch (Exception e) {
            logObj.logger.error(e);
        }
		return null;
	}

	
	public CM_progMasterInfoGetter[] getApplicantsDetails(
			String registration_number) {
		
		try {
			
			List<?> li = null;

            /**
             * query added
             */
            li = client.queryForList("getComponentCombination", registration_number);
            

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		
		return null;
	}

	public CM_progMasterInfoGetter[] getcomponentWeightage(
			String registration_number, String component_id) {
		
		
		CM_progMasterInfoGetter getter = new CM_progMasterInfoGetter();
		
		getter.setRegistration_number(registration_number);
		getter.setComponent_id(component_id);
		
		try {
			
			List<?> li = null;

            /**
             * query added
             */
            li = client.queryForList("getSpecialWeightageComponents", getter);
            

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		
		return null;
	}

	public CM_progMasterInfoGetter[] getPaperCodes(String registration_number) {
		
		try {
			
			List<?> li = null;

            /**
             * query added
             */
            li = client.queryForList("getPapersComponents", registration_number);
            

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		
		return null;
	}

	public CM_progMasterInfoGetter[] getExcelComponents(String program_id,int value) {
			try {
			
			List<?> li = null;
			
			CM_progMasterInfoGetter getter = new CM_progMasterInfoGetter();
			
			getter.setProgram_id(program_id);
			getter.setUniversity_id(program_id.substring(0,4));
			
			if(value==0){				
				/**
	             * query added
	             */
	            li = client.queryForList("getexcelComponents", getter);				
			}else if(value==1){
				
				/**
	             * query added
	             */
	            li = client.queryForList("getDefinedExcelComponents", getter);
				
			}
			return li.toArray(new CM_progMasterInfoGetter[li.size()]);
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		
		return null;
	}

	public String defineComponents(String[] univ, String program_id) {
		try {
			
			CM_progMasterInfoGetter getter = new CM_progMasterInfoGetter();
			
			getter.setProgram_id(program_id);
			getter.setUniversity_id(program_id.substring(0,4));
			
			
			for(int i=0;i<univ.length;i++){
				
				getter.setComponent_id(univ[i]);			
				
				 /**
	             * query added
	             */
	           client.insert("insertExcelComponents", getter);
	            
			}

           return "success";
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		
		
		return "failure";
	}

	@Override
	public String deleteComponents(String[] univ, String program_id) {
		try {
			
			CM_progMasterInfoGetter getter = new CM_progMasterInfoGetter();
			
			getter.setProgram_id(program_id);
			getter.setUniversity_id(program_id.substring(0,4));
			
			
			for(int i=0;i<univ.length;i++){
				
				getter.setComponent_id(univ[i]);			
				
				 /**
	             * query added
	             */
	           client.delete("deleteExcelComponents", getter);
	            
			}

           return "success";
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		return null;
	}

	public CM_progMasterInfoGetter[] methodGetDefinedPrograms(String userId) {
		try {
			
			List<?> li = null;
			
			String university_id = userId.substring(1,5);

            /**
             * query added
             */
            li = client.queryForList("getDefinedPrograms", university_id);          

            return li.toArray(new CM_progMasterInfoGetter[li.size()]);
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}
		
		
		return null;
	}
}
