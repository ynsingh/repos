package org.nmeict.smvdu.Beans.SpringClassFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.BranchMaster;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

public class OrgBranchDAO implements IOrgBranchDAO{

	private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();
	
	@Override
	public void addOrgBranch(BranchMaster branchMaster) {
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
            s.save(branchMaster); 
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

		
	}

	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	@Override
	public void update(List<BranchMaster> branchMasterList) {
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			for(BranchMaster bm : branchMasterList)
			{
				OrgProfile op = new OrgProfile();
				DegreeType dt = new DegreeType();
				OrgDepartmentType odt = new OrgDepartmentType();
				op.setOrgId(new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
				dt.setSeqNo(bm.getDegreeTypeCode());
				odt.setOdtSeqNo(bm.getDepartmentTypeCode());
				bm.setOrgProfile(op);
				bm.setDegreeType(dt);
				bm.setOrgDepartmentType(odt);
				s.update(bm.getBmSeqNo().toString(),bm);
				s.flush();
				s.clear();
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

		
	}

	@Override
	public List<BranchMaster> loadAllBranchList(int bCode) { 
		Session s=null;
		List<BranchMaster> returnBranchMasterList = new ArrayList<BranchMaster>();
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			Criteria criteria = s.createCriteria(BranchMaster.class,"obm");
			criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("obm.branchName"));
			projection.add(Projections.property("odt.orgDepartmentName"));
			projection.add(Projections.property("degType.degreeName"));
			projection.add(Projections.property("obm.bmDegreeCreaterId"));
			projection.add(Projections.property("obm.bmCreaterDate"));
			projection.add(Projections.property("obm.bmSeqNo"));
			projection.add(Projections.property("odt.odtSeqNo"));
			projection.add(Projections.property("degType.seqNo"));
			criteria.setProjection(projection);
			criteria.add(Restrictions.eq("obm.orgProfile.orgId",new OrgProfileSessionDetails().getOrgProfileSession().getOrgId()));
			if(bCode > 0)
			{
				criteria.add(Restrictions.eq("degType.seqNo",bCode));
			}
			List list = (List)criteria.list();
			Iterator i = list.iterator();
			System.out.println("Branch Size : "+list.size()); 
			while(i.hasNext())
			{
				Object[] o = (Object[]) i.next(); 
				BranchMaster bm = new BranchMaster();
				bm.setBranchName(o[0].toString());
				bm.setDepartmentName(o[1].toString());
				bm.setDegreeName(o[2].toString());
				bm.setBmDegreeCreaterId(o[3].toString());
				bm.setBmCreaterDate((Date)o[4]);
				bm.setBmSeqNo((Integer)o[5]);
				bm.setDepartmentTypeCode((Integer)o[6]);
				bm.setDegreeTypeCode((Integer)o[7]);
				returnBranchMasterList.add(bm);
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
		return returnBranchMasterList; 
	}

        @Override
	public void deleteOrgBranch(BranchMaster branchMaster){
            Session s=null;
            
            try {
        	s = HibernateDataSourceConnection.currentSession();
		s.beginTransaction();
                s.delete(branchMaster); 
                s.getTransaction().commit();
            }
            catch(Exception ex)	{
                s.getTransaction().rollback();
            }
            
            finally {
        	if(s.isOpen()==true)
                    HibernateDataSourceConnection.closeSession();
            }
        }
        
    }
