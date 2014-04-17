/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.db;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 *
 * @author Shaista Bano 
 */
public class FeeHeadMasterUtil {
    
    public void FeeHeadMasterUtil(){ }
    
    /*
     * Return FeeId for ledger
     */
    
    public Integer feeHeadId(Integer feeHeadCode){
        Session s1=null;
        Integer feeId =0;
        try{
		s1 = HibernateDataSourceConnection.currentSession();
                s1.beginTransaction();
                ProjectionList projection  = Projections.projectionList();
                Criteria criteria = s1.createCriteria(OtherFeeHeadMaster.class,"ofm");
                criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
                projection.add(Projections.property("ofm.feeHeadId"));
                criteria.setProjection(projection);
                criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
                criteria.add(Restrictions.eq("ofm.feeHeadCode",feeHeadCode));
                List list = (List) criteria.list();
                Iterator i = list.iterator();
                System.out.println("list111="+list.size());
                while(i.hasNext()){
                    Object[] o = (Object[])i.next();
                    feeId = (Integer)o[0];
                }
        }
        catch(Exception ex){
                s1.getTransaction().rollback();
            }
        return feeId;
    }
    
    /*
     * Getting FeeHeadCode by given FeeHeadName
     */
    
    public Integer getFeeHeadCode(String feeHeadName, int tmpDegreeCode, int tmpBranchCode, int tmpSemCode){
        Session s1=null;
        Integer feeCode =0;
        //System.out.println("list111="+tmpDegreeCode+"=="+tmpBranchCode+"--="+tmpSemCode);
        try{
		s1 = HibernateDataSourceConnection.currentSession();
                s1.beginTransaction();
                ProjectionList projection  = Projections.projectionList();
                Criteria criteria = s1.createCriteria(OtherFeeHeadMaster.class,"ofm");
                criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("semesterMaster","sem",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("branchMaster","bm",CriteriaSpecification.LEFT_JOIN);
                projection.add(Projections.property("ofm.feeHeadCode"));
                criteria.setProjection(projection);
                criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
                criteria.add(Restrictions.eq("degType.seqNo", tmpDegreeCode));
                criteria.add(Restrictions.eq("bm.bmSeqNo", tmpBranchCode));
                criteria.add(Restrictions.eq("sem.semSeqNo", tmpSemCode));
                criteria.add(Restrictions.eq("ofm.feeHeadName", feeHeadName));
                System.out.println("crit11="+criteria);
                List list = (List) criteria.list();
                
                Iterator i = list.iterator();
                //System.out.println("list111="+list.size()+"i.hasNext()="+i.hasNext());
                 while(i.hasNext()){
                    feeCode = (Integer)i.next();
                   //System.out.println("feeCode=="+feeCode);
                }
        }
        catch(Exception ex){
                s1.getTransaction().rollback();
            }
        return feeCode;
    }
}
