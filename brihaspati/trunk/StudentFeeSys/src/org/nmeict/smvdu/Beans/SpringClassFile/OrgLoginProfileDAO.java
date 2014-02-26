/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.OrgLogoDetails;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;

/**
 * 
 * @author KESU
 * @author Shaista Bano
 */
public class OrgLoginProfileDAO implements IOrgLoginProfileDAO {
	private HibernateDataSourceConnection hibernateSessionFactory;

	public HibernateDataSourceConnection getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			HibernateDataSourceConnection hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	@Override
	public boolean validateOrgUser(OrgLoginDetails orgLoginDetails) {
		Session s = null;
		try {
			boolean flag = false;
			s = HibernateDataSourceConnection.currentSession();
			// Query query =null;
			// s.createQuery("select adminId,orgId,orgPassword from OrgLoginDetails where adminId = '"+orgLoginDetails.getAdminId()+"' and orgId = '"+orgLoginDetails.getOrgId()+"',and orgPassword = '"+orgLoginDetails.getOrgPassword()+"'");
			Criteria criteria = s.createCriteria(OrgLoginDetails.class);
			criteria.add(Restrictions.eq("adminId",
					orgLoginDetails.getAdminId()));
			criteria.add(Restrictions.eq("orgId", orgLoginDetails.getOrgId()));
			criteria.add(Restrictions.eq("orgPassword",
                                orgLoginDetails.encode(orgLoginDetails.getOrgPassword())));
				//orgLoginDetails.getOrgPassword()));
			List l = criteria.list();
			//System.out.println(":" + l.size());
			if (l.size() == 1) {
				flag = true;
			}
			return flag;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (s.isOpen() == true)
			HibernateDataSourceConnection.closeSession();
		}
	}

	@Override
	public OrgProfile loadAllDetails(String orgId) {

		if (HibernateDataSourceConnection.getSessionFactory().isClosed() == true) {
			HibernateDataSourceConnection.getSessionFactory().close();
		}
		Session s = null;
		OrgProfile orgProfile = new OrgProfile();
		try {
			s = HibernateDataSourceConnection.currentSession();
			s.beginTransaction();
			orgProfile = (OrgProfile)s.get(OrgProfile.class,orgId);
			s.getTransaction().commit();
		} catch (Exception ex) {
			s.getTransaction().rollback();
		} finally {
			if (s.isOpen() == true) {
				HibernateDataSourceConnection.closeSession();
			}
		}
		return orgProfile; 
	}
}
