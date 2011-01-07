package com.myapp.struts.opac;
public class OpacDoc {
        protected String title;
        protected String author;
	protected String accessionno;
	protected String callno;
	protected String subject;
	protected String location;
	protected String noofcopy;
	protected String price;
	protected String isbn;
	protected String publisher;
        protected String pubplace;
	protected String subtitle;
	protected String category;
	protected String db_category;
	protected String pub_yr;
        protected String library_id;

	public String getAccessionno() { return accessionno;}
     public void setAccessionno(String accessionno) {this.accessionno = accessionno;}
        public String getAuthor() { return author;  }
     public void setAuthor(String author) {this.author = author; }
        public String getCallno() { return callno;  }
     public void setCallno(String callno) { this.callno = callno;}
        public String getCategory() {return category; }
     public void setCategory(String category) {this.category = category; }
        public String getDb_category() { return db_category;  }
     public void setDb_category(String db_category) {this.db_category = db_category; }
        public String getIsbn() { return isbn;  }
     public void setIsbn(String isbn) { this.isbn = isbn; }
        public String getLocation() {return location;}
     public void setLocation(String location) {this.location = location;}
        public String getNoofcopy() {return noofcopy; }
     public void setNoofcopy(String noofcopy) {this.noofcopy = noofcopy;}
        public String getPrice() { return price;  }
     public void setPrice(String price) {this.price = price; }
        public String getPub_yr() {return pub_yr;}
     public void setPub_yr(String pub_yr) {this.pub_yr = pub_yr; }
        public String getPublisher() {return publisher;}
     public void setPublisher(String publisher) {this.publisher = publisher;}
        public String getPubplace() {return pubplace; }
     public void setPubplace(String pubplace) {this.pubplace = pubplace;}
        public String getSubject() {return subject;}
     public void setSubject(String subject) {this.subject = subject;}
        public String getSubtitle() {return subtitle;}
     public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
        public String getTitle() {return title;}
     public void setTitle(String title) {this.title = title;}
     public String getLibrary_id() { return library_id;}
     public void setLibrary_id(String library_id) {this.library_id = library_id.toUpperCase();}

			} //End of Class