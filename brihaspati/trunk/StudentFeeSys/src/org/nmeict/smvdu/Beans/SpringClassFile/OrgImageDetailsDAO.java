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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.OrgImageDetails;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 * @author Shaista Bano
 */

public class OrgImageDetailsDAO implements IOrgImageDetailsDAO{

	
	
	
	private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();
	
	
	 
	
	@Override
	public void addOrgProfileImage(OrgImageDetails orgImageDetails) {
		
			Session s=null;
			
			try 
			{
				String userOrgId = new OrgProfileSessionDetails().getUserId();
				String userId = new OrgProfileSessionDetails().getOrgProfileSession().getOrgAdminemailid();
				s = HibernateDataSourceConnection.currentSession();
				s.beginTransaction();
				OrgProfile orgProfileOrgId = (OrgProfile) s.get(OrgProfile.class,userOrgId);
				//orgImageDetails.setOrgProfileByUserId(this.getUserId(userId)); 
				orgImageDetails.setOrgProfileByOrgId(orgProfileOrgId);
	            s.save(orgImageDetails); 
	            s.getTransaction().commit();
	           // HibernateDataSourceConnection.closeSession();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				s.getTransaction().rollback();
			}
			finally
			{
				if(s.isOpen()==true)
		        HibernateDataSourceConnection.closeSession();
			}
	}
		
	

	@Override
	public void deleteOrgProfileImage(OrgImageDetails orgImageDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrgImageDetails> orgProfileImageList(String orgId, String query) {
		// TODO Auto-generated method stub
		
		
		Session s=null;
		List<OrgImageDetails> imageDetailsList = new ArrayList<OrgImageDetails>();
		try
		{
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			
			
			Criteria criteria = s.createCriteria(OrgImageDetails.class,"oid")
			.setProjection(Projections.projectionList()
					.add(Projections.property("oid.imgPath"))
					.add(Projections.property("oid.imgName"))
					.add(Projections.property("oid.imgProfileFlag"))
					.add(Projections.property("oid.upDate")));
			criteria.add(Restrictions.eq("oid.orgProfileByOrgId.orgId",orgId));
			 
			if(query.equals(" ")== false) 
			{
				//System.out.println("Come...");
				criteria.add(Restrictions.eq("oid.imgName",query));
			}
			
			List orgImageDetails = (List)criteria.list();
			Iterator itr = orgImageDetails.iterator();
			while(itr.hasNext())
			{
				Object[] o = (Object[])itr.next();
				OrgImageDetails oid = new OrgImageDetails();
				oid.setImgPath(o[0].toString());
				oid.setImgName(o[1].toString());
				oid.setImgProfileFlag(Byte.parseByte(o[2].toString()));
				oid.setUpDate((Date)o[3]); 
				imageDetailsList.add(oid);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(s.isOpen()==true)
		        HibernateDataSourceConnection.closeSession();
		}
		return imageDetailsList;
	}

	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	@Override
	public OrgImageDetails defaultImageProfile(String orgId, String userId) {
		Session s=null;
		try 
		{
			String userOrgId = new OrgProfileSessionDetails().getUserId();
			
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			
			//orgImageDetails.setOrgProfileByUserId(this.getUserId(userId)); 
			
           
            s.getTransaction().commit();
           // HibernateDataSourceConnection.closeSession();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			s.getTransaction().rollback();
		}
		finally
		{
			if(s.isOpen()==true)
	        HibernateDataSourceConnection.closeSession();
		}
		return null;
	}
	
	
	
	
	

}
