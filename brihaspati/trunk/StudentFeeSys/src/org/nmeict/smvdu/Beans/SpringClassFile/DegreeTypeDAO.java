package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.ExamCodeSetUp;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;



public class DegreeTypeDAO implements IDegreeTypeDAO{

	
	private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();
	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}
 
	@Override
	public void addDegreeType(DegreeType degreeType) {
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
            s.save(degreeType); 
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
	public void updateDegreeType(List<DegreeType> degreeType) 
	{
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			int i=0;
			//System.out.println("Here...."+degreeType.size()); 
			for(DegreeType dt : degreeType)
			{
				dt.setOrgProfile(new OrgProfileSessionDetails().getOrgProfileSession()); 
				OrgDepartmentType odt = new OrgDepartmentType();
				odt.setOdtSeqNo(dt.getOrgDegreeDpType());
				dt.setOrgDepartmentType(odt);
				s.update(dt.getSeqNo().toString(),dt); 
				i++;
				if(i%50 == 0)
				{
					
				}
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
			if(s.isOpen()==true)
	        HibernateDataSourceConnection.closeSession();
		}
	}

	@Override
	public List<DegreeType> allDegreeType(int dCode) {
		Session s=null;
		List<DegreeType> listDetails = new ArrayList<DegreeType>();
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			
			Criteria criteria = s.createCriteria(DegreeType.class,"degtype");
			criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN).
			setProjection(Projections.projectionList().add(Projections.property("odt.orgDepartmentName"))
					.add(Projections.property("degtype.degreeName"))
					.add(Projections.property("degtype.degreeCreatorId"))
					.add(Projections.property("degtype.degreeModifierId"))
					.add(Projections.property("degtype.degreeCreatedDateTime"))
					.add(Projections.property("degtype.degreeModifierDateTime"))
					.add(Projections.property("degtype.seqNo"))
					.add(Projections.property("odt.odtSeqNo")));
			criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
			if(dCode>0)
			{
				criteria.add(Restrictions.eq("odt.odtSeqNo",dCode));
			}
			 
			List degreeTypeList = (List) criteria.list();
			Iterator dlt = degreeTypeList.iterator();
			while(dlt.hasNext())
			{
				Object[] o = (Object[]) dlt.next();
				DegreeType dt = new DegreeType();
				dt.setOrgDepartmentName(o[0].toString());
				dt.setDegreeName(o[1].toString());
				dt.setDegCreaterId(null);
				dt.setDegModifierId(null); 
				dt.setDegreeCreatedDateTime((Date) o[4]);
				dt.setDegreeModifierDateTime((Date) o[5]);
				dt.setSeqNo(Integer.parseInt(o[6].toString()));
				dt.setOrgDegreeDpType((Integer)o[7]);
				listDetails.add(dt);
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
			if(s.isOpen()==true)
	        HibernateDataSourceConnection.closeSession();
		}
		return listDetails; 
	}

	@Override
	public DegreeType loadDegreeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDegreeType(DegreeType degreeType) {
		// TODO Auto-generated method stub
                Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
                        s.delete(degreeType);
                        s.getTransaction().commit();
                        return true;
                }
                catch(Exception e){
                    s.getTransaction().rollback();
                    return false;
                }
                
                finally
		{
                    if(s.isOpen()==true)
                        HibernateDataSourceConnection.closeSession();
		}
	}

	
	
}
