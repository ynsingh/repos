/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;

import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.opac.MemberSubLibrary;
import com.myapp.struts.opac.MixAccessionRecord;
import com.myapp.struts.opac.SearchPOJO;
import com.myapp.struts.utility.LoggerUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
public class OpacSearchDAO {
    Criterion criterion;
    Criterion criterion1,criterion2,criterion3;
    static int size;
    private static Logger log4j =LoggerUtils.getLogger();


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
          hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
     // SIMPLE SEARCH IN OPAC IN ENGLISH LANG
      public List simpleSearch(String library_id,String sub_lib,String [] searching_word,String word_connector,String doc_type,String sortby,String searching_field,String year1,String year2,int pageNumber,String cmbyr)
     {
        List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
             hsession.beginTransaction();
//           Criteria criteria = hsession.createCriteria(BibliographicDetails.class, "aliasOfTableA");
//        criteria.createAlias("aliasOfTableA.documentDetailses","aliasOfTableB",CriteriaSpecification.LEFT_JOIN);
//     //   criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

      String query="select distinct a from BibliographicDetails as a left join a.documentDetailses as l";



        if(!doc_type.equalsIgnoreCase("combined") && !library_id.equalsIgnoreCase("all"))
        {
            query+=" where  a.docType='"+doc_type+"' and a.id.libraryId='"+library_id+"' ";

        }
        else if(doc_type.equalsIgnoreCase("combined")==true &&  !library_id.equalsIgnoreCase("all"))
            query+=" where   a.id.libraryId='"+library_id+"' ";

             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.id.sublibraryId='"+sub_lib+"' ";


//         /*
        /* Searching Criteria for searching words are connected as AND clause
          */
//         if(searching_field.equalsIgnoreCase("any field"))
//         {
//            if(word_connector.equalsIgnoreCase("and"))
//            {
//                 for(int count=0;count<searching_word.length;count++)
//                 {
//                    criteria.add(Restrictions.or(Restrictions.like("aliasOfTableA.title","%"+searching_word[count]+"%"),
//                    Restrictions.or(Restrictions.like("aliasOfTableA.authorName","%"+searching_word[count]+"%"),Restrictions.like("aliasOfTableA.publisherName","%"+searching_word[count]+"%"))));
//                 }
//            }
//          /*
//          * Searching Criteria for searching words are connected as OR clause
//          */
//            else
//            {
//                  if(searching_word.length>0&& !searching_word[0].isEmpty())
//                  {
//                        criterion=Restrictions.or(Restrictions.like("aliasOfTableA.title","%"+searching_word[0]+"%"),
//                        Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry","%"+searching_word[0]+"%"),Restrictions.like("aliasOfTableA.publisherName","%"+searching_word[0]+"%")));
//                        for(int count=1;count<searching_word.length;count++)
//                        {
//                                criterion=Restrictions.or(criterion,Restrictions.or(Restrictions.like("aliasOfTableA.title","%"+searching_word[count]+"%"),
//                                Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry","%"+searching_word[count]+"%"),Restrictions.like("aliasOfTableA.publisherName","%"+searching_word[count]+"%"))));
//                        }
//                        criteria.add(criterion);
//                  }
//            }
//         }
//         /*
//          * Searching criteria if searching is based of any specific field
//          */
//
//         else
//         {
//
//
//
//
//
//               if(word_connector.equalsIgnoreCase("and"))
//               {
//                    for(int count=0;count<searching_word.length;count++)
//                    {
//                    criteria.add(Restrictions.like("aliasOfTableA."+searching_field,"%"+searching_word[count]+"%"));
//                    }
//               }
//                /*
//                * Searching Criteria for searching words are connected as OR clause
//                */
//                else
//                {
//                    if(searching_word.length>0)
//                    {
//                        criterion=Restrictions.like("aliasOfTableA."+searching_field,"%"+searching_word[0]+"%");
//                        for(int count=1;count<searching_word.length;count++)
//                        {
//                            criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA."+searching_field,"%"+searching_word[count]+"%"));
//                        }
//                        criteria.add(criterion);
//                    }
//                }
//
//        }
//            String yr1 =null;
//            if(cmbyr.equalsIgnoreCase("between"))
//            {
//                if(year1!=null && year1.isEmpty()==false)
//                {
//                    yr1 = String.valueOf(year1);
//                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
//                }
//                String yr2=null;
//                if(year2!=null && year2.isEmpty()==false)
//                {
//                    yr2 = String.valueOf(year2);
//                    criteria.add(Restrictions.lt("aliasOfTableA.publishingYear",yr2));
//                }
//            }
//            if(cmbyr.equalsIgnoreCase("upto"))
//            {
//                if(year1!=null && year1.isEmpty()==false)
//                {
//                    yr1 = String.valueOf(year1);
//                    criteria.add(Restrictions.le("aliasOfTableA.publishingYear",yr1));
//                }
//
//            }
//            if(cmbyr.equalsIgnoreCase("after"))
//            {
//               if(year1!=null && year1.isEmpty()==false)
//                {
//                    yr1 = String.valueOf(year1);
//                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
//                }
//
//            }
//            criteria.addOrder(Order.asc(sortby));
                 if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")==true)
                    query+=" where  ";
                  else
                      query+=" and ";


        /*
                * Searching Criteria for searching words are connected as AND clause
                */
                if(searching_field.equalsIgnoreCase("any field"))
                {

                    if(word_connector.equalsIgnoreCase("and"))
                    {
                        for(int count=0;count<searching_word.length;count++)
                        {
                            query+="  (a.title like '%"+searching_word[count]+"%' or a.mainEntry like '%"+searching_word[count]+"%' or a.publisherName like '%"+searching_word[count]+"%') ";
                        }
                    }
                    /*
                    * Searching Criteria for searching words are connected as OR clause
                    */
                    else
                    {

                          if(searching_word.length>0&& !searching_word[0].isEmpty())
                          {
                              query+="  (";
                               query+=" (a.title like '%"+searching_word[0]+"%' or a.mainEntry like '%"+searching_word[0]+"%' or a.publisherName like '%"+searching_word[0]+"%') ";
                                for(int count=1;count<searching_word.length;count++)
                                {
                                    query+=" or (a.title like '%"+searching_word[count]+"%' or a.mainEntry like '%"+searching_word[count]+"%' or a.publisherName like '%"+searching_word[count]+"%') ";
                                }
                               query+=" ) ";
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
                     query+="   a."+searching_field+" like '%"+searching_word[count]+"%' ";
                    }
                }
                /*
                * Searching Criteria for searching words are connected as OR clause
                */
                else
                {
                    if(searching_word.length>0)
                    {
                        query+="  (";
                       query+="  (a."+searching_field+" like '%"+searching_word[0]+"%') ";
                        for(int count=1;count<searching_word.length;count++)
                        {
                           query+=" or (a."+searching_field+" like '%"+searching_word[count]+"%') ";

                        }
                       query+=" ) ";

                    }
                }
            }
             String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    query+=" and a.publishingYear>'"+yr1+"' ";
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                      query+=" and a.publishingYear<'"+yr2+"' ";
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                     query+=" and a.publishingYear<='"+year1+"' ";
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                     query+=" and a.publishingYear>'"+year1+"' ";
                }

            }



            query+="  order by l."+sortby;

System.out.println(query);

           Query simple=hsession.createQuery(query);


             //Total Number of Record Found View in OPAC
            setSearchSize(simple.list());
             if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=(List<BibliographicDetails>)simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=(List<BibliographicDetails>)o.getList();
                }

            hsession.getTransaction().commit();
        }
        catch(Exception e)
        {
           log4j.error(e);

        }
        finally
        {
            hsession.close();
        }

        return obj;
        }
    // GET THE TOTAL NO OF RECORD FOUND FROM SIMPLE SEARCH IN ENGLISH
    public static void setSearchSize(List simpleSearch)
    {
        if(simpleSearch!=null)
            if(simpleSearch.isEmpty()==false)
             size=simpleSearch.size();
        else
            size=0;

        System.out.println("Size"+size);

    }

     // GET THE TOTAL NO OF RECORD FROM DAO METHOD
    public static int getSize()
    {
        return size;

    }
    // SIMPLE SEARCH LANGUAGE IN MLI LANG
    public List simpleLangSearch(String library_id,String sub_lib,String [] searching_word,String word_connector,String doc_type,String sortby,String searching_field,String year1,String year2,String lang,int pageNumber,String cmbyr)
    {
        List obj=new ArrayList();
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
             hsession.beginTransaction();

             String query="select * from bibliographic_details_lang a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";

               if(!library_id.equalsIgnoreCase("all"))
                  query+=" and  a.library_id='"+library_id+"' ";

             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.sublibrary_id='"+sub_lib+"' ";

             if(!doc_type.equalsIgnoreCase("combined"))
                   query+=" and a.document_type='"+doc_type+"' ";

             query+=" and a.entry_language='"+lang+"' ";


               /*
                * Searching Criteria for searching words are connected as AND clause
                */
                if(searching_field.equalsIgnoreCase("any field"))
                {
                    if(word_connector.equalsIgnoreCase("and"))
                    {
                        for(int count=0;count<searching_word.length;count++)
                        {
                            query+=" and (a.title like '%"+searching_word[count]+"%' or a.main_entry like '%"+searching_word[count]+"%' or a.publisher_name like '%"+searching_word[count]+"%') ";
                        }
                    }
                    /*
                    * Searching Criteria for searching words are connected as OR clause
                    */
                    else
                    {

                          if(searching_word.length>0&& !searching_word[0].isEmpty())
                          {
                              query+=" and (";
                               query+=" (a.title like '%"+searching_word[0]+"%' or a.main_entry like '%"+searching_word[0]+"%' or a.publisher_name like '%"+searching_word[0]+"%') ";
                                for(int count=1;count<searching_word.length;count++)
                                {
                                    query+=" or (a.title like '%"+searching_word[count]+"%' or a.main_entry like '%"+searching_word[count]+"%' or a.publisher_name like '%"+searching_word[count]+"%') ";
                                }
                               query+=" ) ";
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
                     query+="  and a."+searching_field+" like '%"+searching_word[count]+"%' ";
                    }
                }
                /*
                * Searching Criteria for searching words are connected as OR clause
                */
                else
                {
                    if(searching_word.length>0)
                    {
                        query+=" and (";
                       query+="  (a."+searching_field+" like '%"+searching_word[0]+"%') ";
                        for(int count=1;count<searching_word.length;count++)
                        {
                           query+=" or (a."+searching_field+" like '%"+searching_word[count]+"%') ";

                        }
                       query+=" ) ";

                    }
                }
            }
             String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    query+=" and a.publishing_year>'"+yr1+"' ";
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                      query+=" and a.publishing_year<'"+yr2+"' ";
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                     query+=" and a.publishing_year<='"+year1+"' ";
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                     query+=" and a.publishing_year>'"+year1+"' ";
                }

            }



             //Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class, "aliasOfTableA");

               //     if(!library_id.equalsIgnoreCase("all"))
                 //       criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
                 //   if(!sub_lib.equalsIgnoreCase("all"))
                   //     criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                    criteria.createCriteria("aliasOfTableA.documentDetailses1" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetailsLang.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.bibliographicDetailsLang.id.sublibraryId",library_id ));
