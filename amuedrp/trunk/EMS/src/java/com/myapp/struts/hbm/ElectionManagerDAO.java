/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;


import com.myapp.struts.election_manager.CandidateReg;
import com.myapp.struts.election_manager.CandidateReg1;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Query;


import org.hibernate.transform.Transformers;

/**
 *
 * @author Edrp-04
 */
public class ElectionManagerDAO {
     public List VotedVoterList(String institueid,String electionid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("innnnnnnnnnnnnnnnn");
            String sql="";

            sql = "select i.instituteName As i_institute_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.email AS v_email,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,e.electionName As e_election_name,e.startDate As e_start,e.endDate As e_end FROM Institute i,VoterRegistration v,VotingProcess sv,Election e WHERE   i.id.instituteId=v.id.instituteId and i.id.instituteId=sv.id.instituteId and v.id.instituteId=sv.id.instituteId and v.email=sv.id.voterId  and  sv.Status='Voted' and sv.id.instituteId=e.id.instituteId and sv.id.electionId=e.id.electionId and v.id.instituteId = :instituteId and sv.id.electionId=:electionId";
/*select i.institute_name As i_institute_name,v.voter_name AS v_voter_name,v.gender
AS v_gender,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department
AS v_department,v.enrollment AS v_enrollment FROM institute i,voter_registration v,votingprocess sv
 WHERE   i.institute_id=v.institute_id and i.institute_id=sv.institute_id and
 v.institute_id=sv.institute_id and v.email=sv.voter_id
and sv.status='Voted' and v.institute_id = '101' and sv.election_id='2'
 *
 *
 */
//select e.id.electionId AS e_election_id,i.instituteName As i_institute_name,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Election e,Institute i,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId=c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.status='REGISTERED' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc
            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
          query.setString("electionId",electionid);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;

}

