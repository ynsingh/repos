/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguingDAO;

import com.myapp.struts.cataloguing.Export;
import com.myapp.struts.cataloguing.StrutsUploadForm;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author khushnood
 */
public class DAO {

     Query query;

     

       public List columnname2(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();

              query = session.createSQLQuery("(select distinct COLUMN_NAME  from information_schema.columns where table_name ='memberexport' and  TABLE_SCHEMA='libms' order by ordinal_position)");

           obj=  query.list();
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
  public  boolean update(BibliographicDetails obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        System.out.println("isert is called :::::::::::::::: " + obj);

        try {
            tx = (Transaction) session.beginTransaction();

            session.update(obj);

            tx.commit();



        } catch (Exception ex) {

tx.rollback();

            ex.printStackTrace();
          return false;

        } finally {
            session.close();
        }
        return true;

    }

    public  boolean insert(BibliographicDetails obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        System.out.println("isert is called :::::::::::::::: " + obj);

        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            
            tx.commit();



        } catch (Exception ex) {

tx.rollback();

            ex.printStackTrace();
          return false;

        } finally {
            session.close();
        }
        return true;

    }
    public  boolean insertImport(BibliographicDetails obj,AccessionRegister acc,DocumentDetails doc,BibliographicDetailsLang bibobjmli) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

System.out.println("I a");
        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            session.save(acc);
            session.save(doc);
            session.save(bibobjmli);
            tx.commit();



        } catch (Exception ex)
        {
                tx.rollback();
                System.out.println(ex+"error........................");
                return false;

        } finally {
            session.close();
        }
        return true;

    }
public  boolean insertImport(BibliographicDetails obj,AccessionRegister acc,DocumentDetails doc) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

