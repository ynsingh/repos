


/*
 * ADVANCED SEARCH OPAC
 */

package com.myapp.struts.opac;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import  com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.utility.LoggerUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
public class AdvanceSearchAction extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String searchtext1[],searchtext2[],searchtext3[]; /* array of fields*/
    BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
     private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try
        {
            System.gc();
                int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
                String p1,p2,p3,yr1,yr2,cf1,cf2,cf3,cmbyr,db;
                String cnf1,cnf2,cnf3,c1,c2,c3;
                String sort;
                AdvanceSearchActionForm myForm = (AdvanceSearchActionForm)form;
                String lib_id= myForm.getCMBLib();
                String sub_lib = myForm.getCMBSUBLib();
                HttpSession session = request.getSession();
                request.setCharacterEncoding("UTF-8");
                session.removeAttribute("ResultSet");
                session.removeAttribute("ResultSet1");
                session.removeAttribute("simple_search_nor");
                //variables for phrases
                 p1=myForm.getTXTPHRASE1();
                 p2=myForm.getTXTPHRASE2();
                 p3=myForm.getTXTPHRASE3();
                 //variable for years
                 yr1=myForm.getTXTYR1().trim();
                 yr2=myForm.getTXTYR2().trim();
                 //variables for fields to be search in
                 cf1=myForm.getCMBFIELD1();
                 cf2=myForm.getCMBFIELD2();
                 cf3=myForm.getCMBFIELD3();
                 //variable for search type of year
                 cmbyr=myForm.getCMBYR();
                 //variable for selecting a document type
                 db=myForm.getCMBDB();
                 //variable for query connectors
                 cnf1=myForm.getCMBF1();
                 cnf2=myForm.getCMBF2();
                 cnf3=myForm.getCMBF3();
                 //variable for word connectors
                 c1=myForm.getCMB1();
                 c2=myForm.getCMB2();
                 c3=myForm.getCMB3();
                 //variable for sort
                 sort=myForm.getCMBSORT();

                 //CREATE SESSION TO SAVE FORM VALUE FOR PAGING
                    if(lib_id!=null)
                        session.setAttribute("advlib_id", lib_id);
                    if(sub_lib!=null)
                        session.setAttribute("advsub_lib", sub_lib);
                    if(p1!=null)
                        session.setAttribute("advp1", p1);
                    if(p2!=null)
                        session.setAttribute("advp2", p2);
                    if(p3!=null)
                        session.setAttribute("advp3", p3);
                    if(yr1!=null)
                        session.setAttribute("advyr1", yr1);
                    if(yr2!=null)
                        session.setAttribute("advyr2", yr2);
                    if(cf1!=null)
                        session.setAttribute("advcf1", cf1);
                    if(cf2!=null)
                        session.setAttribute("advcf2", cf2);
                    if(cf3!=null)
                        session.setAttribute("advcf3", cf3);
                    if(c1!=null)
                        session.setAttribute("advc1", c1);
                    if(c2!=null)
                        session.setAttribute("advc2", c2);
                    if(c3!=null)
                        session.setAttribute("advc3", c3);
                    if(sort!=null)
                        session.setAttribute("advsort", sort);
                    if(cmbyr!=null)
                        session.setAttribute("advcmbyr", cmbyr);
                    if(db!=null)
                        session.setAttribute("advdb", db);
                //RETERIVE DATA FROM SESSION WHEN NAVIGATE TO NEXT PAGE

            if(lib_id==null)
                lib_id=(String)session.getAttribute("advlib_id");

            if(sub_lib==null)
                sub_lib=(String)session.getAttribute("advsub_lib");
            if(yr1==null)
                yr1=(String)session.getAttribute("advyr1");
            if(yr2==null)
                yr2=(String)session.getAttribute("advyr2");
            if(cf1==null || cf1.isEmpty())
                cf1=(String)session.getAttribute("advcf1");
            if(cf2==null || cf2.isEmpty())
                cf2=(String)session.getAttribute("advcf2");
            if(cf3==null || cf3.isEmpty())
                cf3=(String)session.getAttribute("advcf3");
            if(c1==null || c1.isEmpty())
                c1=(String)session.getAttribute("advc1");
            if(c2==null || c2.isEmpty())
                c2=(String)session.getAttribute("advc2");
            if(c3==null || c3.isEmpty())
                c3=(String)session.getAttribute("advc3");
            if(p1==null || p1.isEmpty())
                p1=(String)session.getAttribute("advp1");
            if(p2==null || p2.isEmpty())
                p2=(String)session.getAttribute("advp2");
            if(p3==null || p3.isEmpty())
                p3=(String)session.getAttribute("advp3");
            if(db==null || db.isEmpty())
                db=(String)session.getAttribute("advdb");
            if(sort==null || sort.isEmpty())
                sort=(String)session.getAttribute("advsort");
            if(cmbyr==null || cmbyr.isEmpty())
                cmbyr=(String)session.getAttribute("advcmbyr");
            /*
                * Criteria for setting Variables to pass searching method
                * 1.Set variables for author,title,subject,other field in String Array by seperating word  (if connector is AND or OR )
                * 2.set variables for author,title,subject,other field in String Array at 0 postion (if connector is phrase)
                * 3.if any text box has no value at submit form  then assign Null corresponding to field
                */
               
                 /* conditions for author field  */
                 if(p1.equals("")||p1.equals(" "))
                 {
                     searchtext1=null;
                }
                else
                {
                    if(c1.equalsIgnoreCase("or")||c1.equalsIgnoreCase("and"))
                    {
                        searchtext1=p1.split(" ");
                    }
                    else         /*for Phrase*/
                    {
                        searchtext1[0]=p1;
                    }
                 }
                /* conditions for Title field  */
                if(p2.equals("")||p2.equals(" "))
                {
                    searchtext2=null;
                }
                else
                {
                    if(c2.equalsIgnoreCase("or")||c2.equalsIgnoreCase("and"))
                    {
                        searchtext2=p2.split(" ");
                    }
                    else         /*for Phrase*/
                    {
                        searchtext2[0]=p2;
                    }
                }
                /* conditions for Subject field  */
                if(p3.equals(""))
                {
                    searchtext3=null;
                }
                else
                {
                    if(cnf3.equalsIgnoreCase("or")||cnf3.equalsIgnoreCase("and"))
                    {
                        searchtext3=p3.split(" ");
                    }
                    else         /*for Phrase*/
                    {
                        searchtext3[0]=p3;
                    }
                }
            OpacSearchDAO opac = new OpacSearchDAO();
            List advance_search_list=new ArrayList();
            List<BibliographicDetails> advance_search_list1=new ArrayList();


            if(myForm.getCheckbox()==null)
                myForm.setCheckbox((String)session.getAttribute("advcheckbox"));

            if(myForm.getCheckbox().equals("Checked"))
            {
                 if(sort.equalsIgnoreCase("mainEntry"))
                sort="main_entry";
                if(sort.equalsIgnoreCase("publisherName"))
                sort="publisher_name";

                session.setAttribute("advcheckbox",myForm.getCheckbox() );
                advance_search_list=opac.advanceLangSearch(lib_id, sub_lib, searchtext1, c1, searchtext2, c2,searchtext3,c3,cnf1,cnf2,cnf3,cf1,cf2,cf3,db,sort,yr1,yr2,myForm.getLanguage().toUpperCase(),pageno,cmbyr);
                  int size=opac.getSize();
                session.setAttribute("simple_search_nor",size);
                    request.setAttribute("from", pageno*100);
                if(advance_search_list1.size()<100)
                    request.setAttribute("to", (pageno*100)+advance_search_list.size());
                else
                    request.setAttribute("to", (pageno*100)+100);
                 session.setAttribute("ResultSet1", advance_search_list);
            }
            else
            {
                 session.setAttribute("advcheckbox",myForm.getCheckbox());
                 System.out.println(yr1+yr2+"abc"+cmbyr);
                 advance_search_list1=opac.advanceSearch(lib_id, sub_lib, searchtext1, c1, searchtext2, c2,searchtext3,c3,cnf1,cnf2,cnf3,cf1,cf2,cf3,db,sort,yr1,yr2,pageno,cmbyr);
                  int size=opac.getSize();
                session.setAttribute("simple_search_nor",size);
                    request.setAttribute("from", pageno*100);
                if(advance_search_list1.size()<100)
                    request.setAttribute("to", (pageno*100)+advance_search_list1.size());
                else
                    request.setAttribute("to", (pageno*100)+100);
                 session.setAttribute("ResultSet", advance_search_list1);
            }
        }
       catch(Exception e)
       {
       log4j.error(e);
       }
            
        return mapping.findForward(SUCCESS);
    }
}
