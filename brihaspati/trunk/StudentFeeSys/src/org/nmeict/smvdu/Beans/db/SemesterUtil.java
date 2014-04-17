/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.db;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.ExamCodeSetUp;
import org.nmeict.smvdu.Beans.SemesterMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 *
 * @author Shaista Bano 
 */
public class SemesterUtil {
    public SemesterUtil(){}
    
    public String getSemesterName(int semCode) {
    Session s=null;
    String semName = "";
    try
        {
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            ProjectionList projection = Projections.projectionList();
            Criteria criteria = s.createCriteria(SemesterMaster.class,"sem");
            projection.add(Projections.property("sem.semesterName"));
            criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getOrgProfileSession().getOrgId()));
            criteria.add(Restrictions.eq("sem.semSeqNo",semCode));
            List list = (List) criteria.list();
            Iterator i = list.iterator();
            while(i.hasNext()) {
                Object[] o =(Object[])i.next();
        	semName = o[0].toString();
            }
            //s.save(examCodeSetup);
            s.getTransaction().commit();
            HibernateDataSourceConnection.closeSession();
        }
        catch(Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }
	finally{
        	if(s.isOpen()==true)
	        HibernateDataSourceConnection.closeSession();
	}
        return semName;
	}
    
    //public void addStudentFeeLiability(ExamCodeSetUp examCodeSetup) {
    public void addStudentFeeLiability() {
        Session s = null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            //s.save(examCodeSetup);
            s.getTransaction().commit();
            HibernateDataSourceConnection.closeSession();
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
    
     /* Getting Session time of given degree Code and sem Name
      * Return session begining and ending date in a vector.
      */
    
    public Vector getSemBeginEndDate(int degreeCode, String semName){
        ExamCodeSetUp tempEcs = new ExamCodeSetUp();
        Vector semBeginAndEndDate = new Vector();
        List<ExamCodeSetUp> examSesionCode = tempEcs.getiExamcodeSetupService().loadAllExamcodeSetup(degreeCode);
        for(ExamCodeSetUp ecs : examSesionCode){
            if(ecs.getSemesterName().equals(semName)){
                            
                System.out.println(ecs.getSemesterBegningDate());
                System.out.println(ecs.getSemesterEndingDate());
                 semBeginAndEndDate.add(ecs.getSemesterBegningDate());
                 semBeginAndEndDate.add(ecs.getSemesterEndingDate());
            }
    
        }
        return semBeginAndEndDate;
    }
   
}
