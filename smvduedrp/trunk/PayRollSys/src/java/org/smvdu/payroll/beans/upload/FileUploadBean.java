/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.upload;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * @author Ilya Shaikovsky
 *
 */
public class FileUploadBean{

    private ArrayList<UploadFile> files = new ArrayList<UploadFile>();
    private int uploadsAvailable = 1;
    private boolean autoUpload = false;
    private boolean useFlash = false;
    public int getSize() {
        if (getFiles().size()>0){
            return getFiles().size();
        }else
        {
            return 0;
        }
    }

    public FileUploadBean() {
    }

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get(0).getData());
    }
//    public void listener(UploadEvent event) throws Exception{
//        UploadItem item = event.getUploadItem();
//        UploadFile file= new UploadFile();
//        file.setLength(item.getData().length);
//        file.setName(item.getFileName());
//        file.setData(item.getData());
//        System.err.println("File Size : "+item.getData().length);
//        files.add(file);
//        uploadsAvailable--;
//    }

    public String clearUploadData() {
        files.clear();
        setUploadsAvailable(1);
        return null;
    }

    public long getTimeStamp(){
        return System.currentTimeMillis();
    }

    public ArrayList<UploadFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<UploadFile> files) {
        this.files = files;
    }

    public int getUploadsAvailable() {
        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {
        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }

}
