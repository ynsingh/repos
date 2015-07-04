/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.Administrator;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.Admin.AdminManagedBean;
import org.smvdu.payroll.api.email.OrgConformationEmail;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.beans.db.UserDB;
import org.smvdu.payroll.beans.setup.Org;
import org.springframework.context.annotation.Scope;
/**
 *
 * @author KESU
 */
@ManagedBean
@RequestScoped
public class CollegeList {
    Connection cn;
    String adminUserId;
    public CollegeList()
    {
        
        try
        {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
           // Org admin = (Org) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Organization Profile Bean");
            adminUserId = uf.getUserName();
            System.out.println("ID =======seema: "+adminUserId);
        }
        catch(Exception ex)
        {
            
        }
    }
    
    /**
     * @Load All Pending Details of College List Org Name, Org Id, Org Request Status, Pending Status 
     * @return  
     */
    
    public ArrayList<Org> getPendingCollegeList()
    {
        try
        {
            
            ArrayList<Org> pendingList = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
    //        pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_request_status from org_profile inner join college_pending_status on org_email=org_pen_email where org_request_status = '"+0+"'");
            pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_request_status from org_profile left join college_pending_status on org_id=org_code where org_request_status = '"+0+"'");
            
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org o = new Org();
                o.setId(rst.getInt(1)); 
                o.setName(rst.getString(2)); 
                o.setWeb(rst.getString(3)); 
                o.setEmail(rst.getString(4)); 
                o.setPhone(rst.getString(5)); 
                if(rst.getInt(6) == 1)
                {
                    o.setImgUrl("Active.png");
                    o.setStatus(true); 
                }
                else
                {
                    o.setImgUrl("InActive.png"); 
                    o.setStatus(false);  
                }
                pendingList.add(o); 
            }
            return pendingList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
     public int getDateDIff(String fromDate,String toDate)
    {
        try
        {
            String[] d1 = fromDate.split("-");
            String[] d2 = toDate.split("-");
            int dayDiff = 0;
            int counter = 0;
            int fixDate = 0;
            if(Integer.parseInt(d1[0]) == Integer.parseInt(d2[0]))
            {
                if(Integer.parseInt(d2[1]) > Integer.parseInt(d1[1]))
                {
                    fixDate = Integer.parseInt(d2[2]);
                    while(fixDate!=0)
                    {
                        counter++;
                        fixDate--;
                    }
                    dayDiff = counter;
                }
                else
                {
                    dayDiff = Integer.parseInt(d2[2]) - Integer.parseInt(d1[2]);
                }
                //dayDiff = Integer.parseInt(d2[2]) - Integer.parseInt(d1[2]);
            }
            if(Integer.parseInt(d2[0]) > Integer.parseInt(d1[0]))
            {
                fixDate=0;
                counter=0;
                if(Integer.parseInt(d1[1])>Integer.parseInt(d2[1]))
                {
                    if(Integer.parseInt(d1[2])>Integer.parseInt(d2[2]))
                    {
                        fixDate = Integer.parseInt(d2[2]);
                        while(fixDate!=0)
                        {
                            counter++;
                            fixDate--;
                        }
                        dayDiff=counter;
                    }
                }
            }
            return dayDiff;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    
     /**
      * Load College List which is Registered
      * @return
      * @throws SQLException 
      * 
      */
     
     public ArrayList<Org> getCollegeList() throws SQLException
    {
        try
        {
            ArrayList<Org> cList = new ArrayList<Org>();
            cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            java.util.Date d = new java.util.Date();
        //    PreparedStatement pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_reg_date,flag from org_profile inner join user_master on org_id = user_org_id");
            PreparedStatement pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_reg_date,org_status from org_profile where org_status =1");
            
            ResultSet rst = pst.executeQuery();
            String toDate = d.getYear()+"-"+d.getMonth()+"-"+d.getDay();
            while(rst.next())
            {
                if(this.getDateDIff(rst.getString(6), toDate) >= 7)
                {
                    this.deleteExtendeRegistration(rst.getString(4)); 
                }
                Org o = new Org();
                o.setId(rst.getInt(1)); 
                o.setName(rst.getString(2)); 
                o.setWeb(rst.getString(3)); 
                o.setEmail(rst.getString(4)); 
                o.setPhone(rst.getString(5)); 
                o.setDate(rst.getString(6)); 
                if(rst.getInt(7) == 1)
                {
                    o.setImgUrl("Active.png");
                    o.setStatus(true); 
                }
                else
                {
                    o.setImgUrl("InActive.png"); 
                    o.setStatus(false);  
                }
                cList.add(o); 
            }
            pst.close();
            rst.close();
            cn.commit();
            cn.close();
            return cList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            cn.close();
        }
    }
    
    public boolean deleteExtendeRegistration(String orgEmail)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            PreparedStatement pst1;
            pst1 = cn.prepareStatement("delete from org_profile where org_email = '"+orgEmail+"'");
            pst = cn.prepareStatement("delete from college_pending_status where org_pen_email = '"+orgEmail+"'");
            pst1.executeUpdate();
            pst1.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
   
    
    /**
     * Update the Status of College which is Active/Deactive
     * @param org
     * @return 
     * 
     */
    
    public Exception update(ArrayList<Org> org)
    {
        try
        {
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst = null; 
            PreparedStatement pst1 = null;
            int st;
            for(Org or : org)
            {
                if(or.isStatus() == true)
                {
                    st =1;
                    new OrgConformationEmail().sendMail(or); 
                }
                else
                {
                    st = 0;
                    new OrgConformationEmail().sendDeActivationCollegeMail(or); 
                }
                pst = connection.prepareStatement("update org_profile set org_status = '"+st+"' where org_id = '"+or.getId()+"'");
                pst1 = connection.prepareStatement("update user_master set flag = '"+st+"' where user_org_id='"+or.getId()+"'");
                pst1.executeUpdate();
                pst1.clearParameters();
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            pst1.close();
            connection.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        } 
    }
   
    /**
     * 
     * Update/Insert details for User to access the Payroll with Particular Task 
     * @param org
     * @return 
     * 
     */
    
//    public Exception updateRequest(ArrayList<Org> org)
//    {
//        try
//        {
//            Connection connection = new CommonDB().getConnection();
//            PreparedStatement pst = null; 
//            PreparedStatement pst1 = null;
//            PreparedStatement pst2 = null;
//            ResultSet rst = null;
//            int st;
//            String password = null;
//            for(Org or : org)
//            {
//                if(or.isStatus() == true)
//                {
//                    st =1;
//                    pst2 = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getEmail()+"'");
//                    rst = pst2.executeQuery();
//                    if(rst.next())
//                    {
//                        password = rst.getString(1);
//                    }
//                    rst.close();
//                    pst2.close();
//                    pst1 = connection.prepareStatement("insert into user_master(user_name,user_pass,user_org_id,user_grp_id,user_profile_id,flag) values('"+or.getEmail()+"','"+password+"','"+or.getId()+"','"+4+"','"+0+"','"+1+"')");
//                    pst1.executeUpdate();
//                    pst1.clearParameters();
//                    
//                    new OrgConformationEmail().sendMail(or);
//                    pst = connection.prepareStatement("delete from college_pending_status where org_pen_email = '"+or.getEmail()+"'");
//                    pst.executeUpdate();
//                    pst.clearParameters();
//                }
//                else
//                {
//                    st = 0;
//                }
//            }
//            pst.close();
//            pst1.close();
//            
//            connection.close();
//            return null;
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            return ex;
//        } 
//    }
    
//    public Exception updateRequest(ArrayList<Org> org)
//    {
//        try
//        {
//            Connection connection = new CommonDB().getConnection();
//            Connection connectLogin = new CommonDB().getLoginDBConnection();
//            PreparedStatement pst = null; 
//            PreparedStatement pst1 = null;
//            PreparedStatement pst2 = null;
//            PreparedStatement pst3 = null;
//            PreparedStatement pst4 = null;
//            PreparedStatement pst5 = null;
//            ResultSet rst = null;
//            int st;
//            String password = null;
//            for(Org or : org)
//            {
//                if(or.isStatus() == true)
//                {
//                    st =1;
//                    pst2 = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getEmail()+"'");
//                    rst = pst2.executeQuery();
//                    if(rst.next())
//                    {
//                        password = rst.getString(1);
//                    }
//                    rst.close();
//                    pst2.close();
//                            
//                    pst1 = connection.prepareStatement("insert into user_master(user_name,user_pass,user_org_id,user_grp_id,user_profile_id,flag) values('"+or.getEmail()+"','"+password+"','"+or.getId()+"','"+4+"','"+0+"','"+1+"')");
//                    pst1.executeUpdate();
//                    pst1.clearParameters();
//                    
//                    pst3 = connectLogin.prepareStatement("insert into users(username,password,email,status) values('"+or.getEmail()+"','"+password+"','"+or.getEmail()+"','"+1+"')");
//                    pst3.executeUpdate();
//                    pst3.clearParameters();
//                    
//                    UserDB ud = new UserDB();
//                    int userId = ud.getUserId(or.getEmail(), or.getId());
//                    
//                    pst4 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+userId+"','"+2+"','"+or.getId()+"')");
//                    pst4.executeUpdate();
//                    pst4.clearParameters();
//                    
//                    pst5 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+userId+"','"+3+"','"+or.getId()+"')");
//                    pst5.executeUpdate();
//                    pst5.clearParameters();
//                    
//                    new OrgConformationEmail().sendMail(or);
//                    pst = connection.prepareStatement("delete from college_pending_status where org_code = '"+or.getId()+"'");
//                    pst.executeUpdate();
//                    pst.clearParameters();
//                }
//                else
//                {
//                    st = 0;
//                }
//            }
//            pst.close();
//            pst1.close();
//            
//            connection.close();
//            return null;
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            return ex;
//        } 
//    }
//    
    
    
    
    
    public Exception updateRequest(ArrayList<Org> org)
    {
       
        Connection connection = new CommonDB().getConnection();
        Connection connectLogin = new CommonDB().getLoginDBConnection();
        try
        {
            
            PreparedStatement pst = null; 
            PreparedStatement pst1 = null;
            PreparedStatement pst2 = null;
            PreparedStatement pst3 = null;
            PreparedStatement pst4 = null;
            PreparedStatement pst5 = null;
            PreparedStatement pst6 = null;
            PreparedStatement pst7 = null;
            PreparedStatement pst8 = null;
            PreparedStatement pst9 = null;
            ResultSet rst = null;
            int st;
            String password = null;
            System.out.println("Total Institutes to be approved are : " +org.size());
            for(Org or : org)
            {
                if(or.isStatus() == true)
                {
                    st =1;
    
                    pst = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getEmail()+"'");
                    rst = pst.executeQuery();
                    if(rst.next())
                    {
                        password = rst.getString(1);
                    }
                    rst.close();
                    pst.close();
                    
                    UserDB ud = new UserDB();
                    int userid = ud.CheckUserExistInUserMaster(or.getEmail());
                    
                    System.out.println("User in user_master - " +userid);
                    
                    if(!(userid > 0)){
                            
                        pst1 = connection.prepareStatement("insert into user_master(user_name,user_pass,user_profile_id,flag) values('"+or.getEmail()+"','"+password+"','"+0+"','"+1+"')");
                        pst1.executeUpdate();
                        pst1.clearParameters();
                        pst1.close();
                        
                        System.out.println("Records inserted into user_master for "+or.getEmail());
                        
                   //     String loginDB = "logindb";
                        boolean dbExist = new CommonDB().checkLoginDBExists();
                        
                        if(dbExist){
                            
                            System.out.println("Login Database exist");
                            
                            UserDB user = new UserDB();
                            int id = user.CheckUserExistInLoginDB(or.getEmail());
                            
                            if(!(id > 0))
                            {
                            System.out.println("User does not exist in login database");
                            
                            Org orginfo =  new OrgProfileDB().loadOrgProfileByName(or.getName());
                            String component = "payroll";
                            
                            pst2 = connectLogin.prepareStatement("insert into bgasuser(username,password,email,componentreg,mobile,status) values('"+or.getEmail()+"','"+password+"','"+or.getEmail()+"','"+component+"','"+or.getPhone()+"','"+1+"')");
                            pst2.executeUpdate();
                            pst2.clearParameters();
                            pst2.close();
                            
                            
                            
                            System.out.println("Records inserted into bgasuser of LoginDB for "+or.getEmail());    
                            
                            int userIdInLoginDB = user.CheckUserExistInLoginDB(or.getEmail());
                            
                            System.out.println("User Now exist in Login Database exist with userid " +userIdInLoginDB);
                            
                            
                            
                            
                            pst3 = connectLogin.prepareStatement("insert into userprofile(userid,firstname,lastname,address,status) values('"+userIdInLoginDB+"','"+orginfo.getAdminfn()+"','"+orginfo.getAdminln()+"','"+orginfo.getAddress1()+"','"+1+"')");
                            pst3.executeUpdate();
                            pst3.clearParameters();
                            pst3.close();
                            
                            System.out.println("Records inserted into userprofile of LoginDB for "+or.getEmail());
                                                       
                            }
                            else
                            {
                                System.out.println("Entry already Exist in users of LoginDB for - "+or.getEmail());
                                
                                
                                /*
                                *   write code for retrieving component details. split that details saprating by commma into
                                *   an arraylist.
                                *   if that arraylist contain payroll. than do nothing otherwise insert payroll into that
                                *   column.
                                */
                                
                                pst4 = connectLogin.prepareStatement("select componentreg from bgasuser where username='"+or.getEmail()+"'");
                                ResultSet rst4;
                                rst4 = pst4.executeQuery();
                                String components = null;
                                ArrayList<String> totalComponents = new ArrayList<String>();
                                
                                while(rst4.next()){
                                    components = rst4.getString(1);
                                }
                                
                                for (String componentName : components.split(",")){
                                    totalComponents.add(componentName);
                                    System.out.println("User is already Registered with "+componentName);
                                }
                                
                                boolean flag = totalComponents.contains("payroll");
                                if(flag){
                                    System.out.println("User is already Registered with payroll system");
                                    System.out.println("Do nothing in LoginDB");
                                }
                                else{
                                    System.out.println("User is first time Registering in Payroll SO insert 'payroll' in Componentreg field in LoginDB");
                                    
                                    components = components.concat(",payroll");
                                    pst5 = connectLogin.prepareStatement("update bgasuser set componentreg=? where username=?");
                                    pst5.setString(1, components);
                                    pst5.setString(2, or.getEmail());
                                    pst5.executeUpdate();
                                    pst5.clearParameters();
                                    pst5.close();
                                    
                                    System.out.println("payroll is inserted in bgas user in componentreg field");
                                    
                                }
                                
                            }
                            
                        }   
                    }
                    
                    /*
                    *   User already present in user_master table;
                    */
                    else{
                        System.out.println("Entry already Exist in user_master for - "+or.getEmail());
                    }
                    
                    int UserId = ud.getUserId(or.getEmail());
                    
                    pst6 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+UserId+"','"+4+"','"+or.getId()+"')");
                    pst6.executeUpdate();
                    pst6.clearParameters();
                    pst6.close();
                    
                    System.out.println("Records inserted into user_roles as Admin for "+or.getEmail());
                    
                    pst7 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+UserId+"','"+6+"','"+or.getId()+"')");
                    pst7.executeUpdate();
                    pst7.clearParameters();
                    pst7.close();
                    
                    System.out.println("Records inserted into user_roles as Employee for '"+or.getEmail()+"' and OrgId is :'"+or.getId()+"'" );
                    
                    pst8 = connection.prepareStatement("update org_profile set org_status = 1 where org_id='"+or.getId()+"'");
                    pst8.executeUpdate(); 
                    pst8.clearParameters();
                    pst8.close();
                    
                    System.out.println("Status is set to TRUE for orgnization where org id is : "+or.getId());
                    
                    new OrgConformationEmail().sendMail(or);
                    pst9 = connection.prepareStatement("delete from college_pending_status where org_code = '"+or.getId()+"'");
                    pst9.executeUpdate();
                    pst9.clearParameters();
                    pst9.close();
                    
                }
                else
                {
                    st = 0;
                }
            }
            connection.close();
            connectLogin.close();

            return null;
        }
        
        catch (SQLException e)
        {
            if (connection != null || connectLogin != null)
            {
                try
                {
                    connection.rollback();
                    connectLogin.rollback();
                } // rollback on error
                catch (SQLException i)
                {
                }
            }
            e.printStackTrace();
        } 
        finally
        {
            if (connection != null)
            {
                try
                {
                  //con.rollback();
                    connection.close();
                //    connectLogin.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    
    
    
   /**
    * 
    * Update Admin records 
    * @param admin
    * @return 
    * 
    */
    
    public Exception changePass(Org admin)
    {
        try
        {
            System.out.println("Admin ID : "+adminUserId+admin.getAdPassword());
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst; 
            pst = connection.prepareStatement("update admin_records set admin_pass = '"+admin.getAdPassword()+"' where user_id='"+adminUserId+"'and flag='"+1+"'");
            pst.executeUpdate();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    /**
     * 
     * Active Admin List/Records
     * @return 
     */
    
    public ArrayList<Org> activeAdminList()
    {
        try
        {
            ArrayList<Org> adminBean = new ArrayList<Org>();
            Connection connection = new CommonDB().getConnection(); 
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select * from admin_records");
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org admin = new Org();
                admin.setAdUserId(rst.getString(2)); 
                if(rst.getInt(4) == 1)
                {
                    admin.setStatus(true);
                    admin.setImgUrl("Active.png");
                }
                if(rst.getInt(4) == 0)
                {
                    admin.setStatus(false);
                    admin.setImgUrl("InActive.png");
                }
                admin.setDate(rst.getString(5)); 
                adminBean.add(admin); 
            }
            rst.close();
            pst.close();
            connection.close();
            return adminBean;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
   /**
    * Update Admin Status which Active or Inactive
    * @param admin
    * @return 
    * 
    */
    
    public Exception updateAdminStatus(ArrayList<Org> admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            int st;
            for(Org adm : admin)
            {
                System.out.println("ID : "+adm.getAdUserId());
                if(adm.isStatus() == true)
                {
                    st = 1;
                }
                else
                {
                    st = 0;
                }
                pst = cn.prepareStatement("update admin_records set flag = '"+st+"' where user_id='"+adm.getAdUserId()+"'");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    /**
     * Update Admin Email Status
     * @param admin
     * @return 
     * 
     */
    
    public Exception updateAdminEmailStatus(ArrayList<Org> admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            int st;
            for(Org adm : admin)
            {
                System.out.println("ID : "+adm.getAdUserId());
                if(adm.isStatus() == true)
                {
                    st = 1;
                }
                else
                {
                    st = 0;
                }
                pst = cn.prepareStatement("update admin_email_config set admin_email_status = '"+st+"' where admin_emailid='"+adm.getEmail()+"'");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    /**
     * Check Admin Id for user
     * @param admin
     * @return 
     * 
     */
    
    public Exception checkAdminId(AdminManagedBean admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select user_id from admin_records where user_id = '"+admin.getUserId()+"'");
            rst = pst.executeQuery();
            rst.next();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    /**
     * Insert the Admin Records of User Id and password
     * @param man
     * @return 
     * 
     */
    
    public Exception adminDB(Org man)
    {
        try
        {
            java.util.Date date = new java.util.Date();
            java.util.Date dat = new java.util.Date();
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("yy-MM-dd");
            
            /*int month = date.getMonth();
            int year = date.getYear();
            int day = date.getDate();*/
            String d = String.valueOf(date.getDate())+"-"+String.valueOf(date.getMonth())+"-"+String.valueOf(date.getDate());
            dat = (java.util.Date) dateFormat.parse(d);
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into admin_records(user_id,admin_pass,flag,add_date) values('"+man.getAdUserId()+"','"+man.getAdPassword()+"','"+0+"','"+new java.sql.Date(dat.getTime())+"')");
            pst.executeUpdate();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public boolean featchDetails(Org admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+admin.getAdUserId()+"' and admin_pass='"+admin.getAdPassword()+"' and flag='"+1+"'");
            rst = pst.executeQuery();
            if(rst.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    public int getTotalNoCollege()
    {
        try
        {
            int totalCollege=0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select count(user_name) as total from user_master");
            rst = pst.executeQuery();
            rst.next();
            totalCollege = rst.getInt("total");
            return totalCollege;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    public int getTotalNoAcCollege()
    {
        try
        {
            int totalCollege=0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select count(user_name) as total from user_master where flag = '"+1+"'");
            rst = pst.executeQuery();
            rst.next();
            totalCollege = rst.getInt("total");
            return totalCollege;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    public int getTotalDeAcNoCollege()
    {
        try
        {
            int totalCollege=0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select count(user_name) as total from user_master where flag='"+0+"'");
            rst = pst.executeQuery();
            rst.next();
            totalCollege = rst.getInt("total");
            return totalCollege;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    
   /**
    * Insert Admin Email Configuration 
    * @param org
    * @return 
    * 
    */
    
    public Exception saveEmailConfig(Org org)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into admin_email_config(admin_emailid,admin_config_pass,admin_email_status) values('"+org.getEmail()+"','"+org.getAdPassword()+"','"+0+"')");
            pst.executeUpdate();
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public ArrayList<Org> adminEmaiIdList()
    {
        try
        {
            ArrayList<Org> adminEmail = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = cn.prepareStatement("select * from admin_email_config");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org o = new Org();
                o.setEmail(rst.getString(2)); 
                 if(rst.getInt(4) == 1)
                {
                    o.setStatus(true);
                    o.setImgUrl("Active.png");
                }
                if(rst.getInt(4) == 0)
                {
                    o.setStatus(false);
                    o.setImgUrl("InActive.png");
                }
                
                adminEmail.add(o); 
            }
            
            return adminEmail;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
   /**
    * Update Org Profile
    * @param org
    * @return 
    * 
    */
    
    public Exception updateRow(Org org)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            cn.setAutoCommit(false); 
            pst = cn.prepareStatement("update org_profile set org_name = '"+org.getName()+"',org_email = '"+org.getEmail()+"',org_web = '"+org.getWeb()+"' where org_id = '"+org.getId()+"'");
            pst.executeUpdate();
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
  
    /**
     * update the Password for user for Specific organisation
     * @param org
     * @return 
     * 
     */
    
    public Exception updatePassword(Org org)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            PreparedStatement pst1;
            cn.setAutoCommit(false); 
            pst1 = cn.prepareStatement("update user_master set user_pass = '"+org.getAdPassword()+"' where user_name = '"+org.getEmail()+"'");
            pst = cn.prepareStatement("update org_profile set org_master_password = '"+org.getAdPassword()+"' where org_email = '"+org.getEmail()+"'");
            pst.executeUpdate();
            pst1.executeUpdate();
            pst.close();
            pst1.close();
            cn.commit();
            cn.close();
            new OrgConformationEmail().sendChangePasswordMail(org); 
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
   /**
    * 
    * Update Client Details
    * @param o
    * @return 
    * 
    */
    
    public Exception updateIpList(Org o)
    {
        try
        {
            int st;
            if(o.isIpStatus() == true)
            {
                st = 1;
            }
            else
            {
                st=0;
            }
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            cn.setAutoCommit(false); 
            pst = cn.prepareStatement("update client_details set flag = '"+st+"' where ip_address = '"+o.getIpAddress()+"'");
            pst.executeUpdate();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public boolean isPending()
    {
        try
        {
            boolean flag;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            cn.setAutoCommit(false);
            pst = cn.prepareStatement("select COUNT(org_pen_email) as cp from college_pending_status");
            rst = pst.executeQuery();
            if(rst.getInt("cp") > 0)
            {
                flag = true;
            }
            else
            {
                flag = false;
            }
            return flag;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    public ArrayList<Org> getSMTPDetails()
    {
        try
        {
            ArrayList<Org> smtpList = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false);
            PreparedStatement pst;
            pst = cn.prepareStatement("select * from admin_smtp_details");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                    Org o = new Org();
                    o.setSmtpServerName(rst.getString(2));
                    o.setSmtpPort(rst.getInt(3)); 
                    o.setFromEmailId(rst.getString(4)); 
                    o.setFromPassword(rst.getString(5));
                 if(rst.getInt(6) == 1)
                {
                    o.setSmtpStatus(true);
                    o.setImgUrl("Active.png");
                }
                if(rst.getInt(6) == 0)
                {
                    o.setSmtpStatus(false);
                    o.setImgUrl("InActive.png");
                }
                    o.setHostName(rst.getString(7)); 
                smtpList.add(o); 
            }
            rst.close();
            pst.close();
            cn.commit();
            return smtpList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
   
    /**
     * 
     * Insert Admin SMTP Details
     * 
     * @param o
     * @return 
     * 
     */
    
    public Exception saveSMTPDetails(Org o)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false);
            PreparedStatement pst,pst1;
            pst1 = cn.prepareStatement("update admin_smtp_details set smtp_status = '"+0+"' where smtp_status = '"+1+"'");
            pst1.executeUpdate();
            pst1.close();
            pst = cn.prepareStatement("insert into admin_smtp_details(smtp_name,smtp_port,auth_emailid,auth_password,smtp_status,smtp_host_name) values('"+o.getSmtpServerName()+"','"+o.getSmtpPort()+"','"+o.getFromEmailId()+"','"+o.getFromPassword()+"','"+1+"','"+o.getHostName()+"')");
            pst.executeUpdate();
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    /**
     * Update Admin SMTP Details
     * @param admin
     * @return 
     * 
     */
    
    public Exception updateAdminSMTP(ArrayList<Org> admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst = null;
            int st;
            for(Org adm : admin)
            {
                if(adm.isSmtpStatus() == true)
                {
                    st = 1;
                }
                else
                {
                    st = 0;
                }
                pst = cn.prepareStatement("update admin_smtp_details set smtp_status = '"+st+"' where auth_emailid='"+adm.getFromEmailId()+"'");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    public String getSMTPAuthDetails()
    {
        try
        {
            String authDetails;
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select smtp_port,auth_emailid,auth_password,smtp_host_name from admin_smtp_details where smtp_status = '"+1+"'");
            rst = pst.executeQuery();
            if(rst.next() == true)
            {
                authDetails = rst.getInt(1)+"-"+rst.getString(2)+"-"+rst.getString(3)+"-"+rst.getString(4); 
            }
            else
            {
                authDetails = "";
            }
            rst.close();
            pst.close();
            cn.commit();
            return authDetails;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