//                   if(!doc_type.equalsIgnoreCase("combined"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.documentType",doc_type));

               //    criteria.add(Restrictions.eq("aliasOfTableA.entryLanguage",lang));
//                /*
//                * Searching Criteria for searching words are connected as AND clause
//                */
//                if(searching_field.equalsIgnoreCase("any field"))
//                {
//                    if(word_connector.equalsIgnoreCase("and"))
//                    {
//                        for(int count=0;count<searching_word.length;count++)
//                        {
//                            criteria.add(Restrictions.or(Restrictions.like("aliasOfTableA.title","%"+searching_word[count]+"%"),
//                            Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry","%"+searching_word[count]+"%"),Restrictions.like("aliasOfTableA.publisherName","%"+searching_word[count]+"%"))));
//                        }
//                    }
//                    /*
//                    * Searching Criteria for searching words are connected as OR clause
//                    */
//                    else
//                    {
//                          if(searching_word.length>0&& !searching_word[0].isEmpty())
//                          {
//                                criterion=Restrictions.or(Restrictions.like("aliasOfTableA.title","%"+searching_word[0]+"%"),
//                                Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry","%"+searching_word[0]+"%"),Restrictions.like("aliasOfTableA.publisherName","%"+searching_word[0]+"%")));
//                                for(int count=1;count<searching_word.length;count++)
//                                {
//                                    criterion=Restrictions.or(criterion,Restrictions.or(Restrictions.like("aliasOfTableA.title","%"+searching_word[count]+"%"),
//                                    Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry","%"+searching_word[count]+"%"),Restrictions.like("aliasOfTableA.publisherName","%"+searching_word[count]+"%"))));
//                                }
//                                criteria.add(criterion);
//                          }
//                    }
//                }
//                /*
//                * Searching criteria if searching is based of any specific field
//                */
//                else
//                {
//
//                 if(word_connector.equalsIgnoreCase("and"))
//                {
//                    for(int count=0;count<searching_word.length;count++)
//                    {
//                        criteria.add(Restrictions.like("aliasOfTableA."+searching_field,"%"+searching_word[count]+"%"));
//                    }
//                }
//                /*
//                * Searching Criteria for searching words are connected as OR clause
//                */
//                else
//                {
//                    if(searching_word.length>0)
//                    {
//                        criterion=Restrictions.like("aliasOfTableA."+searching_field,"%"+searching_word[0]+"%");
//                        for(int count=1;count<searching_word.length;count++)
//                        {
//                            criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA."+searching_field,"%"+searching_word[count]+"%"));
//                        }
//                        criteria.add(criterion);
//                    }
//                }
//            }
//               //    System.out.println(cmbyr+year1+searching_word[0]);
//             String yr1 =null;
//            if(cmbyr.equalsIgnoreCase("between"))
//            {
//                if(year1!=null && year1.isEmpty()==false)
//                {
//                    yr1 = String.valueOf(year1);
//                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
//                }
//                String yr2=null;
//                if(year2!=null && year2.isEmpty()==false)
//                {
//                    yr2 = String.valueOf(year2);
//                    criteria.add(Restrictions.lt("aliasOfTableA.publishingYear",yr2));
//                }
//            }
//            if(cmbyr.equalsIgnoreCase("upto"))
//            {
//                if(year1!=null && year1.isEmpty()==false)
//                {
//                    yr1 = String.valueOf(year1);
//                    criteria.add(Restrictions.le("aliasOfTableA.publishingYear",year1));
//                }
//
//            }
//            if(cmbyr.equalsIgnoreCase("after"))
//            {
//               if(year1!=null && year1.isEmpty()==false)
//                {
//                    yr1 = String.valueOf(year1);
//                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
//                }
//
//            }


               // criteria.addOrder(Order.asc("aliasOfTableA."+sortby));

             query+=" order by a."+sortby;
             Query simple=hsession.createSQLQuery(query)
                                .addEntity(BibliographicDetailsLang.class)
                                .addEntity(DocumentDetails.class)
                            .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));


             setSearchSize(simple.list());

                if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=(List<SearchPOJO>)simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=(List<SearchPOJO>)o.getList();
                }
             hsession.getTransaction().commit();

        }
        catch(Exception e)
        {

           log4j.error(e);

        }
        finally
        {
            hsession.close();
        }

        return obj;
      }
 // BROWSE SEARCH IN OPAC IN ENGLISH LANG
      public List<BibliographicDetails> browseSearch(String library_id,String sub_lib,String searching_word,String doc_type,String sortby,String searching_field,int pageNumber)
    {
       List<BibliographicDetails> obj=null;
       //Criteria criteria=null,cri=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();

//
//        criteria = hsession.createCriteria(BibliographicDetails.class, "aliasOfTableA");
//        criteria.createAlias("aliasOfTableA.documentDetailses","aliasOfTableB",CriteriaSpecification.LEFT_JOIN);
//
//            if(!doc_type.equalsIgnoreCase("combined"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.documentType",doc_type));
//
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));
//
//
//         /*
//          * Searching Criteria for searching words are connected as AND clause
//          */
//         if(searching_field.equalsIgnoreCase("any field")==true)
//         {
//
//         criteria.add(Restrictions.or(Restrictions.like("aliasOfTableA.title",searching_word+"%"),
//                 Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry",searching_word+"%"),Restrictions.like("aliasOfTableA.publisherName",searching_word+"%"))));
//         }
//         /*
//          * Searching criteria if searching is based of any specific field
//          */
//
//         else
//         {
//         criteria.add(Restrictions.like("aliasOfTableA."+searching_field,searching_word+"%"));
//         }
//         criteria.addOrder(Order.asc("aliasOfTableA."+sortby));
//
//        criteria.setProjection(Projections.distinct(
//            Projections.projectionList()
//            //.add(Projections.id())
//            .add(Projections.property("aliasOfTableA.id.biblioId"))
//            ));
//        List list = criteria.list();
//
//  // unfortunately we have to copy the ids out of the
//  // resulting Object[] List
//  List idlist = new ArrayList<Integer>();
//
//  for (Iterator iditer = list.iterator(); iditer.hasNext();) {
//    //Object[] record = (Object[]) iditer.next();
//      Integer record = (Integer)iditer.next();
//    idlist.add((Integer)record);
//  }
//
//  // another Hibernate stupidity: empty Lists cause
//  // Expression.in to throw an error
//  if (idlist.size() > 0) {
//    cri = hsession.createCriteria(BibliographicDetails.class);
//    cri.add(Restrictions.in("id.biblioId", idlist));
//  }
//
//
//        //Total Number of Record Found View in OPAC
//          setSearchSize(cri.list());
//   if(pageNumber==0)
//                {
//                    cri = cri.setFirstResult(0);
//                    cri.setMaxResults(100);
//
//                    obj=cri.list();
//
//                }
//                else
//                {
//                    CriteriaPagingAction o=new CriteriaPagingAction(cri,pageNumber,100);
//                    obj=o.getList();
//                }
//String //query="select distinct a. from bibliographic_details a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";

//               if(!library_id.equalsIgnoreCase("all"))
//                  query+=" and  a.library_id='"+library_id+"' ";
//
//             if(!sub_lib.equalsIgnoreCase("all"))
//                   query+=" and a.sublibrary_id='"+sub_lib+"' ";
//
//             if(!doc_type.equalsIgnoreCase("combined"))
//                   query+=" and a.document_type='"+doc_type+"' ";
//
//

           /*
          * Searching Criteria for searching words are connected as AND clause
          */
//         if(searching_field.equalsIgnoreCase("any field"))
//         {
//
//            query+=" and (a.title like '"+searching_word+"%' or a.main_entry like '"+searching_word+"%' or a.publisher_name like '"+searching_word+"%') ";
//         }
         /*
          * Searching criteria if searching is based of any specific field
          */

//         else
//         {
//               query+=" a."+searching_field+" like '"+searching_word+"%' ";
//         }
       // query+=" order by a."+sortby;


      //   Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class, "aliasOfTableA");

//                    if(!library_id.equalsIgnoreCase("all"))
  //                      criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
    //                if(!sub_lib.equalsIgnoreCase("all"))
      //                  criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                    criteria.createCriteria("aliasOfTableA.documentDetailses1" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetailsLang.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.bibliographicDetailsLang.id.sublibraryId",library_id ));

         //if(!doc_type.equalsIgnoreCase("combined"))
         //criteria.add(Restrictions.eq("aliasOfTableA.documentType",doc_type));
        //criteria.add(Restrictions.eq("aliasOfTableA.entryLanguage",language));
         /*
          * Searching Criteria for searching words are connected as AND clause
          */
//         if(searching_field.equalsIgnoreCase("any field"))
//         {
//
//         criteria.add(Restrictions.or(Restrictions.like("aliasOfTableA.title",searching_word+"%"),
//                 Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry",searching_word+"%"),Restrictions.like("aliasOfTableA.publisherName",searching_word+"%"))));
//         }
//         /*
//          * Searching criteria if searching is based of any specific field
//          */
//
//         else
//         {
//         criteria.add(Restrictions.like("aliasOfTableA."+searching_field,searching_word+"%"));
//         }
//         criteria.addOrder(Order.asc("aliasOfTableA."+sortby));


        //       //Total Number of Record Found View in OPAC

         // Query simple=hsession.createSQLQuery(query)
                    //            .addEntity(BibliographicDetailsLang.class)
                     //           .addEntity(DocumentDetails.class)
                     //       .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));

        //    setSearchSize(simple.list());
