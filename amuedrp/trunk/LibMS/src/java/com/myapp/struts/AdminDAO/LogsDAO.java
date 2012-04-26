///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.myapp.struts.AdminDAO;
//import com.myapp.struts.hbm.*;
//import com.myapp.struts.admin.Log1;
//import com.myapp.struts.admin.LogInstitute;
//import com.myapp.struts.hbm.HibernateUtil;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import org.hibernate.Criteria;
//import org.hibernate.Transaction;
//import org.hibernate.Session;
//import org.hibernate.Query;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.transform.Transformers;
///**
// *
// * @author System Administrator
// */
//public class LogsDAO {
//
//    public static List  searchlog(String search_by, String search_keyword, String sort_by){
//  Session session =null;
//    Transaction tx = null;
//    try {
//        session= HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            String sql="";
//
//            sql = "select * from logs   where "+search_by+" like '"+search_keyword +"%' order by "+sort_by+" asc";
//System.out.println("In SQL"+search_by+ "   "+search_keyword);
//            System.out.println(sql);
//
//          Query query =  session.createSQLQuery(sql)
//
//                    .addEntity(Logs.class)
//                    .setResultTransformer(Transformers.aliasToBean(LogInstitute.class));
//            return query.list();
//        }
//        finally {
//         //   session.close();
//        }
//}
//
//
//
//    public static  List<Logs> searchlog(String library_id,String sublibrary_id,String year1,String year2,String userid)
//{
//        //int count=1;
//        Session hsession=HibernateUtil.getSessionFactory().openSession();
//        try
//        {
//         hsession.beginTransaction();
//         Criteria criteria = hsession.createCriteria(Logs.class);
//       if(!library_id.equalsIgnoreCase("all"))
//         criteria.add(Restrictions.eq("libraryId",library_id));
//         if(!sublibrary_id.equalsIgnoreCase("all"))
//         criteria.add(Restrictions.eq("sublibraryId",sublibrary_id));
//
//
//
//             if(!userid.equals(""))
//         criteria.add(Restrictions.eq("userId",userid));
//
//         if(year1!=null){
//           if(!year1.equals(""))
//         criteria.add(Restrictions.gt("date",year1));
//         }
//         if(year2!=null){
//          if(!year2.equals(""))
//         criteria.add(Restrictions.lt("date",year2));
//         }
//         if(userid!=null){
// if(!userid.equals(""))
//         criteria.add(Restrictions.eq("userid",userid));
//         }
//
//
//         return criteria.list();
//
//        }
//        catch(Exception e)
//        {
//
//            return null;
//        }
//        finally
//        {
//           hsession.close();
//        }
// }
//
//
// public static List<Logs> loglist(String library_id,String sublibrary_id) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            session.beginTransaction();
//            if(!library_id.equalsIgnoreCase("all"))
//            {
//            Query query1 = session.createSQLQuery("select * from logs where library_id = :library_id and sublibrary_id =:sublibrary_id")
//                    .addEntity(Logs.class);
//                    //.setResultTransformer(Transformers.TO_LIST);
//            query1.setString("library_id", library_id);
//            query1.setString("sublibrary_id", sublibrary_id);
//            //query1.setString("memId", memId);
//            //System.out.println("query = "+query1.toString());
//
//            return (List<Logs>) query1.list();
//            }
//            Query query1 = session.createSQLQuery("select * from logs")
//                    .addEntity(Logs.class);
//            return (List<Logs>) query1.list();
//        }
//        finally {
//           // session.close();
//        }
//
//
//
//}
//
// public static List SearchlogLib() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            session.beginTransaction();
//            Query query = session.createSQLQuery("select distinct library_id from logs");
//            return (List) query.list();
//        } finally {
//            session.close();
//        }
//
//    }
//
// public static Library SearchlogLibName(String library_id) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("From Library where libraryId=:libraryid");
//            query.setString("libraryid", library_id);
//
//            return (Library) query.uniqueResult();
//        } finally {
//            session.close();
//        }
//
//    }
//
//
//
//    public static List log() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("FROM Logs order by libraryId");
//            return (List) query.list();
//        } finally {
//            session.close();
//        }
//
//    }
//public static boolean insertLog(Logs obj)
//{
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//
//
//        try
//        {
//            tx = (Transaction) session.beginTransaction();
//
//            session.save(obj);
//            tx.commit();
//
//
//
//        }
//        catch (Exception ex)
//        {
//             return false;
//
//       //  System.out.println(ex.toString());
//
//        }
//        finally
//        {
//          session.close();
//        }
//   return true;
//
//}
//
//public static boolean insertSetting(Logsetting obj)
//{
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//
//
//        try
//        {
//            tx = (Transaction) session.beginTransaction();
//
//            session.save(obj);
//            tx.commit();
//
//
//
//        }
//        catch (Exception ex)
//        {
//             return false;
//
//       //  System.out.println(ex.toString());
//
//        }
//        finally
//        {
//          session.close();
//        }
//   return true;
//
//}
//    public static boolean DeleteSetting() {
//      Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//
//
//        try
//        {
//            tx = (Transaction) session.beginTransaction();
//            Query query = session.createQuery("Delete From  Logsetting");
//
//              query.executeUpdate();
//            tx.commit();
//
//
//
//        }
//        catch (Exception ex)
//        {
//             return false;
//
//       //  System.out.println(ex.toString());
//
//        }
//        finally
//        {
//       session.close();
//        }
//   return true;
//
//
//}
// public static Logsetting searchSetting(){
//        Session session = null;
//    try {
//      session= HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Query query = session.createQuery("FROM Logsetting");
//
//            return (Logsetting)query.uniqueResult();
//        }
//        finally {
//            session.close();
//        }
//}
//    public static List<Logs> getUserLog(){
//        Session session = null;
//    try {
//      session= HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Query query = session.createQuery("FROM Logs");
//
//            return (List)query.list();
//        }
//        finally {
//          //  session.close();
//        }
//}
//public static List<Log1> getUserLogChart(){
//    List<Log1> log1=new ArrayList<Log1>();
//       Session session = null;
//
//
//
//    try {
//        session= HibernateUtil.getSessionFactory().openSession();
//String lib_id;
//String count1;
//     //Group By Clause Example
//  String SQL_QUERY = "select a.libraryId,count(a.libraryId)  from Logs a group by a.libraryId";
// Query query = session.createQuery(SQL_QUERY);
// for (Iterator it =
//query.iterate(); it.hasNext();) {
// Object[] row = (Object[]) it.next();
// System.out.println("Invested Amount: " + row[0]);
// System.out.println("Insurance Name: " + row[1]);
//lib_id=row[0].toString();
//count1=row[1].toString();
//Log1 obj=new Log1();
//obj.setLibrary_id(lib_id);
//obj.setCount1(Integer.parseInt(count1));
//log1.add(obj);
//  }
//  session.close();
//  } catch (Exception e) {
//  System.out.println(e.getMessage());
//  } finally {
//  }
//
//  return log1;
//  }
//
//
//
//
//}
