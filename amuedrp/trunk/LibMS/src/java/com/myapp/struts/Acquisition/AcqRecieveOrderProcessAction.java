/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.hbm.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
 import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 * @author maqbool
 */
public class AcqRecieveOrderProcessAction extends org.apache.struts.action.Action {
 
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           AcqOrderManagementActionForm acqorder=(AcqOrderManagementActionForm)form;
 

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String vendor=acqorder.getVendor();

        List<String> acqvendor=AcquisitionDao.searchDoc6(library_id, sub_library_id);

ArrayList list1=new ArrayList();
for(int i=0;i<acqvendor.size();i++){
String v=acqvendor.get(i);
AcqBibliographyDetails acqv=new AcqBibliographyDetails();
acqv.setVendor(v);
list1.add(acqv);
}

System.out.println("#################"+list1.size());
         session.setAttribute("vendors", list1);

        return mapping.findForward("order");
    }
}

       



    
