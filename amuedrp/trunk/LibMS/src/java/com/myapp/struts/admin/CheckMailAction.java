///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.myapp.struts.admin;
//
//import com.myapp.struts.utility.pathConversion;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//import net.sf.jasperreports.engine.export.JRHtmlExporter;
//import java.sql.*;
//import java.io.*;
//import java.util.*;
//import javax.servlet.http.HttpSession;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.util.JRLoader;
// import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import com.myapp.struts.utility.*;
//import com.myapp.struts.utility.Email;
//
//
//
//
///**
// *
// * @author edrp02
// */
//public class CheckMailAction extends org.apache.struts.action.Action {
//   private final ExecutorService executor=Executors.newFixedThreadPool(1);
//  Email obj;
//    boolean result;
//    /* forward name="success" path="" */
//    private static final String SUCCESS = "success";
//    public static final String REPORT_DIRECTORY = "reports";
//    Connection connection=null;
//    Statement statement=null;
//    ResultSet resultSet=null;
////Library library=null;
//
//
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//            List list=null;
//            ResultSet rs=null;
//            JRBeanCollectionDataSource dataSource=null;
//           String path = servlet.getServletContext().getRealPath("/");
//       int x=0;
//                  String path = servlet.getServletContext().getRealPath("/");
//              obj=new Email(path,staffobj.getEmailId(),password,"Approval of request for library Registration","Your request for Library registration has been Successfully Approved .\n User ID :"+login_id+"\n Password :"+password+"\n","\n\nDear "+logobj.getUserName()+",\n","With Regards\nWebAdmin\nLibMS");
//
//            executor.submit(new Runnable() {
//
//                public void run() {
//                 x=obj.send();
//                }
//            });
//
// if (x==1)
//        {
//        response.setContentType("application/xml");
//        response.getWriter().write("Email Verified OK");
//
//        }
//
//
//
//   }
//}
