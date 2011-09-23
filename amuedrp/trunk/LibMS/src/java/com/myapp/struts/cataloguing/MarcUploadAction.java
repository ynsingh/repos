/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.MarcHibDAO;
import com.myapp.struts.hbm.BiblioTempId;
import com.myapp.struts.hbm.BiblioTemp;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
/**
 *
 * @author EdRP-05
 */
public class MarcUploadAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   MarcHibDAO mhd=new MarcHibDAO();
   BiblioTemp bb=new BiblioTemp();
   BiblioTempId bbid=new BiblioTempId();
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StrutsUploadForm nsaf=(StrutsUploadForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
/*
 * Part1:::This Part is used to browse .mrc file and write the data to database
 */
           System.out.println("File       :"+nsaf.getExcelFile().getInputStream().toString());
             FormFile gg=nsaf.getExcelFile();
         InputStream in= nsaf.getExcelFile().getInputStream();
        //  InputStream in = new FileInputStream("/home/Edrp-04/Desktop/record.mrc");
            MarcReader reader = new MarcStreamReader(in);
            while (reader.hasNext()) {
                 Record record = reader.next();
                 System.out.println(record.toString());
              List<DataField> field = record.getDataFields();
              List<ControlField> cfield=record.getControlFields();
              Integer biblio_id = mhd.returnMaxBiblioIdTemp(library_id, sub_library_id);
//             Leader ld=record.getLeader();
              for(int u=0;u<cfield.size();u++){
              String tag=cfield.get(u).getTag();
            bbid.setBibId(biblio_id);
              bbid.setLibraryId(library_id);
            bbid.setMarctag(tag);
            bb.setId(bbid);
            bb.setSublibraryId(sub_library_id);
               bb.setIndicator1(null);
               bb.setIndicator2(null);
              String data=cfield.get(u).getData();
              bb.set$a(data);
             mhd.inserttemp(bb);
//             bb=new BiblioTemp();
//             bbid=new BiblioTempId();
              }
              for(int u=0;u<field.size();u++){
              String tag=field.get(u).getTag();
            bbid.setBibId(biblio_id);
              bbid.setLibraryId(library_id);
            bbid.setMarctag(tag);
              char ind1 = field.get(u).getIndicator1();
              char ind2 = field.get(u).getIndicator2();
              if(!StringUtils.isBlank(String.valueOf(ind1))){
               bb.setIndicator1(ind1);
              }
              if(!StringUtils.isBlank(String.valueOf(ind2))){
               bb.setIndicator2(ind2);
              }
bb.setId(bbid);
bb.setSublibraryId(sub_library_id);
    System.out.println("Tag: " + tag + " Indicator 1: " + ind1 + " Indicator 2: " + ind2);

    List subfields = field.get(u).getSubfields();
    Iterator i = subfields.iterator();
    while (i.hasNext()) {
        Subfield subfield = (Subfield) i.next();
	char code = subfield.getCode();
	String data = subfield.getData();
                       switch(code)
                       {
                          case 'a':
                                bb.set$a(data);
                                    break;
                           case 'b':
                               bb.set$b(data);
                               break;
                           case 'c':
                                bb.set$c(data);
                                    break;
                           case 'd':
                               bb.set$d(data);
                               break;
                           case 'e':
                                bb.set$e(data);
                                    break;
                           case 'f':
                               bb.set$f(data);
                               break;
                           case 'g':
                                bb.set$g(data);
                                    break;
                           case 'h':
                               bb.set$h(data);
                               break;
                           case 'i':
                                bb.set$i(data);
                                    break;
                           case 'j':
                               bb.set$j(data);
                               break;
                          case 'k':
                                bb.set$k(data);
                                    break;
                           case 'l':
                               bb.set$l(data);
                               break;
                           case 'm':
                                bb.set$m(data);
                                    break;
                           case 'n':
                               bb.set$n(data);
                               break;
                           case 'o':
                                bb.set$o(data);
                                    break;
                           case 'p':
                               bb.set$p(data);
                               break;
                           case 'q':
                                bb.set$q(data);
                                    break;
                           case 'r':
                               bb.set$r(data);
                               break;
                           case 's':
                                bb.set$s(data);
                                    break;
                           case 't':
                               bb.set$t(data);
                               break;
                           case 'u':
                                bb.set$u(data);
                                    break;
                           case 'v':
                               bb.set$v(data);
                               break;
                           case 'w':
                                bb.set$w(data);
                                    break;
                           case 'x':
                               bb.set$x(data);
                               break;
                           case 'y':
                                bb.set$y(data);
                                    break;
                           case 'z':
                               bb.set$z(data);
                               break;
                           case '0':
                                bb.set$0(data);
                                    break;
                           case '1':
                               bb.set$1(data);
                               break;
                           case '2':
                                bb.set$2(data);
                                    break;
                           case '3':
                               bb.set$3(data);
                               break;
                           case '4':
                                bb.set$4(data);
                                    break;
                           case '5':
                               bb.set$5(data);
                               break;
                           case '6':
                                bb.set$6(data);
                                    break;
                           case '7':
                               bb.set$7(data);
                               break;
                           case '8':
                                bb.set$8(data);
                                    break;
                           case '9':
                               bb.set$9(data);
                               break;
                            default:

                       }

	System.out.println("Subfield code: " + code + " Data element: " + data);
    }
                     mhd.inserttemp(bb);
             bb=new BiblioTemp();
             bbid=new BiblioTempId();
              }
            }

        return mapping.findForward(SUCCESS);
    }
}
