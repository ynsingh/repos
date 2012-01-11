/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;

import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.opac.MemberSubLibrary;
import com.myapp.struts.opac.MixAccessionRecord;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;

/**
 *
 * @author Dushyant
 */
public class OpacSearchDAO {
    Criterion criterion;
    Criterion criterion1,criterion2,criterion3;

    public BibliographicDetails DocumentSearch2(int doc_id,String library_id,String sub_lib)
    {
        BibliographicDetails obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("id.biblioId",doc_id));
          obj= (BibliographicDetails)criteria.uniqueResult();
        }
      catch(Exception e)
        {
          e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }
      public static List searchVol(String library_id,String sub_lib,int biblio_id)
    {
          SQLQuery query=null;
          List obj=null;
      Session session=HibernateUtil.getSessionFactory().openSession();
       try {
            session.beginTransaction();
              query = session.createSQLQuery("select distinct volume_no  from document_details where library_id='"+library_id+"' and sublibrary_id='"+sub_lib+"' and biblio_id="+biblio_id +" and volume_no is not null and volume_no!=''");

          obj=  (List)query.list();
        }
       catch(Exception e){
           e.printStackTrace();
 
       }
       finally {
            session.close();
        }
       return obj;
        
    }
     public DocumentDetails accessionNoSearch2(String acc_no,String library_id,String sub_lib)
    {
         DocumentDetails obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(DocumentDetails.class);
          if(!library_id.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          if(!sub_lib.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
            if(acc_no.isEmpty()==false)
          criteria.add(Restrictions.eq("accessionNo",acc_no));
          obj= (DocumentDetails)criteria.uniqueResult();
        }
      catch(Exception e)
        {
           e.printStackTrace();
            return null;
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }
     
      public List simpleSearch(String library_id,String sub_lib,String [] searching_word,String word_connector,String doc_type,String sortby,String searching_field,Integer year1,Integer year2)
    {
        List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
         criteria.add(Restrictions.eq("documentType",doc_type));

         /*
          * Searching Criteria for searching words are connected as AND clause
          */
         if(searching_field.equalsIgnoreCase("any field"))
         {
        if(word_connector.equalsIgnoreCase("and"))
        {
           System.out.println("Search word length="+searching_word.length);
         for(int count=0;count<searching_word.length;count++)
         {
         criteria.add(Restrictions.or(Restrictions.like("title","%"+searching_word[count]+"%"),
                 Restrictions.or(Restrictions.like("authorName","%"+searching_word[count]+"%"),Restrictions.like("publisherName","%"+searching_word[count]+"%"))));
         }
        }
          /*
          * Searching Criteria for searching words are connected as OR clause
          */
        else
        {
             System.out.println("Search word length="+searching_word[0]+searching_word.length);
          if(searching_word.length>0&& !searching_word[0].isEmpty()){
            criterion=Restrictions.or(Restrictions.like("title","%"+searching_word[0]+"%"),
          Restrictions.or(Restrictions.like("mainEntry","%"+searching_word[0]+"%"),Restrictions.like("publisherName","%"+searching_word[0]+"%")));
         for(int count=1;count<searching_word.length;count++)
         {
         criterion=Restrictions.or(criterion,Restrictions.or(Restrictions.like("title","%"+searching_word[count]+"%"),
                 Restrictions.or(Restrictions.like("mainEntry","%"+searching_word[count]+"%"),Restrictions.like("publisherName","%"+searching_word[count]+"%"))));
         }
          criteria.add(criterion);
        }
        }
         }
         /*
          * Searching criteria if searching is based of any specific field
          */

         else
         {

                 if(word_connector.equalsIgnoreCase("and"))
        {
         for(int count=0;count<searching_word.length;count++)
         {
         criteria.add(Restrictions.like(searching_field,"%"+searching_word[count]+"%"));
         }
        }
          /*
          * Searching Criteria for searching words are connected as OR clause
          */
        else
        {
         if(searching_word.length>0){
         criterion=Restrictions.like(searching_field,"%"+searching_word[0]+"%");
         for(int count=1;count<searching_word.length;count++)
         {
         criterion=Restrictions.or(criterion,Restrictions.like(searching_field,"%"+searching_word[count]+"%"));
         }
          criteria.add(criterion);
         }
        }

      }
String yr1 =null;
         if(year1!=null && year1>0){
              System.out.println("Year1="+year1);
         yr1 = String.valueOf(year1);
         criteria.add(Restrictions.gt("publishingYear",yr1));}
        String yr2=null;
        if(year2!=null && year2>0){
            System.out.println("Year2="+year2);
         yr2 = String.valueOf(year2);
         criteria.add(Restrictions.lt("publishingYear",yr2));}
         criteria.addOrder(Order.asc(sortby));
         obj= criteria.list();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        finally
        {
hsession.close();
        }
        return obj;
            }
        public List simpleLangSearch(String library_id,String sub_lib,String [] searching_word,String word_connector,String doc_type,String sortby,String searching_field,Integer year1,Integer year2,String lang)
    {
        List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
         criteria.add(Restrictions.eq("documentType",doc_type));

           criteria.add(Restrictions.eq("entryLanguage",lang));
         /*
          * Searching Criteria for searching words are connected as AND clause
          */
         if(searching_field.equalsIgnoreCase("any field"))
         {
        if(word_connector.equalsIgnoreCase("and"))
        {
           System.out.println("Search word length="+searching_word.length);
         for(int count=0;count<searching_word.length;count++)
         {
         criteria.add(Restrictions.or(Restrictions.like("title","%"+searching_word[count]+"%"),
                 Restrictions.or(Restrictions.like("mainEntry","%"+searching_word[count]+"%"),Restrictions.like("publisherName","%"+searching_word[count]+"%"))));
         }
        }
          /*
          * Searching Criteria for searching words are connected as OR clause
          */
        else
        {
             System.out.println("Search word length="+searching_word[0]+searching_word.length);
          if(searching_word.length>0&& !searching_word[0].isEmpty()){
            criterion=Restrictions.or(Restrictions.like("title","%"+searching_word[0]+"%"),
          Restrictions.or(Restrictions.like("mainEntry","%"+searching_word[0]+"%"),Restrictions.like("publisherName","%"+searching_word[0]+"%")));
         for(int count=1;count<searching_word.length;count++)
         {
         criterion=Restrictions.or(criterion,Restrictions.or(Restrictions.like("title","%"+searching_word[count]+"%"),
                 Restrictions.or(Restrictions.like("mainEntry","%"+searching_word[count]+"%"),Restrictions.like("publisherName","%"+searching_word[count]+"%"))));
         }
          criteria.add(criterion);
        }
        }
         }
         /*
          * Searching criteria if searching is based of any specific field
          */

         else
         {

                 if(word_connector.equalsIgnoreCase("and"))
        {
         for(int count=0;count<searching_word.length;count++)
         {
         criteria.add(Restrictions.like(searching_field,"%"+searching_word[count]+"%"));
         }
        }
          /*
          * Searching Criteria for searching words are connected as OR clause
          */
        else
        {
         if(searching_word.length>0){
         criterion=Restrictions.like(searching_field,"%"+searching_word[0]+"%");
         for(int count=1;count<searching_word.length;count++)
         {
         criterion=Restrictions.or(criterion,Restrictions.like(searching_field,"%"+searching_word[count]+"%"));
         }
          criteria.add(criterion);
         }
        }

      }
String yr1 =null;
         if(year1!=null && year1>0){
              System.out.println("Year1="+year1);
         yr1 = String.valueOf(year1);
         criteria.add(Restrictions.gt("publishingYear",yr1));}
        String yr2=null;
        if(year2!=null && year2>0){
            System.out.println("Year2="+year2);
         yr2 = String.valueOf(year2);
         criteria.add(Restrictions.lt("publishingYear",yr2));}
         criteria.addOrder(Order.asc(sortby));
         return criteria.list();

        }
        catch(Exception e)
        {
           e.printStackTrace();
            
        }
        finally
        {
hsession.close();
        }
        return obj;
            }

      public List browseSearch(String library_id,String sub_lib,String searching_word,String doc_type,String sortby,String searching_field)
    {
        List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
         criteria.add(Restrictions.eq("documentType",doc_type));

         /*
          * Searching Criteria for searching words are connected as AND clause
          */
         if(searching_field.equalsIgnoreCase("any field"))
         {

         criteria.add(Restrictions.or(Restrictions.like("title",searching_word+"%"),
                 Restrictions.or(Restrictions.like("mainEntry",searching_word+"%"),Restrictions.like("publisherName",searching_word+"%"))));
         }
         /*
          * Searching criteria if searching is based of any specific field
          */

         else
         {
         criteria.add(Restrictions.like(searching_field,searching_word+"%"));
         }
         criteria.addOrder(Order.asc(sortby));
         obj= criteria.list();
        }
        catch(Exception e)
        {
           e.printStackTrace();
           
        }
        finally
        {
hsession.close();
        }
return obj;
   }

                public List browseLangSearch(String library_id,String sub_lib,String searching_word,String doc_type,String sortby,String searching_field,String language)
    {
       List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
         criteria.add(Restrictions.eq("documentType",doc_type));
        criteria.add(Restrictions.eq("entryLanguage",language));
         /*
          * Searching Criteria for searching words are connected as AND clause
          */
         if(searching_field.equalsIgnoreCase("any field"))
         {

         criteria.add(Restrictions.or(Restrictions.like("title",searching_word+"%"),
                 Restrictions.or(Restrictions.like("mainEntry",searching_word+"%"),Restrictions.like("publisherName",searching_word+"%"))));
         }
         /*
          * Searching criteria if searching is based of any specific field
          */

         else
         {
         criteria.add(Restrictions.like(searching_field,searching_word+"%"));
         }
         criteria.addOrder(Order.asc(sortby));
        obj= criteria.list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
           
        }
        finally
        {
hsession.close();
        }
        return obj;

   }

 public List additionalSearch(String library_id,String sub_lib,String [] author,String author_connector,
         String [] title,String title_connector,String [] subject,String subject_connector,
         String [] other_field,String other_connector,String doc_type,
         String sortby,Integer year1,Integer year2)
    {
        Session hsession=HibernateUtil.getSessionFactory().getCurrentSession();
        List obj=null;
        try
        {
            hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
         if(!library_id.equalsIgnoreCase("all"))
            criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
            criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
            criteria.add(Restrictions.eq("documentType",doc_type));
         /*
          * Criteria for Author field
          */
         if(author!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<author.length;i++)
         {
          criteria.add(Restrictions.like("mainEntry","%"+ author[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
         criterion=Restrictions.like("mainEntry","%"+author[0]+"%");
         for(int count=1;count<author.length;count++)
            {
             criterion=Restrictions.or(criterion,Restrictions.like("mainEntry","%"+author[count]+"%"));
            }
         criteria.add(criterion);
         }
         else
         {
            criteria.add(Restrictions.like("mainEntry", "%"+author[0]+"%"));
         }
         }
         /*
          * Criteria for Title field
          */
         if(title!=null)
         {
         if(title_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<title.length;i++)
         {
          criteria.add(Restrictions.like("title", "%"+title[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
         criterion=Restrictions.like("title","%"+title[0]+"%");
         for(int count=1;count<title.length;count++)
            {
            criterion=Restrictions.or(criterion,Restrictions.like("title","%"+title[count]+"%"));
            }
         criteria.add(criterion);
         }
         else
         {
            criteria.add(Restrictions.like("title","%"+ title[0]+"%"));
         }
         }
         /*
          * Criteria for Subject field
          */
         if(subject!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<subject.length;i++)
         {
          criteria.add(Restrictions.eq("subject","%"+ title[i]+"%"));
          }
         }
         else if(subject_connector.equalsIgnoreCase("or"))
         {
            criterion=Restrictions.like("subject","%"+subject[0]+"%");
           for(int count=0;count<subject.length;count++)
           {
         criterion=Restrictions.or(criterion,Restrictions.like("subject","%"+subject[count]+"%"));
            }
            criteria.add(criterion);
         }
         else
         {
         criteria.add(Restrictions.like("subject","%"+title[0]+"%"));
         }
         }
         /*
          * Criteria for Other field
          */
         if(other_field!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<other_field.length;i++)
         {
          criteria.add(Restrictions.eq("subject", title[i]));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
             for(int i=0;i<other_field.length;i++)
            {
             criteria.add(Restrictions.eq("subject", title[i]));
             }
         }
         else
         {
            criteria.add(Restrictions.eq("subject", title[0]));
         }
         }
         if(year1!=null)
         criteria.add(Restrictions.gt("publishingYear",year1));
         if(year2!=null)
         criteria.add(Restrictions.lt("publishingYear",year2));
         if(sortby!=null)
            criteria.addOrder(Order.asc(sortby));
        obj= criteria.list();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            
           
        }
        finally
        {
hsession.close();
        }
        return obj;
            }

  public List additionalSearchLang(String library_id,String sub_lib,String [] author,String author_connector,
         String [] title,String title_connector,String [] subject,String subject_connector,
         String [] other_field,String other_connector,String doc_type,
         String sortby,Integer year1,Integer year2,String language)
    {
        List obj=null;
      System.out.println("Library not Pass");
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         System.out.println("Library not Pass");
         Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
         criteria.add(Restrictions.eq("documentType",doc_type));

          criteria.add(Restrictions.eq("entryLanguage",language));
         /*
          * Criteria for Author field
          */
         System.out.println("Library Pass");
         if(author!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<author.length;i++)
         {
          criteria.add(Restrictions.like("mainEntry","%"+ author[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
         criterion=Restrictions.like("mainEntry","%"+author[0]+"%");
         for(int count=1;count<author.length;count++)
            {
             criterion=Restrictions.or(criterion,Restrictions.like("mainEntry","%"+author[count]+"%"));
            }
         criteria.add(criterion);
         }
         else
         {
            criteria.add(Restrictions.like("mainEntry", "%"+author[0]+"%"));
         }
         }
         System.out.println("Author Pass");
         /*
          * Criteria for Title field
          */
         if(title!=null)
         {
         if(title_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<title.length;i++)
         {
          criteria.add(Restrictions.like("title", "%"+title[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
         criterion=Restrictions.like("title","%"+title[0]+"%");
         for(int count=1;count<title.length;count++)
            {
            criterion=Restrictions.or(criterion,Restrictions.like("title","%"+title[count]+"%"));
            }
         criteria.add(criterion);
         }
         else
         {
            criteria.add(Restrictions.like("title","%"+ title[0]+"%"));
         }
         }
         System.out.println("title Pass");
         /*
          * Criteria for Subject field
          */
         if(subject!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<subject.length;i++)
         {
          criteria.add(Restrictions.eq("subject","%"+ title[i]+"%"));
          }
         }
         else if(subject_connector.equalsIgnoreCase("or"))
         {
            criterion=Restrictions.like("subject","%"+subject[0]+"%");
           for(int count=0;count<subject.length;count++)
           {
         criterion=Restrictions.or(criterion,Restrictions.like("subject","%"+subject[count]+"%"));
            }
            criteria.add(criterion);
         }
         else
         {
         criteria.add(Restrictions.like("subject","%"+title[0]+"%"));
         }
         }
         System.out.println("Other Field Pass");
         /*
          * Criteria for Other field
          */
         if(other_field!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<other_field.length;i++)
         {
          criteria.add(Restrictions.eq("subject", title[i]));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
             for(int i=0;i<other_field.length;i++)
            {
             criteria.add(Restrictions.eq("subject", title[i]));
             }
         }
         else
         {
            criteria.add(Restrictions.eq("subject", title[0]));
         }
         }
System.out.println("Author Pass");
         if(year1!=null)
         criteria.add(Restrictions.gt("publishingYear",year1));
         if(year2!=null)
         criteria.add(Restrictions.lt("publishingYear",year2));
         if(sortby!=null)
            criteria.addOrder(Order.asc(sortby));
        System.out.println("sortBy Pass");

         obj= criteria.list();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        finally
        {
hsession.close();
        }
        return obj;
            }
  
 public List advanceSearch(String library_id,String sub_lib,String [] searchText1,String searchText1_connector,
         String [] searchText2,String searchText2_connector,String [] searchText3,String searchText3_connector,
         String query1_connector,String query2_connector,String query3_connector,
         String field1,String field2,String field3,
         String doc_type,String sortby,Integer year1,Integer year2)
    {
        List obj=null;
      System.out.println("lib_id="+library_id+" sublib="+sub_lib+" searchText1="+searchText1+" search1connector="+searchText1_connector);
      System.out.println("searchText2="+searchText2+" search2connector="+searchText2_connector+" searchText3="+searchText3+" search3connector="+searchText3_connector);
      System.out.println("query1connector="+query1_connector+" query2connector="+query2_connector+" query3connector="+query3_connector+" field1="+field1+" field2="+field2+" field3="+field3);
      System.out.println("doc_type="+doc_type+" sortby="+sortby+" year1="+year1+" year2="+year2);
      Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
         
         if(!library_id.equalsIgnoreCase("all"))
            criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
            criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
            criteria.add(Restrictions.eq("documentType",doc_type));
         /*
          * Criteria for first searchText
          */
         if(searchText1!=null)
         {
         if(searchText1_connector.equalsIgnoreCase("and"))
         {
               if(field1.equals("author"))
                  criterion1=Restrictions.like("mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("isbn10","%"+searchText1[0]+"%");

         for(int i=1;i<searchText1.length;i++)
         {
              if(field1.equals("author"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("mainEntry","%"+searchText1[i]+"%"));
             else if(field1.equals("subject"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("subject","%"+searchText1[i]+"%"));
             else if(field1.equals("title"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("title","%"+searchText1[i]+"%"));
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("isbn10","%"+searchText1[i]+"%"));

          }
         }
         else if(searchText1_connector.equalsIgnoreCase("or"))
         {
               if(field1.equals("author"))
                  criterion1=Restrictions.like("mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("isbn10","%"+searchText1[0]+"%");
    System.out.println("searchText1="+searchText1[0]);
         for(int count=1;count<searchText1.length;count++)
            {
               if(field1.equals("author"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("mainEntry","%"+searchText1[count]+"%"));
             else if(field1.equals("subject"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("subject","%"+searchText1[count]+"%"));
             else if(field1.equals("title"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("title","%"+searchText1[count]+"%"));
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("isbn10","%"+searchText1[count]+"%"));
System.out.println("searchText1="+searchText1[count]);
            }
        // criteria.add(criterion);
         }
         else
         {
             if(field1.equals("author"))
                  criterion1=Restrictions.like("mainEntry", "%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("subject", "%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("title", "%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("isbn10", "%"+searchText1[0]+"%");

         }
         }
         /*
          * Criteria for second search Text field
          */
          if(searchText2!=null)
         {
         if(searchText2_connector.equalsIgnoreCase("and"))
         {
               if(field2.equals("author"))
                  criterion2=Restrictions.like("mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("isbn10","%"+searchText2[0]+"%");

         for(int i=1;i<searchText2.length;i++)
         {
              if(field2.equals("author"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("mainEntry","%"+searchText2[i]+"%"));
             else if(field2.equals("subject"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("subject","%"+searchText2[i]+"%"));
             else if(field2.equals("title"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("title","%"+searchText2[i]+"%"));
             else if(field1.equals("isbn10"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("isbn10","%"+searchText2[i]+"%"));

          }
         }
         else if(searchText2_connector.equalsIgnoreCase("or"))
         {
               if(field2.equals("author"))
                  criterion2=Restrictions.like("mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("isbn10","%"+searchText2[0]+"%");

         for(int count=1;count<searchText2.length;count++)
            {
               if(field2.equals("author"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("mainEntry","%"+searchText2[count]+"%"));
             else if(field2.equals("subject"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("subject","%"+searchText2[count]+"%"));
             else if(field2.equals("title"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("title","%"+searchText2[count]+"%"));
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("isbn10","%"+searchText2[count]+"%"));

            }
        // criteria.add(criterion);
         }
         else
         {
             if(field2.equals("author"))
                  criterion2=Restrictions.like("mainEntry", "%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("subject", "%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("title", "%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("isbn10", "%"+searchText2[0]+"%");

         }
         }
         /*
          * Criteria for third search Text field
          */
          if(searchText3!=null)
         {
         if(searchText3_connector.equalsIgnoreCase("and"))
         {
               if(field3.equals("author"))
                  criterion3=Restrictions.like("mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("isbn10","%"+searchText3[0]+"%");

         for(int i=1;i<searchText3.length;i++)
         {
              if(field3.equals("author"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("mainEntry","%"+searchText3[i]+"%"));
             else if(field3.equals("subject"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("subject","%"+searchText3[i]+"%"));
             else if(field3.equals("title"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("title","%"+searchText3[i]+"%"));
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("isbn10","%"+searchText3[i]+"%"));

          }
         }
         else if(searchText3_connector.equalsIgnoreCase("or"))
         {
               if(field3.equals("author"))
                  criterion3=Restrictions.like("mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("isbn10","%"+searchText3[0]+"%");

         for(int count=1;count<searchText3.length;count++)
            {
               if(field3.equals("author"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("mainEntry","%"+searchText3[count]+"%"));
             else if(field3.equals("subject"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("subject","%"+searchText3[count]+"%"));
             else if(field3.equals("title"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("title","%"+searchText3[count]+"%"));
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("isbn10","%"+searchText3[count]+"%"));

            }
        // criteria.add(criterion);
         }
         else
         {
             if(field3.equals("author"))
                  criterion3=Restrictions.like("mainEntry", "%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("subject", "%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("title", "%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("isbn10", "%"+searchText3[0]+"%");

         }
         }
         if(searchText1!=null&&searchText2!=null&&searchText3!=null)
         {
         if(query1_connector.equals("or")) criterion = Restrictions.or(criterion1, criterion2);
         else criterion = Restrictions.and(criterion1, criterion2);

         if(query2_connector.equals("or")) criterion = Restrictions.or(criterion, criterion3);
         else criterion = Restrictions.and(criterion, criterion3);
         }
         else if (searchText1!=null&&searchText2!=null)
         {
                if(query1_connector.equals("or")) criterion = Restrictions.or(criterion1, criterion2);
                else criterion = Restrictions.and(criterion1, criterion2);
         }
         else if(searchText1!=null&&searchText3!=null)
         {
             if(query1_connector.equals("or")) criterion = Restrictions.or(criterion1, criterion3);
             else criterion = Restrictions.and(criterion1, criterion3);
         }
         else if(searchText2!=null&&searchText3!=null)
         {
             if(query2_connector.equals("or")) criterion = Restrictions.or(criterion2, criterion3);
             else criterion = Restrictions.and(criterion2, criterion3);
         }
         else if(searchText1!=null)
             criteria.add(criterion1);
         else if(searchText2!=null)
             criteria.add(criterion2);
         else if(searchText3!=null)
             criteria.add(criterion3);

         if(criterion!=null)
            criteria.add(criterion);

      //   criteria.add(Restrictions.eq("status","Available"));
         if(year1>0)
         criteria.add(Restrictions.gt("publishingYear",year1.toString()));
         if(year2>0)
         criteria.add(Restrictions.lt("publishingYear",year2.toString()));
         if(sortby.equals("author"))
              criteria.addOrder(Order.asc("mainEntry"));
         else if(sortby.equals("title"))
              criteria.addOrder(Order.asc("title"));
         else if(sortby.equals("isbn10"))
              criteria.addOrder(Order.asc("isbn10"));
         else if(sortby.equals("subject"))
              criteria.addOrder(Order.asc("subject"));

         obj= criteria.list();

        }
        catch(Exception e)
        {
         e.printStackTrace();
            
        }
        finally
        {
hsession.close();
        }
        return obj;
            }

  public List advanceLangSearch(String library_id,String sub_lib,String [] searchText1,String searchText1_connector,
         String [] searchText2,String searchText2_connector,String [] searchText3,String searchText3_connector,
         String query1_connector,String query2_connector,String query3_connector,
         String field1,String field2,String field3,
         String doc_type,String sortby,Integer year1,Integer year2,String language)
    {
      List obj=null;
      System.out.println("lib_id="+library_id+" sublib="+sub_lib+" searchText1="+searchText1+" search1connector="+searchText1_connector);
      System.out.println("searchText2="+searchText2+" search2connector="+searchText2_connector+" searchText3="+searchText3+" search3connector="+searchText3_connector);
      System.out.println("query1connector="+query1_connector+" query2connector="+query2_connector+" query3connector="+query3_connector+" field1="+field1+" field2="+field2+" field3="+field3);
      System.out.println("doc_type="+doc_type+" sortby="+sortby+" year1="+year1+" year2="+year2);
      Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);

         if(!library_id.equalsIgnoreCase("all"))
            criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
            criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(!doc_type.equalsIgnoreCase("combined"))
            criteria.add(Restrictions.eq("documentType",doc_type));
             criteria.add(Restrictions.eq("entryLanguage",language));
         /*
          * Criteria for first searchText
          */
         if(searchText1!=null)
         {
         if(searchText1_connector.equalsIgnoreCase("and"))
         {
               if(field1.equals("author"))
                  criterion1=Restrictions.like("mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("isbn10","%"+searchText1[0]+"%");

         for(int i=1;i<searchText1.length;i++)
         {
              if(field1.equals("author"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("mainEntry","%"+searchText1[i]+"%"));
             else if(field1.equals("subject"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("subject","%"+searchText1[i]+"%"));
             else if(field1.equals("title"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("title","%"+searchText1[i]+"%"));
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("isbn10","%"+searchText1[i]+"%"));

          }
         }
         else if(searchText1_connector.equalsIgnoreCase("or"))
         {
               if(field1.equals("author"))
                  criterion1=Restrictions.like("mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("isbn10","%"+searchText1[0]+"%");
    System.out.println("searchText1="+searchText1[0]);
         for(int count=1;count<searchText1.length;count++)
            {
               if(field1.equals("author"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("mainEntry","%"+searchText1[count]+"%"));
             else if(field1.equals("subject"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("subject","%"+searchText1[count]+"%"));
             else if(field1.equals("title"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("title","%"+searchText1[count]+"%"));
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("isbn10","%"+searchText1[count]+"%"));
System.out.println("searchText1="+searchText1[count]);
            }
        // criteria.add(criterion);
         }
         else
         {
             if(field1.equals("author"))
                  criterion1=Restrictions.like("mainEntry", "%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("subject", "%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("title", "%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("isbn10", "%"+searchText1[0]+"%");

         }
         }
         /*
          * Criteria for second search Text field
          */
          if(searchText2!=null)
         {
         if(searchText2_connector.equalsIgnoreCase("and"))
         {
               if(field2.equals("author"))
                  criterion2=Restrictions.like("mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("isbn10","%"+searchText2[0]+"%");

         for(int i=1;i<searchText2.length;i++)
         {
              if(field2.equals("author"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("mainEntry","%"+searchText2[i]+"%"));
             else if(field2.equals("subject"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("subject","%"+searchText2[i]+"%"));
             else if(field2.equals("title"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("title","%"+searchText2[i]+"%"));
             else if(field1.equals("isbn10"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("isbn10","%"+searchText2[i]+"%"));

          }
         }
         else if(searchText2_connector.equalsIgnoreCase("or"))
         {
               if(field2.equals("author"))
                  criterion2=Restrictions.like("mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("isbn10","%"+searchText2[0]+"%");

         for(int count=1;count<searchText2.length;count++)
            {
               if(field2.equals("author"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("mainEntry","%"+searchText2[count]+"%"));
             else if(field2.equals("subject"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("subject","%"+searchText2[count]+"%"));
             else if(field2.equals("title"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("title","%"+searchText2[count]+"%"));
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("isbn10","%"+searchText2[count]+"%"));

            }
        // criteria.add(criterion);
         }
         else
         {
             if(field2.equals("author"))
                  criterion2=Restrictions.like("mainEntry", "%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("subject", "%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("title", "%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("isbn10", "%"+searchText2[0]+"%");

         }
         }
         /*
          * Criteria for third search Text field
          */
          if(searchText3!=null)
         {
         if(searchText3_connector.equalsIgnoreCase("and"))
         {
               if(field3.equals("author"))
                  criterion3=Restrictions.like("mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("isbn10","%"+searchText3[0]+"%");

         for(int i=1;i<searchText3.length;i++)
         {
              if(field3.equals("author"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("mainEntry","%"+searchText3[i]+"%"));
             else if(field3.equals("subject"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("subject","%"+searchText3[i]+"%"));
             else if(field3.equals("title"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("title","%"+searchText3[i]+"%"));
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("isbn10","%"+searchText3[i]+"%"));

          }
         }
         else if(searchText3_connector.equalsIgnoreCase("or"))
         {
               if(field3.equals("author"))
                  criterion3=Restrictions.like("mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("isbn10","%"+searchText3[0]+"%");

         for(int count=1;count<searchText3.length;count++)
            {
               if(field3.equals("author"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("mainEntry","%"+searchText3[count]+"%"));
             else if(field3.equals("subject"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("subject","%"+searchText3[count]+"%"));
             else if(field3.equals("title"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("title","%"+searchText3[count]+"%"));
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("isbn10","%"+searchText3[count]+"%"));

            }
        // criteria.add(criterion);
         }
         else
         {
             if(field3.equals("author"))
                  criterion3=Restrictions.like("mainEntry", "%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("subject", "%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("title", "%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("isbn10", "%"+searchText3[0]+"%");

         }
         }
         if(searchText1!=null&&searchText2!=null&&searchText3!=null)
         {
         if(query1_connector.equals("or")) criterion = Restrictions.or(criterion1, criterion2);
         else criterion = Restrictions.and(criterion1, criterion2);

         if(query2_connector.equals("or")) criterion = Restrictions.or(criterion, criterion3);
         else criterion = Restrictions.and(criterion, criterion3);
         }
         else if (searchText1!=null&&searchText2!=null)
         {
                if(query1_connector.equals("or")) criterion = Restrictions.or(criterion1, criterion2);
                else criterion = Restrictions.and(criterion1, criterion2);
         }
         else if(searchText1!=null&&searchText3!=null)
         {
             if(query1_connector.equals("or")) criterion = Restrictions.or(criterion1, criterion3);
             else criterion = Restrictions.and(criterion1, criterion3);
         }
         else if(searchText2!=null&&searchText3!=null)
         {
             if(query2_connector.equals("or")) criterion = Restrictions.or(criterion2, criterion3);
             else criterion = Restrictions.and(criterion2, criterion3);
         }
         else if(searchText1!=null)
             criteria.add(criterion1);
         else if(searchText2!=null)
             criteria.add(criterion2);
         else if(searchText3!=null)
             criteria.add(criterion3);

         if(criterion!=null)
            criteria.add(criterion);

         //criteria.add(Restrictions.eq("status","Available"));
         if(year1>0)
         criteria.add(Restrictions.gt("publishingYear",year1.toString()));
         if(year2>0)
         criteria.add(Restrictions.lt("publishingYear",year2.toString()));
         if(sortby.equals("author"))
              criteria.addOrder(Order.asc("mainEntry"));
         else if(sortby.equals("title"))
              criteria.addOrder(Order.asc("title"));
         else if(sortby.equals("isbn10"))
              criteria.addOrder(Order.asc("isbn10"));
         else if(sortby.equals("subject"))
              criteria.addOrder(Order.asc("subject"));

        obj= criteria.list();

        }
        catch(Exception e)
        {
         e.printStackTrace();
            
        }
        finally
        {
hsession.close();
        }
        return obj;
            }


 public List isbnSearch(String isbn,String library_id,String sub_lib)
    {
     List obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
          if(!library_id.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          if(!sub_lib.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
           if (!StringUtils.isEmpty(isbn)||!StringUtils.isBlank(isbn)) {
          criteria.add(Restrictions.eq("isbn10",isbn));
        }
         obj= (List) criteria.list();
        }
      catch(Exception e)
        {
           e.printStackTrace();
          
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public List isbnLangSearch(String isbn,String library_id,String sub_lib,String language)
    {

List obj=null;

      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);
          if(!library_id.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          if(!sub_lib.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
    if (!StringUtils.isEmpty(isbn)||!StringUtils.isBlank(isbn)) {
          criteria.add(Restrictions.eq("isbn10",isbn));
        }



          criteria.add(Restrictions.eq("entryLanguage",language));
         obj= (List) criteria.list();
        }
      catch(Exception e)
        {
           e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

     public List callNoSearch(String call_no,String library_id,String sub_lib)
    {
        List obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
          if(!library_id.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          if(!sub_lib.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(call_no.isEmpty()==false)
          criteria.add(Restrictions.eq("callNo",call_no));
          obj= (List) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
           
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }
public List callNoLangSearch(String call_no,String library_id,String sub_lib,String language)
    {
List obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);
          if(!library_id.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          if(!sub_lib.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         if(call_no.isEmpty()==false)
          criteria.add(Restrictions.eq("callNo",call_no));
          criteria.add(Restrictions.eq("entryLanguage",language));
       obj= (List) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

   public List<MixAccessionRecord> accessionNoLangSearch(String acc_no,String library_id,String sub_lib)
    {
     Session session =null;
    List<MixAccessionRecord> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
 sql = "select a.*,b.* from accession_register a,bibliographic_details_lang b where  a.biblio_id=b.biblio_id  and a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and  a.accession_no='"+acc_no+"'";
            
if(!library_id.equals("all"))
    sql+="  and a.library_id='"+library_id+"'";
 if(!sub_lib.equals("all"))
     sql+=" and a.sublibrary_id='"+sub_lib+"'";

 System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(BibliographicDetailsLang.class)
                    .addEntity(AccessionRegister.class)
                    .setResultTransformer(Transformers.aliasToBean(MixAccessionRecord.class));
          obj=(List<MixAccessionRecord>) query.list();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }

return obj;


    }
 public List<BibliographicDetails> accessionNoBibSearch(int biblio_id,String library_id,String sub_lib)
    {
      List<BibliographicDetails> obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
         
           criteria.add(Restrictions.eq("id.libraryId",library_id));
          
            criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          
             criteria.add(Restrictions.eq("id.biblioId",biblio_id));
          obj= (List<BibliographicDetails>) criteria.list();
        }
      catch(Exception e)
        {
           e.printStackTrace();
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public List<BibliographicDetailsLang> accessionNoBibLangSearch(int biblio_id,String library_id,String sub_lib)
    {
     List<BibliographicDetailsLang> obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);

           criteria.add(Restrictions.eq("id.libraryId",library_id));

            criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));

             criteria.add(Restrictions.eq("id.biblioId",biblio_id));
          obj= (List<BibliographicDetailsLang>) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }


      public List<DocumentDetails> accessionNoSearch(String acc_no,String library_id,String sub_lib)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List<DocumentDetails> obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(DocumentDetails.class);
          if(!library_id.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          if(!sub_lib.equalsIgnoreCase("all"))
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
            if(acc_no.isEmpty()==false)
          criteria.add(Restrictions.eq("accessionNo",acc_no));
          obj= (List<DocumentDetails>) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public List LibrarySearch(String library_id)
    {
      Session hsession=null;
      List obj=null;
      try
        {
          hsession=HibernateUtil.getSessionFactory().openSession();
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(Library.class);
          criteria.add(Restrictions.not(Restrictions.like("libraryId","libms")));

        obj= (List) criteria.list();
        }
      catch(Exception e)
        {
            
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public List subLibrarySearch(String library_id)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(SubLibrary.class);
          if(library_id!=null)
            criteria.add(Restrictions.like("id.libraryId",library_id));
         criteria.add(Restrictions.not(Restrictions.like("id.libraryId","libms")));

          obj= (List) criteria.list();
      }
      catch(Exception e)
        {
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }
public List<DocumentDetails> DocumentSearchByDocId(String doc_id,String library_id,String sub_lib)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List<DocumentDetails> obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(DocumentDetails.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("accessionNo",doc_id));
          obj=(List<DocumentDetails>) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }


public List<DocumentDetails> DocumentSearchById(int doc_id,String library_id,String sub_lib)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List<DocumentDetails> obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(DocumentDetails.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("biblioId",doc_id));
          obj= (List<DocumentDetails>) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public List<DocumentDetails> DocumentSearchById1(int doc_id,String library_id,String sub_lib)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List<DocumentDetails> obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(DocumentDetails.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("id.documentId",doc_id));
          obj=(List<DocumentDetails>) criteria.list();
        }
      catch(Exception e)
        {
           e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }


public List<BibliographicDetailsLang> DocumentSearch(int doc_id,String library_id,String sub_lib)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List<BibliographicDetailsLang> obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("id.biblioId",doc_id));
          obj= (List<BibliographicDetailsLang>) criteria.list();
        }
      catch(Exception e)
        {
           e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public List<BibliographicDetails> DocumentSearch1(int doc_id,String library_id,String sub_lib)
    {
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      List<BibliographicDetails> obj=null;
      try
        {
          hsession.beginTransaction();
          Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("id.biblioId",doc_id));
          obj= (List<BibliographicDetails>) criteria.list();
        }
      catch(Exception e)
        {
            e.printStackTrace();
            
        }
      finally
        {
            hsession.close();
        }
      return obj;
    }

public static List<MemberSubLibrary> MembersubLibrarySearch(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       List<MemberSubLibrary> obj=null;
       
        try {
            session.beginTransaction();


           
            Query query = session.createSQLQuery("select * from sub_library a inner join cir_member_account b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and b.memId=:mem_id1 and b.library_id=:libraryid")
                    .addEntity(SubLibrary.class)
                    .addEntity(CirMemberAccount.class);



            query.setString("libraryid",library_id);
            query.setString("mem_id1",memid);
           
            query.setResultTransformer(Transformers.aliasToBean(MemberSubLibrary.class));

            obj=(List<MemberSubLibrary>)query.list();
        }
        finally {
        session.close();
        }
return obj;
     }


}



