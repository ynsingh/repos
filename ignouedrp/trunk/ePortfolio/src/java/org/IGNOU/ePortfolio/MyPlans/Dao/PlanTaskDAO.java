/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyPlans.Dao;


import org.IGNOU.ePortfolio.MyPlans.Model.MyPlanList;
import org.IGNOU.ePortfolio.MyPlans.Model.NewTask;
import org.IGNOU.ePortfolio.MyPlans.Model.PlanTaskList;
import org.IGNOU.ePortfolio.MyPlans.Model.UserPlanModel;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 ** Hibernate Utility class with a convenient method to get Session Factory object.
 * 
 * @author Vinay
 */
public class PlanTaskDAO {

    /**
     * saveTaskInfo This is the function to save Plan information into DB.
     * @param upModel 
     * @return upModel
     * @author Vinay
     * This function is used to insert Plan information in to Database
     */
    @SuppressWarnings("unchecked")
    public UserPlanModel saveInfo(UserPlanModel upModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(upModel);
        session.getTransaction().commit();
        session.close();
        sf.close();
        return upModel;
    }

    /**
     * PlanList This is the function to show list of Plan as per user.
     * @param user_id 
     * @return planlistlist
     * @author Vinay
     * This function is used to Fetch Plan List 
     */
    @SuppressWarnings("unchecked")
    public List<MyPlanList> Planlist(String user_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<MyPlanList> planlistlist = null;
        try {
            planlistlist = session.createQuery("from MyPlanList where user_id='" + user_id + "'").list();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        t.commit();
        session.close();
        sf.close();
        return planlistlist;
    }

    /**
     * EditPlanlist This is the function to show Plan details in Editor formate.
     * Added on 23/08/2011
     * @param plan_id 
     * @return editplanlist
     * @author Vinay
     * This function is used to Edit Plan information in to Database 
     */
    @SuppressWarnings("unchecked")
    public List<MyPlanList> EditPlanlist(long plan_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<MyPlanList> editplanlist = null;
        try {
            editplanlist = session.createQuery("from MyPlanList where plan_id='" + plan_id + "'").list();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        t.commit();
        session.close();
        sf.close();
        return editplanlist;
    }
    /*
     * This function is used to Update Plan information in to Database
     */
    @SuppressWarnings("unchecked")
    public MyPlanList UpdatePlanInfo(Long plan_id, String p_title, String p_description) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        MyPlanList updatePlanList = (MyPlanList) session.load(MyPlanList.class, plan_id);
        updatePlanList.setP_title(p_title);
        updatePlanList.setP_description(p_description);
        if (null != updatePlanList) {
            session.update(updatePlanList);
        }
        t.commit();
        session.close();
        sf.close();
        return updatePlanList;
    }
     /*
     * This function is used to Delete Plan information in to Database
     */
    @SuppressWarnings("unchecked")
    public MyPlanList DeletePlan(Long plan_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        MyPlanList deletelist = (MyPlanList) s.load(MyPlanList.class, plan_id);
        if (null != deletelist) {
            s.delete(deletelist);
        }
        t.commit();
        s.close();
        sf.close();
        return deletelist;
    }

    /**
     * saveTaskInfo This is the function to save Task information into DB.
     * @param nt 
     * @return SUCCESS
     * @author Vinay
          * This function is used to Save  Plan Task  information in to Database
          */
    @SuppressWarnings("unchecked")
    public NewTask saveTaskInfo(NewTask nt) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        session.save(nt);
        t.commit();
        session.close();
        sf.close();
        return nt;
    }

    /**
     * TaskList This is the function to show list of Task as per Plan_id.
     * @param plan_id 
     * @return TaskListList
     * @author Vinay
     * This function is used to Fetch  Plan's Task  List information 
     */
    @SuppressWarnings("unchecked")
    public List<PlanTaskList> TaskList(long plan_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();

        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<PlanTaskList> TaskListList = null;
        try {
            //from MyPlanList p, PlanTaskList t where t.plan_id=1 and p.plan_id=1
            //  TaskListList = session.createQuery("from MyPlanList p, PlanTaskList t where t.plan_id='" + plan_id + "' and p.plan_id='"+plan_id+"'").list();
            TaskListList = session.createQuery("from PlanTaskList where plan_id='" + plan_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        session.close();
        sf.close();
        return TaskListList;
    }

    @SuppressWarnings("unchecked")
    public List<MyPlanList> PlanTasklist(long plan_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        List<MyPlanList> planlist = null;
        try {
            planlist = session.createQuery("from MyPlanList where plan_id='" + plan_id + "'").list();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        t.commit();
        session.close();
        sf.close();
        return planlist;
    }

    /**
     * EditTask This is the function to Fetch Task details in editable formate.
     * author Vinay
     * @param task_id 
     * @return EditTask  List
     * Added on 24th Aug 2011
     */
    @SuppressWarnings("unchecked")
    public List<PlanTaskList> EditTaskList(long task_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<PlanTaskList> EditTask = null;
        try {
            EditTask = s.createQuery("from PlanTaskList where task_id='" + task_id + "'").list();
            //       EditTask = session.createQuery("from PlanTaskList where task_id='"+task_id+"'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return EditTask;
    }
    /*This is the function to Update Task details */
    @SuppressWarnings("unchecked")
    public PlanTaskList UpdateTask(Long task_id, String t_title, String t_description, int status, String t_date) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        PlanTaskList UpdateTaskList = (PlanTaskList) s.load(PlanTaskList.class, task_id);
        UpdateTaskList.setT_title(t_title);
        UpdateTaskList.setT_description(t_description);
        UpdateTaskList.setT_date(t_date);
        UpdateTaskList.setStatus(status);
        if (null != UpdateTaskList) {
            s.update(UpdateTaskList);
        }
        t.commit();
        s.close();
        sf.close();
        return UpdateTaskList;
    }
    /*This is the function to Delete Plan's Task details */
    @SuppressWarnings("unchecked")
    public PlanTaskList DeletePlanTask(Long task_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        PlanTaskList deleteList = (PlanTaskList) s.load(PlanTaskList.class, task_id);
        if (null != deleteList) {
            s.delete(deleteList);
        }
        t.commit();
        s.close();
        sf.close();
        return deleteList;
    }
}