      public List<CandidateReg1> VotedVoterListXML(String institueid,String electionid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.email AS v_email,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,e.electionName As e_election_name,e.startDate As e_start,e.endDate As e_end FROM Institute i,VoterRegistration v,VotingProcess sv,Election e WHERE   i.id.instituteId=v.id.instituteId and i.id.instituteId=sv.id.instituteId and v.id.instituteId=sv.id.instituteId and v.email=sv.id.voterId  and  sv.Status='Voted' and sv.id.instituteId=e.id.instituteId and sv.id.electionId=e.id.electionId and v.id.instituteId = :instituteId and sv.id.electionId=:electionId";
            System.out.println(sql);
          Query query =  session.createQuery(sql)
          .setResultTransformer(Transformers.aliasToBean(CandidateReg1.class));
          query.setString("instituteId",institueid);
          query.setString("electionId",electionid);
            tx=  (List<CandidateReg1>)query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;

}

public void insert(ElectionManager electionmanager)
    {
    Session session=null;
    Transaction tx=null;


    try{
        session=HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction();
        session.save(electionmanager);
        tx.commit();
    }

    catch(RuntimeException e){
        if(electionmanager!=null)
            tx.rollback();
        e.printStackTrace();
        throw e;
    }

    finally{
      session.close();
    }

}
public void update(ElectionManager electionmanager)
    {
Session session=null;
Transaction tx=null;
try{
session=HibernateUtil.getSessionFactory().openSession();
tx=session.beginTransaction();
session.update(electionmanager);
tx.commit();
}
catch(RuntimeException e){
    e.printStackTrace();
    tx.rollback();
    throw e;
    
}finally{
      session.close();
    }

}
public void delete(){

    }
public List<Election_Manager_StaffDetail> GetManagerDetails(String ElectionManagerId,String instituteId)
    {
    Session session =null;
    List<Election_Manager_StaffDetail> tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from staff_detail a, election_manager b,login c where a.staff_id=b.staff_id and a.staff_id=c.staff_id and a.institute_id=c.institute_id and a.institute_id=b.institute_id  and b.manager_id=:manager_id and b.institute_id=:institute_id";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(ElectionManager.class)
                    .addEntity(StaffDetail.class)
                    .addEntity(Login.class)
                    .setResultTransformer(Transformers.aliasToBean(Election_Manager_StaffDetail.class));
          query.setString("manager_id", ElectionManagerId);
          query.setString("institute_id", instituteId);
            tx= (List<Election_Manager_StaffDetail>)query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

public List<Election_Manager_StaffDetail> GetManagers(String institute_id,String paraField,String paraVal,String status)
    {
    Session session =null;
    List<Election_Manager_StaffDetail> tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from staff_detail a, election_manager b,login c where a.staff_id=b.staff_id and a.staff_id=c.staff_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id";
            if(paraVal!=null && !paraVal.equals(""))
            {
                paraVal = paraVal + "%";
                if(paraField.equals("managerId")){
            sql+=" and b.manager_id like :managerId";
            }
            else if(paraField.equals("firstName")) {
            sql+=" and a.first_name like :firstName";
            }else if(paraField.equals("lastName")) {
            sql+=" and a.last_name like :lastName";
            }
            }else if(status!=null && !status.equals(""))
            {
                if(status.equalsIgnoreCase("B"))
                sql+=" and b.status like 'B%'";
                else if(status.equalsIgnoreCase("A"))
                    sql+=" and b.status like 'OK%'";
            }
            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(ElectionManager.class)
                    .addEntity(StaffDetail.class)
                    .addEntity(Login.class)
                    .setResultTransformer(Transformers.aliasToBean(Election_Manager_StaffDetail.class));
          query.setString("institute_id", institute_id);
          if(paraVal!=null && !paraVal.equals(""))
            if(paraField.equals("managerId")){
            query.setString("managerId", paraVal);
            }
            else if(paraField.equals("firstName")) {
            query.setString("firstName", paraVal);
            }else if(paraField.equals("lastName")) {
            query.setString("lastName", paraVal);
            }
            tx= (List<Election_Manager_StaffDetail>) query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}


public List getStaffDetails(String staffId,String instituteId){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM ElectionManager where staffId=:staffId and id.instituteId=:instituteId");
             query.setString("staffId", staffId);
             query.setString("instituteId", instituteId);
           tx= query.list();
           session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}

public List getUserId(String user_id){
 Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM ElectionManager  where userId = :userId");
                query.setString("userId",user_id );
                tx= query.list();
                session.getTransaction().commit();
        }
        catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}


public List ManagerDeatils(String managerId,String instituteId)
{
Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM ElectionManager  where id.managerId = :managerId and  id.instituteId=:instituteId");
                query.setString("managerId",managerId );
                query.setString("instituteId",instituteId);
                tx= query.list();
                session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}

public List Report(String enrollment)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";
            //,e.election_name AS e_election_name,p.position_name AS p_position_name,v.voter_name AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobile_number AS v_mobile_number,v.c_address AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email
            sql = "select e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and c1.status='Registered' and v.id.enrollment = :enrollment order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";
            // sql = "select e.election_id AS e_election_id,e.election_name AS e_election_name,p.position_name AS p_position_name,v.voter_name AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobile_number AS v_mobile_number,v.c_address AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.enrollment AS v_enrollment,v.joining_date AS v_admisionDate,v.course_duration AS v_duration,v.year AS v_year,v.current_session AS v_session,c1.p_attendence AS c1_attendence,c1.p_marks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.f_name AS v_father,v.m_name AS v_mother,v.country AS v_country,v.p_address AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zip_code1 AS v_zip1,v.country1 AS v_country1,v.zip_code AS v_zip FROM election e,candidate_registration c1,voter_registration v,position1 p WHERE  e.institute_id = c1.institute_id and v.institute_id = c1.institute_id and e.election_id = p.election_id and c1.position = p.position_id and v.enrollment = c1.enrollment and c1.status1='Registered' and v.enrollment = :enrollment order by e.election_id,e.election_name,p.position_name,v.voter_name asc";
            //,v.course AS v_course,v.department AS v_department,v.enrollment AS v_enrollment,v.joining_date AS v_admisionDate,v.course_duration AS v_duration,v.year AS v_year,v.current_session AS v_session,c1.p_attendence AS c1_attendence,c1.p_marks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.f_name AS v_father,v.m_name AS v_mother,v.country AS v_country,v.p_address AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zip_code1 AS v_zip1,v.country1 AS v_country1,v.zip_code AS v_zip

