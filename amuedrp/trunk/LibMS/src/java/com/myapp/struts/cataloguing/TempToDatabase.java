/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.hbm.AccessionRegister;
import com.myapp.struts.hbm.AccessionRegisterId;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.*;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.DocumentDetailsId;
import com.myapp.struts.hbm.TempExcellImport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khushnood
 */
class MyExcep extends Exception{
String Mess;
MyExcep(String field ){
this.Mess=field;

}
}

public class TempToDatabase extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
String library_id,sublibrary_id;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */

    public boolean Datatype(String t){
    char d=t.charAt(0);
    
    
    switch(d){
        case 48:
            case 49:
                case 50:
                    case 51:
                        case 52:
                            case 53:
                              case 54:
                                case 55:
                                    case 56:
                                        case 57:
                                            return true;
                                            
        default:
            return false;
    }





    }
    

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
        TempExcellImport temobj = new TempExcellImport();
        TempExcellImport temobj2 = new TempExcellImport();
        BibliographicDetails bibobj = new BibliographicDetails();
        BibliographicDetailsId bibid = new BibliographicDetailsId();
        AccessionRegister accessionobj = new AccessionRegister();
        AccessionRegisterId accessionidobj = new AccessionRegisterId();
        DocumentDetails documentobj = new DocumentDetails();
        DocumentDetailsId documentIdobj = new DocumentDetailsId();
        BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        DAO daoobj = new DAO();
        int distinctTitleSize = 0;

        try {
              ArrayList<Integer> biblist=new ArrayList<Integer>();
            distinctTitleSize = daoobj.distintTitle(library_id,sublibrary_id).size();
          
           
            String distinctTitleArray[] = new String[distinctTitleSize];
            List distinctCallNo= (List<String>)daoobj.distintTitle(library_id,sublibrary_id);
             System.out.println("FFFFFFFFFFFFFFFFF"+ distinctCallNo.size());
            for (int i = 0; i < distinctTitleSize; i++) {
         

            for(int k=0;k<distinctCallNo.size();k++){
            
                    List  uniTitle =(List<String>) daoobj.distintTitleCallno(library_id,sublibrary_id,distinctCallNo.get(i).toString());
                    if(uniTitle.size()>1){

                    throw new MyExcep("CAllNo and Title are not uniques combination");

                    }
                    distinctTitleArray[i] = uniTitle.get(0).toString();

            }
             
            }
       

            if (uploadForm.getButton().equals("Import Data")) {
                for (int j = 0; j < distinctTitleSize; j++) {
                    System.out.println(distinctTitleArray[j]+"   "+distinctCallNo.get(j).toString());
                    List list = daoobj.AllrecordForGivenTitle(distinctTitleArray[j],distinctCallNo.get(j).toString());
               
                            for(int i=0;i<list.size();i++)
                            {
                    temobj = (TempExcellImport) list.get(i);
                    System.out.println("+++++++++++++++distint title arry++++" + distinctTitleArray[j]);

//BibliographicDetails ob=DAO.searchBiblio(library_id,sublibrary_id,distinctTitleArray[j],distinctCallNo.get(j).toString());
//if(ob==null)
{         bibid.setBiblioId(bibdao.returnMaxBiblioId(temobj.getLibraryId(),temobj.getSublibraryId()));
                    bibid.setLibraryId(temobj.getLibraryId());
                    bibid.setSublibraryId(temobj.getSublibraryId());
                    bibobj.setId(bibid);
                    bibobj.setDocumentType(temobj.getDocumentType());
                    bibobj.setBookType(temobj.getBookType());
                    bibobj.setAccessionType(temobj.getAccessionType());
                    bibobj.setTitle(temobj.getTitle());
                    bibobj.setSubtitle(temobj.getSubtitle());
                    bibobj.setAltTitle(temobj.getAltTitle());
                    bibobj.setStatementResponsibility(temobj.getStatementResponsibility());
                    bibobj.setMainEntry(temobj.getMainEntry());
                    bibobj.setAddedEntry(temobj.getAddedEntry());
                    bibobj.setAddedEntry1(temobj.getAddedEntry1());
                    bibobj.setAddedEntry2(temobj.getAddedEntry2());
                    bibobj.setAddedEntry3(temobj.getAddedEntry3());
                    bibobj.setCallNo(temobj.getCallNo());
                    if(temobj.getPartsNo()!=null)
                    {
                       String t=temobj.getPartsNo();
                        boolean type=Datatype(t);
                        System.out.println(t+"........"+type);
                        if(type==false)
                        throw new MyExcep("Parts No");
                    if(Integer.parseInt(temobj.getPartsNo())<0)
                    {

                    //delete all record;

                    bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                    boolean result= DAO.TruncateTempTable(temobj.getLibraryId(),temobj.getSublibraryId());
                    System.out.println(result);

                    request.setAttribute("msg1", "Data Type Mismatch in Parts No Field Cannot Import Data Try Again");
                    return mapping.findForward(SUCCESS);

                    }else{
                    bibobj.setPartsNo(Integer.parseInt(temobj.getPartsNo()));
                    }
                    }
                    bibobj.setSubject(temobj.getSubject());
                    bibobj.setEntryLanguage(temobj.getEntryLanguage());
                    if(temobj.getIsbn10()!=null)
                    { BibliographicDetails bibob=bibdao.search1Isbn10(temobj.getIsbn10(),temobj.getLibraryId(),temobj.getSublibraryId());
                      if(bibob!=null)
                      {
                          bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                       boolean result=DAO.TruncateTempTable(temobj.getLibraryId().trim(),temobj.getSublibraryId().trim());
                       System.out.println(result);

                       request.setAttribute("msg1", "ISBN10 already exist cannot import"+temobj.getIsbn10());
                        return mapping.findForward(SUCCESS);
                        //break;

                      }


                    }

                    bibobj.setIsbn10(temobj.getIsbn10());
                    bibobj.setIsbn13(temobj.getIsbn13());
                    bibobj.setLccNo(temobj.getLccNo());
                    bibobj.setEdition(temobj.getEdition());
                    if(temobj.getNoOfCopies()!=null)
                    {
                        String t=temobj.getNoOfCopies();
                        boolean type=Datatype(t);
                        System.out.println(t+"........"+type);
                        if(type==false)
                        throw new MyExcep("No of Copies");
                    if(Integer.parseInt(temobj.getNoOfCopies())<0)
                    {

                    //delete all record;

    bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                     //   DAO.TruncateTempTable();


                        
                        request.setAttribute("msg1", "Data Type Mismatch in No of Copies Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                    bibobj.setNoOfCopies(Integer.parseInt(temobj.getNoOfCopies()));
                    }
                    }

                    bibobj.setAuthorName(temobj.getAuthorName());
                    bibobj.setGuideName(temobj.getGuideName());
                    bibobj.setUniversityFaculty(temobj.getUniversityFaculty());
                    bibobj.setDegree(temobj.getDegree());
                    bibobj.setSubmittedOn(temobj.getSubmittedOn());
                    bibobj.setAcceptanceYear(temobj.getAcceptanceYear());
                    bibobj.setCollation1(temobj.getCollation1());
                    bibobj.setNotes(temobj.getNotes());
                    bibobj.setAbstract_(temobj.getAbstract_());
                    bibobj.setAddress(temobj.getAddress());
                    bibobj.setState1(temobj.getState1());
                    bibobj.setCountry(temobj.getCountry());
                    bibobj.setEmail(temobj.getEmail());
                    bibobj.setFrmrFrq(temobj.getFrmrFrq());
                    bibobj.setFrqDate(temobj.getFrqDate());
                    bibobj.setIssn(temobj.getIssn());

                    daoobj.insertBibligraphicDetails(bibobj);
                    biblist.add(bibdao.returnMaxBiblioId(temobj.getLibraryId(),temobj.getSublibraryId()));
             
}


                }

                }
                // for accession register
                for (int j = 0; j < distinctTitleSize; j++) {

                    BibliographicDetails obj=daoobj.getTitle(library_id,sublibrary_id,Integer.parseInt(biblist.get(j).toString()));
                    List<TempExcellImport> list=daoobj.getTempRecord(library_id,sublibrary_id,obj.getTitle(),obj.getCallNo());
                    
                    
                
                    System.out.println("*****************list size  vvvvvvvvvvvvvvvvvvvv:::::::" + list.size());
                    for (int k = 0; k < list.size(); k++) {
                        temobj2 = (TempExcellImport) list.get(k);
                        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG" + temobj2.getTitle() + "dsffffff" + temobj2.getSno());
                        
                           
                            accessionidobj.setRecordNo(bibdao.returnMaxRecord(temobj2.getLibraryId(), temobj2.getSublibraryId()));
                            accessionidobj.setLibraryId(library_id);
                            accessionidobj.setSublibraryId(sublibrary_id);
                            accessionobj.setId(accessionidobj);
                            
            accessionobj.setBiblioId(obj.getId().getBiblioId());
                             if(temobj2.getAccessionNo()!=null)
                    { DocumentDetails  bibob=bibdao.searchAccession(temobj2.getLibraryId(),temobj2.getSublibraryId(),temobj2.getAccessionNo());
                      if(bibob!=null)
                      {
                               bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                     //   DAO.TruncateTempTable();

                       request.setAttribute("msg1", "Accession No already exist cannot import"+temobj2.getAccessionNo());
                        return mapping.findForward(SUCCESS);


                      }


                    }




                            accessionobj.setAccessionNo(temobj2.getAccessionNo());
                            accessionobj.setVolumeNo(temobj2.getVolumeNo());
                            accessionobj.setLocation(temobj2.getLocation());
                            accessionobj.setShelvingLocation(temobj2.getShelvingLocation());
                            accessionobj.setIndexNo(temobj2.getIndexNo());
                            accessionobj.setNoOfPages(temobj2.getNoOfPages());
                            accessionobj.setPhysicalWidth(temobj2.getPhysicalWidth());

                            accessionobj.setBindType(temobj2.getPhysicalForm());
                            accessionobj.setPhysicalDescription(temobj2.getPhysicalDescription());
                            accessionobj.setPhysicalForm(temobj2.getPhysicalForm());
                            accessionobj.setColour(temobj2.getColour());

                            daoobj.insertAccessionRegister(accessionobj);
                        
                        
                            documentIdobj.setDocumentId(bibdao.returnMaxDocumentId(temobj2.getLibraryId(), temobj2.getSublibraryId()));
                            documentIdobj.setLibraryId(temobj2.getLibraryId());
                            documentIdobj.setSublibraryId(temobj2.getSublibraryId());

                            documentobj.setId(documentIdobj);
                            

                            documentobj.setBiblioId(obj.getId().getBiblioId());
                            documentobj.setDocumentType(temobj2.getDocumentType());
                            documentobj.setBookType(temobj2.getBookType());
                            documentobj.setAccessionType(temobj2.getAccessionType());
                            documentobj.setDateAcquired(temobj2.getDateAcquired());
                            documentobj.setTitle(temobj2.getTitle());
                            documentobj.setSubtitle(temobj2.getSubtitle());
                            documentobj.setAltTitle(temobj2.getAltTitle());
                            documentobj.setStatementResponsibility(temobj2.getStatementResponsibility());
                            documentobj.setMainEntry(temobj2.getMainEntry());
                            documentobj.setAddedEntry1(temobj2.getAddedEntry1());
                            documentobj.setAddedEntry2(temobj2.getAddedEntry2());
                            documentobj.setAddedEntry3(temobj2.getAddedEntry3());
                            documentobj.setPublisherName(temobj2.getPublisherName());
                            documentobj.setPublicationPlace(temobj2.getPublicationPlace());
                            documentobj.setPublishingYear(temobj2.getPublishingYear());
                        if(temobj2.getPartsNo()!=null)
                        {
                       String t=temobj2.getPartsNo();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("Parts No");

                        if(Integer.parseInt(temobj2.getPartsNo())<0)
                            {

                    //delete all record;
      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                    //    DAO.TruncateTempTable();




                        request.setAttribute("msg1", "Data Type Mismatch in PartNo Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                      documentobj.setPartsNo(Integer.parseInt(temobj2.getPartsNo()));
                    }

}
                            documentobj.setSubject(temobj2.getSubject());
                            documentobj.setEntryLanguage(temobj2.getEntryLanguage());
                            documentobj.setIsbn10(temobj2.getIsbn10());
                            documentobj.setIsbn13(temobj2.getIsbn13());
                            documentobj.setLccNo(temobj2.getLccNo());
                            documentobj.setEdition(temobj2.getEdition());

if(temobj2.getNoOfCopies()!=null){
     String t=temobj2.getNoOfCopies();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("No of Copies in Document Details");
                           if(Integer.parseInt(temobj2.getNoOfCopies())<0)
                    {

                    //delete all record;

      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                 //       DAO.TruncateTempTable();



                        request.setAttribute("msg1", "Data Type Mismatch in No of Copies  Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                     documentobj.setNoOfCopies(Integer.parseInt(temobj2.getNoOfCopies()));
                    }

}

                         
                            documentobj.setAuthorName(temobj2.getAuthorName());
                            documentobj.setGuideName(temobj2.getGuideName());
                            documentobj.setUniversityFaculty(temobj2.getUniversityFaculty());
                            documentobj.setDegree(temobj2.getDegree());
                            documentobj.setSubmittedOn(temobj2.getSubmittedOn());
                            documentobj.setAcceptanceYear(temobj2.getAcceptanceYear());
                            documentobj.setCollation1(temobj2.getCollation1());
                            documentobj.setNotes(temobj2.getNotes());
                            documentobj.setAbstract_(temobj2.getAbstract_());
                            documentobj.setAddress(temobj2.getAddress());
                            documentobj.setState1(temobj2.getState1());
                            documentobj.setCountry(temobj2.getCountry());
                            documentobj.setEmail(temobj2.getEmail());
                            documentobj.setFrmrFrq(temobj2.getFrmrFrq());
                            documentobj.setFrqDate(temobj2.getFrmrFrq());
                            documentobj.setIssn(temobj2.getIssn());
                            documentobj.setVolumeLocation(temobj2.getVolumeLocation());
if(temobj2.getProductionYear()!=null){
     String t=temobj2.getProductionYear();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("Productuion Year");


                           if(Integer.parseInt(temobj2.getProductionYear())<0)
                    {

                    //delete all record;

      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                   //     DAO.TruncateTempTable();



                        request.setAttribute("msg1", "Data Type Mismatch in Pushing Year  Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                     documentobj.setProductionYear(Integer.parseInt(temobj2.getProductionYear()));
                    }
}
                            documentobj.setSource1(temobj2.getSource1());
                            documentobj.setDuration(temobj2.getDuration());
                            documentobj.setSeries(temobj2.getDuration());
                            documentobj.setPhysicalForm(temobj2.getPhysicalForm());
                            documentobj.setColour(temobj2.getColour());
                            documentobj.setTypeOfDisc(temobj2.getTypeOfDisc());
                            documentobj.setAccessionNo(temobj2.getAccessionNo());

                       if(temobj2.getRecordNo()!=null){

                          String t=temobj2.getRecordNo();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("Record No");

                               if(Integer.parseInt(temobj2.getRecordNo())<0)
                    {

                    //delete all record;
      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                   //     DAO.TruncateTempTable();




                        request.setAttribute("msg1", "Data Type Mismatch in Record No  Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                     documentobj.setRecordNo(Integer.parseInt(temobj2.getRecordNo()));
                    }
                       }
                            documentobj.setCallNo(temobj2.getCallNo());
                            documentobj.setVolumeNo(temobj2.getVolumeNo());
                            documentobj.setLocation(temobj2.getLocation());
                            documentobj.setShelvingLocation(temobj2.getShelvingLocation());
                            documentobj.setIndexNo(temobj2.getIndexNo());
                            documentobj.setNoOfPages(temobj2.getNoOfPages());
                            documentobj.setPhysicalWidth(temobj2.getPhysicalWidth());
                            documentobj.setBindType(temobj2.getBindType());



                        
                        
                        daoobj.insertDocumentsDetails(documentobj);
                          
                    }

                }
                request.setAttribute("msg2", "data has been successfully added");
                return mapping.findForward(SUCCESS);
            }
        } catch (MyExcep e) {
            request.setAttribute("msg1", "Data Type Mismatch cannot import"+e.Mess);
            mapping.findForward("failure");
        }

        return mapping.findForward(SUCCESS);
    }
}
