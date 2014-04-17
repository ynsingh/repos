/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.BranchMaster;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;
import org.nmeict.smvdu.Beans.SemesterMaster;
import org.nmeict.smvdu.Beans.StudentMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 *
 * @author Shaista Bano 
 */
public class StudentMasterUtil {
    
    public StudentMasterUtil () {}
    
    public void addStudentMaster(StudentMaster studentFeeMaster) {
        Session s = null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            s.save(studentFeeMaster);
            s.getTransaction().commit();
        }
        catch(Exception ex){
            s.getTransaction().rollback();
            ex.printStackTrace();
        }
        finally{
         	if(s.isOpen() == true)
        		HibernateDataSourceConnection.closeSession();
        }
		
    }
    
    public void updateStudentMaster(List<StudentMaster> studentMasterList) {
        Session s=null;
    	try {
                s = HibernateDataSourceConnection.currentSession();
                s.beginTransaction();  
                for(StudentMaster stm : studentMasterList){
                    OrgProfile op = new OrgProfile();
                    OrgDepartmentType odt = new OrgDepartmentType();
                    DegreeType dt = new DegreeType();
                    SemesterMaster sm = new SemesterMaster();
                    BranchMaster bm = new BranchMaster();

                    op.setOrgId(new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
                    odt.setOdtSeqNo(stm.getDepartmentCode());
                    dt.setSeqNo(stm.getDegreeCode());
                    sm.setSemSeqNo(stm.getSemCode());
                    bm.setBmSeqNo(stm.getBranchCode());
                    stm.setOrgProfile(op);
                    stm.setOrgDepartmentType(odt);
                    stm.setDegreeType(dt);
                    stm.setSemesterMaster(sm);
                    stm.setBranchMaster(bm);
                
                    s.update(stm.getFeeHeadCode().toString(),stm);
                    s.flush();
                    s.clear();
            
            }
            s.getTransaction().commit();
        }
        catch(Exception ex){
            s.getTransaction().rollback();
        }
	finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
        }
    }
    
    public List<StudentMaster> loadStudentFeeDetails(String entryId,OtherFeeHeadMaster ofm) {
        List<StudentMaster> returnFeeHeadValue = new ArrayList<StudentMaster>();
        Session s=null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            ProjectionList projection  = Projections.projectionList();
            Criteria criteria = s.createCriteria(StudentMaster.class,"sm");
            criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("semesterMaster","sem",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("branchMaster","bm",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("otherFeeHeadMaster","ofhm",CriteriaSpecification.LEFT_JOIN);
            projection.add(Projections.property("sm.entryNo"));
            projection.add(Projections.property("sm.studentOpbalAmount"));
            projection.add(Projections.property("odt.orgDepartmentName"));
            projection.add(Projections.property("odt.odtSeqNo"));
            projection.add(Projections.property("degType.degreeName"));
            projection.add(Projections.property("degType.seqNo"));
            projection.add(Projections.property("bm.branchName"));
            projection.add(Projections.property("bm.bmSeqNo"));
            projection.add(Projections.property("sem.semesterName"));
            projection.add(Projections.property("sem.semSeqNo"));
            projection.add(Projections.property("ofhm.feeHeadName"));
            projection.add(Projections.property("ofhm.feeHeadCode"));
            projection.add(Projections.property("ofhm.feeHeadValue"));
            projection.add(Projections.property("ofhm.feeHeadId"));
            criteria.setProjection(projection);
            criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
            criteria.add(Restrictions.eq("sm.entryNo",entryId));
            criteria.add(Restrictions.eq("degType.seqNo", ofm.getDegreeCode()));
            criteria.add(Restrictions.eq("bm.bmSeqNo", ofm.getBranchCode()));
            criteria.add(Restrictions.eq("sem.semSeqNo", ofm.getSemCode()));
            criteria.add(Restrictions.eq("ofhm.feeHeadCode", ofm.getFeeHeadCode()));
            List list = (List) criteria.list();
             
            Iterator i = list.iterator();
            while(i.hasNext()){
            	Object[] o = (Object[])i.next();
            	StudentMaster stm = new StudentMaster();
            	stm.setEntryNo(o[0].toString());
                stm.setStudentOpbalAmount((Double)o[1]);                
            	stm.setDepartmentName(o[2].toString());
            	stm.setDepartmentCode((Integer)o[3]);
            	stm.setDegreeName(o[4].toString());
            	stm.setDegreeCode((Integer)o[5]);
            	stm.setBranchName(o[6].toString());
            	stm.setBranchCode((Integer)o[7]);
            	stm.setSemesterName(o[8].toString());
            	stm.setSemCode((Integer)o[9]);
            	stm.setFeeHeadName(o[10].toString());
            	stm.setFeeHeadCode((Integer)o[11]);
            	stm.setFeeHeadValue((Double)o[12]);
            	stm.setNetBalence(((Double)o[12])-((Double)o[1]));
                stm.setFeeHeadId((Integer)o[13]);
            	returnFeeHeadValue.add(stm);
            }
            s.getTransaction().commit();
        }
	catch(Exception ex){
            s.getTransaction().rollback();
	}
        finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
        }
               
	return returnFeeHeadValue;
    }
    
    public List<StudentMaster> loadStudentFeeDetails(String entryId, int degreeCode, int branchCode, int semCode, int feeHeadCode) {
        List<StudentMaster> returnFeeHeadValue = new ArrayList<StudentMaster>();
        Session s=null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            ProjectionList projection  = Projections.projectionList();
            Criteria criteria = s.createCriteria(StudentMaster.class,"sm");
            criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("semesterMaster","sem",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("branchMaster","bm",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("otherFeeHeadMaster","ofhm",CriteriaSpecification.LEFT_JOIN);
            projection.add(Projections.property("sm.entryNo"));
            projection.add(Projections.property("sm.studentOpbalAmount"));
            projection.add(Projections.property("odt.orgDepartmentName"));
            projection.add(Projections.property("odt.odtSeqNo"));
            projection.add(Projections.property("degType.degreeName"));
            projection.add(Projections.property("degType.seqNo")); //Integer
            projection.add(Projections.property("bm.branchName"));
            projection.add(Projections.property("bm.bmSeqNo")); //Integer 
            projection.add(Projections.property("sem.semesterName"));
            projection.add(Projections.property("sem.semSeqNo")); //Integer
            projection.add(Projections.property("ofhm.feeHeadName")); 
            projection.add(Projections.property("ofhm.feeHeadCode")); //Integer 
            projection.add(Projections.property("ofhm.feeHeadValue"));//Double  
            projection.add(Projections.property("sm.creditLiability")); //Double
            projection.add(Projections.property("sm.debitLiability")); //Double
            projection.add(Projections.property("sm.smSeqId")); //Integer
            projection.add(Projections.property("ofhm.feeHeadId")); //Integer
                       
            criteria.setProjection(projection);
            criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
            criteria.add(Restrictions.eq("sm.entryNo",entryId));
            criteria.add(Restrictions.eq("degType.seqNo", degreeCode));
            criteria.add(Restrictions.eq("bm.bmSeqNo", branchCode));
            criteria.add(Restrictions.eq("sem.semSeqNo", semCode));
            criteria.add(Restrictions.eq("ofhm.feeHeadCode", feeHeadCode));
            List list = (List) criteria.list();
             
            Iterator i = list.iterator();
            while(i.hasNext()){
            	Object[] o = (Object[])i.next();
            	StudentMaster stm = new StudentMaster();
            	stm.setEntryNo(o[0].toString());
                //System.out.println("StudentMasterUtil class in Beans package=="+ o[0].toString());
                stm.setStudentOpbalAmount((Double)o[1]);                
            	stm.setDepartmentName(o[2].toString());
            	stm.setDepartmentCode((Integer)o[3]);
            	stm.setDegreeName(o[4].toString());
            	stm.setDegreeCode((Integer)o[5]);
            	stm.setBranchName(o[6].toString());
            	stm.setBranchCode((Integer)o[7]);
            	stm.setSemesterName(o[8].toString());
            	stm.setSemCode((Integer)o[9]);
            	stm.setFeeHeadName(o[10].toString());
            	stm.setFeeHeadCode((Integer)o[11]);
            	stm.setFeeHeadValue((Double)o[12]);
            	stm.setNetBalence(((Double)o[12])-((Double)o[1]));
                stm.setCreditLiability((Double)o[13]);
                stm.setDebitLiability((Double)o[14]);
                stm.setSmSeqId((Integer)o[15]);
                stm.setFeeHeadId((Integer)o[16]);
                
            	returnFeeHeadValue.add(stm);
            }
            s.getTransaction().commit();
        }
	catch(Exception ex){
            s.getTransaction().rollback();
	}
        finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
        }
               
	return returnFeeHeadValue;
    }
    
    public void update(StudentMaster stm){
        Session s=null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction(); 
            //s.update(s);
            s.update(stm.getSmSeqId().toString(),stm);
            //System.out.println("s="+s);
            s.getTransaction().commit();
            
            /*
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps = null;
            System.out.println("stm.getStudentOpbalAmount()="+stm.getStudentOpbalAmount()+"stm.getCreditLiability()="+stm.getCreditLiability()+
                    "stm.getDebitLiability()="+stm.getDebitLiability()+"stm.getFeeHeadCode()="+stm.getFeeHeadCode()+
                            "stm.getEntryNo()="+stm.getEntryNo());
            ps = c.prepareStatement("update student_master set student_opbal_amount=?,CR_of_liability=?, "
                    + "DR_of_liability=? where fee_head_code=? and entry_no=?");
                    
            ps.setDouble(1, stm.getStudentOpbalAmount());
            ps.setDouble(2, stm.getCreditLiability());
            ps.setDouble(3, stm.getDebitLiability());
            ps.setInt(4, stm.getFeeHeadCode());
            ps.setString(5, stm.getEntryNo());
            
            ps.executeUpdate();
            ps.close();
            c.close();
            */
         
        }
	catch(Exception ex){
            s.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+ex, ""));
	}
        
        finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
        }
    }
}