//              if(pageNumber==0)
//                {
//                    simple = simple.setFirstResult(0);
//                    simple.setMaxResults(100);
//                    obj=simple.list();
//                }
//                else
//                {
//                    PagingAction o=new PagingAction(simple,pageNumber,100);
//                    obj=o.getList();
//                }
//

      String query="select distinct t from BibliographicDetails as t left join t.documentDetailses as l";

      /*
          * Searching Criteria for searching words are connected as AND clause
          */
         if(searching_field.equalsIgnoreCase("any field") && searching_word.isEmpty()==false)
         {

            query+=" where  l.title like '%"+searching_word+"%' or l.mainEntry like '%"+searching_word+"%' or l.publisherName like '%"+searching_word+"%' ";
                if(!library_id.equalsIgnoreCase("all"))
               {  query+=" and l.id.libraryId='"+library_id+"'";}

            if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and l.id.sublibraryId='"+sub_lib+"'";

             if(!doc_type.equalsIgnoreCase("combined"))
                   query+=" and l.documentType='"+doc_type+"'";


         }
         /*
          * Searching criteria if searching is based of any specific field
          */

         else if(searching_field.isEmpty()==false && searching_word.isEmpty()==false)
         {
               query+=" where l."+searching_field+" like '%"+searching_word+"%'";
                      if(!library_id.equalsIgnoreCase("all"))
               {  query+=" and l.id.libraryId='"+library_id+"'";}

               if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and l.id.sublibraryId='"+sub_lib+"'";

             if(!doc_type.equalsIgnoreCase("combined"))
                   query+=" and l.documentType='"+doc_type+"'";

         }
         else{
                             if(!library_id.equalsIgnoreCase("all"))
               {  query+=" where l.id.libraryId='"+library_id+"'";}

               if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and l.id.sublibraryId='"+sub_lib+"'";

             if(!library_id.equalsIgnoreCase("all") && !doc_type.equalsIgnoreCase("combined"))
                   query+=" and l.documentType='"+doc_type+"'";
             else if(!doc_type.equalsIgnoreCase("combined"))
                    query+=" where l.documentType='"+doc_type+"'";
         }









            query+="  order by l."+sortby;



           Query simple=hsession.createQuery(query);

//                if(!library_id.equalsIgnoreCase("all"))
//               {
//                    simple.setString("library_id", library_id);
//                }
//             if(!sub_lib.equalsIgnoreCase("all"))
//               {
//                    simple.setString("sub_lib", sub_lib);
//                }
//             if(!doc_type.equalsIgnoreCase("combined"))
//               {
//                    simple.setString("doc_type", doc_type);
//                }
                 //      simple.setString("searching_word", searching_word);
            //System.out.println(simple.list().size());

//                              String query="select * from bibliographic_details a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";
//
//               if(!library_id.equalsIgnoreCase("all"))
//                  query+=" and  a.library_id='"+library_id+"' ";
//
//             if(!sub_lib.equalsIgnoreCase("all"))
//                   query+=" and a.sublibrary_id='"+sub_lib+"' ";
//
//             if(!doc_type.equalsIgnoreCase("combined"))
//                   query+=" and a.document_type='"+doc_type+"' ";
//
//         //  query+=" and a.entry_language='"+language+"' ";
//
//           /*
//          * Searching Criteria for searching words are connected as AND clause
//          */
//         if(searching_field.equalsIgnoreCase("any field"))
//         {
//
//            query+=" and (a.title like '"+searching_word+"%' or a.main_entry like '"+searching_word+"%' or a.publisher_name like '"+searching_word+"%') ";
//         }
//         /*
//          * Searching criteria if searching is based of any specific field
//          */
//
//         else
//         {
//               query+=" a."+searching_field+" like '"+searching_word+"%' ";
//         }
       // query+=" order by a."+sortby;
                //Query simple=hsession.createQuery(query);
                                //.addEntity(BibliographicDetailsLang.class)
                                //.addEntity(DocumentDetails.class)
                            //.setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));

            setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=(List<BibliographicDetails>)simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=(List<BibliographicDetails>)o.getList();
                }








         hsession.getTransaction().commit();
//        BibliographicDetails biblio=null;
//        DetachedCriteria criteria=DetachedCriteria.forClass(BibliographicDetails.class);
//        DetachedCriteria documentdetailscriteria=criteria.createCriteria("documentDetailses");
//      //  documentdetailscriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        List<BibliographicDetails> biblist=documentdetailscriteria.getExecutableCriteria(hsession).list();
//
//        System.out.println(biblist.size()+"..............." );
//

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
// BROWSE SEARCH IN OPAC IN MLI LANG
    public List browseLangSearch(String library_id,String sub_lib,String searching_word,String doc_type,String sortby,String searching_field,String language,int pageNumber)
    {
       List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();

                     String query="select  * from bibliographic_details_lang a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";

               if(!library_id.equalsIgnoreCase("all"))
                  query+=" and  a.library_id='"+library_id+"' ";

             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.sublibrary_id='"+sub_lib+"' ";

             if(!doc_type.equalsIgnoreCase("combined"))
                   query+=" and a.document_type='"+doc_type+"' ";

           query+=" and a.entry_language='"+language+"' ";

           /*
          * Searching Criteria for searching words are connected as AND clause
          */
         if(searching_field.equalsIgnoreCase("any field"))
         {

            query+=" and (a.title like '%"+searching_word+"%' or a.main_entry like '%"+searching_word+"%' or a.publisher_name like '%"+searching_word+"%') ";
         }
         /*
          * Searching criteria if searching is based of any specific field
          */

         else
         {
               query+=" a."+searching_field+" like '%"+searching_word+"%' ";
         }
        query+=" order by a."+sortby;
          Query simple=hsession.createSQLQuery(query)
                                .addEntity(BibliographicDetailsLang.class)
                                .addEntity(DocumentDetails.class)
                            .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));



      //   Criteria criteria = hsession.createCriteria(BibliographicDetailsLang.class, "aliasOfTableA");

//                    if(!library_id.equalsIgnoreCase("all"))
  //                      criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
    //                if(!sub_lib.equalsIgnoreCase("all"))
      //                  criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                    criteria.createCriteria("aliasOfTableA.documentDetailses1" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetailsLang.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableA.bibliographicDetailsLang.id.sublibraryId",library_id ));

         //if(!doc_type.equalsIgnoreCase("combined"))
         //criteria.add(Restrictions.eq("aliasOfTableA.documentType",doc_type));
        //criteria.add(Restrictions.eq("aliasOfTableA.entryLanguage",language));
         /*
          * Searching Criteria for searching words are connected as AND clause
          */
//         if(searching_field.equalsIgnoreCase("any field"))
//         {
//
//         criteria.add(Restrictions.or(Restrictions.like("aliasOfTableA.title",searching_word+"%"),
//                 Restrictions.or(Restrictions.like("aliasOfTableA.mainEntry",searching_word+"%"),Restrictions.like("aliasOfTableA.publisherName",searching_word+"%"))));
//         }
//         /*
//          * Searching criteria if searching is based of any specific field
//          */
//
//         else
//         {
//         criteria.add(Restrictions.like("aliasOfTableA."+searching_field,searching_word+"%"));
//         }
//         criteria.addOrder(Order.asc("aliasOfTableA."+sortby));


        //       //Total Number of Record Found View in OPAC

