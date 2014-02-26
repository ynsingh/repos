package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.ExamCodeSetUp;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 * @author Shaista Bano
 */

public class ExamcodeSetupDAO implements IExamcodeSetupDAO{

	private HibernateDataSourceConnection hibernateSessionFactory;

	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	
	@Override
	public void addExamcodeSetup(ExamCodeSetUp examCodeSetup) {
		Session s = null;
        try
        {
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            s.save(examCodeSetup);
            s.getTransaction().commit();
            HibernateDataSourceConnection.closeSession();
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
	public void update(List<ExamCodeSetUp> examCodeSetup) {
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			int examUpdateFlush = 0;
			//System.out.println("Calling "+new OrgProfileSessionDetails().getOrgProfileSession().getOrgId()+" : "+examCodeSetup.size());
			for(ExamCodeSetUp ecs : examCodeSetup)
			{
				ecs.setOrgProfile(new OrgProfileSessionDetails().getOrgProfileSession());
				DegreeType dt = new DegreeType();
				OrgDepartmentType odt = new OrgDepartmentType();
				dt.setSeqNo(ecs.getEcsDegreeType());
				odt.setOdtSeqNo(ecs.getEcsOrgDepartmentType());
				ecs.setDegreeType(dt);
				ecs.setOrgDepartmentType(odt);
				s.update(ecs.getSeqNo().toString(),ecs);  
				examUpdateFlush++;
				if(examUpdateFlush % 50 == 0)
				{
					s.flush();
					s.clear();
				}
				//System.out.println("calling update");
				//System.out.println("------> "+ecs.getEcsDegreeType()+" : "+ecs.getSemesterName()+" : "+ecs.getOrgProfile().getOrgId());
			}
            s.getTransaction().commit();
            HibernateDataSourceConnection.closeSession();
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
		
	}

	@Override
	public List<ExamCodeSetUp> loadAllExamcodeSetup(int ecsCode) {
		Session s = null;
		List<ExamCodeSetUp> retuECS = new ArrayList<ExamCodeSetUp>();
        try
        {
        	List kl;
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            //System.out.println("loadAllExamcodeSetup method DAO OrgId()======="+new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
            Criteria criteria = s.createCriteria(ExamCodeSetUp.class,"ecs");
            criteria.createAlias("orgProfile","org",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("degreeType","dType",CriteriaSpecification.LEFT_JOIN).
            setProjection(Projections.projectionList().
            		add(Projections.property("dType.degreeName")).
            		add(Projections.property("odt.orgDepartmentName")).
            		add(Projections.property("ecs.semesterBegningDate")).
            		add(Projections.property("ecs.semesterEndingDate")).
            		add(Projections.property("ecs.semesterName")).
            		add(Projections.property("dType.seqNo")).
            		add(Projections.property("ecs.seqNo")).
            		add(Projections.property("odt.odtSeqNo")));
            criteria.add(Restrictions.eq("org.orgId",new OrgProfileSessionDetails().getOrgProfileSession().getOrgId()));
            if(ecsCode>0)
            {
            	criteria.add(Restrictions.eq("dType.seqNo",ecsCode));
            }
            
            kl = criteria.list();
            //System.out.print("\n\n ExamCodeSetup DAO====List"+kl);
            Iterator ki = kl.iterator();
            while(ki.hasNext())
            {
            	Object[] o = (Object[]) ki.next();
                //System.out.print("\n\nooooooooooooooo==="+(String)o[1]);
            	ExamCodeSetUp ecs = new ExamCodeSetUp();
            	ecs.setDegreeName(o[0].toString());
            	ecs.setDepartmentName((String)o[1]);
            	ecs.setSemesterBegningDate((Date) o[2]); 
            	ecs.setSemesterEndingDate((Date) o[3]); 
            	ecs.setSemesterName(o[4].toString()); 
           	ecs.setEcsDegreeType((Integer)o[5]);
            	ecs.setSeqNo((Integer)o[6]);
            	ecs.setEcsOrgDepartmentType((Integer)o[7]);
            	//System.out.println("Degree Name : "+o[0].toString()+" : "+o[4].toString()); 
            	retuECS.add(ecs); 
            }
            //System.out.println("\n======retuECS="+retuECS.size());
            
            s.getTransaction().commit();
            HibernateDataSourceConnection.closeSession();
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
	
		return retuECS;
	}

}
