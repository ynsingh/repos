/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

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
public class Main {



    private static int count=0;

private static String header = " *  Copyright (c) 2010 - 2011 SMVDU, Katra.\n"+
 "*  All Rights Reserved.\n"+
 "*"+
 "*  Redistribution and use in source and binary forms, with or \n"+
 "*  without modification, are permitted provided that the following \n"+
 "*  conditions are met: \n"+
 "*"+
 "*  Redistributions of source code must retain the above copyright \n"+
 "*  notice, this  list of conditions and the following disclaimer. \n"+
 "* \n"+
 "*  Redistribution in binary form must reproduce the above copyright\n"+
 "*  notice, this list of conditions and the following disclaimer in \n"+
 "*  the documentation and/or other materials provided with the \n"+
 "*  distribution. \n"+
 "* \n"+
 "* \n"+
 "*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED \n"+
 "*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES \n"+
 "*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE \n"+
 "*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE \n"+
 "*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR \n"+
 "*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT \n"+
 "*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR \n"+
 "*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,\n"+
 "*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE \n"+
 "*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, \n"+
 "*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. \n"+
 "* \n"+
 "* \n"+
 "*  Contributors: Members of ERP Team @ SMVDU, Katra\n"+
 "*";

    
private static String root = "f:\\edit\\core-pls\\src";


private void read(File f)
    {
    System.out.println("Reading file :"+f.getName());
    if(f.getName().contains("svn"))
    {
        return;
    }
    try
    {
        Scanner sc = new Scanner(f);
        
        String line = "";
        boolean dirty = false;
        while(sc.hasNextLine())
        {
            line +=sc.nextLine()+"\n";
            
        }
        if(line.contains("Algox"))
        {
            line = line.replace("Algox", header);
            dirty = true;
        }
        sc.close();
        System.err.println(line);
        if(dirty)
        {
            PrintWriter pw = new PrintWriter(f);
            pw.print(line);
            pw.close();
        }
        
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}


public static void main(String[] args)
    {
    Main ma  = new Main();
    ma.browse(root);
    System.out.println(count);
}
public void browse(String root)
    {

        System.out.println("Browsing folder "+root);
        File file = new File(root);
        File[] listFiles = file.listFiles();
        if(listFiles==null)
        {
            return;
        }
        for(File f : listFiles)
        {
            if(f.isDirectory())
            {
                browse(f.toString());
            }
            else
            {
                if(f.getName().contains("java"))
                    read(f);
            }
        }
    
    
}

}
