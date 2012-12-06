/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.Vistingcard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Amit
 */
public class VisitingCardDao {
    private SessionFactory sessionFactory;

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public Vistingcard AddVisitingCardDetail(Vistingcard vcModel){
    sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    Session s=sessionFactory.openSession();
     Transaction t = null;
    t=s.beginTransaction();
    s.save(vcModel);
    t.commit();
    return vcModel;
    }
    
    public List<Vistingcard> VisitingCardData(String user_id){
        sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
        Session s=sessionFactory.openSession();
        Transaction t=null;
        t=s.beginTransaction();
        List<Vistingcard> vCardList=null;
        vCardList=s.createQuery("from Vistingcard where userId='"+user_id+"'").list();
        t.commit();
        return vCardList;
    }
    
    public List<Vistingcard> EditVcardData(Integer visitcardId){
    sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    Session s=sessionFactory.openSession();
    Transaction t=null;
    t=s.beginTransaction();
    List<Vistingcard> vacrdlist=null;
    
    vacrdlist=s.createQuery("from Vistingcard where visitcardId='"+visitcardId+"'").list();
    t.commit();
    return vacrdlist;
    
    }
    
    
    public Vistingcard UpdateVcardDetail(Integer visitcardId,String UserId, String displayName, String designation, String company, Long mobile, Long officePh, Integer fax, String email, String websiteOff, String websitePer){
    sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    Session s=sessionFactory.openSession();
    Transaction t=null;
    t=s.beginTransaction();
    
    Vistingcard vacardmod=(Vistingcard)s.load(Vistingcard.class,visitcardId );
    vacardmod.setUserId(UserId);
    vacardmod.setDisplayName(displayName);
    vacardmod.setDesignation(designation);
    vacardmod.setCompany(company);
    vacardmod.setMobile(mobile);
    vacardmod.setOfficePh(officePh);
    vacardmod.setFax(fax);
    vacardmod.setEmail(email);
    vacardmod.setWebsiteOff(websiteOff);
    vacardmod.setWebsitePer(websitePer);
    t.commit();
    s.close();
    sessionFactory.close();
    return vacardmod;
    }
    
}
