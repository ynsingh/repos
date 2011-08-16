package com.myapp.struts.cataloguing;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
/**
 * This Bean Form is used to get and set the bibliographic details of documents irrespective of the type of document.
 * All the document type fields are defined here.
 * Accession No, Call No, Record No, Location are for holding management.
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailEntryActionForm extends org.apache.struts.validator.ValidatorForm {

    private int biblio_id;
    private String library_id;
    private String sublibrary_id;
    private String document_type;
    private String accession_type;//For retrosepctive document it is 'old' and for new document it is 'new'
    private String date_acquired;
    private int record_no;
    private String button;
    private String search_keyword;
    private String search_by;
    private String sort_by;
//  Common Entry
    private String title;
    private String subtitle;
    private String alt_title;
    private String statement_responsibility;
    private String main_entry;
    private String added_entry;
    private String added_entry0;
    private String added_entry1;
    private String added_entry2;
    private String publisher_name;
    private String publication_place;
    private String publishing_year;
    private String call_no;
    private String accession_no;
    private int parts_no; //No of parts
    private String volume_no; // Volume no if document is in parts
    private String subject;
    private String language;
    private String location;
    private String shelving_location;
//  Book
    private String isbn10;
    private String isbn13;
    private String LCC_no;
    private String edition;
    private String index_no;
    private String no_of_pages;
    private String physical_width;
    private String ser_note;
    private String bind_type;
    private String price;
    private int no_of_copies;
    private String book_type;
//  Thesis
    private String author_name;
    private String guide_name;//teacher name for thesis
    private String university_faculty;//thesis
    private String degree;
    private String submitted_on;
    private String acceptance_year;
    private String collation;
    private String notes;
    private String thesis_abstract;
//  Bound Volume
    private String address;
    private String state;
    private String country;
    private String email;
    private String frmr_frq;
    private String frq_date;
    private String issn;
    private String volume_location;
//  CD Entry
    private int production_year;
    private String source;
    private String duration;
    private String series;
    private String physical_form;
    private String colour;
    private String type_of_disc;
    private String physical_desc;
//  Digital File Type
    private String file_type;// pdf,doc,rtf
    //Language Change
//  Common Entry
    private String title1;
    private String subtitle1;
    private String alt_title1;
    private String statement_responsibility1;
    private String main_entry1;
    private String added_entryl;
    private String added_entry01;
    private String added_entry11;
    private String added_entry21;
    private String publisher_name1;
    private String publication_place1;
    private String publishing_year1;
    private String call_no1;
    private String subject1;
//  Book
    private String isbn101;
    private String isbn131;
    private String LCC_no1;
    private String edition1;
   private String ser_note1;
   private String notes1;
   private String checkbox;

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }
      private String thesis_abstract1;

    public String getThesis_abstract1() {
        return thesis_abstract1;
    }

    public void setThesis_abstract1(String thesis_abstract1) {
        this.thesis_abstract1 = thesis_abstract1;
    }

    public String getLCC_no1() {
        return LCC_no1;
    }

    public void setLCC_no1(String LCC_no1) {
        this.LCC_no1 = LCC_no1;
    }

    public String getAdded_entry01() {
        return added_entry01;
    }

    public void setAdded_entry01(String added_entry01) {
        this.added_entry01 = added_entry01;
    }

    public String getAdded_entry11() {
        return added_entry11;
    }

    public void setAdded_entry11(String added_entry11) {
        this.added_entry11 = added_entry11;
    }

    public String getAdded_entry21() {
        return added_entry21;
    }

    public void setAdded_entry21(String added_entry21) {
        this.added_entry21 = added_entry21;
    }

    public String getAdded_entryl() {
        return added_entryl;
    }

    public void setAdded_entryl(String added_entryl) {
        this.added_entryl = added_entryl;
    }

    public String getAlt_title1() {
        return alt_title1;
    }

    public void setAlt_title1(String alt_title1) {
        this.alt_title1 = alt_title1;
    }

    public String getCall_no1() {
        return call_no1;
    }

    public void setCall_no1(String call_no1) {
        this.call_no1 = call_no1;
    }

    public String getEdition1() {
        return edition1;
    }

    public void setEdition1(String edition1) {
        this.edition1 = edition1;
    }

    public String getIsbn101() {
        return isbn101;
    }

    public void setIsbn101(String isbn101) {
        this.isbn101 = isbn101;
    }

    public String getIsbn131() {
        return isbn131;
    }

    public void setIsbn131(String isbn131) {
        this.isbn131 = isbn131;
    }

    public String getMain_entry1() {
        return main_entry1;
    }

    public void setMain_entry1(String main_entry1) {
        this.main_entry1 = main_entry1;
    }

    public String getPublication_place1() {
        return publication_place1;
    }

    public void setPublication_place1(String publication_place1) {
        this.publication_place1 = publication_place1;
    }

    public String getPublisher_name1() {
        return publisher_name1;
    }

    public void setPublisher_name1(String publisher_name1) {
        this.publisher_name1 = publisher_name1;
    }

    public String getPublishing_year1() {
        return publishing_year1;
    }

    public void setPublishing_year1(String publishing_year1) {
        this.publishing_year1 = publishing_year1;
    }

    public String getSer_note1() {
        return ser_note1;
    }

    public void setSer_note1(String ser_note1) {
        this.ser_note1 = ser_note1;
    }

    public String getStatement_responsibility1() {
        return statement_responsibility1;
    }

    public void setStatement_responsibility1(String statement_responsibility1) {
        this.statement_responsibility1 = statement_responsibility1;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    @Override
     public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
      request.setCharacterEncoding("UTF-8");
    }
    catch (UnsupportedEncodingException ex) {
    }
    }
    /**
     *
     * @return LCC_no
     */
    public String getLCC_no() {
        return LCC_no;
    }

    /**
     *
     * @param LCC_no
     */
    public void setLCC_no(String LCC_no) {
        this.LCC_no = LCC_no;
    }

    /**
     *
     * @return acceptance_year
     */
    public String getAcceptance_year() {
        return acceptance_year;
    }

    /**
     *
     * @param acceptance_year
     */
    public void setAcceptance_year(String acceptance_year) {
        this.acceptance_year = acceptance_year;
    }

    /**
     *
     * @return accession_no
     */
    public String getAccession_no() {
        return accession_no;
    }

    /**
     *
     * @param accession_no
     */
    public void setAccession_no(String accession_no) {
        this.accession_no = accession_no;
    }

    /**
     *
     * @return accesssion_type
     */
    public String getAccession_type() {
        return accession_type;
    }

    /**
     *
     * @param accession_type
     */
    public void setAccession_type(String accession_type) {
        this.accession_type = accession_type;
    }

    /**
     *
     * @return added_entry
     */
    public String getAdded_entry() {
        return added_entry;
    }

    /**
     *
     * @param added_entry
     */
    public void setAdded_entry(String added_entry) {
        this.added_entry = added_entry;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return alt_title
     */
    public String getAlt_title() {
        return alt_title;
    }

    /**
     *
     * @param alt_title
     */
    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    /**
     *
     * @return author_name
     */
    public String getAuthor_name() {
        return author_name;
    }

    /**
     *
     * @param author_name
     */
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    /**
     *
     * @return biblio_id
     */
    public int getBiblio_id() {
        return biblio_id;
    }

    /**
     *
     * @param biblio_id
     */
    public void setBiblio_id(int biblio_id) {
        this.biblio_id = biblio_id;
    }

    /**
     *
     * @return bind_type
     */
    public String getBind_type() {
        return bind_type;
    }

    /**
     *
     * @param bind_type
     */
    public void setBind_type(String bind_type) {
        this.bind_type = bind_type;
    }

    /**
     *
     * @return button
     */
    public String getButton() {
        return button;
    }

    /**
     *
     * @param button
     */
    public void setButton(String button) {
        this.button = button;
    }

    /**
     *
     * @return call_no
     */
    public String getCall_no() {
        return call_no;
    }

    /**
     *
     * @param call_no
     */
    public void setCall_no(String call_no) {
        this.call_no = call_no;
    }

    /**
     *
     * @return collation
     */
    public String getCollation() {
        return collation;
    }

    /**
     *
     * @param collation
     */
    public void setCollation(String collation) {
        this.collation = collation;
    }

    /**
     *
     * @return colour
     */
    public String getColour() {
        return colour;
    }

    /**
     *
     * @param colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     *
     * @param degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     *
     * @return document_type
     */
    public String getDocument_type() {
        return document_type;
    }

    /**
     *
     * @param document_type
     */
    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    /**
     *
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     *
     * @param edition
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return file_type
     */
    public String getFile_type() {
        return file_type;
    }

    /**
     *
     * @param file_type
     */
    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    /**
     *
     * @return frmr_frq
     */
    public String getFrmr_frq() {
        return frmr_frq;
    }

    /**
     *
     * @param frmr_frq
     */
    public void setFrmr_frq(String frmr_frq) {
        this.frmr_frq = frmr_frq;
    }

    /**
     *
     * @return frq_date
     */
    public String getFrq_date() {
        return frq_date;
    }

    /**
     *
     * @param frq_date
     */
    public void setFrq_date(String frq_date) {
        this.frq_date = frq_date;
    }

    /**
     *
     * @return guide_name
     */
    public String getGuide_name() {
        return guide_name;
    }

    /**
     *
     * @param guide_name
     */
    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    /**
     *
     * @return index_no
     */
    public String getIndex_no() {
        return index_no;
    }

    /**
     *
     * @param index_no
     */
    public void setIndex_no(String index_no) {
        this.index_no = index_no;
    }

    /**
     *
     * @return isbn10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     *
     * @param isbn10
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     *
     * @return isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     *
     * @param isbn13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     *
     * @return issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     *
     * @param issn
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     *
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return library_id
     */
    public String getLibrary_id() {
        return library_id;
    }

    /**
     *
     * @param library_id
     */
    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    /**
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return main_entry
     */
    public String getMain_entry() {
        return main_entry;
    }

    /**
     *
     * @param main_entry
     */
    public void setMain_entry(String main_entry) {
        this.main_entry = main_entry;
    }

    /**
     *
     * @return no_of_copies
     */
    public int getNo_of_copies() {
        return no_of_copies;
    }

    /**
     *
     * @param no_of_copies
     */
    public void setNo_of_copies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    /**
     *
     * @return no_of_pages
     */
    public String getNo_of_pages() {
        return no_of_pages;
    }

    /**
     *
     * @param no_of_pages
     */
    public void setNo_of_pages(String no_of_pages) {
        this.no_of_pages = no_of_pages;
    }

    /**
     *
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     *
     * @return physical_form
     */
    public String getPhysical_form() {
        return physical_form;
    }

    /**
     *
     * @param physical_form
     */
    public void setPhysical_form(String physical_form) {
        this.physical_form = physical_form;
    }

    /**
     *
     * @return physical_width
     */
    public String getPhysical_width() {
        return physical_width;
    }

    /**
     *
     * @param physical_width
     */
    public void setPhysical_width(String physical_width) {
        this.physical_width = physical_width;
    }

    /**
     *
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return production_year
     */
    public int getProduction_year() {
        return production_year;
    }

    /**
     *
     * @param production_year
     */
    public void setProduction_year(int production_year) {
        this.production_year = production_year;
    }

    /**
     *
     * @return publication_year
     */
    public String getPublication_place() {
        return publication_place;
    }

    /**
     *
     * @param publication_place
     */
    public void setPublication_place(String publication_place) {
        this.publication_place = publication_place;
    }

    /**
     *
     * @return publisher_name
     */
    public String getPublisher_name() {
        return publisher_name;
    }

    /**
     *
     * @param publisher_name
     */
    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    /**
     *
     * @return publishing_year
     */
    public String getPublishing_year() {
        return publishing_year;
    }

    /**
     *
     * @param publishing_year
     */
    public void setPublishing_year(String publishing_year) {
        this.publishing_year = publishing_year;
    }

    /**
     *
     * @return ser_note
     */
    public String getSer_note() {
        return ser_note;
    }

    /**
     *
     * @param ser_note
     */
    public void setSer_note(String ser_note) {
        this.ser_note = ser_note;
    }

    /**
     *
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     *
     * @param series
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     *
     * @return shelving_location
     */
    public String getShelving_location() {
        return shelving_location;
    }

    /**
     *
     * @param shelving_location
     */
    public void setShelving_location(String shelving_location) {
        this.shelving_location = shelving_location;
    }

    /**
     *
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return statement_responsibility
     */
    public String getStatement_responsibility() {
        return statement_responsibility;
    }

    /**
     *
     * @param statement_responsibility
     */
    public void setStatement_responsibility(String statement_responsibility) {
        this.statement_responsibility = statement_responsibility;
    }

    /**
     *
     * @return sub_library_id
     */
    public String getSub_library_id() {
        return sublibrary_id;
    }

    /**
     *
     * @param sub_library_id
     */
    public void setSub_library_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    /**
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return submitted_on
     */
    public String getSubmitted_on() {
        return submitted_on;
    }

    /**
     *
     * @param submitted_on
     */
    public void setSubmitted_on(String submitted_on) {
        this.submitted_on = submitted_on;
    }

    /**
     *
     * @return subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     *
     * @param subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     *
     * @return thesis_abstract
     */
    public String getThesis_abstract() {
        return thesis_abstract;
    }

    /**
     *
     * @param thesis_abstract
     */
    public void setThesis_abstract(String thesis_abstract) {
        this.thesis_abstract = thesis_abstract;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return type_of_disc
     */
    public String getType_of_disc() {
        return type_of_disc;
    }

    /**
     *
     * @param type_of_disc
     */
    public void setType_of_disc(String type_of_disc) {
        this.type_of_disc = type_of_disc;
    }

    /**
     *
     * @return university_faculty
     */
    public String getUniversity_faculty() {
        return university_faculty;
    }

    /**
     *
     * @param university_faculty
     */
    public void setUniversity_faculty(String university_faculty) {
        this.university_faculty = university_faculty;
    }

    /**
     *
     * @return volume_location
     */
    public String getVolume_location() {
        return volume_location;
    }

    /**
     *
     * @param volume_location
     */
    public void setVolume_location(String volume_location) {
        this.volume_location = volume_location;
    }

    /**
     *
     * @return date_acquired
     */
    public String getDate_acquired() {
        return date_acquired;
    }

    /**
     *
     * @param date_acquired
     */
    public void setDate_acquired(String date_acquired) {
        this.date_acquired = date_acquired;
    }

    /**
     *
     * @return parts_no
     */
    public int getParts_no() {
        return parts_no;
    }

    /**
     *
     * @param parts_no
     */
    public void setParts_no(int parts_no) {
        this.parts_no = parts_no;
    }

    /**
     *
     * @return record_no
     */
    public int getRecord_no() {
        return record_no;
    }

    /**
     *
     * @param record_no
     */
    public void setRecord_no(int record_no) {
        this.record_no = record_no;
    }

    /**
     *
     * @return sublibrary_id
     */
    public String getSublibrary_id() {
        return sublibrary_id;
    }

    /**
     *
     * @param sublibrary_id
     */
    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    /**
     *
     * @return volume_no
     */
    public String getVolume_no() {
        return volume_no;
    }

    /**
     *
     * @param volume_no
     */
    public void setVolume_no(String volume_no) {
        this.volume_no = volume_no;
    }

    /**
     *
     * @return book_type
     */
    public String getBook_type() {
        return book_type;
    }

    /**
     *
     * @param book_type
     */
    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    /**
     *
     * @return search_keyword
     */
    public String getSearch_keyword() {
        return search_keyword;
    }

    /**
     *
     * @param search_keyword
     */
    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    /**
     *
     * @return search_by
     */
    public String getSearch_by() {
        return search_by;
    }

    /**
     *
     * @param search_by
     */
    public void setSearch_by(String search_by) {
        this.search_by = search_by;
    }

    /**
     *
     * @return sort_by
     */
    public String getSort_by() {
        return sort_by;
    }

    /**
     *
     * @param sort_by
     */
    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    /**
     *
     * @return physical_desc
     */
    public String getPhysical_desc() {
        return physical_desc;
    }

    /**
     *
     * @param physical_desc
     */
    public void setPhysical_desc(String physical_desc) {
        this.physical_desc = physical_desc;
    }

    /**
     *
     * @return added_entry0
     */
    public String getAdded_entry0() {
        return added_entry0;
    }

    /**
     *
     * @param added_entry0
     */
    public void setAdded_entry0(String added_entry0) {
        this.added_entry0 = added_entry0;
    }

    /**
     *
     * @return added_entry1
     */
    public String getAdded_entry1() {
        return added_entry1;
    }

    /**
     *
     * @param added_entry1
     */
    public void setAdded_entry1(String added_entry1) {
        this.added_entry1 = added_entry1;
    }

    /**
     *
     * @return added_entry2
     */
    public String getAdded_entry2() {
        return added_entry2;
    }

    /**
     *
     * @param added_entry2
     */
    public void setAdded_entry2(String added_entry2) {
        this.added_entry2 = added_entry2;
    }
}
