/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import  com.myapp.struts.hbm.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Property;

/**
 *
 * @author maqbool
 */
public class AcqApprovalDao {

     public List<AcqApprovalHeader> searchDoc1(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqApprovalHeader.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("acqMode", "Approved")));
            return criteria.list();
        } finally {
          //session.close();
        }
    }
public static List<AcqBibliographyDetails> searchBudgetHead(String library_id,String sublibrary_id, String budgetHeadId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqBibliographyDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sublibrary_id))
                    .add(Restrictions.eq("primaryBudget", budgetHeadId))
                    );
            return (List<AcqBibliographyDetails>)criteria.list();
        } finally {
          //session.close();
        }
    }
}
