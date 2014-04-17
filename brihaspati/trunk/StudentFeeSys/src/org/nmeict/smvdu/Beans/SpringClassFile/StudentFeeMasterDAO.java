package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;
import org.nmeict.smvdu.Beans.StudentMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

public class StudentFeeMasterDAO implements IStudentFeeMasterDAO{

	
	private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();
	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	@Override
	public void addStudentFeeMaster(StudentMaster studentFeeMaster) {
		 Session s = null;
	        try
	        {
	            s = HibernateDataSourceConnection.currentSession();
	            s.beginTransaction();
	            s.save(studentFeeMaster);
	            s.getTransaction().commit();
	        }
	        catch(Exception ex)
	        {
	            s.getTransaction().rollback();
	            ex.printStackTrace();
	        }
	        finally
	        {
	        	if(s.isOpen() == true)
	        		HibernateDataSourceConnection.closeSession();
	        }
		
		
	}

	@Override
	public void update(List<StudentMaster> studentFeeMaster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StudentMaster> loadAllFeeDetails() {
		List<StudentMaster> returnFeeHeadValue = new ArrayList<StudentMaster>();
		Session s=null;
		try
		{
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
            criteria.setProjection(projection);
            criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
            List list = (List) criteria.list();
            Iterator i = list.iterator();
            while(i.hasNext())
            {
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
            	returnFeeHeadValue.add(stm);
            }
            s.getTransaction().commit();
		}
		catch(Exception ex)
		{
			s.getTransaction().rollback();
		}
		finally
		{
			if(s.isOpen()==true)
	        HibernateDataSourceConnection.closeSession();
		}
		return returnFeeHeadValue;
	}
	
	public List<StudentMaster> loadStudentDetail(String entryId){
        	return null;
	}           
		
}
