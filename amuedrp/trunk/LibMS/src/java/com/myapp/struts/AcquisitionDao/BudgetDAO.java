/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import com.myapp.struts.Acquisition.MixBudgetAllocation;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.AcqBudget;
import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.AcqBudgetAllocation;
import com.myapp.struts.hbm.AcqBudgetTransaction;
import com.myapp.struts.hbm.BaseCurrency;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import com.myapp.struts.utility.DateCalculation;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author EdRP-05
 */
public class BudgetDAO {

    public static List<MixBudgetAllocation> getMixBudgetAllocation(String library_id){
 String dob=DateCalculation.now();
 int year=Integer.parseInt(dob.substring(0,4));
  Session session =null;
    Transaction tx = null;
    List<MixBudgetAllocation> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select a.*,b.* from acq_budget a left outer join acq_budget_allocation b on a.library_id=b.library_id and a.budgethead_id=b.budgethead_id where a.library_id='"+library_id+"' and b.financial_yr1='"+String.valueOf(year)+"'";

        System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(AcqBudget.class)
                    .addEntity(AcqBudgetAllocation.class)
                    .setResultTransformer(Transformers.aliasToBean(MixBudgetAllocation.class));
            obj= (List<MixBudgetAllocation>)query.list();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return obj;
}

      public static String getBudgetTransaction(String library_id,String budgetheadId) {
        Session sess = null;
            String amount=null;
    int count = 0;
    try {
      SessionFactory fact = new Configuration().configure().buildSessionFactory();
      sess = fact.openSession();
      String SQL_QUERY = "select sum(amount) from AcqBudgetTransaction insurance where insurance.id.libraryId='"+library_id+"' and insurance.acqBudgetHeadId='"+budgetheadId+"' group by insurance.acqBudgetHeadId";
        Query query = sess.createQuery(SQL_QUERY);
        for (Iterator it = query.iterate(); it.hasNext();) {
          it.next();
            count++;
        }
        System.out.println("Total rows: " + count);
        Iterator it = query.iterate(); it.hasNext();
     amount=query.uniqueResult().toString();
        System.out.println("Total rows1: " + amount);

     
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
    sess.close();
    }
     return amount;
}

     public static List<AcqBudget> getBudget(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqBudget> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudget  WHERE id.libraryId =:library_id  ");
            query1.setString("library_id", library_id);
      

            obj= (List<AcqBudget>) query1.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
        public static AcqBudgetAllocation getSearchBudgetHead(String library_id,String budgethead,String year) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBudgetAllocation obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudgetAllocation  WHERE id.libraryId =:library_id and budgetheadId=:budgethead and financialYr1=:year");
            query1.setString("library_id", library_id);
            query1.setString("budgethead", budgethead);
            query1.setString("year",year);

            obj= (AcqBudgetAllocation) query1.uniqueResult();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
        public static List<AcqBudgetAllocation> getSearchBudgetHead1(String library_id,String budgethead) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqBudgetAllocation> obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudgetAllocation  WHERE id.libraryId =:library_id and budgetheadId=:budgethead");
            query1.setString("library_id", library_id);
            query1.setString("budgethead", budgethead);


            obj= (List<AcqBudgetAllocation>) query1.list();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
      public static BaseCurrency getBaseCurrency(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       BaseCurrency obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  BaseCurrency  WHERE id.libraryId =:library_id  ");
            query1.setString("library_id", library_id);


            obj= (BaseCurrency) query1.uniqueResult();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
      public static AcqCurrency getConversionRate(String library_id,String scurr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqCurrency obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id  and sourceCurrency=:scurr order by sourceCurrency desc");
            query1.setString("library_id", library_id);
            query1.setString("scurr", scurr);

            obj= (AcqCurrency) query1.setMaxResults(1).uniqueResult();
        }
      catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
       public static List<AcqBudgetAllocation> getBudgetAllocation(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AcqBudgetAllocation> obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudgetAllocation  WHERE id.libraryId =:library_id  ");
            query1.setString("library_id", library_id);


           obj= (List<AcqBudgetAllocation>) query1.list();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
  public static AcqBudget getBudgetid(String library_id,String id1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqBudget obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudget  WHERE id.libraryId =:library_id and id.budgetheadId=:id1");
            query1.setString("library_id", library_id);
        query1.setString("id1",id1);


            obj= (AcqBudget) query1.uniqueResult();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}


public static AcqBudget getLocationByName(String library_id,String budegetheadname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBudget obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudget  WHERE id.libraryId =:library_id and budgetheadName=:budgetheadName");
            query1.setString("library_id", library_id);
            
            query1.setString("budgetheadName", budegetheadname);

            obj= (AcqBudget) query1.uniqueResult();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
public static AcqBudgetAllocation getBudgetHeadByName(String library_id,String budegetheadId,String financial_yr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqBudgetAllocation obj = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqBudgetAllocation  WHERE id.libraryId =:library_id and budgetheadId=:budgetheadId and financialYr1");
            query1.setString("library_id", library_id);

            query1.setString("budgetheadName", budegetheadId);
           query1.setString("financialYr1", financial_yr);

            obj= (AcqBudgetAllocation) query1.uniqueResult();
        }
 catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}

public static Integer returnMaxBiblioId(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Integer maxbiblio=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetAllocation.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            
           // LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(a).setProjection(Projections.max("id.transactionId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return maxbiblio;

}
    public static void insert1(AcqBudgetAllocation obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
        catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
            session.close();
        }
        

}

 public static void insertTransaction(AcqBudgetTransaction obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
        catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
            session.close();
        }

}
    public static boolean insert(AcqBudget obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }

}
        public static boolean update(AcqBudget obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        return true;
        }
        catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        

}
             public static void update1(AcqBudgetAllocation obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (Exception e) {

            tx.rollback();
        e.printStackTrace();

        }
        finally{
        session.close();
        }

   

    }
        public static boolean delete(String library_id,String budgethead_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  AcqBudget where id.libraryId = :library_id and  id.budgetheadId= :budgetheadId");
           query.setString("library_id", library_id);
         
            query.setString("budgetheadId", budgethead_id);
            query.executeUpdate();
            tx.commit();

return true;

        } catch (Exception ex) {
   ex.printStackTrace();
   tx.rollback();
            return false;

            

        } finally {
             session.close();
        }
       

    }
     public static void delete1(String library_id,int trans_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  AcqBudgetAllocation where id.libraryId = :library_id and  id.transactionId= :transactionId");
           query.setString("library_id", library_id);
            query.setInteger("transactionId", trans_id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
      tx.rollback();

            

        } finally {
          session.close();
        }
    

    }
        public static AcqBudget searchBudget(String library_id, String budgethead_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqBudget obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudget.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))                  
                    .add(Restrictions.eq("id.budgetheadId", budgethead_id)));
            obj= (AcqBudget) criteria.uniqueResult();


        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}

    public static AcqBudgetAllocation searchBudgetAllocation(String library_id, String budgethead_id,String fyear) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqBudgetAllocation obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudgetAllocation.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("financialYr1", fyear))
                    .add(Restrictions.eq("budgetheadId", budgethead_id)));
            obj= (AcqBudgetAllocation) criteria.uniqueResult();


        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
    public static List<AcqBudget> listlocation(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<AcqBudget> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqBudget.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   );
            obj= (List<AcqBudget>) criteria.list();


        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;

}
}
