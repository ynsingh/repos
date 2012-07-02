/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.CirMemberDetail;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.awt.Image;
import java.io.InputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class MemberList implements Serializable{

    

  



    private java.awt.image.BufferedImage image1,image2;
    private String fname;
    private String mname;
    private String lname;
    private String address1;
    private String deptId;
    private String courseId;
    private String facultyId;
   // private String libraryId;
    private byte[] image;

    private String fname11;
    private String mname11;
    private String lname11;
    private String address11;
    private String deptId11;
    private String courseId11;
    private String facultyId11;
   // private String libraryId;
    private byte[] image11;

  public   MemberList()
     {
      
     }
  public   MemberList(String fname,String mname,String lname,String address1,byte[] image,String facultyId,String deptId,String courseId,String fname11,String mname11,String lname11,String address11,byte[] image11,String facultyId11,String deptId11,String courseId11)
     {
       this.fname=fname;
       this.mname=mname;
       this.lname=lname;
       this.address1=address1;
       this.deptId=deptId;
       this.facultyId=facultyId;
       this.courseId=courseId;
       setImage(image);
       this.fname11=fname11;
       this.mname11=mname11;
       this.lname11=lname11;
       this.address11=address11;
       this.deptId11=deptId11;
       this.facultyId11=facultyId11;
       this.courseId11=courseId11;
       setImage11(image11);
     }

   public   MemberList(String fname,String mname,String lname,String address1,byte[] image,String facultyId,String deptId,String courseId)
     {
       this.fname=fname;
       this.mname=mname;
       this.lname=lname;
       this.address1=address1;
       this.deptId=deptId;
       this.facultyId=facultyId;
       this.courseId=courseId;
       setImage(image);
      
     }

    public String getAddress11() {
        return address11;
    }

    public void setAddress11(String address11) {
        this.address11 = address11;
    }

    public String getCourseId11() {
        return courseId11;
    }

    public void setCourseId11(String courseId11) {
        this.courseId11 = courseId11;
    }

    public String getDeptId11() {
        return deptId11;
    }

    public void setDeptId11(String deptId11) {
        this.deptId11 = deptId11;
    }

    public String getFacultyId11() {
        return facultyId11;
    }

    public void setFacultyId11(String facultyId11) {
        this.facultyId11 = facultyId11;
    }

    public String getFname11() {
        return fname11;
    }

    public void setFname11(String fname11) {
        this.fname11 = fname11;
    }

    public byte[] getImage11() {
        return image11;
    }

    public void setImage11(byte[] image11) {
        this.image11 = image11;

         try{
            InputStream in = new ByteArrayInputStream(this.image11);
                this.image2 = ImageIO.read(in);
                System.out.println("File Output1:"+this.image2+" actual data1="+this.image11);

        }catch(Exception e){System.out.println("File input Exception:"+e);}
    }

    public BufferedImage getImage2() {
        return image2;
    }

    public void setImage2(BufferedImage image2) {
        this.image2 = image2;
    }

    public String getLname11() {
        return lname11;
    }

    public void setLname11(String lname11) {
        this.lname11 = lname11;
    }

    public String getMname11() {
        return mname11;
    }

    public void setMname11(String mname11) {
        this.mname11 = mname11;


    }

  
     public BufferedImage getImage1() {
        

        System.out.println(this.image1);
        return this.image1;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }



    public void setImage1(BufferedImage image1) {
        this.image1 = image1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

   

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
        
	try{
            InputStream in = new ByteArrayInputStream(this.image);
                this.image1 = ImageIO.read(in);
                System.out.println("File Output.......:"+this.image1+" actual data="+this.image);
       
        }catch(Exception e){System.out.println("File input Exception:"+e);}
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }



//    private java.awt.image.BufferedImage image1;
//    private String fname;
//    private String mname;
//    private String lname;
//    private String address1;
//    private String deptId;
//    private String courseId;
//    private String facultyId;
//   // private String libraryId;
//    private byte[] image;
//
//  public   MemberList()
//     {
//
//     }
//  public   MemberList(String fname,String mname,String lname,String address1,byte[] image,String facultyId,String deptId,String courseId)
//     {
//       this.fname=fname;
//       this.mname=mname;
//       this.lname=lname;
//       this.address1=address1;
//       this.deptId=deptId;
//       this.facultyId=facultyId;
//       this.courseId=courseId;
//       setImage(image);
//     }
//
//     public BufferedImage getImage1() {
//
//
//        System.out.println(this.image1);
//        return this.image1;
//    }
//
//    public String getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(String courseId) {
//        this.courseId = courseId;
//    }
//
//    public String getDeptId() {
//        return deptId;
//    }
//
//    public void setDeptId(String deptId) {
//        this.deptId = deptId;
//    }
//
//    public String getFacultyId() {
//        return facultyId;
//    }
//
//    public void setFacultyId(String facultyId) {
//        this.facultyId = facultyId;
//    }
//
//
//
//    public void setImage1(BufferedImage image1) {
//        this.image1 = image1;
//    }
//
//    public String getAddress1() {
//        return address1;
//    }
//
//    public void setAddress1(String address1) {
//        this.address1 = address1;
//    }
//
//
//
//    public String getFname() {
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//
//	try{
//            InputStream in = new ByteArrayInputStream(this.image);
//                this.image1 = ImageIO.read(in);
//                System.out.println("File Output:"+this.image1+" actual data="+this.image);
//
//        }catch(Exception e){System.out.println("File input Exception:"+e);}
//    }
//
//    public String getLname() {
//        return lname;
//    }
//
//    public void setLname(String lname) {
//        this.lname = lname;
//    }
//
//    public String getMname() {
//        return mname;
//    }
//
//    public void setMname(String mname) {
//        this.mname = mname;
//    }
//

}
