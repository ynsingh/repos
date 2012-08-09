/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;


import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.circulation.CheckInDocumentDetails;

import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author edrp02
 */
public class CirRequestfromOpacDAO {



    public static List<CheckInDocumentDetails> getCheckIn(String library_id,String sublibrary_id,String memid,String  starting_date,String end_date) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
      List<CheckInDocumentDetails> obj=null;
        Transaction tx = null;
        try {
            session.beginTransaction();
   session.flush();
   String sql="select * from cir_checkin cc,cir_transaction_history d,document_details dd where cc.library_id=d.library_id and cc.sublibrary_id=d.sublibrary_id and cc.checkin_id=d.checkin_id and cc.library_id=dd.library_id and cc.sublibrary_id=dd.sublibrary_id and cc.document_id=dd.document_id and cc.library_id=:libraryId and cc.sublibrary_id=:sublibrary_id";

   if(memid!=null && !memid.isEmpty())
sql+=" and cc.member_id=:memId ";

   if(starting_date!=null && !starting_date.isEmpty())
       sql+=" and cc.returning_date>=:starting_date";
   if(end_date!=null && !end_date.isEmpty())
       sql+=" and cc.returning_date<=:end_date";

            Query query1 = session.createSQLQuery(sql)
                    .addEntity(CirCheckin.class)
                    .addEntity(DocumentDetails.class)
                    .addEntity(CirTransactionHistory.class)
                    .setResultTransformer(Transformers.aliasToBean(CheckInDocumentDetails.class)) ;

            if(memid!=null && !memid.isEmpty())
            query1.setParameter("memId", memid);
                    query1.setParameter("libraryId", library_id);
                    query1.setParameter("sublibrary_id", sublibrary_id);
                    if(starting_date!=null && !starting_date.isEmpty())
                        query1.setParameter("starting_date", starting_date);
                    if(end_date!=null && !end_date.isEmpty())
                        query1.setParameter("end_date", end_date);


obj= (List<CheckInDocumentDetails>)query1.list();
    session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
        session.close();
        }
  return obj;
     }


public static CirMemberDetail getMemberId(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CirMemberDetail obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM CirMemberDetail where id.libraryId = :library_id  and id.memId=:mem_id");
            query.setString("library_id",library_id);
            query.setString("mem_id",memid);


          obj= (CirMemberDetail)query.uniqueResult();
         session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
        session.close();
        }
         return obj;
     }

  public static  boolean insert(CirRequestfromOpac obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
            tx.rollback();
            ex.printStackTrace();
             return false;

      
        }
        finally
        {
          session.close();
        }


}


 

     public static CirRequestfromOpac getMemberDetail(String library_id,String sublibrary_id,String memid) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        CirRequestfromOpac obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM CirRequestfromOpac where libraryId = :library_id and sublibraryId=:sublibrary_id and memId=:mem_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);
            obj=(CirRequestfromOpac)query.uniqueResult();
session.getTransaction().commit();
           
        }
         catch (Exception ex)
        {

          ex.printStackTrace();


        }
        finally
        {
          session.close();
        }
 return  obj;

     }



public static  boolean update(CirRequestfromOpac obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (Exception e) {
e.printStackTrace();
                tx.rollback();
                return false;

        }
finally{
session.close();
}
   return true;

}
  
public static List<CirOpacRequest> checkDuplicateRequest(String library_id,String sublibrary_id,String memid,String docId) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        List<CirOpacRequest> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class);
            criteria.add(Restrictions.eq("id.libraryId", library_id));
            criteria.add(Restrictions.eq("id.sublibraryId", sublibrary_id));
            criteria.add(Restrictions.eq("memid", memid));
            criteria.add(Restrictions.eq("documentId", Integer.parseInt(docId)));
            criteria.add(Restrictions.eq("status", "Pending"));
            obj= (List<CirOpacRequest>)criteria.list();
            session.getTransaction().commit();
        }
         catch (Exception e) {
        e.printStackTrace();



        }
finally{
session.close();
}
        return obj;
     }
      

public static Integer returnMaxRequestId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
                 Integer maxrequestId =null;
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(CirOpacRequest.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
           maxrequestId= (Integer) criteria.add(le).setProjection(Projections.max("id.rid")).uniqueResult();
            if (maxrequestId == null) {
                maxrequestId = 1;
            } else {
                maxrequestId++;
            }

                      session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
  return maxrequestId;
    }

