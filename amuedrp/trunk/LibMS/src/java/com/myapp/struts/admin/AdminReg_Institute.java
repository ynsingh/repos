package com.myapp.struts.admin;
import com.myapp.struts.hbm.*;

public class AdminReg_Institute
{
      private AdminRegistration adminRegistration;
    private Library library;

    /**
     * @return the adminReg
     */
    public AdminRegistration getAdminRegistration() {
        return adminRegistration;
    }

    /**
     * @param adminReg the adminReg to set
     */
    public void setAdminRegistration(AdminRegistration adminReg) {
        this.adminRegistration = adminReg;
    }

    /**
     * @return the institute
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * @param institute the institute to set
     */
    public void setLibrary(Library library) {
        this.library = library;
    }
       

}