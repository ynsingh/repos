/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.nmeict.smvdu.Beans.OrgLogoDetails;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;

/**
 *
 * @author KESU
 */
public class OrgLogoProfileDAO implements IOrgLogoProfileDAO{

    
    private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();

    public HibernateDataSourceConnection getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }

    public void setHibernateSessionFactory(HibernateDataSourceConnection hibernateSessionFactory) {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }
	@Override
    public List<OrgLogoDetails> AllCollegeLogoDetails(String query) {
        
        List<OrgLogoDetails> orgLogoDetails = new ArrayList<OrgLogoDetails>();
        List<OrgLogoDetails> orgReturnLogoDetails = new ArrayList<OrgLogoDetails>();
        List l;
        Session s  = null;
        try
        {
            s = HibernateDataSourceConnection.currentSession();
            //System.out.println(query);
          Criteria criteria = s.createCriteria(OrgLogoDetails.class,"old");
            criteria.createAlias("orgProfile","orp",CriteriaSpecification.LEFT_JOIN).
            setProjection(Projections.projectionList().
            		add(Projections.property("orp.orgWeb")).
            		add(Projections.property("orp.orgName")).
            		add(Projections.property("orp.orgId")));
            		//add(Projections.property("old.orgId")));
            		//criteria.add(Restrictions.and(orgIdCriteria,orgCriteria)); 
                    criteria.add(Restrictions.like("orp.orgName", query.toString().trim(),MatchMode.ANYWHERE));
            l = criteria.list();
            Iterator i = l.iterator();
            while(i.hasNext())
            {
            	Object[] o = (Object[])i.next();
            	OrgLogoDetails od = new OrgLogoDetails();
            	od.setOrgWeb((String)o[0]); 
            	od.setOrgName((String)o[1]); 
            	od.setOrgId((String)o[2]); 
            	//od.setLogoImage(ImageIO.read(new ByteArrayInputStream((byte[])o[4]))); 
            	//od.setLogoFormat((String)o[5]); 
            	orgReturnLogoDetails.add(od);
            	//System.out.println(o[0] + " : "+o[1]+" : "+o[2]+" : "); 
            }
           //orgLogoDetails = s.createQuery("select orp.orgName,orp.orgCity,orp.orgPinCode,orp.orgState,orp.orgWeb from OrgLogoDetails olo Left Join OrgProfile orp on olo.orgWeb = orp.orgWeb where orp.orgName like '"+query.trim().toString()+"'%").list();
           /* for(OrgLogoDetails orld : orgLogoDetails)
            {
                OrgLogoDetails od = new OrgLogoDetails();
                od.setOrgWeb(orld.getOrgWeb());
                od.setOrgName(orld.getOrgProfile().getOrgName());
                System.out.println(orld.getOrgWeb()+" : "+orld.getOrgProfile().getOrgName());
                orgReturnLogoDetails.add(od); 
            }*/
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(s.isOpen() == true)
            {
                HibernateDataSourceConnection.closeSession();
            }
        }
        return orgReturnLogoDetails;
    }
    
}
