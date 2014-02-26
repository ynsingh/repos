/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.nmeict.smvdu.Beans.BranchMaster;
import org.nmeict.smvdu.Beans.OrgProfile;

import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KESU
 */


/*
 * OrgProfileDAO Class is created by implementing IOrgProfileDAO Interface.
 */
@Service
@Transactional
public class OrgProfileDAO implements IOrgProfileDAO{
    
    @Autowired
    private String orgId;
    private SessionFactory sessionFactory;
    private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();

    public HibernateDataSourceConnection getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }

    public void setHibernateSessionFactory(HibernateDataSourceConnection hibernateSessionFactory) {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    @SuppressWarnings("empty-statement")
    @Override
    public void addNewOrgProfile(OrgProfile orgProfile) {
        Session s = null;
        try
        {
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            s.save(orgProfile);
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
    public void updateOrgProfile(OrgProfile orgProfile) {
    	
    	Session s = null;
        try
        {
        	//System.out.println("Org Name : "+orgProfile.getOrgName());
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            s.update(orgProfile.getOrgId(),orgProfile);
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
    public List<OrgProfile> loadOrgProfile() {
        Session s=null; 
        List<OrgProfile> returnOrgList = new ArrayList<OrgProfile>();
                
        try
        {
            //System.out.print("\n This is try ==========in SpringOrgPrfileDao");
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();

            Criteria criteria = s.createCriteria(OrgProfile.class,"orgp");
            //criteria.createAlias("OrgLogoDetails","oLogoDetail",CriteriaSpecification.LEFT_JOIN);

            ProjectionList projection = Projections.projectionList();
            projection.add(Projections.property("orgp.orgId"));
            projection.add(Projections.property("orgp.orgName"));
            projection.add(Projections.property("orgp.orgCity"));
            projection.add(Projections.property("orgp.orgState"));
            projection.add(Projections.property("orgp.orgInstitutedomain"));
            projection.add(Projections.property("orgp.orgAdminfn"));
            projection.add(Projections.property("orgp.orgAdminln"));
            projection.add(Projections.property("orgp.orgAdminemailid"));
            projection.add(Projections.property("orgp.orgMasterPassword"));
            projection.add(Projections.property("orgp.orgRecoveryId"));
            criteria.setProjection(projection);
            criteria.addOrder( Order.asc("orgp.orgId") );
            List list = (List)criteria.list();
            Iterator i = list.iterator();
            //System.out.println("organization list Size : "+list.size());
             while(i.hasNext()) {
                Object[] o = (Object[]) i.next();
                OrgProfile orgProfile = new OrgProfile();
                orgProfile.setOrgId(o[0].toString());
                orgProfile.setOrgName(o[1].toString());
                orgProfile.setOrgCity(o[2].toString());
                orgProfile.setOrgState(o[3].toString());
                orgProfile.setOrgInstitutedomain((String)o[4]);
                orgProfile.setOrgAdminfn((String)o[5]);
                orgProfile.setOrgAdminln((String)o[6]);
                orgProfile.setOrgAdminemailid((String)o[7]);
                orgProfile.setOrgMasterPassword((String)o[8]);
                orgProfile.setOrgRecoveryId((String)o[9]);
                
                returnOrgList.add(orgProfile);
             }
              
             s.getTransaction().commit();
        }
        catch(Exception ex)
        {
            s.getTransaction().rollback();  
        }
        
        finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
        }
        //System.out.println("\norg list Size : "+returnOrgList.size());
        return returnOrgList;
    }

    
    
    
    public OrgProfile searchOrgProfile(String orgEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	
    
}