//          Query simple=hsession.createSQLQuery(query)
//                                .addEntity(BibliographicDetailsLang.class)
//                                .addEntity(DocumentDetails.class)
//                            .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));

            setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=o.getList();
                }
            hsession.getTransaction().commit();
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
         String sortby,String year1,String year2,int pageNumber,String cmbyr)
    {
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try
        {
            hsession.beginTransaction();
       /*   Criteria criteria = hsession.createCriteria(BibliographicDetails.class, "aliasOfTableA");
        criteria.createAlias("aliasOfTableA.documentDetailses","aliasOfTableB",CriteriaSpecification.LEFT_JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

                    if(!library_id.equalsIgnoreCase("all"))
                        criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
                    if(!sub_lib.equalsIgnoreCase("all"))
                        criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                    criteria.createCriteria("aliasOfTableA.documentDetailses" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.sublibraryId",sub_lib ));

           if(!doc_type.equalsIgnoreCase("combined"))
                        criteria.add(Restrictions.eq("aliasOfTableA.documentType",doc_type));
*/
              String query="select distinct a from BibliographicDetails as a left join a.documentDetailses as l";



        if(!doc_type.equalsIgnoreCase("combined") && !library_id.equalsIgnoreCase("all"))
        {
            query+=" where  a.docType='"+doc_type+"' and a.id.libraryId='"+library_id+"' ";

        }
        else if(doc_type.equalsIgnoreCase("combined")==true &&  !library_id.equalsIgnoreCase("all"))
            query+=" where   a.id.libraryId='"+library_id+"' ";


             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.id.sublibraryId='"+sub_lib+"' ";


         /*
          * Criteria for Author field
          */
         if(author!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
             if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" where  ";
             else
                 query+=" and  ";


         for(int i=0;i<author.length;i++)
         {
            query+="  a.mainEntry like '%"+author[i]+"%' and ";
          //criteria.add(Restrictions.like("aliasOfTableA.mainEntry","%"+ author[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
             if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" where  ";
             else
                 query+=" or  ";
       //  criterion=Restrictions.like("aliasOfTableA.mainEntry","%"+author[0]+"%");
             query+="   ";
             query+=" a.mainEntry like '%"+author[0]+"%' or ";
         for(int count=1;count<author.length;count++)
            {
             query+="  a.mainEntry like '%"+author[count]+"%' or ";
             //criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.mainEntry","%"+author[count]+"%"));
            }

         //criteria.add(criterion);
         }
         else
         {
             if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" where  ";
             else
                 query+=" and  ";

              query+="   a.mainEntry like '%"+author[0]+"%' ";
            //criteria.add(Restrictions.like("aliasOfTableA.mainEntry", "%"+author[0]+"%"));
         }
         }
              if(query.lastIndexOf("and")>0)
              query=query.substring(0,query.lastIndexOf("and")-1);
              else if(query.lastIndexOf("or")>0)
                  query=query.substring(0,query.lastIndexOf("or")-1);



         /*
          * Criteria for Title field
          */
         if(title!=null)
         {

         if(author_connector.equalsIgnoreCase("and"))
         {
               if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all") && author==null)
            query+=" where  ";
             else if (doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all") && author!=null)
                 query+=" and  ";

         for(int i=0;i<title.length;i++)
         {
               query+="   a.title like '%"+title[i]+"%' and ";
          //criteria.add(Restrictions.like("aliasOfTableA.title", "%"+title[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {

               if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null)
            query+=" where  ";
             else
                 query+=" or  ";


            query+="  a.title like '%"+title[0]+"%' or ";
            // criterion=Restrictions.like("aliasOfTableA.title","%"+title[0]+"%");
         for(int count=1;count<title.length;count++)
            {
               query+="  a.title like '%"+title[count]+"%' or ";
            //criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.title","%"+title[count]+"%"));
            }

         //criteria.add(criterion);
         }
         else
         {
                    if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null)
            query+=" where  ";
             else
                 query+=" and  ";
          query+="  a.title like '%"+title[0]+"%' ";
             //criteria.add(Restrictions.like("aliasOfTableA.title","%"+ title[0]+"%"));
         }
         }
                if(query.lastIndexOf("and")>0)
              query=query.substring(0,query.lastIndexOf("and")-1);
              else if(query.lastIndexOf("or")>0)
                  query=query.substring(0,query.lastIndexOf("or")-1);


         /*
          * Criteria for Subject field
          */
         if(subject!=null)
         {



         if(title_connector.equalsIgnoreCase("and"))
         {
                   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null && title==null)
            query+=" where  ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null )
                 query+=" "+title_connector+" ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && title==null )
                    query+=" "+author_connector+" ";
             else
                    query+=" and ";

         for(int i=0;i<subject.length;i++)
         {
          query+="  a.subject like '%"+subject[i]+"%'  ";
            if(author==null )
                 query+=" "+title_connector+" ";
             else if(title==null)
                    query+=" "+author_connector+" ";
             else
                    query+=" "+title_connector+" ";


          //criteria.add(Restrictions.eq("aliasOfTableA.subject","%"+ title[i]+"%"));
          }
         }
         else if(title_connector.equalsIgnoreCase("or"))
         {
          System.out.println("YEs");

            if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null && title==null)
            query+=" where  ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null )
                 query+=" "+title_connector+" ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && title==null )
                    query+=" "+author_connector+" ";
             else
                    query+=" or ";


             query+=" a.subject like '%"+subject[0]+"%'  ";

             if(author==null )
                 query+=" "+title_connector+" ";
             else if(title==null)
                    query+=" "+author_connector+" ";
             else
                    query+=" "+title_connector+" ";



            //criterion=Restrictions.like("aliasOfTableA.subject","%"+subject[0]+"%");
           for(int count=0;count<subject.length;count++)
           {
               query+="   a.subject like '%"+subject[count]+"%'  ";

             if(author==null )
                 query+=" "+title_connector+" ";
             else if(title==null)
                    query+=" "+author_connector+" ";
             else
                    query+=" "+title_connector+" ";

         //criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.subject","%"+subject[count]+"%"));
            }

            //criteria.add(criterion);
         }
         else
         {
              if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null && title==null)
            query+=" where  ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null )
                 query+=" "+title_connector+" ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && title==null )
                    query+=" "+author_connector+" ";
             else
                    query+=" and ";

               query+="    a.subject like '%"+subject[0]+"%' ";
         //criteria.add(Restrictions.like("aliasOfTableA.subject","%"+title[0]+"%"));
         }
         }

     //System.out.println("SqL======"+query);
              query=query.trim();
              String last=query.substring(query.length()-3);
              last=last.trim();
                   if(last.equalsIgnoreCase("and"))
                    query=query.substring(0,query.length()-4);
                   else if(last.equalsIgnoreCase("or"))
                          query=query.substring(0,query.length()-3);


         /*
          * Criteria for Other field

         if(other_field!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<other_field.length;i++)
         {
           query+=" and a.subject like '%"+title[i]+"%' ";
          //criteria.add(Restrictions.eq("aliasOfTableA.subject", title[i]));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
             System.out.println(other_field.length+""+other_field[0]);
             for(int i=0;i<other_field.length;i++)
            {
                 System.out.println(title[i]);
                  query+=" and a.subject like '%"+title[i]+"%' ";
            // criteria.add(Restrictions.eq("aliasOfTableA.subject", title[i]));
             }
         }
         else
         {
               query+=" and a.subject like '"+title[0]+"' ";
            //criteria.add(Restrictions.eq("aliasOfTableA.subject", title[0]));
         }
         }*/



    log4j.error("Author Pass"+cmbyr+year1+year2);
         String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    query+=" and a.publishingYear>'"+yr1+"' ";
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                      query+=" and a.publishingYear<'"+yr2+"' ";
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                     query+=" and a.publishingYear<='"+year1+"' ";
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                     query+=" and a.publishingYear>'"+year1+"' ";
                }

            }



         /*
          * Criteria for Author field
          */
/*         if(author!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<author.length;i++)
         {
          criteria.add(Restrictions.like("aliasOfTableA.mainEntry","%"+ author[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
         criterion=Restrictions.like("aliasOfTableA.mainEntry","%"+author[0]+"%");
         for(int count=1;count<author.length;count++)
            {
             criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.mainEntry","%"+author[count]+"%"));
            }
         criteria.add(criterion);
         }
         else
         {
            criteria.add(Restrictions.like("aliasOfTableA.mainEntry", "%"+author[0]+"%"));
         }
         }
         /*
          * Criteria for Title field
          */
   /*      if(title!=null)
         {
         if(title_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<title.length;i++)
         {
          criteria.add(Restrictions.like("aliasOfTableA.title", "%"+title[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
         criterion=Restrictions.like("aliasOfTableA.title","%"+title[0]+"%");
         for(int count=1;count<title.length;count++)
            {
            criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.title","%"+title[count]+"%"));
            }
         criteria.add(criterion);
         }
         else
         {
            criteria.add(Restrictions.like("aliasOfTableA.title","%"+ title[0]+"%"));
         }
         }
         /*
          * Criteria for Subject field
          */
  /*       if(subject!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<subject.length;i++)
         {
          criteria.add(Restrictions.eq("aliasOfTableA.subject","%"+ title[i]+"%"));
          }
         }
         else if(subject_connector.equalsIgnoreCase("or"))
         {
            criterion=Restrictions.like("aliasOfTableA.subject","%"+subject[0]+"%");
           for(int count=0;count<subject.length;count++)
           {
         criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.subject","%"+subject[count]+"%"));
            }
            criteria.add(criterion);
         }
         else
         {
         criteria.add(Restrictions.like("aliasOfTableA.subject","%"+title[0]+"%"));
         }
         }
         /*
          * Criteria for Other field
          */
    /*     if(other_field!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
         for(int i=0;i<other_field.length;i++)
         {
          criteria.add(Restrictions.eq("aliasOfTableA.subject", title[i]));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
             for(int i=0;i<other_field.length;i++)
            {
             criteria.add(Restrictions.eq("aliasOfTableA.subject", title[i]));
             }
         }
         else
         {
            criteria.add(Restrictions.eq("aliasOfTableA.subject", title[0]));
         }
         }

           String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                    criteria.add(Restrictions.lt("aliasOfTableA.publishingYear",yr2));
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    criteria.add(Restrictions.le("aliasOfTableA.publishingYear",year1));
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
                }

            }

         if(sortby!=null)
            criteria.addOrder(Order.asc("aliasOfTableA."+sortby));
     *
     *
     */
              query+="  order by a."+sortby;



           Query simple=hsession.createQuery(query);
        //Total Number of Record Found View in OPAC
                 /*   obj=criteria.list();
                    setSearchSize(obj);
                    obj=null;

              if(pageNumber==0)
                {
                    criteria = criteria.setFirstResult(0);
                    criteria.setMaxResults(100);
                    obj=criteria.list();
                }
                else
                {
                    CriteriaPagingAction o=new CriteriaPagingAction(criteria,pageNumber,100);
                    obj=o.getList();
                }*/
             setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=(List<BibliographicDetails>)simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=(List<BibliographicDetails>)o.getList();
                }

                    hsession.getTransaction().commit();

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
         String sortby,String year1,String year2,String language,int pageNumber,String cmbyr)
    {
        List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
           String query="select * from bibliographic_details_lang a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";

               if(!library_id.equalsIgnoreCase("all"))
                  query+=" and  a.library_id='"+library_id+"' ";

             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.sublibrary_id='"+sub_lib+"' ";

             if(!doc_type.equalsIgnoreCase("combined"))
                   query+=" and a.document_type='"+doc_type+"' ";

           query+=" and a.entry_language='"+language+"' ";

  System.out.println(query+"IN MLI");
         /*
          * Criteria for Author field
          */
         if(author!=null)
         {
         if(author_connector.equalsIgnoreCase("and"))
         {
           
                 query+=" and  ";


         for(int i=0;i<author.length;i++)
         {
            query+="  a.main_entry like '"+author[i]+"' and ";
          //criteria.add(Restrictions.like("aliasOfTableA.mainEntry","%"+ author[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {
            
                 query+=" or  ";
       //  criterion=Restrictions.like("aliasOfTableA.mainEntry","%"+author[0]+"%");
             query+="   ";
             query+=" a.main_entry like '"+author[0]+"' or ";
         for(int count=1;count<author.length;count++)
            {
             query+="  a.main_entry like '"+author[count]+"' or ";
             //criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.mainEntry","%"+author[count]+"%"));
            }

         //criteria.add(criterion);
         }
         else
         {
            
                 query+=" and  ";

              query+="   a.main_entry like '"+author[0]+"' ";
            //criteria.add(Restrictions.like("aliasOfTableA.mainEntry", "%"+author[0]+"%"));
         }
         }
            


         /*
          * Criteria for Title field
          */
         if(title!=null)
         {

         if(author_connector.equalsIgnoreCase("and"))
         {
            
                 query+=" and  ";

         for(int i=0;i<title.length;i++)
         {
               query+="   a.title like '"+title[i]+"' and ";
          //criteria.add(Restrictions.like("aliasOfTableA.title", "%"+title[i]+"%"));
          }
         }
         else if(author_connector.equalsIgnoreCase("or"))
         {

              
                 query+=" or  ";


            query+="  a.title like '"+title[0]+"' or ";
            // criterion=Restrictions.like("aliasOfTableA.title","%"+title[0]+"%");
         for(int count=1;count<title.length;count++)
            {
               query+="  a.title like '"+title[count]+"' or ";
            //criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.title","%"+title[count]+"%"));
            }

         //criteria.add(criterion);
         }
         else
         {
                  
                 query+=" and  ";
          query+="  a.title like '"+title[0]+"' ";
             //criteria.add(Restrictions.like("aliasOfTableA.title","%"+ title[0]+"%"));
         }
         }
             

         /*
          * Criteria for Subject field
          */
         if(subject!=null)
         {



         if(title_connector.equalsIgnoreCase("and"))
         {
                    if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null )
                 query+=" "+title_connector+" ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && title==null )
                    query+=" "+author_connector+" ";
             else
                    query+=" and ";

         for(int i=0;i<subject.length;i++)
         {
          query+="  a.subject like '"+subject[i]+"'  ";
            if(author==null )
                 query+=" "+title_connector+" ";
             else if(title==null)
                    query+=" "+author_connector+" ";
             else
                    query+=" "+title_connector+" ";


          //criteria.add(Restrictions.eq("aliasOfTableA.subject","%"+ title[i]+"%"));
          }
         }
         else if(title_connector.equalsIgnoreCase("or"))
         {
          System.out.println("YEs");

               if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null )
                 query+=" "+title_connector+" ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && title==null )
                    query+=" "+author_connector+" ";
             else
                    query+=" or ";


             query+=" a.subject like '"+subject[0]+"'  ";

             if(author==null )
                 query+=" "+title_connector+" ";
             else if(title==null)
                    query+=" "+author_connector+" ";
             else
                    query+=" "+title_connector+" ";



            //criterion=Restrictions.like("aliasOfTableA.subject","%"+subject[0]+"%");
           for(int count=0;count<subject.length;count++)
           {
               query+="   a.subject like '"+subject[count]+"'  ";

             if(author==null )
                 query+=" "+title_connector+" ";
             else if(title==null)
                    query+=" "+author_connector+" ";
             else
                    query+=" "+title_connector+" ";

         //criterion=Restrictions.or(criterion,Restrictions.like("aliasOfTableA.subject","%"+subject[count]+"%"));
            }

            //criteria.add(criterion);
         }
         else
         {
               if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && author==null )
                 query+=" "+title_connector+" ";
             else   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all")  && title==null )
                    query+=" "+author_connector+" ";
             else
                    query+=" and ";

               query+="    a.subject like '"+subject[0]+"' ";
         //criteria.add(Restrictions.like("aliasOfTableA.subject","%"+title[0]+"%"));
         }
         }

     System.out.println("SqL======"+query);
              query=query.trim();
              String last=query.substring(query.length()-3);
              last=last.trim();
                   if(last.equalsIgnoreCase("and"))
                    query=query.substring(0,query.length()-4);
                   else if(last.equalsIgnoreCase("or"))
                          query=query.substring(0,query.length()-3);