public static boolean SendCheckOutRequest(String memId,String libId, String sublibId, String docId)
{
boolean flag=false;
try{
    
                CirOpacRequestId ciropacReqId = new CirOpacRequestId();
                CirOpacRequest ciropacReq = new CirOpacRequest();

                //getting next value for request Id corresponding to library Id and SubLibrary Id
                Integer rId = CirRequestfromOpacDAO.returnMaxRequestId(libId, sublibId);
                CirMemberDetail memDetail = (CirMemberDetail)CirculationDAO.searchCirMemDetails(libId, memId);
                DocumentDetails dd = (DocumentDetails)CirculationDAO.getDocument(libId, sublibId, Integer.parseInt(docId));
                ciropacReqId.setRid(rId);
                ciropacReqId.setLibraryId(libId);
                ciropacReqId.setSublibraryId(sublibId);

                ciropacReq.setId(ciropacReqId);

                String memName = memDetail.getFname()+" "+ memDetail.getMname()+ " "+memDetail.getLname();
                String accno = dd.getAccessionNo();
                String author = dd.getMainEntry();
                String title = dd.getTitle();
                String callno = dd.getCallNo();

                ciropacReq.setMemid(memId);
                ciropacReq.setMemname(memName);
                ciropacReq.setAccessionNo(accno);
                ciropacReq.setAuthor(author);
                ciropacReq.setCallNo(callno);
                ciropacReq.setDocumentId(Integer.parseInt(docId));
                ciropacReq.setTitle(title);
                ciropacReq.setStatus("Pending");
		ciropacReq.setCirMemberDetail(memDetail);
                 flag = CirculationDAO.insert(ciropacReq);


}
catch(Exception e){
e.printStackTrace();

}
finally{


}
return flag;
}

public static List<CheckoutDeocumentDetails> getCheckOuts(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CheckoutDeocumentDetails> obj=null;
        try {
            session.beginTransaction();
            session.flush();
         
            Query query1 = session.createSQLQuery("select * from cir_checkout cc,document_details dd where cc.library_id=dd.library_id and cc.sublibrary_id=dd.sublibrary_id and cc.document_id=dd.document_id and cc.memid=:memId and cc.status = 'issued' and cc.library_id=:libraryId and cc.sublibrary_id=:sublibrary_id")
                    .addEntity(CirCheckout.class)
                    .addEntity(DocumentDetails.class)
                    .setResultTransformer(Transformers.aliasToBean(CheckoutDeocumentDetails.class)) ;
                    query1.setParameter("memId", memid);
                    query1.setParameter("libraryId", library_id);
                    query1.setParameter("sublibrary_id", sublibrary_id);
            obj=  (List<CheckoutDeocumentDetails>)query1.list();
                        session.getTransaction().commit();
        }
        catch(Exception e){
       e.printStackTrace();


        }
        finally {
            session.close();
        }
return obj;
     }

public static List<CheckoutDeocumentDetails> getCheckOuts(String library_id,String sublibrary_id,String memid,String  starting_date,String end_date,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CheckoutDeocumentDetails> obj=null;
        try {
            session.beginTransaction();
   session.flush();
          
   String sql="select * from cir_checkout cc,document_details dd where cc.library_id=dd.library_id and cc.sublibrary_id=dd.sublibrary_id and cc.document_id=dd.document_id and  cc.status = 'issued' and cc.library_id=:libraryId and cc.sublibrary_id=:sublibrary_id ";

   if(memid!=null && !memid.isEmpty())
sql+=" and cc.memid=:memId ";
   if(title!=null && !title.isEmpty())
sql+=" and dd.title=:title ";

   if(starting_date!=null && !starting_date.isEmpty())
       sql+=" and cc.issue_date>=:starting_date";
   if(end_date!=null && !end_date.isEmpty())
       sql+=" and cc.issue_date<=:end_date";

            Query query1 = session.createSQLQuery(sql)
                    .addEntity(CirCheckout.class)
                    .addEntity(DocumentDetails.class)
                    .setResultTransformer(Transformers.aliasToBean(CheckoutDeocumentDetails.class)) ;

            if(memid!=null && !memid.isEmpty())
            query1.setParameter("memId", memid);

            if(title!=null && !title.isEmpty())
            query1.setParameter("title", title);

                    query1.setParameter("libraryId", library_id);
                    query1.setParameter("sublibrary_id", sublibrary_id);
                    if(starting_date!=null && !starting_date.isEmpty())
                        query1.setParameter("starting_date", starting_date);
                    if(end_date!=null && !end_date.isEmpty())
                        query1.setParameter("end_date", end_date);



            obj=  (List<CheckoutDeocumentDetails>)query1.list();
                        session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally {
session.close();
        }
return obj;
     }


}
