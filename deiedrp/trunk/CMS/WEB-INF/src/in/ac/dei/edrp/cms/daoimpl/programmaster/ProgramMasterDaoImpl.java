/*
 * @(#) ProgramMasterDaoImpl.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.daoimpl.programmaster;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programmaster.ProgramMasterDao;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


/**
 * Implementation file for ProgramMasterDao
 * @author Manpreet Kaur
 * @date 24-02-2011
 * @version 1.0
 */
public class ProgramMasterDaoImpl extends SqlMapClientDaoSupport
    implements ProgramMasterDao {
    private static Logger logObj = Logger.getLogger(ProgramMasterDaoImpl.class);
    TransactionTemplate transactionTemplate = null;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Method to get list of program modes available in university
     * @param userId user id of the user logged in
     * @return List<ProgramInfoGetter> list of program modes in a university
     */
    @SuppressWarnings("unchecked")
    public List<ProgramMasterInfoGetter> methodUniversityProgramMode(
        String userId) throws Exception {
        try {
            String universityId = userId;
            List<ProgramMasterInfoGetter> modeListInUniv;
            modeListInUniv = getSqlMapClientTemplate()
                                 .queryForList("ProgMasterInfo.uniMode",
                    universityId);

            return modeListInUniv;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method to get list of program types available in university
     * @param userId user id of the user logged in
     * @return List<ProgramInfoGetter> list of program types in a university
     */
    @SuppressWarnings("unchecked")
    public List<ProgramMasterInfoGetter> methodUniversityProgramType(
        String userId) throws Exception {
        try {
            String universityId = userId;
            List<ProgramMasterInfoGetter> typeListInUniv;
            typeListInUniv = getSqlMapClientTemplate()
                                 .queryForList("ProgMasterInfo.uniType",
                    universityId);

            return typeListInUniv;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for getting list of programs added under university
     * @param userId user id of the user logged in
     * @return List<ProgramInfoGetter> list of programs in a university
     */
    @SuppressWarnings("unchecked")
    public List<ProgramMasterInfoGetter> methodprogList(String userId) {
        try {
            String universityId = userId;
            ProgramMasterInfoGetter CMPMIG = new ProgramMasterInfoGetter();
            CMPMIG.setUniversityId(universityId + "%");

            List<ProgramMasterInfoGetter> list = getSqlMapClientTemplate()
                                                     .queryForList("ProgMasterInfo.selectProgList",
                    CMPMIG);

            return list;
        } catch (Exception e) {
            System.out.println(e);
            logObj.error("exception in methodprogList " + e);
        }

        return null;
    }

    /**
     * Method for getting list of branches that can be associated for programs under university
     * @param userId user id of the user logged in
     * @return List<ProgramMasterInfoGetter> list of branches
     */
    @SuppressWarnings("unchecked")
    public List<ProgramMasterInfoGetter> methodBranchList(String userId)
        throws Exception {
        try {
            String universityId = userId;

            List<ProgramMasterInfoGetter> branchList = null;

            branchList = getSqlMapClientTemplate()
                             .queryForList("ProgMasterInfo.branchList",
                    universityId);

            return branchList;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for getting list of specializations that can be associated for programs under university
     * @param userId user id of the user logged in
     * @return List<ProgramMasterInfoGetter> list of specializations
     */
    @SuppressWarnings({"unchecked"
    })
    public List<ProgramMasterInfoGetter> methodSpecList(String userId)
        throws Exception {
        try {
            String universityId = userId;

            List<ProgramMasterInfoGetter> specList = null;

            specList = getSqlMapClientTemplate()
                           .queryForList("ProgMasterInfo.specList",
                    universityId);

            return specList;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for getting list of semesters
     * @param userId user id of the user logged in
     * @return List<ProgramMasterInfoGetter> list of specializations
     */
    @SuppressWarnings({"unchecked"
    })
    public List<ProgramMasterInfoGetter> methodSemesterList(String userId)
        throws Exception {
        try {
            String universityId = userId;

            List<ProgramMasterInfoGetter> specList = null;

            specList = getSqlMapClientTemplate()
                           .queryForList("ProgMasterInfo.semList", universityId);

            return specList;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for adding new program under university
     * @param userId
     * @param progInfo
     * @return String
     */
    public String methodAddProgDetails(final String userId,
        final ProgramMasterInfoGetter progInfo) throws Exception {
        transactionTemplate.execute(new TransactionCallback() {
                Object savepoint = null;

                public String doInTransaction(TransactionStatus status) {
                    try {
                        String universityId = userId;
                        String code = "PRGMID";

                        progInfo.setUniversityId(universityId);
                        progInfo.setSystemCode(code);

                        List<?> li;
                        li = getSqlMapClientTemplate()
                                 .queryForList("ProgMasterInfo.sysvalue",
                                progInfo);

                        ProgramMasterInfoGetter sysInfo = (ProgramMasterInfoGetter) li.get(0);

                        int nextId = Integer.parseInt(sysInfo.getProgramId()) +
                            1;

                        String formatted = String.format("%07d", nextId);

                        //creating savepoint
                        savepoint = status.createSavepoint();

                        progInfo.setSystemValue(formatted.substring(4, 7));
                        progInfo.setProgramId(formatted);
                        //updating system_values table
                        getSqlMapClientTemplate()
                            .update("ProgMasterInfo.updatesysvalue", progInfo);

                        // adding basic details
                        getSqlMapClientTemplate()
                            .insert("ProgMasterInfo.insertintoprogmaster",
                            progInfo);

                        StringTokenizer startdate = new StringTokenizer(progInfo.getStartdate(),
                                "|");

                        //adding duration details
                        while (startdate.hasMoreTokens()) {
                            progInfo.setStartdate(startdate.nextToken());
                            getSqlMapClientTemplate()
                                .insert("ProgMasterInfo.insertprogduration",
                                progInfo);
                        }

                        StringTokenizer branches = new StringTokenizer(progInfo.getBranchcode(),
                                "|");
                        StringTokenizer specs = new StringTokenizer(progInfo.getSpecializationCode(),
                                "|");
                        StringTokenizer activeSem = new StringTokenizer(progInfo.getActiveSemester(),
                                "|");

                        //adding branch details
                        while (branches.hasMoreTokens()) {
                            progInfo.setBranchcode(branches.nextToken());
                            progInfo.setSpecializationCode(specs.nextToken());

                            try {
                                progInfo.setActiveSemester(activeSem.nextToken());

                                if (progInfo.getActiveSemester()
                                                .equalsIgnoreCase(";")) {
                                    throw new NoSuchElementException();
                                } else {
                                    getSqlMapClientTemplate()
                                        .insert("ProgMasterInfo.insertbranchdetail",
                                        progInfo);
                                }
                            } catch (NoSuchElementException e) {
                                progInfo.setActiveSemester(null);
                                getSqlMapClientTemplate()
                                    .insert("ProgMasterInfo.insertbranchdetail1",
                                    progInfo);
                            }
                        }

                        getSqlMapClientTemplate().getDataSource().getConnection()
                            .commit();
                    }catch (DataIntegrityViolationException e) {
                    	 logObj.error("methodAddProgDetails  " + e);
                         System.out.println(e);
                         status.rollbackToSavepoint(savepoint);
                         throw new MyException("DBF"+e);
					}
                    
                    catch (Exception ex) {
                        logObj.error("methodAddProgDetails  " + ex);
                        System.out.println(ex);
                        status.rollbackToSavepoint(savepoint);
                        throw new MyException("F"+ex);
                    }

                    return userId;
                }
            });

        return userId;
    }

    /**
     * Method for getting program details for manage screen
     * @param userId
     * @param object
     * @param criteria
     * @return List of ProgramMasterInfoGetter type
     */
    @SuppressWarnings("unchecked")
    public List<ProgramMasterInfoGetter> methodProgMasterDetailsForManage(
        String userId, ProgramMasterInfoGetter object, String criteria)
        throws Exception {
        try {
            List<ProgramMasterInfoGetter> li = null;

//            if (criteria.equalsIgnoreCase("code")) {
//                li = getSqlMapClientTemplate()
//                         .queryForList("ProgMasterInfo.progBasicDetailFromCode",
//                        object);
//            } else {
                li = getSqlMapClientTemplate()
                         .queryForList("ProgMasterInfo.progBasicDetail", object);
//            }

            return li;
        } catch (Exception e) {
            System.out.println(e);
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for getting duration details for given program
     * @param progInfo
     * @return List of ProgramMasterInfoGetter type
     */
    @SuppressWarnings("unchecked")
    public List<ProgramMasterInfoGetter> methodProgDurationDetailsForManage(
        ProgramMasterInfoGetter progInfo) throws Exception {
        try {
            List<ProgramMasterInfoGetter> li = null;
            ProgramMasterInfoGetter progId = null;
            progInfo.setUniversityId(progInfo.getCreatorId().substring(1, 5));

            if (progInfo.getProgramCode() != null) {
                progInfo.setProgramId(progInfo.getUniversityId() + "%");
                progId = (ProgramMasterInfoGetter) getSqlMapClientTemplate()
                                                       .queryForObject("ProgMasterInfo.progIdFromCode",
                        progInfo);

                if (progId != null) {
                    progInfo.setProgramId(progId.getProgramId());
                } else {
                    throw new MyException("fail");
                }
            }
            li = getSqlMapClientTemplate()
                     .queryForList("ProgMasterInfo.progDurationDetail", progInfo);

            return li;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for getting program's branch and specialization details for editing purpose
     * @param object
     * @param criteria
     * @return List of ProgramMasterInfoGetter type
     */
    @SuppressWarnings({"unchecked"
    })
    public List<ProgramMasterInfoGetter> methodProgBranchSpecDetailsForManage(
        ProgramMasterInfoGetter object, String criteria)
        throws Exception {
        try {
            List<ProgramMasterInfoGetter> li = null;

            if (criteria.equalsIgnoreCase("code")) {
                li = getSqlMapClientTemplate()
                         .queryForList("ProgMasterInfo.progBranchSpecDetailWithCode",
                        object);
            } else {
                li = getSqlMapClientTemplate()
                         .queryForList("ProgMasterInfo.progBranchSpecDetail",
                        object);
            }

            return li;
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for updating program details
     * @param userId
     * @param object
     */
    public void methodUpdateProgBasicDetails(String userId,
        ProgramMasterInfoGetter object) throws Exception {
        try {
            object.setModifierId(userId);
            getSqlMapClientTemplate()
                .update("ProgMasterInfo.updateProgBasicDetail", object);
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for deleting program details
     * @param programId
     * @return String error string
     */
    public String methodDeleteProg(final String programIds)
        throws Exception {
        transactionTemplate.execute(new TransactionCallback() {
                Object savepoint = null;
                String error = "";

                public String doInTransaction(TransactionStatus status) {
                    try {
                        StringTokenizer progId = new StringTokenizer(programIds,"|");

                        while (progId.hasMoreTokens()) {
                            String programId = progId.nextToken();
                            System.out.println(programId);

                            ProgramMasterInfoGetter object = new ProgramMasterInfoGetter();
                            object.setProgramId(programId);
                            savepoint = status.createSavepoint();

                            object.setStartdate("%");
                            getSqlMapClientTemplate()
                                .delete("ProgMasterInfo.deleteProgDurationDetails",
                                object);
                            object.setBranchcode("%");
                            object.setSpecializationCode("%");
                            getSqlMapClientTemplate()
                                .delete("ProgMasterInfo.deleteProgBranchDetails",
                                object);
                            getSqlMapClientTemplate()
                                .delete("ProgMasterInfo.deleteProgBasicDetails",
                                object);
                            getSqlMapClientTemplate().getDataSource()
                                .getConnection().commit();
                        }
                    } catch (MySQLIntegrityConstraintViolationException ie) {
                        logObj.error(ie);
                        error = "ie";
                        
                        status.rollbackToSavepoint(savepoint);
                        throw new MyException(error);
                    } catch (Exception e) {
                        logObj.error(e);
                        error = "ie";
                        status.rollbackToSavepoint(savepoint);
                        throw new MyException(error);
                    }

                    return error;
                }
            });

        return null;
    }

    /**
     * Method for deleting program duration detail
     * @param object
     */
    public void methodProgDurationDelete(ProgramMasterInfoGetter object)
        throws Exception {
        try {
            StringTokenizer startDate = new StringTokenizer(object.getStartdate(),
                    "|");
            StringTokenizer progId = new StringTokenizer(object.getProgramId(),
                    "|");

            while (startDate.hasMoreTokens()) {
                object.setProgramId(progId.nextToken());
                object.setStartdate(startDate.nextToken());

                getSqlMapClientTemplate()
                    .delete("ProgMasterInfo.deleteProgDurationDetails", object);
            }
        }catch (DataIntegrityViolationException ie) {
            logObj.error(ie);            
            throw new MyException("ie");
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for deleting a branch related to given program
     * @param object
     * @return String
     */
    public String methodBranchDelete(ProgramMasterInfoGetter object)
        throws Exception {
        try {
            StringTokenizer branches = new StringTokenizer(object.getBranchcode(),
                    "|");
            StringTokenizer specs = new StringTokenizer(object.getSpecializationCode(),
                    "|");
            StringTokenizer progId = new StringTokenizer(object.getProgramId(),
                    "|");

            while (branches.hasMoreTokens()) {
                object.setBranchcode(branches.nextToken());
                object.setSpecializationCode(specs.nextToken());
                object.setProgramId(progId.nextToken());
                getSqlMapClientTemplate()
                    .delete("ProgMasterInfo.deleteProgBranchDetails", object);
            }
        }catch (DataIntegrityViolationException ie) {
            logObj.error(ie);            
            throw new MyException("ie");
        }  catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }

        return null;
    }

    /**
     * Method to update program duration details
     * @param object
     */
    public void methodUpdateProgDurationDetails(ProgramMasterInfoGetter object)
        throws Exception {
        try {
            getSqlMapClientTemplate()
                .update("ProgMasterInfo.updateProgDurationDetails1", object);
            getSqlMapClientTemplate()
                .update("ProgMasterInfo.updateProgDurationDetails2", object);
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for add new start date for program
     *  @param object
     */
    public void methodAddStartDate(ProgramMasterInfoGetter object)
        throws Exception {
        try {
            getSqlMapClientTemplate()
                .insert("ProgMasterInfo.insertprogduration", object);
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }
    }

    /**
     * Method for adding new branch and specialization under program
     *  @param object
     *  @return String
     */
    public String methodAddAnotherBranch(ProgramMasterInfoGetter progInfo)
        throws Exception {
        try {
            ProgramMasterInfoGetter progId = null;
            progInfo.setUniversityId(progInfo.getCreatorId().substring(1, 5));

            if (progInfo.getProgramId() == "") {
                progInfo.setProgramId(progInfo.getUniversityId() + "%");
                progId = (ProgramMasterInfoGetter) getSqlMapClientTemplate()
                                                       .queryForObject("ProgMasterInfo.progIdFromCode",
                        progInfo);

                if (progId != null) {
                    progInfo.setProgramId(progId.getProgramId());
                } else {
                    return "fail";
                }
            }

            if (progInfo.getActiveSemester().equalsIgnoreCase(";")) {
                progInfo.setActiveSemester(null);
                getSqlMapClientTemplate()
                    .insert("ProgMasterInfo.insertbranchdetail1", progInfo);
            } else {
                getSqlMapClientTemplate()
                    .insert("ProgMasterInfo.insertbranchdetail", progInfo);
            }
        } catch (Exception e) {
            logObj.error(e);
            throw new Exception(e);
        }

        return null;
    }
    /**
     * Method for getting domain
     *  @param object
     *  @author Mandeep
     *  @return String
     */
	public List<ProgramMasterInfoGetter> getPrograDomainList(
			ProgramMasterInfoGetter input) {

        List<ProgramMasterInfoGetter> domainList = null;
        try {

            domainList = getSqlMapClientTemplate().queryForList("ProgMasterInfo.domainList",input);

            return domainList;
        } catch (Exception e) {
            logObj.error(e);
            
        }
        return domainList;
	}
}