System.out.println("SqL======"+query);


    log4j.error("Author Pass"+cmbyr+year1+year2);
         String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    query+=" and a.publishing_year>'"+yr1+"' ";
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                      query+=" and a.publishing_year<'"+yr2+"' ";
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                     query+=" and a.publishing_year<='"+year1+"' ";
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                     query+=" and a.publishing_year>'"+year1+"' ";
                }

            }
              query+="  order by a."+sortby;


       //Total Number of Record Found View in OPAC
              Query simple=hsession.createSQLQuery(query)
                                .addEntity(BibliographicDetailsLang.class)
                                .addEntity(DocumentDetails.class)
                            .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));

            setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=o.getList();
                }
hsession.getTransaction().commit();

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
         String doc_type,String sortby,String year1,String year2,int pageNumber,String cmbyr)
    {
        List obj=null;
      log4j.error("lib_id="+library_id+" sublib="+sub_lib+" searchText1="+searchText1+" search1connector="+searchText1_connector);
      log4j.error("searchText2="+searchText2+" search2connector="+searchText2_connector+" searchText3="+searchText3+" search3connector="+searchText3_connector);
      log4j.error("query1connector="+query1_connector+" query2connector="+query2_connector+" query3connector="+query3_connector+" field1="+field1+" field2="+field2+" field3="+field3);
      log4j.error("doc_type="+doc_type+" sortby="+sortby+" year1="+year1+" year2="+year2);
      Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
            //Criteria criteria = hsession.createCriteria(BibliographicDetails.class, "aliasOfTableA");
            //criteria.createAlias("aliasOfTableA.documentDetailses", "aliasOfTableB", CriteriaSpecification.LEFT_JOIN);
            //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

              //      if(!library_id.equalsIgnoreCase("all"))
                //        criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
                 //   if(!sub_lib.equalsIgnoreCase("all"))
                  //      criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                    criteria.createCriteria("aliasOfTableA.documentDetailses" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.sublibraryId",sub_lib ));

    /*     if(!doc_type.equalsIgnoreCase("combined"))
            criteria.add(Restrictions.eq("aliasOfTableA.documentType",doc_type));
         /*
          * Criteria for first searchText

         if(searchText1!=null)
         {
         if(searchText1_connector.equalsIgnoreCase("and"))
         {
               if(field1.equals("author"))
                  criterion1=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("aliasOfTableA.subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("aliasOfTableA.title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[0]+"%");

         for(int i=1;i<searchText1.length;i++)
         {
              if(field1.equals("author"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[i]+"%"));
             else if(field1.equals("subject"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.subject","%"+searchText1[i]+"%"));
             else if(field1.equals("title"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.title","%"+searchText1[i]+"%"));
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[i]+"%"));

          }
         }
         else if(searchText1_connector.equalsIgnoreCase("or"))
         {
               if(field1.equals("author"))
                  criterion1=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("aliasOfTableA.subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("aliasOfTableA.title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[0]+"%");
    System.out.println("searchText1="+searchText1[0]);
         for(int count=1;count<searchText1.length;count++)
            {
               if(field1.equals("author"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[count]+"%"));
             else if(field1.equals("subject"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.subject","%"+searchText1[count]+"%"));
             else if(field1.equals("title"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.title","%"+searchText1[count]+"%"));
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[count]+"%"));
System.out.println("searchText1="+searchText1[count]);
            }
        // criteria.add(criterion);
         }
         else
         {
             if(field1.equals("author"))
                  criterion1=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                  criterion1=Restrictions.like("aliasOfTableA.subject", "%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                  criterion1=Restrictions.like("aliasOfTableA.title", "%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                  criterion1=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText1[0]+"%");

         }
         }
         /*
          * Criteria for second search Text field

          if(searchText2!=null)
         {
         if(searchText2_connector.equalsIgnoreCase("and"))
         {
               if(field2.equals("author"))
                  criterion2=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("aliasOfTableA.subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("aliasOfTableA.title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[0]+"%");

         for(int i=1;i<searchText2.length;i++)
         {
              if(field2.equals("author"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[i]+"%"));
             else if(field2.equals("subject"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.subject","%"+searchText2[i]+"%"));
             else if(field2.equals("title"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.title","%"+searchText2[i]+"%"));
             else if(field1.equals("isbn10"))
                  criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[i]+"%"));

          }
         }
         else if(searchText2_connector.equalsIgnoreCase("or"))
         {
               if(field2.equals("author"))
                  criterion2=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("aliasOfTableA.subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("aliasOfTableA.title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[0]+"%");

         for(int count=1;count<searchText2.length;count++)
            {
               if(field2.equals("author"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[count]+"%"));
             else if(field2.equals("subject"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.subject","%"+searchText2[count]+"%"));
             else if(field2.equals("title"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.title","%"+searchText2[count]+"%"));
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[count]+"%"));

            }
        // criteria.add(criterion);
         }
         else
         {
             if(field2.equals("author"))
                  criterion2=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                  criterion2=Restrictions.like("aliasOfTableA.subject", "%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                  criterion2=Restrictions.like("aliasOfTableA.title", "%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                  criterion2=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText2[0]+"%");

         }
         }
         /*
          * Criteria for third search Text field

          if(searchText3!=null)
         {
         if(searchText3_connector.equalsIgnoreCase("and"))
         {
               if(field3.equals("author"))
                  criterion3=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("aliasOfTableA.subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("aliasOfTableA.title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[0]+"%");

         for(int i=1;i<searchText3.length;i++)
         {
              if(field3.equals("author"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[i]+"%"));
             else if(field3.equals("subject"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.subject","%"+searchText3[i]+"%"));
             else if(field3.equals("title"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.title","%"+searchText3[i]+"%"));
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[i]+"%"));

          }
         }
         else if(searchText3_connector.equalsIgnoreCase("or"))
         {
               if(field3.equals("author"))
                  criterion3=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("aliasOfTableA.subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("aliasOfTableA.title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[0]+"%");

         for(int count=1;count<searchText3.length;count++)
            {
               if(field3.equals("author"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[count]+"%"));
             else if(field3.equals("subject"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.subject","%"+searchText3[count]+"%"));
             else if(field3.equals("title"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.title","%"+searchText3[count]+"%"));
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[count]+"%"));

            }
        // criteria.add(criterion);
         }
         else
         {
             if(field3.equals("author"))
                  criterion3=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                  criterion3=Restrictions.like("aliasOfTableA.subject", "%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                  criterion3=Restrictions.like("aliasOfTableA.title", "%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                  criterion3=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText3[0]+"%");

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

         String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                    criteria.add(Restrictions.lt("aliasOfTableA.publishingYear",yr2));
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    criteria.add(Restrictions.le("aliasOfTableA.publishingYear",year1));
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    criteria.add(Restrictions.gt("aliasOfTableA.publishingYear",yr1));
                }

            }

         if(sortby.equals("author"))
              criteria.addOrder(Order.asc("aliasOfTableA.mainEntry"));
         else if(sortby.equals("title"))
              criteria.addOrder(Order.asc("aliasOfTableA.title"));
         else if(sortby.equals("isbn10"))
              criteria.addOrder(Order.asc("aliasOfTableA.isbn10"));
         else if(sortby.equals("subject"))
              criteria.addOrder(Order.asc("aliasOfTableA.subject"));
*/
           String query="select distinct a from BibliographicDetails as a left join a.documentDetailses as l";



        if(!doc_type.equalsIgnoreCase("combined") && !library_id.equalsIgnoreCase("all"))
        {
            query+=" where  a.docType='"+doc_type+"' and a.id.libraryId='"+library_id+"' ";

        }
        else if(doc_type.equalsIgnoreCase("combined")==true &&  !library_id.equalsIgnoreCase("all"))
            query+=" where   a.id.libraryId='"+library_id+"' ";


             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.id.sublibraryId='"+sub_lib+"' ";


           String query1=" ",query2=" ",query3=" ",connector=" ";






         /*
          * Criteria for first searchText
          */
         if(searchText1!=null)
         {



         if(searchText1_connector.equalsIgnoreCase("and"))
         {
               if(field1.equals("author"))
             query1=" a.mainEntry like '%"+searchText1[0]+"%' ";
                   //criterion1=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                 query1=" a.subject like '%"+searchText1[0]+"%' ";
             //     criterion1=Restrictions.like("aliasOfTableA.subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                 query1=" a.title like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                 query1=" a.isbn10 like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[0]+"%");

         for(int i=1;i<searchText1.length;i++)
         {
              if(field1.equals("author"))
                   query1+=" and a.mainEntry like '%"+searchText1[i]+"%' ";
                 // criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[i]+"%"));
             else if(field1.equals("subject"))
                 query1+=" and a.subject like '%"+searchText1[i]+"%' ";
               //   criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.subject","%"+searchText1[i]+"%"));
             else if(field1.equals("title"))
                 query1+=" and a.title like '%"+searchText1[i]+"%' ";
                 // criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.title","%"+searchText1[i]+"%"));
             else if(field1.equals("isbn10"))
                 query1+=" and a.isbn10 like '%"+searchText1[i]+"%' ";
                  //criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[i]+"%"));

          }
         }
         else if(searchText1_connector.equalsIgnoreCase("or"))
         {

         System.out.println("Here");

               if(field1.equals("author"))
                   query1="  a.mainEntry like '%"+searchText1[0]+"%' ";
                 // criterion1=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                 query1="  a.subject like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                 query1="  a.title like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                 query1="  a.isbn10 like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[0]+"%");
    log4j.error("searchText1="+searchText1[0]);
         for(int count=1;count<searchText1.length;count++)
            {
               if(field1.equals("author"))
                   query1+=" or a.mainEntry like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[count]+"%"));
             else if(field1.equals("subject"))
                 query1+=" or a.subject like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.subject","%"+searchText1[count]+"%"));
             else if(field1.equals("title"))
                 query1+=" or a.title like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.title","%"+searchText1[count]+"%"));
             else if(field1.equals("isbn10"))
                 query1+=" or a.isbn10 like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[count]+"%"));