            System.out.println(sql);

          Query query =  session.createQuery(sql)
//                  .setEntity("v",VoterRegistration.class)
//                  .setEntity("e",Election.class)
//                  .setEntity("p",Position1.class)
//                  .setEntity("c1",CandidateRegistration.class)
//                  .addScalar("e_election_id", Hibernate.STRING)
                  //.addScalar("p_position_name", Hibernate.STRING)
                  .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));

          query.setString("enrollment", enrollment);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

public List NominationReport(String institueid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and e.id.electionId = p.id.electionId and c1.id.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and c1.status='Registered' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";


            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}


public List VoterList(String institueid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i,VoterRegistration v WHERE   v.id.instituteId = i.id.instituteId   and v.status='REGISTERED' and v.id.instituteId = :instituteId order by v.voterName asc";

//select e.id.electionId AS e_election_id,i.instituteName As i_institute_name,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Election e,Institute i,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId=c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.status='REGISTERED' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc
            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
    
}

public List RejectedReport(String institueid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i, Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId= c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and c1.status='Rejected' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";


            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
            tx=  query.list();
            session.getTransaction().commit();
        }

        catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}


public List WithdrawlReport(String institueid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i, Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId= c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and c1.status='Withdraw' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";


            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

//public static List VoterList2(String institueid,String electionid)
//    {
//    Session session =null;
//    List tx = null;
//    try {
//        session= HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//
//            String sql="";
//
//            sql = "select * FROM SetVoter WHERE id.instituteId = :instituteId and id.electionId=:electionId ";
//           //  sql = "select i.instituteName As i_institute_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,sv.id.enrollment AS v_enrollment FROM Institute i,VoterRegistration v,SetVoter sv WHERE   i.id.instituteId=v.id.instituteId and i.id.instituteId=sv.id.instituteId and v.id.instituteId=sv.id.instituteId and sv.id.enrollment=v.id.enrollment  and v.status='REGISTERED' and v.id.instituteId = :instituteId and sv.id.electionId=:electionId ";
//
//            //select e.id.electionId AS e_election_id,i.instituteName As i_institute_name,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Election e,Institute i,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId=c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.status='REGISTERED' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc
//            System.out.println(sql);
//
//          Query query =  session.createQuery(sql);
//
//          query.setString("instituteId",institueid);
//          query.setString("electionId",electionid);
//            tx= query.list();
//            session.getTransaction().commit();
//        }
//    catch(RuntimeException e){
//    e.printStackTrace();
//    }
//    finally {
//            session.close();
//        }
//    return tx;
//
//}

public List VoterList1(String institueid,String electionid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.email AS v_email,v.course AS v_course,v.department AS v_department,sv.id.enrollment AS v_enrollment FROM Institute i,VoterRegistration v,SetVoter sv WHERE   i.id.instituteId=v.id.instituteId and i.id.instituteId=sv.id.instituteId and v.id.instituteId=sv.id.instituteId and sv.id.enrollment=v.id.enrollment  and v.status='REGISTERED' and v.id.instituteId = :instituteId and sv.id.electionId=:electionId ";
           //  sql = "select i.instituteName As i_institute_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,sv.id.enrollment AS v_enrollment FROM Institute i,VoterRegistration v,SetVoter sv WHERE   i.id.instituteId=v.id.instituteId and i.id.instituteId=sv.id.instituteId and v.id.instituteId=sv.id.instituteId and sv.id.enrollment=v.id.enrollment  and v.status='REGISTERED' and v.id.instituteId = :instituteId and sv.id.electionId=:electionId ";

            //select e.id.electionId AS e_election_id,i.instituteName As i_institute_name,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Election e,Institute i,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId=c1.id.instituteId and e.id.electionId = p.id.electionId and c1.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.status='REGISTERED' and v.id.instituteId = :instituteId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc
            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
          query.setString("electionId",electionid);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;

}

public List AllCandiReport(String institueid,String election_id)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,c1.status AS status,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i, Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId= c1.id.instituteId and e.id.electionId = p.id.electionId and c1.id.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.id.instituteId = :instituteId and e.id.electionId =:electionId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";

           //  sql = "select i.instituteName As i_institute_name,e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,c1.status AS status,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i, Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId= c1.id.instituteId and e.id.electionId = p.id.electionId and c1.id.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.id.instituteId = :instituteId and e.id.electionId =:electionId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";
            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
          query.setString("electionId",election_id);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}


