package org.iitk.brihaspati.modules.utils;

/*
 * @(#) FileLockUnlock.java
 *
 *  Copyright (c) 2017 IITK.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: IITK
 *
 *
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.apache.commons.lang.math.DoubleRange;
import java.util.Random;
import org.apache.commons.lang.math.Range;


/**
 * class for getting lock and unlock a file
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>   
 */

//public class FileLocked extends RandomAccessFile

//method for accuring lock on a file for read & write operation.

public class FileLockUnlock
{

    private String fname;
    FileLockUnlock(String filename)
    {
        this.fname = filename;
    }
    FileLock fileLock = null;
    public boolean getlock() throws Exception
    {
        RandomAccessFile file = null;
        FileLock fileLock = null;
        int attempt=1;
        boolean gotlock = false;
        do{
            file = new RandomAccessFile(fname, "rw");
            FileChannel fileChannel = file.getChannel();
            fileLock = fileChannel.tryLock();
            if (fileLock != null)
            {
                try
                {
                    gotlock=true;
                }
                catch(Exception exception)
                {
                    ErrorDumpUtil.ErrorLog("--Exception in File Lock--"+exception);                   
                }
            }
            else
            {
               //waiting code
                Random randomgen = new Random();
                double attemptd = (double)attempt;
                double powerval = Math.pow(2.0,attemptd);
                int ranval = randomgen.nextInt(30*(int)powerval);

                Thread.sleep(ranval);
                if(attempt <= 10)
                    attempt++;
                if(attempt >=20) break;
            }

    }
    while(fileLock==null);
    //Thread.sleep(1000);
    return gotlock;
    }

    //method for releasing the lock on a file

    public void releaselock() throws Exception
    {
        if(fileLock!=null)
        {
            fileLock.release();
        }
    }

}