log4j.error("searchText1="+searchText1[count]);
            }
        // criteria.add(criterion);
         }
//         else
//         {
//              if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
//            query+=" where  ";
//             else
//                 query+=" and  ";
//
//
//             if(field1.equals("author"))
//                  query1=" or a.mainEntry like '%"+searchText1[0]+"%' ";
//                 //criterion1=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText1[0]+"%");
//             else if(field1.equals("subject"))
//                 query1=" or a.subject like '%"+searchText1[0]+"%' ";
//                  //criterion1=Restrictions.like("aliasOfTableA.subject", "%"+searchText1[0]+"%");
//             else if(field1.equals("title"))
//                 query1=" or a.title like '%"+searchText1[0]+"%' ";
//                  //criterion1=Restrictions.like("aliasOfTableA.title", "%"+searchText1[0]+"%");
//             else if(field1.equals("isbn10"))
//                 query1=" or a.isbn10 like '%"+searchText1[0]+"%' ";
//                  //criterion1=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText1[0]+"%");
//
//         }
         }
         /*
          * Criteria for second search Text field
          */
          if(searchText2!=null)
         {
         if(searchText2_connector.equalsIgnoreCase("and"))
         {
               if(field2.equals("author"))
                        query2="  a.mainEntry like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                 query2="  a.subject like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                 query2="  a.title like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                 query2="  a.isbn10 like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[0]+"%");

         for(int i=1;i<searchText2.length;i++)
         {
              if(field2.equals("author"))
                  query2+="  and a.mainEntry like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[i]+"%"));
             else if(field2.equals("subject"))
                 query2+="  and a.subject like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.subject","%"+searchText2[i]+"%"));
             else if(field2.equals("title"))
                 query2+="  and a.title like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.title","%"+searchText2[i]+"%"));
             else if(field1.equals("isbn10"))
                 query2+="  and a.isbn10 like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[i]+"%"));

          }
         }
         else if(searchText2_connector.equalsIgnoreCase("or"))
         {
               if(field2.equals("author"))
                   query2="  a.mainEntry like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                 query2="  a.subject like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                 query2="  a.title like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                 query2="  a.isbn10 like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[0]+"%");

         for(int count=1;count<searchText2.length;count++)
            {
               if(field2.equals("author"))
                   query2+=" or a.mainEntry like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[count]+"%"));
             else if(field2.equals("subject"))
                 query2+=" or a.subject like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.subject","%"+searchText2[count]+"%"));
             else if(field2.equals("title"))
                 query2+=" or a.title like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.title","%"+searchText2[count]+"%"));
             else if(field2.equals("isbn10"))
                 query2+=" or a.isbn10 like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[count]+"%"));

            }
         }
         else
         {
             if(field2.equals("author"))
                     query2=" a.mainEntry like '%"+searchText2[0]+"%' ";
//                  criterion2=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                 query2=" a.subject like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.subject", "%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                 query2=" a.title like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.title", "%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                 query2=" a.isbn10 like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText2[0]+"%");

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
                   query3=" a.mainEntry like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                 query3=" a.subject like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                 query3=" a.title like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                 query3=" a.isbn10 like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[0]+"%");

         for(int i=1;i<searchText3.length;i++)
         {
              if(field3.equals("author"))
                   query3+=" and a.mainEntry like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[i]+"%"));
             else if(field3.equals("subject"))
                 query3+=" and a.subject like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.subject","%"+searchText3[i]+"%"));
             else if(field3.equals("title"))
                 query3+=" and a.title like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.title","%"+searchText3[i]+"%"));
             else if(field3.equals("isbn10"))
                 query3+=" and a.isbn10 like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[i]+"%"));

          }
         }
         else if(searchText3_connector.equalsIgnoreCase("or"))
         {
               if(field3.equals("author"))
                     query3=" a.mainEntry like '%"+searchText3[0]+"%' ";
                //  criterion3=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                 query3=" a.subject like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                 query3=" a.title like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                 query3=" a.isbn10 like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[0]+"%");

         for(int count=1;count<searchText3.length;count++)
            {
               if(field3.equals("author"))
                   query3+=" or a.mainEntry like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[count]+"%"));
             else if(field3.equals("subject"))
                 query3+=" or a.subject like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.subject","%"+searchText3[count]+"%"));
             else if(field3.equals("title"))
                 query3+=" or a.title like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.title","%"+searchText3[count]+"%"));
             else if(field3.equals("isbn10"))
                 query3+=" or a.isbn10 like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[count]+"%"));

            }

         }
         else
         {
             if(field3.equals("author"))
                 query3="  a.mainEntry like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                 query3="  a.subject like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.subject", "%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                 query3="  a.title like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.title", "%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                 query3="  a.isbn10 like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText3[0]+"%");

         }
         }
         if(searchText1!=null&&searchText2!=null&&searchText3!=null)
         {
            if(query1_connector.equals("or"))
                connector+=query1+" or "+query2;
              //   criterion = Restrictions.or(criterion1, criterion2);
            else
                connector+=query1+" and "+query2;
                 //criterion = Restrictions.and(criterion1, criterion2);

            if(query2_connector.equals("or"))
                connector+=" or "+query3;
                //criterion = Restrictions.or(criterion, criterion3);
            else
                connector+=" and "+query3;
                //criterion = Restrictions.and(criterion, criterion3);

             if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
                query+=" where  ";
             else
                 query+=" and  ";
                query+=connector;


         }
         else if (searchText1!=null&&searchText2!=null)
         {
                if(query1_connector.equals("or"))
                    //criterion = Restrictions.or(criterion1, criterion2);
                    connector+=query1+ " or "+query2;
                else
                    connector+=query1+ " and "+query2;
                    //criterion = Restrictions.and(criterion1, criterion2);

            if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
                query+=" where  ";
             else
                 query+=" and  ";
                query+=connector;


         }
         else if(searchText1!=null&&searchText3!=null)
         {
             if(query1_connector.equals("or"))
                 connector+=query1+ " or "+query3;
                 //criterion = Restrictions.or(criterion1, criterion3);
             else
                 connector+=query1+ " and "+query3;
                 //criterion = Restrictions.and(criterion1, criterion3);

              if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
                query+=" where  ";
             else
                 query+=" and  ";
                query+=connector;


         }
         else if(searchText2!=null&&searchText3!=null)
         {
             if(query2_connector.equals("or"))
                 //criterion = Restrictions.or(criterion2, criterion3);
                 connector+=query2+ " or "+query3;
             else
                 //criterion = Restrictions.and(criterion2, criterion3);
                 connector+=query2+ " and "+query3;

             if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
                query+=" where  ";
             else
                 query+=" and  ";
                query+=connector;


         }
         else if(searchText1!=null)
         {
         if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" where  ";
             else
                 query+=" and  ";

             query+="  " +query1;



         }
             //criteria.add(criterion1);
         else if(searchText2!=null)
         {
              if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" where  ";
             else
                 query+=" and  ";

             query+="  " +query2;

         }
             //criteria.add(criterion2);
         else if(searchText3!=null)
         {
              if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" where  ";
             else
                 query+=" and  ";


             query+="  " +query3;}
             //criteria.add(criterion3);

         //if(criterion!=null)
           //  query+=" and " +connector;
            //criteria.add(criterion);

        System.out.println(cmbyr+year1+year2);
          String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    query+=" and a.publishingYear>"+yr1;
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                      query+=" and a.publishingYear<"+yr2;
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                     query+=" and a.publishingYear<="+year1;
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                     query+=" and a.publishingYear>"+year1;
                }

            }



         if(sortby.equals("author"))
             query+=" order by a.mainEntry ";
            //  criteria.addOrder(Order.asc("aliasOfTableA.mainEntry"));
         else if(sortby.equals("title"))
             query+=" order by a.title ";
              //criteria.addOrder(Order.asc("aliasOfTableA.title"));
         else if(sortby.equals("isbn10"))
             query+=" order by a.isbn10 ";
              //criteria.addOrder(Order.asc("aliasOfTableA.isbn10"));
         else if(sortby.equals("subject"))
             query+=" order by a.subject ";
              //criteria.addOrder(Order.asc("aliasOfTableA.subject"));

              Query simple=hsession.createQuery(query);

        //Total Number of Record Found View in OPAC
            setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=(List<BibliographicDetails>)simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=(List<BibliographicDetails>)o.getList();
                }
            hsession.getTransaction().commit();

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
         String doc_type,String sortby,String year1,String year2,String language,int pageNumber,String cmbyr)
    {
      List obj=null;
      log4j.error("lib_id="+library_id+" sublib="+sub_lib+" searchText1="+searchText1+" search1connector="+searchText1_connector);
      log4j.error("searchText2="+searchText2+" search2connector="+searchText2_connector+" searchText3="+searchText3+" search3connector="+searchText3_connector);
      log4j.error("query1connector="+query1_connector+" query2connector="+query2_connector+" query3connector="+query3_connector+" field1="+field1+" field2="+field2+" field3="+field3);
      log4j.error("doc_type="+doc_type+" sortby="+sortby+" year1="+year1+" year2="+year2);
      Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();

          String query1=" ",query2=" ",query3=" ",connector=" ";
         String query="select * from bibliographic_details_lang a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";
 


        if(!doc_type.equalsIgnoreCase("combined") && !library_id.equalsIgnoreCase("all"))
        {
            query+=" and  a.doc_type='"+doc_type+"' and a.library_id='"+library_id+"' ";

        }
        else if(doc_type.equalsIgnoreCase("combined")==true &&  !library_id.equalsIgnoreCase("all"))
            query+=" and   a.library_id='"+library_id+"' ";


             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.sublibrary_id='"+sub_lib+"' ";


           






         /*
          * Criteria for first searchText
          */
         if(searchText1!=null)
         {



         if(searchText1_connector.equalsIgnoreCase("and"))
         {
               if(field1.equals("author"))
             query1=" a.main_entry like '%"+searchText1[0]+"%' ";
                   //criterion1=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                 query1=" a.subject like '%"+searchText1[0]+"%' ";
             //     criterion1=Restrictions.like("aliasOfTableA.subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                 query1=" a.title like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                 query1=" a.isbn10 like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[0]+"%");

         for(int i=1;i<searchText1.length;i++)
         {
              if(field1.equals("author"))
                   query1+=" and a.main_entry like '%"+searchText1[i]+"%' ";
                 // criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[i]+"%"));
             else if(field1.equals("subject"))
                 query1+=" and a.subject like '%"+searchText1[i]+"%' ";
               //   criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.subject","%"+searchText1[i]+"%"));
             else if(field1.equals("title"))
                 query1+=" and a.title like '%"+searchText1[i]+"%' ";
                 // criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.title","%"+searchText1[i]+"%"));
             else if(field1.equals("isbn10"))
                 query1+=" and a.isbn10 like '%"+searchText1[i]+"%' ";
                  //criterion1=Restrictions.and(criterion1,Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[i]+"%"));

          }
         }
         else if(searchText1_connector.equalsIgnoreCase("or"))
         {

         System.out.println("Here");

               if(field1.equals("author"))
                   query1="  a.main_entry like '%"+searchText1[0]+"%' ";
                 // criterion1=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[0]+"%");
             else if(field1.equals("subject"))
                 query1="  a.subject like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.subject","%"+searchText1[0]+"%");
             else if(field1.equals("title"))
                 query1="  a.title like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.title","%"+searchText1[0]+"%");
             else if(field1.equals("isbn10"))
                 query1="  a.isbn10 like '%"+searchText1[0]+"%' ";
                  //criterion1=Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[0]+"%");
    log4j.error("searchText1="+searchText1[0]);
         for(int count=1;count<searchText1.length;count++)
            {
               if(field1.equals("author"))
                   query1+=" or a.main_entry like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText1[count]+"%"));
             else if(field1.equals("subject"))
                 query1+=" or a.subject like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.subject","%"+searchText1[count]+"%"));
             else if(field1.equals("title"))
                 query1+=" or a.title like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.title","%"+searchText1[count]+"%"));
             else if(field1.equals("isbn10"))
                 query1+=" or a.isbn10 like '%"+searchText1[count]+"%' ";
                  //criterion1=Restrictions.or(criterion1,Restrictions.like("aliasOfTableA.isbn10","%"+searchText1[count]+"%"));
