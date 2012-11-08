<%@ page import="java.io.*,java.util.*, java.util.*,javax.servlet.*,com.myapp.struts.cataloguingDAO.*,com.myapp.struts.utility.*,com.myapp.struts.hbm.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<jsp:include page="/admin/header.jsp"/>
<%
  String library_id=(String)session.getAttribute("library_id");
  String sublibrary_id=(String)session.getAttribute("sublibrary_id");
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
   String filePath = AppPath.getPropertiesFilePath().toString();

   // Verify the content type
   String contentType = request.getContentType();
   if ((contentType.indexOf("multipart/form-data") >= 0)) {

      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("/home/"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      try{ 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);
         List log=new ArrayList();

         // Process the uploaded file items
         Iterator i = fileItems.iterator();

         out.println("<html>");
         out.println("<head>");
         out.println("<title>LibMS</title>");
         out.println("</head>");
         out.println("<body><div style='position:absolute; left:20%; top:20%'>");
         while ( i.hasNext () ) 
         {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () )	
            {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
            file = new File( filePath + 
            fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
            file = new File( filePath + 
            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write( file ) ;
            out.println("Upload activity completed"+"<br>");

            
            //Copy Data into Table
            FileInputStream fstream = new FileInputStream(AppPath.getPropertiesFilePath()+fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            strLine = br.readLine();
            System.out.println(strLine);
            //String[] RowHeader=UserLog.SeparateFileRow(strLine);
            String data[]=null;
             BibliographicEntryDAO bibdao=new BibliographicEntryDAO();
            
         DAO daoobj = new DAO();
         int row=0;
            begin:

            while ((strLine = br.readLine()) != null)   {
            row++;
                //separate Fields using delimeter
                data=UserLog.SeparateFileRow(strLine);
                System.out.println(data.length);
                                                String doc_type=data[0];
                                                String book_type=data[1];
                                                String title=data[3];
                                                String subtite=data[4];
                                                String alt_title=data[5];
                                                String state_resp=data[6];
                                                String main_entry=data[7];
                                                String added_entry=data[8];
                                                String add1=data[9];
                                                String add2=data[10];
                                                String add3=data[11];
                                                String pub_name=data[12];
                                                String pub_place=data[13];
                                                String pub_year=data[14];
                                                String call_no=data[16];
                                                String subject=data[17];
                                                String entry_lang=data[18];
                                                String isbn=data[19];
                                                String isbn13=data[20];
                                                String lcc_no=data[21];
                                                String edition=data[22];
                                                String no_of_copy=data[23];
                                                String coll1=data[24];
                                                String series_notes=data[25];
                                                String abstract1=data[26];
                                                String series=data[27];
                                                String acc_no=data[28];
                                                String vol_no=data[29];
                                                String loc=data[30];
                                                String shel_loc=data[31];
                                                String index=data[32];
                                                String no_page=data[33];
                                                String bind_type=data[34];
                                                String clref=data[35];
                                                String date_acq=data[2];



                                        //Skip Row if complusory field missing.
                                            if(UserLog.isValidDate(date_acq)==false)
                                            {
                                            log.add("Date_Acq not in correct format  missing at record no"+row);
                                           // session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(doc_type==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                           // session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(book_type==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                          //  session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(title==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                          //  session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(main_entry==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                          //  session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(state_resp==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                           // session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(no_of_copy==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                           // session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                                 if(AppPath.IsNumber(no_of_copy)==false)
                                            {
                                            log.add("No of Copy cannot be number at record no"+row);
                                          //  session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(call_no==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                           // session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(acc_no==null)
                                            {
                                            log.add("Mandatory fields  missing at record no"+row);
                                          //  session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                                            if(AppPath.IsNumber(acc_no)==false)
                                            {
                                            log.add("Accession No cannot be number at record no"+row);
                                          //  session.setAttribute("importlog", log);
                                            continue begin;
                                            }
                        //import in Database
                        if((call_no!=null && call_no.isEmpty()==false) && (isbn!=null &&  isbn.isEmpty()==false) && (title!=null && title.isEmpty()==false) && (main_entry!=null && main_entry.isEmpty()==false))
                        {

                            //Add Data in Bibliographic Details
                            BibliographicDetails bibobj;
                            BibliographicDetailsId bibid;
                            AccessionRegister accessionobj = new AccessionRegister();
                            AccessionRegisterId accessionidobj = new AccessionRegisterId();
                            DocumentDetails documentobj = new DocumentDetails();
                            DocumentDetailsId documentIdobj = new DocumentDetailsId();
                           
                                            //Check for Duplicate CallNO/Title/ISBN10/MainEntry Combination
                                            BibliographicDetails  uniTitle =(BibliographicDetails) daoobj.DuplicateTitleCallno(library_id,sublibrary_id,call_no,title,isbn,main_entry);
                                             System.out.println(uniTitle);
                                            if(uniTitle!=null)
                                            {
                                                //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,acc_no);
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+row);
                                            //        session.setAttribute("importlog", log);
                                                    continue begin;
                                                }}
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                 bibobj=(BibliographicDetails)uniTitle;

                                                 System.out.println(bibobj.getTitle()+"...................");

                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bibobj.getId().getBiblioId());
                                                accessionobj.setAccessionNo(acc_no);
                                                accessionobj.setVolumeNo(vol_no);
                                                accessionobj.setLocation(loc);
                                                accessionobj.setShelvingLocation(shel_loc);
                                                accessionobj.setIndexNo(index);
                                                accessionobj.setNoOfPages(no_page);
                                                accessionobj.setPhysicalDescription(coll1);
                                                accessionobj.setBindType(bind_type);
                                                accessionobj.setDateAcquired(date_acq);

                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bibobj.getId().getBiblioId());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(doc_type);
                                                documentobj.setBookType(book_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                documentobj.setTitle(title);
                                                documentobj.setSubtitle(subtite);
                                                documentobj.setAltTitle(alt_title);
                                                documentobj.setStatementResponsibility(state_resp);
                                                documentobj.setMainEntry(main_entry);
                                                documentobj.setAddedEntry(added_entry);
                                                documentobj.setAddedEntry1(add1);
                                                documentobj.setAddedEntry2(add2);
                                                documentobj.setAddedEntry3(add3);
                                                documentobj.setPublisherName(pub_name);
                                                documentobj.setPublicationPlace(pub_place);
                                                documentobj.setPublishingYear(pub_year);
                                                documentobj.setSubject(subject);
                                                documentobj.setEntryLanguage(entry_lang);
                                                documentobj.setIsbn10(isbn);
                                                documentobj.setIsbn13(isbn13);
                                                documentobj.setLccNo(lcc_no);
                                                documentobj.setEdition(edition);
                                                documentobj.setCollation1(coll1);
                                                documentobj.setNotes(series_notes);
                                                documentobj.setAbstract_(abstract1);
                                                documentobj.setAccessionNo(acc_no);
                                                documentobj.setCallNo(call_no);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(vol_no);
                                                documentobj.setLocation(loc);
                                                documentobj.setShelvingLocation(shel_loc);
                                                documentobj.setIndexNo(index);
                                                documentobj.setNoOfPages(no_page);
                                                documentobj.setBindType(bind_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                daoobj.insertAccessionRegister(accessionobj);
                                                daoobj.insertDocumentsDetails(documentobj);

                                            }
                                            else if(uniTitle==null)
                                            {
                                                 System.out.println("jjj");
                                                 //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,acc_no);
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+row);
                                              //          session.setAttribute("importlog", log);
                                                    continue begin;
                                                }}
                                                System.out.println("i am here");
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                int bib_id=bibdao.returnMaxBiblioId(library_id,sublibrary_id);
                                                System.out.println("i am here111");
                                                bibid = new BibliographicDetailsId();
                                                bibid.setBiblioId(bib_id);
                                                bibid.setLibraryId(library_id);
                                                bibid.setSublibraryId(sublibrary_id);
                                                bibobj = new BibliographicDetails();
                                                bibobj.setId(bibid);
                                                System.out.println("i am here112");
                                                bibobj.setDocumentType(doc_type);
                                                bibobj.setBookType(book_type);
                                                bibobj.setTitle(title);
                                                bibobj.setSubtitle(subtite);
                                                bibobj.setAltTitle(alt_title);
                                                bibobj.setStatementResponsibility(state_resp);
                                                bibobj.setMainEntry(main_entry);
                                                bibobj.setAddedEntry(added_entry);
                                                bibobj.setAddedEntry1(add1);
                                                bibobj.setAddedEntry2(add2);
                                                bibobj.setAddedEntry3(add3);
                                                bibobj.setPublisherName(pub_name);
                                                bibobj.setPublicationPlace(pub_place);
                                                bibobj.setPublishingYear(Integer.parseInt(pub_year));
                                                bibobj.setCallNo(call_no);
                                                System.out.println("i am here113");
                                                bibobj.setSubject(subject);
                                                bibobj.setEntryLanguage(entry_lang);
                                                bibobj.setIsbn10(isbn);
                                                bibobj.setIsbn13(isbn13);
                                                bibobj.setLccNo(lcc_no);
                                                bibobj.setEdition(edition);
                                                bibobj.setCollation1(coll1);
                                                bibobj.setNotes(series_notes);
                                                bibobj.setAbstract_(abstract1);
                                                bibobj.setSeries(series);
                                                bibobj.setDateAcquired(date_acq);
                                                 System.out.println("i am here114");
                                                 if(no_of_copy!=null && AppPath.IsNumber(no_of_copy))
                                                    bibobj.setNoOfCopies(Integer.parseInt(no_of_copy));

                                                System.out.println("i am here115");
                                                System.out.println("i am here acc");
                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bib_id);
                                                accessionobj.setAccessionNo(acc_no);
                                                accessionobj.setVolumeNo(vol_no);
                                                accessionobj.setLocation(loc);
                                                accessionobj.setShelvingLocation(shel_loc);
                                                accessionobj.setIndexNo(index);
                                                accessionobj.setNoOfPages(no_page);
                                                accessionobj.setPhysicalDescription(coll1);
                                                accessionobj.setBindType(bind_type);
                                                accessionobj.setDateAcquired(date_acq);
                                                System.out.println("i am here doc");
                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bib_id);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(doc_type);
                                                documentobj.setBookType(book_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                documentobj.setTitle(title);
                                                documentobj.setSubtitle(subtite);
                                                documentobj.setAltTitle(alt_title);
                                                documentobj.setStatementResponsibility(state_resp);
                                                documentobj.setMainEntry(main_entry);
                                                documentobj.setAddedEntry(added_entry);
                                                documentobj.setAddedEntry1(add1);
                                                documentobj.setAddedEntry2(add2);
                                                documentobj.setAddedEntry3(add3);
                                                documentobj.setPublisherName(pub_name);
                                                documentobj.setPublicationPlace(pub_place);
                                                documentobj.setPublishingYear(pub_year);
                                                documentobj.setSubject(subject);
                                                documentobj.setEntryLanguage(entry_lang);
                                                documentobj.setIsbn10(isbn);
                                                documentobj.setIsbn13(isbn13);
                                                documentobj.setLccNo(lcc_no);
                                                documentobj.setEdition(edition);
                                                documentobj.setCollation1(coll1);
                                                documentobj.setNotes(series_notes);
                                                documentobj.setAbstract_(abstract1);
                                                documentobj.setAccessionNo(acc_no);
                                                documentobj.setCallNo(call_no);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(vol_no);
                                                documentobj.setLocation(loc);
                                                documentobj.setShelvingLocation(shel_loc);
                                                documentobj.setIndexNo(index);
                                                documentobj.setNoOfPages(no_page);
                                                documentobj.setBindType(bind_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                //insert in three table jointly
                                                daoobj.insertImport(bibobj,accessionobj,documentobj);
                                            }
                        }
                        else if((call_no!=null && call_no.isEmpty()==false) && (isbn==null ||  isbn.isEmpty()==true) && (title!=null && title.isEmpty()==false) && (main_entry!=null && main_entry.isEmpty()==false))
                        {
                                           //Add Data in Bibliographic Details
                            BibliographicDetails bibobj;
                            BibliographicDetailsId bibid;
                            AccessionRegister accessionobj = new AccessionRegister();
                            AccessionRegisterId accessionidobj = new AccessionRegisterId();
                            DocumentDetails documentobj = new DocumentDetails();
                            DocumentDetailsId documentIdobj = new DocumentDetailsId();

                                            //Check for Duplicate CallNO/Title/ISBN10/MainEntry Combination
                                            List  uniTitle =(List<String>) daoobj.DuplicateTitleCallno(library_id,sublibrary_id,call_no,title,isbn,main_entry);
                                            if(uniTitle!=null) if(uniTitle.isEmpty()==false)
                                            {
                                                //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,acc_no);
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+row);
                                                //        session.setAttribute("importlog", log);
                                                    continue begin;
                                                }}
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                 bibobj=(BibliographicDetails)uniTitle.get(0);
                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bibobj.getId().getBiblioId());
                                                accessionobj.setAccessionNo(acc_no);
                                                accessionobj.setVolumeNo(vol_no);
                                                accessionobj.setLocation(loc);
                                                accessionobj.setShelvingLocation(shel_loc);
                                                accessionobj.setIndexNo(index);
                                                accessionobj.setNoOfPages(no_page);
                                                accessionobj.setPhysicalDescription(coll1);
                                                accessionobj.setBindType(bind_type);
                                                accessionobj.setDateAcquired(date_acq);

                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bibobj.getId().getBiblioId());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(doc_type);
                                                documentobj.setBookType(book_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                documentobj.setTitle(title);
                                                documentobj.setSubtitle(subtite);
                                                documentobj.setAltTitle(alt_title);
                                                documentobj.setStatementResponsibility(state_resp);
                                                documentobj.setMainEntry(main_entry);
                                                documentobj.setAddedEntry(added_entry);
                                                documentobj.setAddedEntry1(add1);
                                                documentobj.setAddedEntry2(add2);
                                                documentobj.setAddedEntry3(add3);
                                                documentobj.setPublisherName(pub_name);
                                                documentobj.setPublicationPlace(pub_place);
                                                documentobj.setPublishingYear(pub_year);
                                                documentobj.setSubject(subject);
                                                documentobj.setEntryLanguage(entry_lang);
                                                documentobj.setIsbn10(isbn);
                                                documentobj.setIsbn13(isbn13);
                                                documentobj.setLccNo(lcc_no);
                                                documentobj.setEdition(edition);
                                                documentobj.setCollation1(coll1);
                                                documentobj.setNotes(series_notes);
                                                documentobj.setAbstract_(abstract1);
                                                documentobj.setAccessionNo(acc_no);
                                                documentobj.setCallNo(call_no);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(vol_no);
                                                documentobj.setLocation(loc);
                                                documentobj.setShelvingLocation(shel_loc);
                                                documentobj.setIndexNo(index);
                                                documentobj.setNoOfPages(no_page);
                                                documentobj.setBindType(bind_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                daoobj.insertAccessionRegister(accessionobj);
                                                daoobj.insertDocumentsDetails(documentobj);

                                            }
                                            else
                                            {
                                                 //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,acc_no);
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+row);
                                                  //      session.setAttribute("importlog", log);
                                                    continue begin;
                                                }}
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                int bib_id=bibdao.returnMaxBiblioId(library_id,sublibrary_id);
                                                bibobj = new BibliographicDetails();
                                                bibid = new BibliographicDetailsId();
                                                bibid.setBiblioId(bib_id);
                                                bibid.setLibraryId(library_id);
                                                bibid.setSublibraryId(sublibrary_id);
                                                bibobj.setId(bibid);
                                                bibobj.setDocumentType(doc_type);
                                                bibobj.setBookType(book_type);
                                                bibobj.setTitle(title);
                                                bibobj.setSubtitle(subtite);
                                                bibobj.setAltTitle(alt_title);
                                                bibobj.setStatementResponsibility(state_resp);
                                                bibobj.setMainEntry(main_entry);
                                                bibobj.setAddedEntry(added_entry);
                                                bibobj.setAddedEntry1(add1);
                                                bibobj.setAddedEntry2(add2);
                                                bibobj.setAddedEntry3(add3);
                                                bibobj.setPublisherName(pub_name);
                                                bibobj.setPublicationPlace(pub_place);
                                                bibobj.setPublishingYear(Integer.parseInt(pub_year));
                                                bibobj.setCallNo(call_no);
                                                bibobj.setSubject(subject);
                                                bibobj.setEntryLanguage(entry_lang);
                                                bibobj.setIsbn10(isbn);
                                                bibobj.setIsbn13(isbn13);
                                                bibobj.setLccNo(lcc_no);
                                                bibobj.setEdition(edition);
                                                bibobj.setCollation1(coll1);
                                                bibobj.setNotes(series_notes);
                                                bibobj.setAbstract_(abstract1);
                                                bibobj.setSeries(series);
                                                bibobj.setDateAcquired(date_acq);
                                                bibobj.setNoOfCopies(Integer.parseInt(no_of_copy));

                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bib_id);
                                                accessionobj.setAccessionNo(acc_no);
                                                accessionobj.setVolumeNo(vol_no);
                                                accessionobj.setLocation(loc);
                                                accessionobj.setShelvingLocation(shel_loc);
                                                accessionobj.setIndexNo(index);
                                                accessionobj.setNoOfPages(no_page);
                                                accessionobj.setPhysicalDescription(coll1);
                                                accessionobj.setBindType(bind_type);
                                                accessionobj.setDateAcquired(date_acq);

                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bib_id);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(doc_type);
                                                documentobj.setBookType(book_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                documentobj.setTitle(title);
                                                documentobj.setSubtitle(subtite);
                                                documentobj.setAltTitle(alt_title);
                                                documentobj.setStatementResponsibility(state_resp);
                                                documentobj.setMainEntry(main_entry);
                                                documentobj.setAddedEntry(added_entry);
                                                documentobj.setAddedEntry1(add1);
                                                documentobj.setAddedEntry2(add2);
                                                documentobj.setAddedEntry3(add3);
                                                documentobj.setPublisherName(pub_name);
                                                documentobj.setPublicationPlace(pub_place);
                                                documentobj.setPublishingYear(pub_year);
                                                documentobj.setSubject(subject);
                                                documentobj.setEntryLanguage(entry_lang);
                                                documentobj.setIsbn10(isbn);
                                                documentobj.setIsbn13(isbn13);
                                                documentobj.setLccNo(lcc_no);
                                                documentobj.setEdition(edition);
                                                documentobj.setCollation1(coll1);
                                                documentobj.setNotes(series_notes);
                                                documentobj.setAbstract_(abstract1);
                                                documentobj.setAccessionNo(acc_no);
                                                documentobj.setCallNo(call_no);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(vol_no);
                                                documentobj.setLocation(loc);
                                                documentobj.setShelvingLocation(shel_loc);
                                                documentobj.setIndexNo(index);
                                                documentobj.setNoOfPages(no_page);
                                                documentobj.setBindType(bind_type);
                                                documentobj.setDateAcquired(date_acq);
                                                documentobj.setSeries(series);
                                                //insert in three table jointly
                                                daoobj.insertImport(bibobj,accessionobj,documentobj);
                                            }


                        }

            }
            //Close the input stream
            in.close();

            }
         }

                        if(log!=null)
                        {
                        for(int j=0;j<log.size();j++)
                        {
                          %><%=log.get(j)%><br>
                       <%}}

         out.println("</div></body>");
        out.println("</html>");
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }else{
      out.println("<html>");
      out.println("<head>");
      out.println("<title>LibMS</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>");
      out.println("</body>");
      out.println("</html>");
         

   }

%>