public List AllCandiReport1(String institueid,String election_id)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select i.instituteName As i_institute_name,e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,c1.status AS status,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i, Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId= c1.id.instituteId and e.id.electionId = p.id.electionId and c1.id.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.id.instituteId = :instituteId and e.id.electionId =:electionId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";

           //  sql = "select i.instituteName As i_institute_name,e.id.electionId AS e_election_id,e.electionName AS e_election_name,p.positionName AS p_position_name,v.voterName AS v_voter_name,v.gender AS v_gender,v.birthdate AS v_birthdate,v.mobileNumber AS v_mobile_number,v.CAddress AS v_c_address,v.city AS v_city,v.state AS v_state,v.email AS v_email,v.image AS v_image,v.course AS v_course,v.department AS v_department,v.id.enrollment AS v_enrollment,v.joiningDate AS v_admisionDate,v.courseDuration AS v_duration,v.year AS v_year,v.currentSession AS v_session,c1.PAttendence AS c1_attendence,c1.PMarks AS c1_marks,c1.backlog AS c1_back,c1.criminal AS c1_criminal,c1.status AS status,v.FName AS v_father,v.MName AS v_mother,v.country AS v_country,v.PAddress AS v_peradd,v.city1 AS v_city1,v.state1 AS v_state1,v.zipCode1 AS v_zip1,v.country1 AS v_country1,v.zipCode AS v_zip FROM Institute i, Election e,CandidateRegistration c1,VoterRegistration v,Position1 p WHERE  e.id.instituteId = c1.id.instituteId and v.id.instituteId = c1.id.instituteId and i.id.instituteId= c1.id.instituteId and e.id.electionId = p.id.electionId and c1.id.position = p.id.positionId and v.id.enrollment = c1.id.enrollment and v.id.instituteId = :instituteId and e.id.electionId =:electionId order by e.id.electionId,e.electionName,p.positionName,v.voterName asc";
            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
          query.setString("electionId",election_id);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}
public List ElectionDetail(String institueid,String electionid)
    {
    Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select e.electionName As e_election_name,e.description As e_description,e.nstart As e_nomistart,e.nend As e_nomiend,e.scrutnyDate As e_scrstart,e.scrutnyEndDate As e_scrend,e.withdrawlDate As e_withstart,e.withdrawlEndDate As e_withend,e.startDate As e_start,e.endDate As e_end from Election e where  e.id.instituteId=:instituteId and e.id.electionId=:electionId";

//"select e.electionName As e_election_name,e.description As e_description,e.nstart As e_nomistart,e.nend As e_nomiend,e.scrutnyDate As e_scrstart,e.scrutnyEndDate As e_scrend,e.withdrawlDate As e_withstart,e.withdrawlEndDate As e_withend,e.startDate As e_start,e.endDate As e_end,p.positionName As p_position_name from Election e,Position1 p where e.id.electionId=p.id.electionId and e.id.instituteId=p.id.instituteId and e.id.instituteId=:instituteId and e.id.electionId=:electionId"
            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
          query.setString("electionId",electionid);
           tx=  query.list();
           session.getTransaction().commit();
        }

        catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}

//select e.*,p.position_name from election e,position1 p where e.election_id=p.election_id and e.institute_id=p.institute_id and e.institute_id='JMI' and e.election_id=1
public List PositionDetail(String institueid,String electionid)
    {
    Session session =null;
   List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select p.positionName As p_position_name from Position1 p where p.id.instituteId=:instituteId and p.id.electionId=:electionId";


            System.out.println(sql);

          Query query =  session.createQuery(sql)
               .setResultTransformer(Transformers.aliasToBean(CandidateReg.class));



          query.setString("instituteId",institueid);
          query.setString("electionId",electionid);
            tx=  query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return tx;
}


}