log4j.error("searchText1="+searchText1[count]);
            }
        // criteria.add(criterion);
         }
//         else
//         {
//              if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
//            query+=" where  ";
//             else
//                 query+=" and  ";
//
//
//             if(field1.equals("author"))
//                  query1=" or a.mainEntry like '%"+searchText1[0]+"%' ";
//                 //criterion1=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText1[0]+"%");
//             else if(field1.equals("subject"))
//                 query1=" or a.subject like '%"+searchText1[0]+"%' ";
//                  //criterion1=Restrictions.like("aliasOfTableA.subject", "%"+searchText1[0]+"%");
//             else if(field1.equals("title"))
//                 query1=" or a.title like '%"+searchText1[0]+"%' ";
//                  //criterion1=Restrictions.like("aliasOfTableA.title", "%"+searchText1[0]+"%");
//             else if(field1.equals("isbn10"))
//                 query1=" or a.isbn10 like '%"+searchText1[0]+"%' ";
//                  //criterion1=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText1[0]+"%");
//
//         }
         }
         /*
          * Criteria for second search Text field
          */
          if(searchText2!=null)
         {
         if(searchText2_connector.equalsIgnoreCase("and"))
         {
               if(field2.equals("author"))
                        query2="  a.main_entry like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                 query2="  a.subject like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                 query2="  a.title like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                 query2="  a.isbn10 like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[0]+"%");

         for(int i=1;i<searchText2.length;i++)
         {
              if(field2.equals("author"))
                  query2+="  and a.main_entry like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[i]+"%"));
             else if(field2.equals("subject"))
                 query2+="  and a.subject like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.subject","%"+searchText2[i]+"%"));
             else if(field2.equals("title"))
                 query2+="  and a.title like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.title","%"+searchText2[i]+"%"));
             else if(field1.equals("isbn10"))
                 query2+="  and a.isbn10 like '%"+searchText2[i]+"%' ";
                  //criterion2=Restrictions.and(criterion2,Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[i]+"%"));

          }
         }
         else if(searchText2_connector.equalsIgnoreCase("or"))
         {
               if(field2.equals("author"))
                   query2="  a.main_entry like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                 query2="  a.subject like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.subject","%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                 query2="  a.title like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.title","%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                 query2="  a.isbn10 like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[0]+"%");

         for(int count=1;count<searchText2.length;count++)
            {
               if(field2.equals("author"))
                   query2+=" or a.main_entry like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText2[count]+"%"));
             else if(field2.equals("subject"))
                 query2+=" or a.subject like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.subject","%"+searchText2[count]+"%"));
             else if(field2.equals("title"))
                 query2+=" or a.title like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.title","%"+searchText2[count]+"%"));
             else if(field2.equals("isbn10"))
                 query2+=" or a.isbn10 like '%"+searchText2[count]+"%' ";
                  //criterion2=Restrictions.or(criterion2,Restrictions.like("aliasOfTableA.isbn10","%"+searchText2[count]+"%"));

            }
         }
         else
         {
             if(field2.equals("author"))
                     query2=" a.main_entry like '%"+searchText2[0]+"%' ";
//                  criterion2=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText2[0]+"%");
             else if(field2.equals("subject"))
                 query2=" a.subject like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.subject", "%"+searchText2[0]+"%");
             else if(field2.equals("title"))
                 query2=" a.title like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.title", "%"+searchText2[0]+"%");
             else if(field2.equals("isbn10"))
                 query2=" a.isbn10 like '%"+searchText2[0]+"%' ";
                  //criterion2=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText2[0]+"%");

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
                   query3=" a.main_entry like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                 query3=" a.subject like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                 query3=" a.title like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                 query3=" a.isbn10 like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[0]+"%");

         for(int i=1;i<searchText3.length;i++)
         {
              if(field3.equals("author"))
                   query3+=" and a.main_entry like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[i]+"%"));
             else if(field3.equals("subject"))
                 query3+=" and a.subject like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.subject","%"+searchText3[i]+"%"));
             else if(field3.equals("title"))
                 query3+=" and a.title like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.title","%"+searchText3[i]+"%"));
             else if(field3.equals("isbn10"))
                 query3+=" and a.isbn10 like '%"+searchText3[i]+"%' ";
                  //criterion3=Restrictions.and(criterion3,Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[i]+"%"));

          }
         }
         else if(searchText3_connector.equalsIgnoreCase("or"))
         {
               if(field3.equals("author"))
                     query3=" a.main_entry like '%"+searchText3[0]+"%' ";
                //  criterion3=Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                 query3=" a.subject like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.subject","%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                 query3=" a.title like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.title","%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                 query3=" a.isbn10 like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[0]+"%");

         for(int count=1;count<searchText3.length;count++)
            {
               if(field3.equals("author"))
                   query3+=" or a.main_entry like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.mainEntry","%"+searchText3[count]+"%"));
             else if(field3.equals("subject"))
                 query3+=" or a.subject like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.subject","%"+searchText3[count]+"%"));
             else if(field3.equals("title"))
                 query3+=" or a.title like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.title","%"+searchText3[count]+"%"));
             else if(field3.equals("isbn10"))
                 query3+=" or a.isbn10 like '%"+searchText3[count]+"%' ";
                  //criterion3=Restrictions.or(criterion3,Restrictions.like("aliasOfTableA.isbn10","%"+searchText3[count]+"%"));

            }

         }
         else
         {
             if(field3.equals("author"))
                 query3="  a.main_entry like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.mainEntry", "%"+searchText3[0]+"%");
             else if(field3.equals("subject"))
                 query3="  a.subject like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.subject", "%"+searchText3[0]+"%");
             else if(field3.equals("title"))
                 query3="  a.title like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.title", "%"+searchText3[0]+"%");
             else if(field3.equals("isbn10"))
                 query3="  a.isbn10 like '%"+searchText3[0]+"%' ";
                  //criterion3=Restrictions.like("aliasOfTableA.isbn10", "%"+searchText3[0]+"%");

         }
         }
         if(searchText1!=null&&searchText2!=null&&searchText3!=null)
         {
            if(query1_connector.equals("or"))
                connector+=query1+" or "+query2;
              //   criterion = Restrictions.or(criterion1, criterion2);
            else
                connector+=query1+" and "+query2;
                 //criterion = Restrictions.and(criterion1, criterion2);

            if(query2_connector.equals("or"))
                connector+=" or "+query3;
                //criterion = Restrictions.or(criterion, criterion3);
            else
                connector+=" and "+query3;
                //criterion = Restrictions.and(criterion, criterion3);

          //   if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            //    query+=" where  ";
             //else
                 query+=" and  ";
                query+=connector;


         }
         else if (searchText1!=null&&searchText2!=null)
         {
                if(query1_connector.equals("or"))
                    //criterion = Restrictions.or(criterion1, criterion2);
                    connector+=query1+ " or "+query2;
                else
                    connector+=query1+ " and "+query2;
                    //criterion = Restrictions.and(criterion1, criterion2);

          //  if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            //    query+=" where  ";
             //else
                 query+=" and  ";
                query+=connector;


         }
         else if(searchText1!=null&&searchText3!=null)
         {
             if(query1_connector.equals("or"))
                 connector+=query1+ " or "+query3;
                 //criterion = Restrictions.or(criterion1, criterion3);
             else
                 connector+=query1+ " and "+query3;
                 //criterion = Restrictions.and(criterion1, criterion3);

              //if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
                //query+=" where  ";
             //else
                 query+=" and  ";
                query+=connector;


         }
         else if(searchText2!=null&&searchText3!=null)
         {
             if(query2_connector.equals("or"))
                 //criterion = Restrictions.or(criterion2, criterion3);
                 connector+=query2+ " or "+query3;
             else
                 //criterion = Restrictions.and(criterion2, criterion3);
                 connector+=query2+ " and "+query3;

             //if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
               // query+=" where  ";
             //else
                 query+=" and  ";
                query+=connector;


         }
         else if(searchText1!=null)
         {
         //if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            query+=" and  ";
             
             query+="  " +query1;



         }
             //criteria.add(criterion1);
         else if(searchText2!=null)
         {
              //if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            //query+=" where  ";
             //else
                 query+=" and  ";

             query+="  " +query2;

         }
             //criteria.add(criterion2);
         else if(searchText3!=null)
         {
              //if(doc_type.equalsIgnoreCase("combined")==true &&  library_id.equalsIgnoreCase("all"))
            //query+=" where  ";
             //else
                 query+=" and  ";


             query+="  " +query3;}
             //criteria.add(criterion3);

         //if(criterion!=null)
           //  query+=" and " +connector;
            //criteria.add(criterion);

        System.out.println(cmbyr+year1+year2);
          String yr1 =null;
            if(cmbyr.equalsIgnoreCase("between"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                    query+=" and a.publishing_year>"+yr1;
                }
                String yr2=null;
                if(year2!=null && year2.isEmpty()==false)
                {
                    yr2 = String.valueOf(year2);
                      query+=" and a.publishing_year<"+yr2;
                }
            }
            if(cmbyr.equalsIgnoreCase("upto"))
            {
                if(year1!=null && year1.isEmpty()==false)
                {
                     query+=" and a.publishing_year<="+year1;
                }

            }
            if(cmbyr.equalsIgnoreCase("after"))
            {
               if(year1!=null && year1.isEmpty()==false)
                {
                    yr1 = String.valueOf(year1);
                     query+=" and a.publishing_year>"+year1;
                }

            }



         if(sortby.equals("author"))
             query+=" order by a.main_entry ";
            //  criteria.addOrder(Order.asc("aliasOfTableA.mainEntry"));
         else if(sortby.equals("title"))
             query+=" order by a.title ";
              //criteria.addOrder(Order.asc("aliasOfTableA.title"));
         else if(sortby.equals("isbn10"))
             query+=" order by a.isbn10 ";
              //criteria.addOrder(Order.asc("aliasOfTableA.isbn10"));
         else if(sortby.equals("subject"))
             query+=" order by a.subject ";
              //criteria.addOrder(Order.asc("aliasOfTableA.subject"));

              Query simple=hsession.createSQLQuery(query)
                                .addEntity(BibliographicDetailsLang.class)
                                .addEntity(DocumentDetails.class)
                            .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));

            setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=o.getList();
                }
            hsession.getTransaction().commit();


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


 public List isbnSearch(String isbn,String library_id,String sub_lib,int pageNumber)
    {
     List obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
           Criteria criteria = hsession.createCriteria(BibliographicDetails.class, "aliasOfTableA");
        criteria.createAlias("aliasOfTableA.documentDetailses","aliasOfTableB",CriteriaSpecification.LEFT_JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

                    if(!library_id.equalsIgnoreCase("all"))
                        criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
                    if(!sub_lib.equalsIgnoreCase("all"))
                        criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                    criteria.createCriteria("aliasOfTableA.documentDetailses" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.sublibraryId",sub_lib ));

           if (!StringUtils.isEmpty(isbn)||!StringUtils.isBlank(isbn)) {
          criteria.add(Restrictions.eq("aliasOfTableA.isbn10",isbn));
        }
         //Total Number of Record Found View in OPAC
            setSearchSize(criteria.list());
              if(pageNumber==0)
                {
                    criteria = criteria.setFirstResult(0);
                    criteria.setMaxResults(100);
                    obj=criteria.list();
                }
                else
                {
                    CriteriaPagingAction o=new CriteriaPagingAction(criteria,pageNumber,100);
                    obj=o.getList();
                }
            hsession.getTransaction().commit();
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

public List isbnLangSearch(String isbn,String library_id,String sub_lib,String language,int pageNumber)
    {

List obj=null;

      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
              String query="select * from bibliographic_details_lang a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";
               if(!library_id.equalsIgnoreCase("all"))
                  query+=" and  a.library_id='"+library_id+"' ";

             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.sublibrary_id='"+sub_lib+"' ";



            if (!StringUtils.isEmpty(isbn)||!StringUtils.isBlank(isbn)) {
                 query+=" and a.isbn10='"+isbn+"' ";
            }
             query+=" order by a.title ";
             Query simple=hsession.createSQLQuery(query)
                                 .addEntity(BibliographicDetailsLang.class)
                                 .addEntity(DocumentDetails.class)
                                 .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));

            setSearchSize(simple.list());
              if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=o.getList();
                }
            hsession.getTransaction().commit();

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

     public List callNoSearch(String call_no,String library_id,String sub_lib,int pageNumber)
    {
        List obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetails.class, "aliasOfTableA");
        criteria.createAlias("aliasOfTableA.documentDetailses","aliasOfTableB",CriteriaSpecification.LEFT_JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

                    if(!library_id.equalsIgnoreCase("all"))
                        criteria.add(Restrictions.eq("aliasOfTableA.id.libraryId",library_id ));
                    if(!sub_lib.equalsIgnoreCase("all"))
                        criteria.add(Restrictions.eq("aliasOfTableA.id.sublibraryId",sub_lib ));

//                        criteria.createCriteria("aliasOfTableA.documentDetailses" , "aliasOfTableB");
//                    if(!library_id.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.libraryId",library_id));
//                    if(!sub_lib.equalsIgnoreCase("all"))
//                        criteria.add(Restrictions.eq("aliasOfTableB.bibliographicDetails.id.sublibraryId",sub_lib ));




         if(call_no.isEmpty()==false)
          criteria.add(Restrictions.eq("aliasOfTableA.callNo",call_no));
           //Total Number of Record Found View in OPAC
            setSearchSize(criteria.list());
              if(pageNumber==0)
                {
                    criteria = criteria.setFirstResult(0);
                    criteria.setMaxResults(100);
                    obj=criteria.list();
                }
                else
                {
                    CriteriaPagingAction o=new CriteriaPagingAction(criteria,pageNumber,100);
                    obj=o.getList();
                }
            hsession.getTransaction().commit();
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
public List callNoLangSearch(String call_no,String library_id,String sub_lib,String language,int pageNumber)
    {
List obj=null;
      Session hsession=HibernateUtil.getSessionFactory().openSession();
      try
        {
          hsession.beginTransaction();
              String query="select * from bibliographic_details_lang a,document_details b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.biblio_id=b.biblio_id ";
               if(!library_id.equalsIgnoreCase("all"))
                  query+=" and  a.library_id='"+library_id+"' ";

             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.sublibrary_id='"+sub_lib+"' ";

//          query+=" and a.entry_language='"+language+"' ";

            if (!StringUtils.isEmpty(call_no)||!StringUtils.isBlank(call_no)) {
                 query+=" and a.call_no='"+call_no+"' ";
            }
             query+=" order by a.title ";
               Query simple=hsession.createSQLQuery(query)
                                 .addEntity(BibliographicDetailsLang.class)
                                 .addEntity(DocumentDetails.class)
                                 .setResultTransformer(Transformers.aliasToBean(SearchPOJO.class));
       //Total Number of Record Found View in OPAC
            setSearchSize(simple.list());
                if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=o.getList();
                }
            hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
        hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
          hsession.getTransaction().commit();
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
      Session session=HibernateUtil.getSessionFactory().openSession();
      List<BibliographicDetailsLang> obj=null;
      try
        {
          session.beginTransaction();
         Criteria criteria = session.createCriteria(BibliographicDetailsLang.class);
          criteria.add(Restrictions.eq("id.libraryId",library_id));
          criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
          criteria.add(Restrictions.eq("id.biblioId",doc_id));
          obj= (List<BibliographicDetailsLang>) criteria.list();

          session.getTransaction().commit();
        }
      catch(Exception e)
        {
           e.printStackTrace();

        }
      finally
        {
            session.close();
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
          hsession.getTransaction().commit();
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

public static List<MemberSubLibrary> MembersubLibrarySearch(String library_id,String memid)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
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
            session.getTransaction().commit();
        }
        finally {
        session.close();
        }
return obj;
     }


}



