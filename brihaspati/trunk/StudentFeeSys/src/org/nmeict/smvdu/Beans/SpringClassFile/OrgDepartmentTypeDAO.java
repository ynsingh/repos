package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

public class OrgDepartmentTypeDAO implements IOrgDepartmentTypeDAO{

	private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();

	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	
	@Override
	public void addOrgDepartmentType(OrgDepartmentType orgDepartmentType) {
			
		
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
            s.save(orgDepartmentType); 
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
	public void update(List<OrgDepartmentType> orgDepartmentType) {
		Session s=null;
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			int i=0;
			for(OrgDepartmentType odt : orgDepartmentType)
			{

				odt.setOrgProfile(new OrgProfileSessionDetails().getOrgProfileSession());
				s.update(odt.getOdtSeqNo().toString(),odt);  
				i++;
				if(i%50 == 0)
				{
					s.flush();
					s.clear();
				}
			}
            s.getTransaction().commit();
     //       HibernateDataSourceConnection.closeSession();
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
	public List<OrgDepartmentType> loadAllOrgDepartmentType() {
			Session s = null;
			List<OrgDepartmentType> returnOrgDepartmentTypes = new ArrayList<OrgDepartmentType>();
			try
			{
				s = HibernateDataSourceConnection.currentSession();
				s.beginTransaction();
				Query query = s.createQuery("From OrgDepartmentType odt where odt.orgProfile.orgId = '"+new OrgProfileSessionDetails().getOrgProfileSession().getOrgId()+"'");
				List l = (List) query.list();
				Iterator i = l.iterator();
				while(i.hasNext())
				{
					OrgDepartmentType odt = (OrgDepartmentType)i.next();
					returnOrgDepartmentTypes.add(odt);
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
		return returnOrgDepartmentTypes; 
	}
        
        @Override
        public void deleteOrgDepartment(OrgDepartmentType orgDepartmentType){
            
            Session s=null;
            try{
        	s = HibernateDataSourceConnection.currentSession();
		s.beginTransaction();
                s.delete(orgDepartmentType); 
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

}