System.out.println("I a");
        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            session.save(acc);
            session.save(doc);
            
            tx.commit();



        } catch (Exception ex)
        {
                tx.rollback();
                System.out.println(ex+"error........................");
                return false;

        } finally {
            session.close();
        }
        return true;

    }


    public  List columnname(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
              query = session.createSQLQuery("(select distinct COLUMN_NAME  from information_schema.columns where table_name ='temp_excell_import' and  TABLE_SCHEMA='libms' order by ordinal_position)");
           obj=  query.list();
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
 public  List columnnamestaff(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
              query = session.createSQLQuery("(select  COLUMN_NAME  from information_schema.columns where table_name ='staff_detail' and  TABLE_SCHEMA='libms' order by ordinal_position)");
           obj=  query.list();
           System.out.println(obj.size()+".........................");
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
    public  List columnnameopac(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
         //    SQLQuery query = session.createQuery("select COLUMN_NAME  from information_schema.columns where table_name ='bibliographic_details' order by ordinal_position) UNION (select COLUMN_NAME from information_schema.columns where table_name ='accession_register' order by ordinal_position)");

          //  SQLQuery query = session.createSQLQuery("(select COLUMN_NAME  from information_schema.columns where table_name ='bibliographic_details' order by ordinal_position) UNION ALL (select COLUMN_NAME from information_schema.columns where table_name ='accession_register' order by ordinal_position)");
              query = session.createSQLQuery("(select distinct COLUMN_NAME  from information_schema.columns where table_name ='demandlist' and  TABLE_SCHEMA='libms' order by ordinal_position)");
            //SQLQuery query = session.createSQLQuery("SHOW COLUMNS FROM temp_excell_import");
//query.setResultTransformer(Transformers.aliasToBean(Tab.class));
           obj=  query.list();
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

        public  List columnname1(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();


            SQLQuery query = session.createSQLQuery("(select COLUMN_NAME  from information_schema.columns where table_name ='"+table_name+"' and TABLE_SCHEMA='libms' order by ordinal_position)");

           obj=  query.list();
           session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
     public  List datatype(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select DATA_TYPE  from information_schema.columns where table_name ='temp_excell_import' and TABLE_SCHEMA='libms' order by ordinal_position") ;
            obj=  query.list();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }
public  List datatypestaff(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select DATA_TYPE  from information_schema.columns where table_name ='staff_detail' and TABLE_SCHEMA='libms' order by ordinal_position") ;
            obj=  query.list();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }




public  boolean insertImportmember(CirMemberDetail obj,CirMemberAccount cir) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            session.save(cir);


            tx.commit();



        } catch (Exception ex)
        {
                tx.rollback();
                return false;

        } finally {
            session.close();
        }
        return true;

    }



        public  List datatypemember(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
         //    SQLQuery query = session.createQuery("select COLUMN_NAME  from information_schema.columns where table_name ='bibliographic_details' order by ordinal_position) UNION (select COLUMN_NAME from information_schema.columns where table_name ='accession_register' order by ordinal_position)");

            SQLQuery query = session.createSQLQuery("select DATA_TYPE  from information_schema.columns where table_name ='memberexport' and TABLE_SCHEMA='libms' order by ordinal_position") ;
//query.setResultTransformer(Transformers.aliasToBean(Tab.class));
            obj=  query.list();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
     public  List datatypeopac(String table_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
         //    SQLQuery query = session.createQuery("select COLUMN_NAME  from information_schema.columns where table_name ='bibliographic_details' order by ordinal_position) UNION (select COLUMN_NAME from information_schema.columns where table_name ='accession_register' order by ordinal_position)");

            SQLQuery query = session.createSQLQuery("select DATA_TYPE  from information_schema.columns where table_name ='demandlist' and TABLE_SCHEMA='libms' order by ordinal_position") ;
//query.setResultTransformer(Transformers.aliasToBean(Tab.class));
            obj=  query.list();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
    public  List<String> distintTitle(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select distinct call_no from temp_excell_import where library_id='"+library_id+"' and sublibrary_id='"+sublibrary_id+"'");

            
           obj= (List<String>) query.list();
           session.getTransaction().commit();
            
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
  public  List distintTitleCallno(String library_id,String sublibrary_id,String callno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select distinct title from temp_excell_import where library_id='"+library_id+"' and sublibrary_id='"+sublibrary_id+"' and call_no='"+callno+"'");

            
            obj= (List) query.list();
            session.getTransaction().commit();
            
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
  public  BibliographicDetails DuplicateTitleCallno(String library_id,String sublibrary_id,String callno,String title,String isbn10,String mainentry) {
        Session session = HibernateUtil.getSessionFactory().openSession();
BibliographicDetails obj=null;
        try {
            session.beginTransaction();
            Query query =null;
            if(isbn10==null)
            query= session.createQuery("From BibliographicDetails where id.libraryId=:library and id.sublibraryId=:sublibrary and callNo=:callno and title=:title and  mainEntry=:mainentry");
            else
            query = session.createQuery("From BibliographicDetails where id.libraryId=:library and id.sublibraryId=:sublibrary and callNo=:callno and title=:title and isbn10=:isbn10 and mainEntry=:mainentry");
            query.setString("library", library_id);
            query.setString("sublibrary", sublibrary_id);
            query.setString("callno", callno);
            query.setString("title", title);
            query.setString("mainentry", mainentry);
            if(isbn10!=null)
            query.setString("isbn10", isbn10);


            obj= (BibliographicDetails) query.list();
            session.getTransaction().commit();

        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
  public  BibliographicDetails DuplicateTitleCallno(String library_id,String sublibrary_id,String callno,String title,String mainentry) {
       Session session = HibernateUtil.getSessionFactory().openSession();
BibliographicDetails obj=null;
        try {
            session.beginTransaction();
            Query query =null;

            query= session.createQuery("From BibliographicDetails where id.libraryId=:library and id.sublibraryId=:sublibrary and callNo=:callno and title=:title and  mainEntry=:mainentry");
            query.setString("library", library_id);
            query.setString("sublibrary", sublibrary_id);
            query.setString("callno", callno);
            query.setString("title", title);
            query.setString("mainentry", mainentry);
           

            obj= (BibliographicDetails) query.uniqueResult();
            session.getTransaction().commit();

        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
   public  List DuplicateISBNTitle(String library_id,String sublibrary_id,String isbn,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from temp_excell_import where library_id=:library and sublibrary_id=:sublibrary and isbn10=:isbn and title=:title");
             query.setString("library", library_id);
            query.setString("sublibrary", sublibrary_id);
            query.setString("isbn", isbn);
            query.setString("title", title);

            obj= (List) query.list();
            session.getTransaction().commit();

        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
 public  List distintTitleAccessionno(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT accession_no, count(*) as n  FROM temp_excell_import  where  library_id='"+library_id+"' and sublibrary_id='"+sublibrary_id+"'  group by accession_no  HAVING n>1 ");

           
            obj= (List) query.list();
           session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
 public  List getTotalNumberDocument(String library_id,String sublibrary_id,String call_no,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT *  FROM document_details  where  library_id='"+library_id+"' and sublibrary_id='"+sublibrary_id+"'  and call_no='"+call_no+"' and title='"+title+"'");
            obj= (List) query.list();
           session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }

 public  List duplicateAccessionno(String library_id,String sublibrary_id,String accno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT *  FROM accession_register  where  library_id='"+library_id+"' and sublibrary_id='"+sublibrary_id+"'  and accession_no='"+accno+"'");
            obj= (List) query.list();
           session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }


    public  boolean  TruncateTempTable(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
  Transaction tx=session.beginTransaction();
        try {
            
        
            Query query = session.createQuery("Delete From TempExcellImport where libraryId='"+library_id+"' and sublibraryId='"+sublibrary_id+"'");

            query.executeUpdate();
              tx.commit();
            return  true;
        }
        catch(Exception e){
            tx.rollback();
       e.printStackTrace();
        return  false;
        }
        finally {
            session.close();
        }

    }

    public List AllrecordForGivenTitle(String title,String callno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(TempExcellImport.class).add(Restrictions.eq("title", title)).add(Restrictions.eq("callNo", callno));
           obj=(List) criteria.list();
           session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }

     public List getTempRecord(String library_id,String sublibrary_id,String title,String callno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(TempExcellImport.class).add(Restrictions.eq("libraryId", library_id)).add(Restrictions.eq("sublibraryId",sublibrary_id)).add(Restrictions.eq("title",title)).add(Restrictions.eq("callNo", callno));
           obj= (List) criteria.list();
           session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }

    public BibliographicDetails getTitle(String library_id,String sublibrary_id,int biblio_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       BibliographicDetails obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.eq("id.biblioId", biblio_id)).add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.sublibraryId", sublibrary_id));
          obj= (BibliographicDetails) criteria.uniqueResult();
          session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
       public BibliographicDetails getBiblio(String library_id,String sublibrary_id,String title,String callno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BibliographicDetails obj=null;
       
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(BibliographicDetails.class).add(Restrictions.eq("libraryId",library_id)).add(Restrictions.eq("sublibraryId",library_id)).add(Restrictions.eq("libraryId",library_id)).add(Restrictions.eq("libraryId",library_id));
           obj= (BibliographicDetails) criteria.uniqueResult();
           session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }
    public  List ViewAllTable1(String tablename) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
          
            SQLQuery query = session.createSQLQuery("select * from " + tablename);


          obj= (List) query.list();
          session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }

    public  List ViewAllTable(String tablename,String library_id,String sublibrary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try {
            session.beginTransaction();
            if(tablename.equalsIgnoreCase("bibliographic_details"))
            {        query = session.createSQLQuery("select * from "+ tablename+" where library_id='"+library_id+"' and sublibrary_id='"+sublibrary+"'")
                        .addEntity(BibliographicDetails.class)
                        .setResultTransformer(Transformers.aliasToBean(Export.class));
                    obj=query.list();
                      
            }
            else if(tablename.equalsIgnoreCase("accession_register"))
            {        query = session.createSQLQuery("select * from "+ tablename+" where library_id='"+library_id+"' and sublibrary_id='"+sublibrary+"'")
                        .addEntity(AccessionRegister.class)
                        .setResultTransformer(Transformers.aliasToBean(Export.class));
                    obj=query.list();

            }
            else if(tablename.equalsIgnoreCase("document_details"))
            {        query = session.createSQLQuery("select * from "+ tablename+" where library_id='"+library_id+"' and sublibrary_id='"+sublibrary+"'")
                        .addEntity(DocumentDetails.class)
                        .setResultTransformer(Transformers.aliasToBean(Export.class));
                    obj=query.list();

            }
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
    }

    public  Integer  getmax_biblio_id_temp_excell_import(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();


 Integer maxbiblio=null;
        
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(TempExcellImport.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = criteria.add(le).setProjection(Projections.max("id.biblioId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(le).setProjection(Projections.count("id.biblioId")).uniqueResult().toString());
           System.out.println(maxbiblio);

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
session.getTransaction().commit();
           
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
 return maxbiblio;
    }

    public  int getmax_biblio_id_bibliographic_detals() {
        Session session = HibernateUtil.getSessionFactory().openSession();
 Integer maxbiblio=null;
        try {
            session.beginTransaction();
            query = session.createSQLQuery("select max(biblio_id) from bibliographic_details");
            if (query.uniqueResult() != null) {
                maxbiblio= (Integer) query.uniqueResult();
            } else {
                maxbiblio=new Integer("0");
            }
session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return maxbiblio;
    }

    public  int getmax_record_no_accession_register() {
        Session session = HibernateUtil.getSessionFactory().openSession();
 Integer maxbiblio=null;
        try {
            session.beginTransaction();
            query = session.createSQLQuery("select max(record_no) from accession_register");
            if (query.uniqueResult() != null) {
                maxbiblio= (Integer) query.uniqueResult();
            } else {
               maxbiblio=new Integer("0");
            }
session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return maxbiblio;
    }
        public  int getmax_documentId_DocumentDetalils() {
        Session session = HibernateUtil.getSessionFactory().openSession();
Integer maxbiblio=null;
        try {
            session.beginTransaction();
            query = session.createSQLQuery("select max(document_id) from document_details");
            if (query.uniqueResult() != null) {
               maxbiblio= (Integer) query.uniqueResult();
            } else {
                maxbiblio=new Integer("0");
            }
session.getTransaction().commit();
        }catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return maxbiblio;
    }
public  boolean insertTemp(TempExcellImport obj) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
Transaction tx = session.beginTransaction();
        try
        {
        session.save(obj);
        tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
return false;


        } finally {
            session.close();
        }
        return true;
    }
    public  boolean insertgeneric(List<TempExcellImport> obj) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
Transaction tx = session.beginTransaction();
try{
    System.out.println(obj.size());
for ( int i=0; i<obj.size(); i++ ) {
    TempExcellImport customer = obj.get(i);
    session.save(customer);
//    if ( i % 20 == 0 ) { //20, same as the JDBC batch size
//        //flush a batch of inserts and release memory:
//        session.flush();
//        session.clear();
//    }
}

tx.commit();


        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
return false;


        } finally {
            session.close();
        }
        return true;
    }

    public  boolean insertBibligraphicDetails(BibliographicDetails obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
      

        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
           
            tx.commit();



        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
            return false;



        } finally {
            session.close();
        }
        return true;

    }

    public  boolean insertAccessionRegister(AccessionRegister obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        

        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
           
            tx.commit();



        } catch (Exception ex) {
          ex.printStackTrace();
          tx.rollback();
          return false;



        } finally {
            session.close();
        }
        return true;

    }
    public  boolean insertDocumentsDetails(DocumentDetails obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
      

        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
           
            tx.commit();



        } catch (Exception ex) {
           ex.printStackTrace();
           tx.rollback();
           return false;



        } finally {
            session.close();
        }
        return true;

    }

    public  String[] selectedcombo(String[] table, int size, StrutsUploadForm ub) {
        try {

            table = new String[size];
            table[0] = ub.getCombo0();
            table[1] = ub.getCombo1();
            table[2] = ub.getCombo2();
            table[3] = ub.getCombo3();
            table[4] = ub.getCombo4();
            table[5] = ub.getCombo5();
            table[6] = ub.getCombo6();
            table[7] = ub.getCombo7();
            table[8] = ub.getCombo8();
            table[9] = ub.getCombo9();
            table[10] = ub.getCombo10();
            table[11] = ub.getCombo11();
            table[12] = ub.getCombo12();
            table[13] = ub.getCombo13();
            table[14] = ub.getCombo14();
            table[15] = ub.getCombo15();
            table[16] = ub.getCombo16();
            table[17] = ub.getCombo17();
            table[18] = ub.getCombo18();
            table[19] = ub.getCombo19();
            table[20] = ub.getCombo20();
            table[21] = ub.getCombo21();
            table[22] = ub.getCombo22();
            table[23] = ub.getCombo23();
            table[24] = ub.getCombo24();
            table[25] = ub.getCombo25();
            table[26] = ub.getCombo26();
            table[27] = ub.getCombo27();
            table[28] = ub.getCombo28();
            table[29] = ub.getCombo29();
            table[30] = ub.getCombo30();
            table[31] = ub.getCombo31();
            table[32] = ub.getCombo32();
            table[33] = ub.getCombo33();
            table[34] = ub.getCombo34();
            table[35] = ub.getCombo35();
            table[36] = ub.getCombo36();
            table[37] = ub.getCombo37();
            table[38] = ub.getCombo38();
            table[39] = ub.getCombo39();
            table[40] = ub.getCombo40();
            table[41] = ub.getCombo41();
            table[42] = ub.getCombo42();
            table[43] = ub.getCombo43();
            table[44] = ub.getCombo44();
            table[45] = ub.getCombo45();
            table[46] = ub.getCombo46();
            table[47] = ub.getCombo47();
            table[48] = ub.getCombo48();
            table[49] = ub.getCombo49();
            table[50] = ub.getCombo50();
            table[51] = ub.getCombo51();
            table[52] = ub.getCombo52();
            table[53] = ub.getCombo53();
            table[54] = ub.getCombo54();
            table[55] = ub.getCombo55();
            table[56] = ub.getCombo56();
            table[57] = ub.getCombo57();
            table[58] = ub.getCombo58();
            table[59] = ub.getCombo59();
            table[60] = ub.getCombo60();
            table[61] = ub.getCombo61();
            table[62] = ub.getCombo62();
            table[63] = ub.getCombo63();
            table[64] = ub.getCombo64();
            table[65] = ub.getCombo65();
            table[66] = ub.getCombo66();
            table[67] = ub.getCombo67();
            table[68] = ub.getCombo68();
            table[69] = ub.getCombo69();
            table[70] = ub.getCombo70();
            table[71] = ub.getCombo71();
            table[72] = ub.getCombo72();
            table[73] = ub.getCombo73();
            table[74] = ub.getCombo74();
            table[75] = ub.getCombo75();
            table[76] = ub.getCombo76();
            table[77] = ub.getCombo77();
            table[78] = ub.getCombo78();
            table[79] = ub.getCombo79();
            table[80] = ub.getCombo80();
            table[81] = ub.getCombo81();
            table[82] = ub.getCombo82();
            table[83] = ub.getCombo83();
            table[84] = ub.getCombo84();
            table[85] = ub.getCombo85();
            table[86] = ub.getCombo86();
            table[87] = ub.getCombo87();
            table[88] = ub.getCombo88();
            table[89] = ub.getCombo89();
            table[90] = ub.getCombo90();
            table[91] = ub.getCombo91();
            table[92] = ub.getCombo92();
            table[93] = ub.getCombo93();
            table[94] = ub.getCombo94();
            table[95] = ub.getCombo95();
            table[96] = ub.getCombo96();
            table[97] = ub.getCombo97();
            table[98] = ub.getCombo98();
            table[99] = ub.getCombo99();
            table[100] = ub.getCombo100();
            table[111] = ub.getCombo101();
            table[112] = ub.getCombo102();
            table[113] = ub.getCombo103();
            table[114] = ub.getCombo104();
            table[115] = ub.getCombo105();
            table[116] = ub.getCombo106();
            table[117] = ub.getCombo107();
            table[118] = ub.getCombo108();
            table[119] = ub.getCombo109();
            return table;

        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            return table;
        } catch (Exception e) {
            return table;
        }

    }

    public  BibliographicDetails setBibliographicDetailsPojo(String[] map_table, BibliographicDetails pojo_object,
            BibliographicDetailsId id, String cellvalue, int column_index) throws Exception {



        try {
            if (map_table[column_index].equals("biblio_id")) {
                //  id.setBiblioId((getmax_biblio_id()+1));
            }
            if (map_table[column_index].equals("library_id")) {
                id.setLibraryId(cellvalue);


            }
            if (map_table[column_index].equals("sublibrary_id")) {
                id.setSublibraryId(cellvalue);
                pojo_object.setId(id);

            }
            if (map_table[column_index].equals("document_type")) {
                pojo_object.setDocumentType(cellvalue);

            }
            if (map_table[column_index].equals("book_type")) {
                pojo_object.setBookType(cellvalue);

            }
            if (map_table[column_index].equals("accession_type")) {
                pojo_object.setAccessionType(cellvalue);


            }

            if (map_table[column_index].equals("title")) {
                pojo_object.setTitle(cellvalue);

            }
            if (map_table[column_index].equals("subtitle")) {
                pojo_object.setSubtitle(cellvalue);


            }
            if (map_table[column_index].equals("alt_title")) {
                pojo_object.setAltTitle(cellvalue);

            }
            if (map_table[column_index].equals("statement_responsibility")) {
                pojo_object.setStatementResponsibility(cellvalue);

            }
            if (map_table[column_index].equals("main_entry")) {
                pojo_object.setMainEntry(cellvalue);

            }
            if (map_table[column_index].equals("added_entry")) {
                pojo_object.setAddedEntry(cellvalue);

            }
            if (map_table[column_index].equals("added_entry1")) {
                pojo_object.setAddedEntry1(cellvalue);

            }
            if (map_table[column_index].equals("added_entry2")) {
                pojo_object.setAddedEntry2(cellvalue);

            }
            if (map_table[column_index].equals("added_entry3")) {
                pojo_object.setAddedEntry3(cellvalue);

            }
            if (map_table[column_index].equals("publisher_name")) {
                pojo_object.setPublisherName(cellvalue);

            }
            if (map_table[column_index].equals("publication_place")) {
                pojo_object.setPublicationPlace(cellvalue);

            }
            if (map_table[column_index].equals("publishing_year")) {
                pojo_object.setPublishingYear(Integer.parseInt(cellvalue));

            }
            if (map_table[column_index].equals("call_no")) {
                pojo_object.setCallNo(cellvalue);

            }
            if (map_table[column_index].equals("parts_no")) {
                pojo_object.setPartsNo((Integer.getInteger(cellvalue)));

            }
            if (map_table[column_index].equals("subject")) {
                pojo_object.setSubject(cellvalue);

            }
            if (map_table[column_index].equals("entry_language")) {
                pojo_object.setEntryLanguage(cellvalue);

            }
            if (map_table[column_index].equals("isbn10")) {
                pojo_object.setIsbn10(cellvalue);

            }
            if (map_table[column_index].equals("isbn13")) {
                pojo_object.setIsbn13(cellvalue);

            }
            if (map_table[column_index].equals("LCC_no")) {
                pojo_object.setLccNo(cellvalue);

            }
            if (map_table[column_index].equals("edition")) {
                pojo_object.setEdition(cellvalue);

            }
            if (map_table[column_index].equals("no_of_copies")) {
                pojo_object.setNoOfCopies(column_index);

            }
            if (map_table[column_index].equals("author_name")) {
                pojo_object.setAuthorName(cellvalue);

            }
            if (map_table[column_index].equals("guide_name")) {
                pojo_object.setGuideName(cellvalue);

            }
            if (map_table[column_index].equals("university_faculty")) {
                pojo_object.setUniversityFaculty(cellvalue);

            }
            if (map_table[column_index].equals("degree")) {
                pojo_object.setDegree(cellvalue);

            }
            if (map_table[column_index].equals("submitted_on")) {
                pojo_object.setSubmittedOn(cellvalue);

            }
            if (map_table[column_index].equals("acceptance_year")) {
                pojo_object.setAcceptanceYear(cellvalue);

            }
            if (map_table[column_index].equals("collation1")) {
                pojo_object.setCollation1(cellvalue);

            }
            if (map_table[column_index].equals("notes")) {
                pojo_object.setNotes(cellvalue);

            }
            if (map_table[column_index].equals("abstract")) {
                pojo_object.setAbstract_(cellvalue);

            }
            if (map_table[column_index].equals("address")) {
                pojo_object.setAddress(cellvalue);

            }
            if (map_table[column_index].equals("state1")) {
                pojo_object.setState1(cellvalue);

            }
            if (map_table[column_index].equals("country")) {
                pojo_object.setCountry(cellvalue);

            }
            if (map_table[column_index].equals("email")) {
                pojo_object.setEmail(cellvalue);

            }
            if (map_table[column_index].equals("frmr_frq")) {
                pojo_object.setFrmrFrq(cellvalue);

            }
            if (map_table[column_index].equals("frq_date")) {
                pojo_object.setFrqDate(cellvalue);

            }
            if (map_table[column_index].equals("issn")) {
                pojo_object.setIssn(cellvalue);

            }
            if (map_table[column_index].equals("volume_location")) {
                pojo_object.setVolumeLocation(cellvalue);

            }
            if (map_table[column_index].equals("production_year")) {
                pojo_object.setProductionYear((Integer.getInteger(cellvalue)));

            }
            if (map_table[column_index].equals("source1")) {
                pojo_object.setSource1(cellvalue);

            }
            if (map_table[column_index].equals("duration")) {
                pojo_object.setDuration(cellvalue);

            }
            if (map_table[column_index].equals("series")) {
                pojo_object.setSeries(cellvalue);

            }
            if (map_table[column_index].equals("type_of_disc")) {
                pojo_object.setTypeOfDisc(cellvalue);

            }
            if (map_table[column_index].equals("file_type")) {
                pojo_object.setFileType(cellvalue);

            }

            pojo_object.setId(id);



        } catch (Exception ex) {
           ex.printStackTrace();


            

        } 
        return pojo_object;
    }
}
