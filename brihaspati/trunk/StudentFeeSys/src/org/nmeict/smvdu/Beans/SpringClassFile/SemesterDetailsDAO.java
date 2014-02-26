package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.ExamCodeSetUp;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.SemesterMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
  * @author Shaista Bano
 */

public class SemesterDetailsDAO implements ISemesterDetailsDAO{

	private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();
	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	@Override
	public void addAllSemester(SemesterMaster semesterMaster) {
		 Session s = null;
	        try
	        {
	            s = HibernateDataSourceConnection.currentSession();
	            s.beginTransaction();
	            s.save(semesterMaster);
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
	public void update(List<SemesterMaster> semesterDetails) {
		 Session s = null;
	        try
	        {
	            s = HibernateDataSourceConnection.currentSession();
	            s.beginTransaction();
	            for(SemesterMaster sm : semesterDetails)
	            {
	            	OrgProfile op = new OrgProfile();
	            	OrgDepartmentType odt = new OrgDepartmentType();
	            	DegreeType dt = new DegreeType();
	            	ExamCodeSetUp ecs = new ExamCodeSetUp();
	            	op.setOrgId(new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
	            	odt.setOdtSeqNo(sm.getDepartmentCode());
	            	dt.setSeqNo(sm.getDegreeCode());
	            	ecs.setSeqNo(sm.getExamCode());
	            	sm.setOrgProfile(op);
	            	sm.setOrgDepartmentType(odt);
	            	sm.setDegreeType(dt);
	            	sm.setExamCodeSetUp(ecs);
	            	s.update(sm.getSemSeqNo().toString(),sm);
	            	s.flush();
	            	s.clear();
	            }
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
	public List<SemesterMaster> loadAllSemesterDetails(int semCode) {
		List<SemesterMaster> returnSemester = new ArrayList<SemesterMaster>();
		 Session s = null;
	        try
	        {
	            s = HibernateDataSourceConnection.currentSession();
	            s.beginTransaction();
                    //System.out.println("loadAll SemesterDetail=== DAO Class"+new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
	            ProjectionList projection = Projections.projectionList();
	            Criteria criteria = s.createCriteria(SemesterMaster.class,"sem");
	            criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
	            criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
	            criteria.createAlias("examCodeSetUp","ecs",CriteriaSpecification.LEFT_JOIN);
	            criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
	            
	            projection.add(Projections.property("sem.semesterName"));
	            projection.add(Projections.property("odt.orgDepartmentName"));
	            projection.add(Projections.property("ecs.semesterName"));
	            projection.add(Projections.property("ecs.semesterBegningDate"));
	            projection.add(Projections.property("ecs.semesterEndingDate"));
	            projection.add(Projections.property("degType.degreeName"));
	            projection.add(Projections.property("odt.odtSeqNo"));
	            projection.add(Projections.property("degType.seqNo"));
	            projection.add(Projections.property("ecs.seqNo"));
	            projection.add(Projections.property("sem.semSeqNo"));
	            projection.add(Projections.property("sem.semCreaterId"));
	            projection.add(Projections.property("sem.semCreaterDate"));
	            criteria.setProjection(projection);
	            criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getOrgProfileSession().getOrgId()));
	            if(semCode > 0)
	            {
	            	criteria.add(Restrictions.eq("degType.seqNo",semCode));
	            }
	            List list = (List) criteria.list();
	            Iterator i = list.iterator();
	            while(i.hasNext())
	            {
	            	Object[] o =(Object[])i.next();
	            	SemesterMaster sm = new SemesterMaster();
	            	sm.setSemesterName(o[0].toString());
                        //System.out.println("\n======== o[1]=="+o[1]);
	            	sm.setDepartmentName((String)o[1]);
	            	sm.setExamCodeName(o[2].toString());
	            	sm.setSemesterBegningDate((Date)o[3]);
	            	sm.setSemesterEndingDate((Date)o[4]);
	            	sm.setDegreeName(o[5].toString());
	            	sm.setDepartmentCode((Integer)o[6]);
	            	sm.setDegreeCode((Integer)o[7]);
	            	sm.setExamCode((Integer)o[8]);
	            	sm.setSemSeqNo((Integer)o[9]);
	            	sm.setSemCreaterId(o[10].toString());
	            	sm.setSemCreaterDate((Date)o[11]);
                        //System.out.println("\n======== o[2]=="+o[2]);
	            	returnSemester.add(sm);
	            }
                    //System.out.println("\n======== END======");
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
		
		return returnSemester;
	}

        @Override
        public void deleteSemester(SemesterMaster semesterMaster){
                Session s = null;
                try{
                   // System.out.print("\n\nDAOOOO ================Semester Delete");
	            s = HibernateDataSourceConnection.currentSession();
	            s.beginTransaction();
	            s.delete(semesterMaster);
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
}
